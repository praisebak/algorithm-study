//https://www.acmicpc.net/problem/1062
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Main{

    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{


    private int N;
    private int K;
    HashSet<Character> set = new HashSet<>();


    List<String> list = new ArrayList<>();
    private int answer = 0;

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        K = Integer.parseInt(sArr[1]);

        if(K < 5){
            System.out.println(0);
            return;
        }

        set.add('a');
        set.add('n');
        set.add('t');
        set.add('i');
        set.add('c');

        for(int i=0;i<N;i++){
            String s = bufferedReader.readLine();
            list.add(s);
            for (int j = 0; j < s.length(); j++) {
                char ch = s.charAt(j);
                set.add(ch);
            }
        }



        boolean[] visit = new boolean[26];

        visit['a' - 97] = true;
        visit['n' - 97] = true;
        visit['t' - 97] = true;
        visit['i' - 97] = true;
        visit['c' - 97] = true;
        dfs(5,visit,0);

        System.out.println(answer);

    }

    private void dfs(int depth, boolean[] visit,int prev) {
        if(depth == K){
            check(visit);
            return;
        }
        for(int i=prev;i<26;i++){
            if(visit[i]) continue;
            visit[i] = true;
            dfs(depth+1,visit,i);
            visit[i] = false;
        }
    }

    private void check(boolean[] visit) {
        int known = 0;
        for(String word : list){
            boolean unknown = false;
            for(int i=0;i<word.length();i++){
                char ch = word.charAt(i);
                if(!visit[ch - 97]){
                    unknown = true;
                    break;
                }
            }
            if(unknown) continue;
            known++;
            answer = Math.max(known,answer);
        }

    }
}
