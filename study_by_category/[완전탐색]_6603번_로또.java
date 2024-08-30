import java.io.*;

class Main{

    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    StringBuilder stringBuilder = new StringBuilder();
    int K;
    BufferedWriter bufferedWriter;
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = bufferedReader.readLine();
        while (!s.equals("0")){
            String[] sTmp = s.split(" ");
            int K = Integer.parseInt(sTmp[0]);
            perm(K,sTmp,"",0,1,new boolean[K+1]);
            stringBuilder.append("\n");
            s = bufferedReader.readLine();
        }
        System.out.println(stringBuilder);
    }


    private void perm(int k, String[] sTmp, String s, int depth, int start, boolean[] visit) throws IOException {

        if(depth == 6){
            stringBuilder.append(s + "\n");
            return;
        }

        for(int i=start;i<=k;i++){
            if(visit[i]) continue;
            visit[i] = true;
            perm(k,sTmp,s + sTmp[i] + " ",depth+1,i+1, visit);
            visit[i] = false;
        }

    }


}
