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
    int[] parent;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr =  bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);
        parent = new int[N+1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int mode = Integer.parseInt(sArr[0]);
            int a = Integer.parseInt(sArr[1]);
            int b = Integer.parseInt(sArr[2]);

            if(mode == 0){
                union(a,b);
            }else{
                int findA = find(a);
                int findB = find(b);
                if(findA == findB){
                    stringBuilder.append("YES");
                }else{
                    stringBuilder.append("NO");
                }
                stringBuilder.append("\n");
            }
        }
        System.out.println(stringBuilder);

    }

    private int find(int a) {
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a == b) return;
        if(a > b){
            parent[a] = b;
        }else{
            parent[b] = a;
        }
    }
}