import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    class Jewel{
        int weight;
        int value;
        public Jewel(int weight,int value){
            this.weight = weight;
            this.value = value;
        }
    }
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);
        List<Integer> bagList = new ArrayList<>();

        List<Jewel> jewelList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            sArr = bufferedReader.readLine().split(" ");

            int w = Integer.parseInt(sArr[0]);
            int v = Integer.parseInt(sArr[1]);

            jewelList.add(new Jewel(w,v));
        }
        for (int i = 0; i < M; i++) {
            bagList.add(Integer.parseInt(bufferedReader.readLine()));
        }

        Collections.sort(jewelList,((o1, o2) -> o1.weight - o2.weight));
        Collections.sort(bagList,((o1, o2) -> o1 - o2));

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(((o1,o2)-> o2 - o1));
        int curBagIdx = 0;
        long answer = 0;

        int jewelIdx = 0;

        //가방에 대해서 먼저 작은 가방부터 시작해서 넣을 수 있는거 다 넣고 제일 좋은거 하나 넣기
        for (int i = 0; i < bagList.size(); i++) {
            while (jewelIdx < N && bagList.get(i) >= jewelList.get(jewelIdx++).weight){
                priorityQueue.add(jewelList.get(i).value);
            }
            
            if(!priorityQueue.isEmpty()){
                answer += priorityQueue.poll();
            }
        }
        System.out.println(answer);


    }
}
