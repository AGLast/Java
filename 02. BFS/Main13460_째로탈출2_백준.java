import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main13460_°��Ż��2_���� {
	
	static Marble start;
	static int r, c, resCnt, hole[], map[][], dir[][];
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		jjaero();
		
		print();
	}

	private static void input() throws IOException {
		
		dir = new int[][]{	{-1, 0}, {1, 0}, {0, -1}, {0, 1}	};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];	// 1: ��, 0: ��� // ���� ��ǥ �� ���� �ʱ� ��ǥ ����
		
		char item, line[];
		int redR = 0, redC = 0, blueR = 0, blueC = 0;
		for (int i = 0; i < r; i++) {
			line = br.readLine().trim().toCharArray();
			for (int j = 0; j < c; j++) {
				item = line[j];
				switch (item) {
				case '#':
					map[i][j] = 1; break;
				case 'O':
					hole = new int[]{i, j}; break;
				case 'R':
					redR = i; redC = j; break;
				case 'B':
					blueR = i; blueC = j; break;
				}
			}
		}
		
		resCnt = -1;	// Red�� ���ۿ� ���� ��츦 �����ϰ�� ��� -1�� ����ϹǷ� �ʱⰪ -1�� ����
		start = new Marble(redR, redC, blueR, blueC, 0);
		
	}

	private static void jjaero() {
		
		Queue<Marble> que = new LinkedList<>();
		que.offer(start);
		
		Marble from;
		int cnt, cha;
		int fromRedR, fromRedC, fromBlueR, fromBlueC;
		int toRedR, toRedC, toBlueR, toBlueC;
		
		while (!que.isEmpty()) {
			
			from = que.poll();
			fromRedR = from.redR; fromRedC = from.redC;
			fromBlueR = from.blueR; fromBlueC = from.blueC;
			cnt = from.cnt;
			
			if (cnt > 10) return;	// cnt�� 10���� ũ�� resCnt ���ž��ϰ� BFS ���� -> resCnt = -1�� ����
			if (fromRedR == hole[0] && fromRedC == hole[1])	// Red�� �����̸� �����̹Ƿ� resCnt ����
				{resCnt = cnt; return;}
			
			for (int i = 0; i < 4; i++) {	// 4 ���� Ž�� (��>��>��>��)
				
				// �� �Ǵ� ������ ���� ������ to��ǥ ����
				toRedR = fromRedR + dir[i][0]; toRedC = fromRedC + dir[i][1];
				toBlueR = fromBlueR + dir[i][0]; toBlueC = fromBlueC + dir[i][1];
				
				while (map[toRedR][toRedC] != 1 && !(toRedR == hole[0] && toRedC == hole[1]))
					{toRedR += dir[i][0]; toRedC += dir[i][1];}
				
				while (map[toBlueR][toBlueC] != 1 && !(toBlueR == hole[0] && toBlueC == hole[1]))
					{toBlueR += dir[i][0]; toBlueC += dir[i][1];}
				
				// Blue�� �����̸� que�� offer���� �ʱ� ���� continue
				// 4 ���� �� Blue�� ������ �ƴ� ��츸 BFS ��� ����
				if (toBlueR == hole[0] && toBlueC == hole[1]) continue;
				
				// Blue�� ������ ���� ������ continue�߱� ������
				// Red�� �����̰� Blue�� ���� ���� ���� or ���� ��� ���� ���� �ִ� ����
				// Red�� ������ ���� �� ��ǥ �״�� que�� offer�ϱ� ���� �� ���� ��츸 �� ���� ��� ��ĭ �ڷ� back
				if (!(toRedR == hole[0] && toRedC == hole[1]))
					{toRedR -= dir[i][0]; toRedC -= dir[i][1];}
				
				toBlueR -= dir[i][0]; toBlueC -= dir[i][1];
				
				// 2�� ������ ��ġ�� ��츦 ó���ϴ� �κ� (Red�� Blue�� ��ǥ�� ���� ���)
				// �̵� ����(i)�� �� ������ from��ǥ�� ����(cha)�� �̿��Ͽ� ��� ������ �ڵ��� ������ ��ĭ �ڷ� back
				if (toRedR == toBlueR && toRedC == toBlueC) {
					
					cha = (fromRedR-fromBlueR) + (fromRedC-fromBlueC);
					
					if (i == 0 || i == 2) {
						
						if (cha > 0) {toRedR -= dir[i][0]; toRedC -= dir[i][1];}
						else {toBlueR -= dir[i][0]; toBlueC -= dir[i][1];}
						
					} else {
						
						if (cha > 0) {toBlueR -= dir[i][0]; toBlueC -= dir[i][1];}
						else {toRedR -= dir[i][0]; toRedC -= dir[i][1];}
						
					}
				}
				
				// �� ���� ��� from��ǥ���� to��ǥ ���� ���� ���� ���̻� ��ȭ�� �����Ƿ� continue
				if (fromRedR == toRedR && fromBlueR == toBlueR &&
					fromRedC == toRedC && fromBlueC == toBlueC) continue;
				
				// �� �̵��� ������ �ְų� Red�� ���ۿ� �� ��츸 offer
				que.offer(new Marble(toRedR, toRedC, toBlueR, toBlueC, cnt + 1));
				
			}
		}
	}
	
	private static void print() {
		System.out.println(resCnt);
	}
}

class Marble {
	int redR;
	int redC;
	int blueR;
	int blueC;
	int cnt;
	public Marble(int redR, int redC, int blueR, int blueC, int cnt) {
		this.redR = redR;
		this.redC = redC;
		this.blueR = blueR;
		this.blueC = blueC;
		this.cnt = cnt;
	}
	public String toString() {
		return "Red[" + redR + ", " + redC + "], Blue[" + blueR + ", " + blueC + "], " + cnt;
	}
}