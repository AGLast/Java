import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1894_��ܿ�����2 {
	
	static int n, dynamic[];
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		step();
		
		print();
		
	}

	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		dynamic = new int[n + 1];
		dynamic[0] = 1;
		
	}
	
	private static void step() {
		
		int temp;
		
		for (int i = 1; i <= n; i++) {
			
			temp = 0;
			temp += (i - 1 >= 0)? dynamic[i - 1]:0;
			temp += (i - 2 >= 0)? dynamic[i - 2]:0;
			
			dynamic[i] = temp;
		}
	}
	
	private static void print() {
		
		System.out.println(dynamic[n]);
		
	}
}
