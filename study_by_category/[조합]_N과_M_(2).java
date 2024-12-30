import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);

        visit = new boolean[N+1];
        comb(N,M,0,"",1);
    }

    static boolean[] visit;

    static int answer = 0;
    private static void comb(int n, int m, int i,String s,int prev) {
        if(i == m){
            System.out.println(s.strip());
            return;
        }

        for (int j = prev; j <= n; j++) {
            if(visit[j]) continue;
            visit[j] = true;
            comb(n,m,i+1,s + " " + j,j+1);
            visit[j] = false;
        }
    }
}
