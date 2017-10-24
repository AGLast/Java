import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1841_������ {

	static final int n = 6; // ����(���� ��)
	static final int m = 15; // 6���� �� 2���� ġ��� �� ����� �� 6C2
	static int win[], lose[], draw[];
	static int tryWin[], tryLose[], tryDraw[];
	static int player1[], player2[];
	static boolean feasibility;

	// cnt�� �ش��ϴ� ����� ������ ���� 3������ ���� �õ��غ�
	// �� ��찡 �����ϴٸ� �ش� ��쿡�� ���� ���� ���ȣ�� 
	static void recur(int cnt) {
		if (cnt == m) {// ������ ������ �� �����߾��ٸ� 
			feasibility = true;
			return;
		}
		
		int p1 = player1[cnt];
		int p2 = player2[cnt];
		
		
		///////////////////////////////////////////////////////////
		
		
		tryWin[p1]++;// player1�� �̱�� ó��
		tryLose[p2]++;//player2�� ���� ó��
		if (tryWin[p1] <= win[p1] && tryLose[p2] <= lose[p2]){ // ������ ó���� ������� ���ڰ� ������ ��������� �۰ų� ���ٸ� 
														// ���� ��������� ��� ���ȣ�� �Ͽ� �õ��غ�.
			recur(cnt + 1);
			if(feasibility){ // ������ ������ try�� ��� �����ߴٸ� �����ǰ�� ������ ����̹Ƿ� ��͸���.
				return;
			}
		}
		tryWin[p1]--; // ������ ó���� ��� �ǵ�������
		tryLose[p2]--;// ������ ó���� ��� �ǵ�������
		
		
		///////////////////////////////////////////////////////////
		
		
		tryDraw[p1]++;// player1�� ���� ó��
		tryDraw[p2]++;//player2�� ���� ó��
		if (tryDraw[p1] <= draw[p1] && tryDraw[p2] <= draw[p2]){
			recur(cnt + 1);
			if(feasibility){
				return;
			}
		}
		tryDraw[p1]--;// ������ ó���� ��� �ǵ�������
		tryDraw[p2]--;// ������ ó���� ��� �ǵ�������
		
		
		///////////////////////////////////////////////////////////
		
		
		tryLose[p1]++;//player1�� ���� ó��
		tryWin[p2]++;//player2�� �̱�� ó��
		if (tryLose[p1] <= lose[p1] && tryWin[p2] <= win[p2]){
			recur(cnt + 1);
			if(feasibility){
				return;
			}
		}
		tryLose[p1]--;// ������ ó���� ��� �ǵ�������
		tryWin[p2]--;// ������ ó���� ��� �ǵ�������
	}

	static void process() {
		feasibility = false;
		Arrays.fill(tryWin, 0);
		Arrays.fill(tryLose, 0);
		Arrays.fill(tryDraw, 0);
		recur(0);
	}

	public static void main(String[] args) throws IOException {
		win = new int[n];	// �� ���� �� �� �� �迭
		lose = new int[n];
		draw = new int[n];
		
		tryWin = new int[n];	// ��⸦ �����ذ��鼭 �¹��� �����ϴ� �迭
		tryLose = new int[n];	// ���� win lose draw�� �����س����鼭 ��� ����
		tryDraw = new int[n];
		
		player1 = new int[m];	// ��� ����� ���� ����
		player2 = new int[m];

		// ��� ���ո����
		// 0-1,2,3,4,5   1-2,3,4,5  2-3,4,5   3-4,5   4-5
		for (int i = 0, cnt = 0; i < n; i++){
			for (int j = i + 1; j < n; j++) {
				player1[cnt] = i;
				player2[cnt] = j;
				cnt++;
			}
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		L:for (int c = 0; c < 4; c++) {
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < n; i++) {
				win[i] = Integer.parseInt(st.nextToken());
				draw[i] = Integer.parseInt(st.nextToken());
				lose[i] = Integer.parseInt(st.nextToken());
				
				if (win[i] + lose[i] + draw[i] != n - 1){ // �̱�� ���� ��� ����� ���� n-1 ��, 5��Ⱑ �ƴϸ� ������ �Ұ����� �����
					System.out.print(0+" ");
					continue L; //���� ���̽��� �ǳʶ�
				}
			}
			process();
			if (feasibility) {
				System.out.print(1+" ");
			} else {
				System.out.print(0+" ");

			}
		}

	}

}
