import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }
}
class Solve{
    int N;
    private int answer;

    class Egg{
        int durability;
        int w;

        public Egg(int durability, int w) {
            this.durability = durability;
            this.w = w;
        }
    }
    List<Egg> eggList = new ArrayList<>();

    int[] arr;

    public void solve()throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            //durability
            int d =Integer.parseInt(sArr[0]);
            int w =Integer.parseInt(sArr[1]);
            eggList.add(new Egg(d,w));
        }
        perm(0);

        System.out.println(answer);
    }

    private void perm(int depth) {
        if(depth == N){
            breakEgg();
            return;
        }
        for (int i = 0; i < N; i++) {
            if(depth == i) continue;
            arr[depth] = i;
            perm(depth+1);
        }

    }

    private void breakEgg() {
        List<Egg> curEggList = copyList();
        int breakEgg = 0;

        for(int i=0;i<N;i++){
            Egg curEgg = curEggList.get(i);
            Egg nextEgg = curEggList.get(arr[i]);
            if(curEgg.durability <= 0) continue;
            //이건 내가 생각했을때 이 조건도 괜찮다 판단함
            if(nextEgg.durability <= 0) continue;

            curEgg.durability -= nextEgg.w;
            nextEgg.durability -= curEgg.w;
            if(curEgg.durability <= 0){
                breakEgg++;
            }
            if(nextEgg.durability <= 0){
                breakEgg++;
            }
        }

        answer = Math.max(breakEgg,answer);
    }

    private List<Egg> copyList() {
        List<Egg> copyList = new ArrayList<>();
        for (Egg egg : eggList){
            copyList.add(new Egg(egg.durability,egg.w));
        }
        return copyList;
    }
}

