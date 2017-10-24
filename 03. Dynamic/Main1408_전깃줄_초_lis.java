import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main1408_������_��_lis {
	
	static int n, dynamic[];
	static Integer lines[][];
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		electricLines();
		
		print();
	}

	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		
		lines = new Integer[n][2];
		dynamic = new int[n];
		Arrays.fill(dynamic, 1);
		
		StringTokenizer st;
		for (int i = 0; i < n; i++ ) {
			st = new StringTokenizer(br.readLine().trim());
			lines[i] = new Integer[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		}
		
		Arrays.sort(lines, new Comparator<Integer[]>() {
			public int compare(Integer[] o1, Integer[] o2) {
				return o1[0]-o2[0];
			}
		});
		
	}
	
	private static void electricLines() {
		
		for (int i = 1; i < n; i++)
			for (int j = 0; j < i; j++)
				
				if (lines[i][1] > lines[j][1] &&
						dynamic[j] + 1 > dynamic[i]) dynamic[i] = dynamic[j] + 1;
		
	}
	
	private static void print() {
		
		Arrays.sort(dynamic);
		System.out.println(n-dynamic[n-1]);
		
	}
}