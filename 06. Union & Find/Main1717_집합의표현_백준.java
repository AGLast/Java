import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* �Է� ������
7 8
0 1 3
1 1 7
0 7 6
1 7 1
0 3 7
0 4 2
0 1 1
1 1 1
 */

public class Main1717_������ǥ��_���� {	// 0 : Union, 1 : Find
	
	static int n, m, parents[], inputs[][];
	static StringBuilder result;
	
    public static void main(String[] args) throws Exception {
    	
    	input();
		
		unionFind();
		
		print();
    }
    
    private static void input() throws IOException {
		
    	result = new StringBuilder();
    	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parents = new int[n+1];		// �ڽ��� �θ� ��带 ����ϴ� �迭
		Arrays.fill(parents, -1);
		
		inputs = new int[m][3];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < 3; j++) {
				inputs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
	}
    
    private static void unionFind() {
		
    	int direction, a, b;
    	
    	for (int i = 0; i < m; i++) {
    		
    		direction = inputs[i][0];
    		a = inputs[i][1];
    		b = inputs[i][2];
    		
    		if (direction == 0) union(a, b);
    			
    		else
    			if (findParent(a) == findParent(b))
    				result.append("YES\n");
    			else result.append("NO\n");
    			    		
    	}
    	
	}
    
    private static int findParent(int index) { // �Է� �ε����� ���� Ʈ���� Root ����
    	
    	if (parents[index] == -1) return index;
    	
    	// return findParent(parents[index]);	// �������� ã�� �ε��� (root)�� ����� ���� �θ�� ����
    	return parents[index] = findParent(parents[index]);
    }
    
    private static void union(int a, int b) {
		
    	int rootA = findParent(a);
    	int rootB = findParent(b);
    	
    	if (rootA != rootB) parents[rootB] = rootA;
    	
	}
    
    private static void print() {
    	
    	System.out.println(result.toString());
    	
    }
}