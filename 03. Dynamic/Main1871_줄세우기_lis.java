import java.util.Arrays;
import java.util.Scanner;

public class Main1871_�ټ����_lis {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[] arr = new int[n+1];

		for (int i = 1; i <= n; i++) {
			arr[i] = s.nextInt();
		}
		s.close();
		int dynamic[] = new int[n+1]; // len[x] : x��° ���� ������ ���ҷ� ������ lis�� ���� 


		for (int i = 1; i <= n; i++) {// ���� ���������� ������ ���
			for (int j = 0 ; j < i; j++) { // �� ó�� ���Һ��� ������ ��� �� ���� ������ ���Ͽ� ������ �� �ִ��� üũ�ϰ� 
											// ������ �� �ִٸ� �����̴� ���� �ڽ��� ���� ������̺��� ������ ��츸 update 
				// len[j] + 1 > len[i] ���ؾ��ϴ� ����
				// len[i] : jó���������� �ִ����,  len[j]+1 : j�ڿ� i�� ���ٿ����������� ����
				// ������ �Ǿ� �ִ� �����Ͱ� �ƴϹǷ� jó���������� �ִ���̰� j�������ִ����+1���� �� Ŭ�� �ִ�.
				// �ᱹ i���� ����� �ִ� ��ȣ�� �� �ִ���̰��� �̿��ϰ� �� ==> �ε���Ʈ�� Ȱ������Ʈ
				if (arr[j] < arr[i] && dynamic[j] + 1 > dynamic[i]) {
						dynamic[i] = dynamic[j] + 1;
				}
			}
		}
		Arrays.sort(dynamic);//�������� �����Ͽ� ���� ū���� ������ ������.(max�� : �������������� ����)
		System.out.println(n - dynamic[n]); // �̵��� ������ ���̹Ƿ� ��ü�ο������� �������������� ����(�̵����� �ʴ� ������ �ִ����)�� ����.

	}

}
