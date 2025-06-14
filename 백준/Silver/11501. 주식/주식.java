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
        int T = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int day = Integer.parseInt(bufferedReader.readLine());
            String[] sArr = bufferedReader.readLine().split(" ");
            int[] days = new int[day];
            for (int j = 0; j < day; j++) {
                days[j] = Integer.parseInt(sArr[j]);
            }

            int max = 0;
            int prevMoney = 0;
            long answer = 0;

            for (int j = day-1; j >= 0; j--) {
                //고점이니까 판다
                if(days[j] < max){
                    answer += (long)(max - days[j]);
                }
                max = Math.max(days[j],max);
            }
            stringBuilder.append(answer).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }
}
