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
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N= Integer.parseInt(bufferedReader.readLine());
        String[] sArr = bufferedReader.readLine().split(" ");

        int[] arr = new int[N+1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }

        boolean[] isVisit = new boolean[N];

        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            int needLeftSize = arr[i];

            int leftSize = 0;
            for (int j = 0; j < N; j++) {
                if(isVisit[j]) continue;

                if(needLeftSize == leftSize){
                    result[j] = i+1;
                    isVisit[j] = true;
                    break;
                }
                leftSize++;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < N; i++) {
            stringBuilder.append(result[i] + " ");
        }
        System.out.println(stringBuilder.toString());

    }
}
