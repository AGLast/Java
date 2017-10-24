import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2101_���Ӻκ��ִ�� {
	
	static int n;
	static double dynamic[];
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		max();
		
		print();
	}

	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		
		dynamic = new double[n+1];
		
		for (int i = 1; i < n+1; i++) {
			dynamic[i] = Double.parseDouble(br.readLine().trim());
		}
		
	}
	
	private static void max() {
		
		double prev, pres, multiple;
		
		for (int i = 1; i < n+1; i++) {
			
			pres = dynamic[i];
			prev = dynamic[i-1];
			multiple = pres * prev;
			
			if (multiple > pres) dynamic[i] = multiple;
			
		}
		
	}
	
	
	private static void print() {
		
		Arrays.sort(dynamic);
		System.out.printf("%.3f", dynamic[n]);
		
	}
}