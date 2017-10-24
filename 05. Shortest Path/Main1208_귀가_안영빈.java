import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

/* �Է� ������
5 
A d 6 
B d 3 
C e 9 
d Z 8 
e Z 3
*/

/* ���� ���
���� : a~z, A~Y
�갣 : Z
 - �갣�� ������ �빮�� ���忡 �� �Ѹ�����
 - �� ���峢�� ���� ������ �ȵ� ��쵵 �ְ�, �� ���� ������ ���� 2�� �̻��� ��쵵 ���� (���⼭ Dijstra�� PriorityQueue ��� ����)
 - ��� �� �� �̻��� ���忡�� �갣���� ���� ���� ���� (�갣���� ���� ���ϴ� ���� ����)
 - ���� �Ϲ������� �ƴϰ�, ��� ���� �ӵ��� ����
��� : ���� ���� �갣�� �����ϴ� �Ұ� ���� �ִ� ���� ��ȣ, �� �Ұ� �ȴ� �Ÿ�(�ð�)
*/

public class Main1208_�Ͱ�_�ȿ��� {
	
	static char leastCow;	// ���� ���� ������ ��
	static int leastTime;	// ���� ���� ������ �Ұ� �ɸ� �ð�
	static TreeSet<Character> cows, farms;	// cows: ���� �ִ� �빮�� ����, farms: ���� �ִ� ��� ����
	static TreeMap<Character, Road> roads;	// ���� ����Ʈ Map<��߸����ȣ, ��(���������ȣ, �ҿ�ð�)>
	static final int MAX_TIME = 99999;		// �ִ� �ҿ� �ð� ���� : 1000 * 52 (���� �ִ� ���� * �� ���� �ִ� ����)
	
	public static void main(String[] args) throws IOException {
		
		input();	// cows, farms, roads �غ�
					// cows : �Ұ� �갣���� ���� �ּҽð��� ���ϱ� ���ؼ�, �Ұ� �ִ� ���常 ���� �۾� �ʿ�
					// farms : �Ұ� ���� ������ �������� ���̱� ������, visited�� distance �ڷ� ������ ���� �ʿ�
					// roads : �� ���帶�� ����� ����� �ҿ�ð��� �����ϴ� ���� ����Ʈ
		
		goHuts();	// ���� �ִ� �빮�� ����(��, �Ұ� �ִ� ����)���� �갣 ���� �ð� ��� �� �ּҰ� ����
		
		print();
		
	}
	
	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int p = Integer.parseInt(st.nextToken());
		
		int len;
		char a, b;
		leastTime = MAX_TIME;
		cows = new TreeSet<>();		// cows, farms ������ ����Ʈ�̹Ƿ�
		farms = new TreeSet<>();	// �ߺ� ������ ���ϱ� ����, ���ĺ� ���� ������ ����, TreeSet ���
		roads = new TreeMap<>();	// ���� �ε����� char Ÿ������ �����ؾ� �ϹǷ� Map<Character, Road> ���
		for (int i = 0; i < p; i++) {
			st = new StringTokenizer(br.readLine());
			a = st.nextToken().charAt(0);
			b = st.nextToken().charAt(0);
			len = Integer.parseInt(st.nextToken());
			
			// �濡 ������ �����Ƿ� ����� ��� ���
			input(a, b, len);
			input(b, a, len);
			
			farms.add(a);
			farms.add(b);
			if (Character.isUpperCase(a) && a != 'Z') cows.add(a);
			if (Character.isUpperCase(b) && b != 'Z') cows.add(b);
		}
	}
	
	private static void input(char a, char b, int len) {
		Road road = new Road(b, len);
		if (!roads.containsKey(a)) roads.put(a, road);
		else roads.get(a).add(road);
	}
	
	private static void goHuts() {
		for (char cow : cows) goHut(cow);	// ���� �ִ� �빮�� ���帶�� �갣���� ���� �ð� ���
	}
	
	private static void goHut(char start) {
		
		PriorityQueue<Cow> que = new PriorityQueue<>();
		HashMap<Character, Boolean> visit = new HashMap<>();
		HashMap<Character, Integer> minTimes = new HashMap<>();
		
		for (char farm : farms) visit.put(farm, false);			// ��� ���� �湮 false ó��
		for (char farm : farms) minTimes.put(farm, MAX_TIME);	// ��� ���� �ʱⰪ ����
		
		minTimes.put(start, 0);		// ���� ���� �ҿ�ð� 0 ����
		que.offer(new Cow(start, minTimes.get(start)));		// queue�� ��� ����
		
		Cow cow;
		Road road;
		char fromHut, toHut; 				// fromHut : ���� ���� ��ȣ, toHut : ��ǥ ���� ��ȣ
		int startFrom_Time, fromTo_Time;	// startFrom_Time : "���� ���� -> ���� ����" �ּ� �ҿ� �ð�
		while (!que.isEmpty()) {			// fromTo_Time : "���� ���� -> ��ǥ ����" �ּ� �ҿ� �ð�
			
			cow = que.poll();
			fromHut = cow.hut;
			startFrom_Time = cow.time;
			if (visit.get(fromHut)) continue;
			if (fromHut == 'Z') break;
			visit.put(fromHut, true);
			
			road = roads.get(fromHut);		// ���� ���忡�� �� �� �ִ� ��ǥ ������ ��� ���� ����Ʈ
			
			while (road !=null) {
				toHut = road.hut;
				fromTo_Time = road.time;
				if (!visit.get(toHut) &&
					startFrom_Time + fromTo_Time < minTimes.get(toHut)) {
					minTimes.put(toHut, startFrom_Time + fromTo_Time);
					que.offer(new Cow(toHut, minTimes.get(toHut)));
				}
				road = road.next;	// ���� ��ǥ ���� ����
			}
		}
		if (minTimes.get('Z') < leastTime) {	// �ش� ���� ������ �갣 ���� �ð� ����� �������Ƿ�
			leastCow = start;					// �ּ� ���� �ð��� �� ���� ���� ����
			leastTime = minTimes.get('Z');
		}
	}
	
	private static void print() {
		System.out.println(leastCow + " " + leastTime);
	}
}

//class Cow implements Comparable<Cow> {
//	char hut;
//	int time;
//	public Cow(char hut, int time) {
//		this.hut = hut;
//		this.time = time;
//	}
//	public String toString() {
//		return "[���� : " + hut + ", �ð�=" + time + "]";
//	}
//	public int compareTo(Cow other) {
//		return this.time-other.time;
//	}
//}
//
//class Road {
//	char hut;
//	int time;
//	Road next;
//	public Road(char hut, int time) {
//		this.hut = hut;
//		this.time = time;
//	}
//	public String toString() {
//		return "[hut=" + hut + ", time=" + time + ", next=" + next + "]";
//	}
//	public void add(Road road) {
//		road.next = this.next;
//		this.next = road;
//	}
//}