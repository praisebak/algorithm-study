import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Solve{
    int N;
    int M;
    boolean[][] friends;
    int answer = Integer.MAX_VALUE;
    HashMap<Integer,Integer> friendsMap = new HashMap<>();
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);
        friends = new boolean[N+1][N+1];

        //친구수 세기

        for(int i=0;i<M;i++){
            sArr = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(sArr[0]);
            int b = Integer.parseInt(sArr[1]);
            friends[a][b] = true;
            friends[b][a] = true;
            friendsMap.put(a,friendsMap.getOrDefault(a,0)+1);
            friendsMap.put(b,friendsMap.getOrDefault(b,0)+1);
        }

        visit = new boolean[N+1];
        perm(0,0,0,0,0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    boolean[] visit;

    private void perm(int depth, int prev, int fA, int fB, int fC) {
        if(depth == 3){
            //각자 서로 친구가 아니라면 return
            if(!(friends[fA][fB] && friends[fA][fC] && friends[fB][fC])) return;
            int sum = friendsMap.get(fA) + friendsMap.get(fB) + friendsMap.get(fC) - 6;
            answer = Math.min(sum,answer);
            return;
        }

        for(int i=prev+1;i<=N;i++){
            if(visit[i]) continue;

            if(depth == 0){
                fA = i;
            }else if(depth == 1){
                fB = i;
                if(!friends[fA][fB]){
                    continue;
                }
            }else{
                fC = i;
                if(!friends[fA][fC] && !friends[fB][fC]){
                    continue;
                }
            }

            visit[i] = true;
            perm(depth+1,i, fA,fB,fC);
            visit[i] = false;
        }
    }
}

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve= new Solve();
        solve.solve();
    }

}
