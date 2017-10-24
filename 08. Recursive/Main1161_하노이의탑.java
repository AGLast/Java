import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1161_�ϳ�����ž {
	
	static StringBuilder builder = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine().trim());
    	fun(n, 1, 2, 3);
    	System.out.println(builder.toString());
    }
	
	public static void fun(int n, int from, int temp, int to) {
		if (n == 0) return;
		
		// n-1���� temp��
		fun(n-1, from, to, temp);
		
		// n�� to��
		builder.append(n);
		builder.append(" : ");
		builder.append(from);
		builder.append(" -> ");
		builder.append(to);
		builder.append("\n");
		
		// n-1�� to��
		fun(n-1, temp, from, to);
		
	}
}