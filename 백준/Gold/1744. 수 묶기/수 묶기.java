import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> plus = new ArrayList<>();
        List<Integer> minus = new ArrayList<>();
        int zeroCount = 0;
        int oneCount = 0;
        long sum = 0;

        // 1. 입력 분류
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 1) {
                plus.add(num);
            } else if (num == 1) {
                oneCount++;
            } else if (num == 0) {
                zeroCount++;
            } else { // num < 0
                minus.add(num);
            }
        }

        // 2. 양수 처리
        Collections.sort(plus, Collections.reverseOrder()); // 내림차순 정렬
        for (int i = 0; i < plus.size(); i += 2) {
            if (i + 1 < plus.size()) { // 묶을 다음 숫자가 있으면
                sum += (long) plus.get(i) * plus.get(i + 1);
            } else { // 홀수 개라서 마지막에 하나 남으면
                sum += plus.get(i);
            }
        }

        // 3. 음수 처리
        Collections.sort(minus); // 오름차순 정렬
        for (int i = 0; i < minus.size(); i += 2) {
            if (i + 1 < minus.size()) { // 묶을 다음 숫자가 있으면
                sum += (long) minus.get(i) * minus.get(i + 1);
            } else { // 홀수 개라서 마지막에 하나 남으면
                if (zeroCount == 0) { // 0이 없으면 그냥 더해야 함
                    sum += minus.get(i);
                }
                // 0이 있으면 남은 음수는 0과 곱해져 사라지므로 아무것도 안 함
            }
        }

        // 4. 1 처리
        sum += oneCount;

        System.out.println(sum);
    }
}
