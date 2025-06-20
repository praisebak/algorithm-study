import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.PriorityQueue;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }

}

class Solve {
    class Flower{
        int startTime;
        int endTime;
        public Flower(int startM, int startD, int endM, int endD){
            startTime = startM * 100 + startD;
            endTime = endM * 100 + endD;
        }
    }

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        PriorityQueue<Flower> priorityQueue = new PriorityQueue<>(((o1, o2) -> {
            if(o1.startTime == o2.startTime){
                return o2.endTime - o1.endTime;
            }
            return o1.startTime - o2.startTime;
        }));

        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            int startM = Integer.parseInt(sArr[0]);
            int startD = Integer.parseInt(sArr[1]);
            int endM = Integer.parseInt(sArr[2]);
            int endD = Integer.parseInt(sArr[3]);
            Flower flower = new Flower(startM, startD, endM, endD);
            priorityQueue.add(flower);
        }

        int startTime = 3 * 100 + 1;
        int endTime = 11 * 100 + 31;

        int answer = 0;
        while (startTime <= endTime){
            Flower selectedFlower = null;
            while (!priorityQueue.isEmpty()){
                Flower cur = priorityQueue.peek();

                //이후 꽃임
                if(cur.startTime > startTime){
                    break;
                }

                priorityQueue.poll();
                if(selectedFlower == null){
                    selectedFlower = cur;
                    continue;
                }

                if(selectedFlower.endTime < cur.endTime){
                    selectedFlower = cur;
                }
            }

            if(selectedFlower == null){
                System.out.println(0);
                return;
            }

            startTime = selectedFlower.endTime;
            answer++;
        }

        System.out.println(answer);
    }

}
