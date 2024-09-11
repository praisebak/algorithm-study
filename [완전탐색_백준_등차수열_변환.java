import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    int N;
    int answer = Integer.MAX_VALUE;
    private int[] arr;

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());

        if(N < 2){
            System.out.println(0);
            return;
        }

        String[] sArr = bufferedReader.readLine().split(" ");

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }


        int origin = arr[0];
        int originB = arr[1];
        for(int i=-1;i<2;i++){
            for (int j = -1; j < 2; j++) {
                int count = 0;

                if(i != 0){
                    count++;
                }

                if(j != 0){
                    count++;
                }

                arr[0] = origin + i;
                arr[1] = originB + j;


                int diff = arr[0] - arr[1];
//                System.out.println("start = " + arr[0] + "," + arr[1] + " " + diff);
                dfs(2,count,diff);
                arr[0] = origin;
                arr[1] = originB;
            }
        }
        if(answer == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(answer);
        }
    }

    private void dfs(int depth, int count, int diff) {
//        System.out.println("cur depth = " + depth + " " + count);
        if(depth == N) {
//            System.out.println("답 발견" + count);
            answer = Math.min(answer,count);
            return;
        }
        int origin = arr[depth];

        for (int j = -1; j < 2; j++) {
            int next = origin + j;

            if(arr[depth-1] - next == diff){
                arr[depth] = next;
                if(j == 0){
                    dfs(depth+1,count,diff);
                }else{
                    dfs(depth+1,count+1,diff);
                }
                arr[depth] = origin;
            }
        }

    }
}
