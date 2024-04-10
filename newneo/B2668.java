package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class B2668{
    int N;
    int[] arr;
    boolean[] visit;
    List<Integer> answer = new ArrayList<>();
    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         N = Integer.parseInt(br.readLine());

         arr = new int[N+1];
         visit = new boolean[N+1];
         for(int i=1;i<=N;i++){
             arr[i] = Integer.parseInt(br.readLine());
         }

         for(int i=1;i<=N;i++){
             visit[i] = true;
             dfs(i,i);
             visit[i] = false;
         }

         System.out.println(answer.size());

         answer.sort(Comparator.naturalOrder());
         for(Integer i : answer){
             System.out.println(i);
         }
    }

    private void dfs(int index, int target) {
        int next = arr[index];
        if(!visit[next]){
            visit[next] = true;
            dfs(next,target);
            visit[next] = false;
        }

        if(target == arr[index]){
            answer.add(index);
        }
    }
}