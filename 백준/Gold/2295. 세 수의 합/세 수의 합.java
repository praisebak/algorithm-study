import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {

		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int[] numbers = new int[N];
		while (N-- > 0) {
			numbers[N] = Integer.parseInt(bufferedReader.readLine());
		}


		int[] twoValuesSum = new int[(numbers.length * (numbers.length + 1)) / 2];
		int count = 0;
		for (int i = 0; i < numbers.length; i++) {
			for (int j = i; j < numbers.length; j++) {
				twoValuesSum[count++] = numbers[i] + numbers[j];
			}
		}
		Arrays.sort(twoValuesSum);

		int answer = -1;
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < numbers.length; j++) {
				if (binarySearch(twoValuesSum, numbers[i] - numbers[j])) {
					answer = Math.max(answer, numbers[i]);
				}
			}
		}
		System.out.println(answer);
	}

	private static boolean binarySearch(int[] arr, int value) {
		int l = 0;
		int r = arr.length - 1;
		while (l <= r) {
			int m = (l + r) / 2;
			if (arr[m] > value) {
				r = m - 1;
			} else if (arr[m] < value) {
				l = m + 1;
			} else{
				return true;
			}
		}
		return false;
	}
	
}