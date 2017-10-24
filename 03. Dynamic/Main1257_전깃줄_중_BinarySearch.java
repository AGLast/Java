import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main1257_������_��_BinarySearch {
	
	static int n, size, lineB[], path[], dynamic[], dynamicIndex[];
	static Integer lines[][];
	
	// �Ź� lineB�� �ε����� ����
	// ��� ���� : BinarySearch ���� -1���� ���� ��, 
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		electricLines();
		
		print();
		
	}

	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		
		path = new int[n];
		lineB = new int[n];
		dynamic = new int[n];
		dynamicIndex = new int[n];
		lines = new Integer[n][2];
		
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
		
		for (int i = 0; i < n; i++) lineB[i] = lines[i][1];
		
	}
	
	private static void electricLines() {
		
		int temp, insert;
		for (int i = 0; i < n; i++) {
			temp = Arrays.binarySearch(dynamic, 0, size, lineB[i]);
			insert = -1 * temp - 1;
			dynamic[insert] = lineB[i];
			dynamicIndex[insert] = i;	// ��� ������ ���� ����
			if (insert == size) size++;
			if (temp < -1) path[i] = dynamicIndex[insert-1];	// ���� �ְ� �� ���� ���� ��ġ ���
		}
		
	}
	
	private static void print() {
		
		System.out.println(n-size);
		int id = 0;
		int end = dynamic[size-1];
		for (int i = n - 1; i >= 0; i--) {
			if (lineB[i] == end) {
				id = i;
				break;
			}
		}
		int cuts[] = new int[n-size];
		int retain[] = new int[size];
		for (int i = size-1; i >= 0; i--) {
			retain[i] = lineB[id];
			id = path[id];
		}
		id = size-1;
		int cutsId = n-size-1;
		for (int i = n - 1; i >= 0; i--) {
			if (id >= 0 && lineB[i] == retain[id]) {
				id--;
			} else {
				cuts[cutsId--] = lines[i][0];
			}
		}
		StringBuilder res = new StringBuilder();
		for (int cut : cuts)
			res.append(cut).append("\n");
		System.out.println(res.toString());
	}
}