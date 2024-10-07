import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve =new Solve();
        solve.solve();

    }
}

class Solve{
    char[][] map;
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int  N = Integer.parseInt(bufferedReader.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String s = bufferedReader.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
            }
        }


        int answer = Integer.MAX_VALUE;
        //행에 대하여
        for (int i = 0; i < (1 << N); i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                int count = 0;
                for (int k = 0; k < N; k++) {
                    char tmp = map[k][j];
                    if((i & (1 << k) ) != 0){
                        tmp = map[k][j] == 'T' ? 'H' : 'T';
                    }
                    if(tmp == 'T'){
                        count++;
                    }
                }
                sum += Math.min(count,N-count);
            }
            answer = Math.min(answer,sum);

        }

        System.out.println(answer);
    }

}
