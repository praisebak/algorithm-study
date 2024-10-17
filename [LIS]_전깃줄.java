import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }
}

class Solve{
    class Node{
        int a;
        int b;
        public Node(int a,int b){
            this.a=a;
            this.b=b;
        }
    }
    List<Node> nodeList = new ArrayList<>();
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(sArr[0]);
            int b = Integer.parseInt(sArr[1]);
            nodeList.add(new Node(a, b));
        }
        Collections.sort(nodeList, (o1, o2) -> o1.a - o2.a);
        lisWithBinsearch();
    }

    private int binSearch(int key, int len, int[] lis) {
        int left = 0;
        int right = len - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (lis[mid] == key) {
                return mid;  // 정확히 일치하는 값을 찾았을 때
            } else if (lis[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;

    }
    private void lisWithBinsearch() {
        int len = 1;
        int[] lis = new int[nodeList.size()];
        lis[0] = nodeList.get(0).b;

        for (int i = 1; i < nodeList.size(); i++) {
            int cur = nodeList.get(i).b;

            if (cur > lis[len - 1]) {
                lis[len++] = cur;  // 증가하는 값이므로 LIS 길이를 늘려준다
            } else {
                int nextIdx = binSearch(cur, len,lis);  // 이진 탐색을 통해 적절한 위치를 찾음
                lis[nextIdx] = cur;  // LIS 배열에서 해당 위치를 업데이트
            }
        }


        // 전체 길이에서 LIS 길이를 빼면 추가적인 조정이 필요한 개수
        System.out.println(nodeList.size() - len);
    }

}
