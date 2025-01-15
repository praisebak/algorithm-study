import javax.sql.rowset.serial.SerialArray;
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

    private int[] parent;

    public int find(int v){
        if(parent[v] == v){
            return v;
        }
        return find(parent[v]);
    }

    public void union(int a,int b){
        a = find(a);
        b = find(b);
        if(a > b){
            parent[a] = b;
        }else{
            parent[b] = a;
        }
    }

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= M; i++){
            sArr = bufferedReader.readLine().split(" ");
            int cur = Integer.parseInt(sArr[0]);
            int next = Integer.parseInt(sArr[1]);
            int parentA = find(cur);
            int parentB = find(next);
            if(parentA == parentB){
                System.out.println(i);
                return;
            }
            union(cur,next);
        }
        System.out.println(0);
    }
}
