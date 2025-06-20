import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    class Box{
        int start;
        int end;
        int count;

        public Box(int start, int end, int count) {
            this.start = start;
            this.end = end;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Box{" +
                    "start=" + start +
                    ", end=" + end +
                    ", count=" + count +
                    '}';
        }
    }

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int TOWN = Integer.parseInt(sArr[0]);
        int MAX_WEIGHT = Integer.parseInt(sArr[1]);
        int T = Integer.parseInt(bufferedReader.readLine());

        List<Box> allBoxes = new ArrayList<>();

        for (int i = 0; i < T; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(sArr[0]);
            int end = Integer.parseInt(sArr[1]);
            int count = Integer.parseInt(sArr[2]);
            allBoxes.add(new Box(start, end, count));
        }

        // 박스들을 받는 마을(end) 오름차순, 보내는 마을(start) 오름차순으로 정렬
        Collections.sort(allBoxes, (b1, b2) -> {
            if (b1.end != b2.end) {
                return b1.end - b2.end;
            }
            return b1.start - b2.start;
        });

        // 각 마을 구간별 현재 트럭의 잔여 용량을 저장하는 배열
        // capacities[i]는 i번 마을에서 i+1번 마을로 이동할 때의 최대 용량 (잔여 용량)
        int[] capacities = new int[TOWN + 1]; // 1번 마을부터 TOWN번 마을까지
        Arrays.fill(capacities, MAX_WEIGHT); // 모든 구간의 초기 용량을 트럭 최대 용량으로 설정

        long totalDeliveredBoxes = 0; // 총 배송한 박스 수

        // 정렬된 박스들을 순회하며 처리
        for (Box box : allBoxes) {
            // 현재 박스가 지나갈 모든 마을 구간 (box.start ~ box.end-1)의 최소 잔여 용량을 찾는다
            int currentMinCapacity = MAX_WEIGHT;
            for (int i = box.start; i < box.end; i++) {
                currentMinCapacity = Math.min(currentMinCapacity, capacities[i]);
            }

            // 현재 박스를 얼마나 실을 수 있는지 계산 (박스 개수와 최소 잔여 용량 중 작은 값)
            int canLoad = Math.min(box.count, currentMinCapacity);

            // 실을 수 있는 만큼 싣고, 해당 구간들의 잔여 용량을 감소시킨다
            for (int i = box.start; i < box.end; i++) {
                capacities[i] -= canLoad;
            }

            // 총 배송 박스 수에 더한다
            totalDeliveredBoxes += canLoad;
        }

        System.out.println(totalDeliveredBoxes);
    }
}
