import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1889_N��2 {

	static int count, caseCount, colSpot[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		count = Integer.parseInt(br.readLine());
		
		colSpot = new int[count+1];
		
		setQueens(1);
		
		System.out.println(caseCount);
	}
	
	public static void setQueens(int rowNo) {
 
		if (rowNo > count) {
			caseCount++;
			return;
		}
		
		for(int j = 1; j <= count; j++) {
			colSpot[rowNo] = j; 
			if (checking(rowNo)) setQueens(rowNo+1);
		}
	}
	
	public static boolean checking(int rowNo) {
		
		for (int k = 1; k < rowNo; k++)		
			if (colSpot[rowNo] == colSpot[k] ||
				rowNo - k == Math.abs(colSpot[rowNo] - colSpot[k]))
				return false;
		
		return true;
	}
}
