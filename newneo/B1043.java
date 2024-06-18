package newneo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class B1043 {
    public static void main(String[] args) {
        B1043 b1043 = new B1043();
        b1043.solve();
    }

    private int find(int x){
        if(parent[x] == x){
            return x;
        }
        return find(parent[x]);
    }

    private void union(int a,int b){
        a = find(a);
        b = find(b);

        if(knownPeopleInPartyList.contains(a)) {
            parent[b] = a;
            return;
        }
        parent[a] = b;
    }
    int[] parent;
    List<Integer> knownPeopleInPartyList = new ArrayList<>();

    private void solve() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int K = scanner.nextInt();

        parent = new int[N+1];

        for(int i=0;i<N+1;i++){
            parent[i] = i;
        }

        for (int i = 0; i < K; i++) {
            int knownMan = scanner.nextInt();
            knownPeopleInPartyList.add(knownMan);
        }

        List<Integer>[] partyPeopleList = new ArrayList[M];

        for(int i=0;i<M;i++){
            partyPeopleList[i] = new ArrayList<>();
            int pn = scanner.nextInt();
            int first = scanner.nextInt();

            partyPeopleList[i].add(first);
            for(int n=1;n<pn;n++){
                int next = scanner.nextInt();
                union(first,next);
                partyPeopleList[i].add(next);

            }
        }

        int count = 0;
        for (int i = 0; i < M; i++) {
            List<Integer> list = partyPeopleList[i];
            boolean flag = true;
            for(Integer integer : list){
                if(knownPeopleInPartyList.contains(find(integer))){
                    flag = false;
                    break;
                }
            }
            if(flag){
                count++;
            }
        }
        System.out.println(count);

    }
}
