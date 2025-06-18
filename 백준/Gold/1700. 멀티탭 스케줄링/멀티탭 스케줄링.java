import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
    public static void main(String[] args)throws IOException {
        Solve solve = new Solve();
        solve.solve();

    }
}

class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int K = Integer.parseInt(sArr[1]);

        int[] arr = new int[104];
        boolean[] visit = new boolean[104];
        sArr = bufferedReader.readLine().split(" ");
        for (int i = 0; i < K; i++) {
            int cur = Integer.parseInt(sArr[i]);
            arr[i] = cur;
        }

        List<Integer> list = new ArrayList<>();
        int curN = 0;
        int answer = 0;
        for (int i = 0; i < K; i++) {
            if(visit[arr[i]]) continue;

            if(curN == N){
                int maxDist = 0;
                int maxDistVal = 0;
                for (int idx = 1; idx <= K; idx++) {
                    if(visit[idx]){
                        int curDist = Integer.MAX_VALUE;
                        for (int j = i+1; j < K; j++) {
                            if(arr[j] == idx){
                                curDist = j;
                                break;
                            }
                        }
                        if(curDist >= maxDist){
                            maxDist = curDist;
                            maxDistVal = idx;
                        }
                    }
                }

                answer++;
                visit[maxDistVal] = false;
                curN--;
            }

            visit[arr[i]] = true;
            curN++;
        }


        System.out.println(answer);

    }

    private void removeList(int val,List<Integer> removeList) {
        int removeIdx = 0;
        for (int i = 0; i < removeList.size(); i++) {
            if(removeList.get(i) == val){
                removeIdx = i;
                break;
            }
        }
        removeList.remove(removeIdx);
    }
}