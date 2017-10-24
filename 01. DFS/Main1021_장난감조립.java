import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1021_�峭������ {
	
	static int n, m;	// ��ǰ ���� ��, ��ǰ ���� ������
	static int[][] parts;		// ��ǰ�� ���ۿ� �ʿ��� ��ǰ ������ ����
	static int[] cntBasic;		// �ϼ�ǰ ���ۿ� �ʿ��� �⺻ ��ǰ ������ ���� (���� ��� �迭)
	static boolean[] ifBasic;	// �⺻ ��ǰ : true, �߰���ǰ or �ϼ�ǰ : false
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		parts = new int[n][n]; 
		cntBasic = new int[n];
		ifBasic = new boolean[n];
		
		StringTokenizer st;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			parts	[Integer.parseInt(st.nextToken())-1]
					[Integer.parseInt(st.nextToken())-1]
				=	 Integer.parseInt(st.nextToken());	// i�� : ��ǰ ��ȣ, j�� : ���� ��ǰ ��ȣ, �� : ����
		}
		
		int rowSum;
		for (int i = 0; i < n; i++) {		// parts�� �� �࿡�� ��� ���� ���� 0�̸� �⺻ ��ǰ
			rowSum = 0;
			for (int j = 0; j < n; j++) rowSum += parts[i][j];
			if (rowSum == 0) ifBasic[i] = true; 
		}
		
//		for (int[] line : parts)
//			System.out.println(Arrays.toString(line));
		
		fun(n);	// �ϼ�ǰ ��ȣ �Է�
		
		for (int i = 0; i < cntBasic.length; i++) {		// �⺻ ��ǰ�� �� ���� ���
			if (ifBasic[i]) System.out.printf("%d %d\n", i+1, cntBasic[i]);
		}
		
	}
	
	public static void fun(int partNumber) {	// �Ű������� ��ǰ ��ȣ ����
		
		if (ifBasic[partNumber-1]) {
			cntBasic[partNumber-1]++;	// �ش� ��ǰ�� �⺻ ��ǰ�̸� �� ���� ����
			return;
		}
		
		int[] part = parts[partNumber-1];	 // �ش� ��ǰ �ʿ��� ��ǰ ������ ���� ����
		for (int i = 0; i < part.length; i++) {
			if (part[i] > 0) {	// �ʿ� ��ǰ ���� 1�� �̻��� ���
				for (int j = 0; j < part[i]; j++) { // �ʿ� ��ǰ ��ȣ�� �Ű������� �� ������ŭ �ݺ� ȣ��
					fun(i+1);
				}
			}
		}
	}
}