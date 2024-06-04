package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B12851 {

    class Node{
        public Node(int time, int val) {
            this.time = time;
            this.val = val;
        }

        int time;
        int val;
    }
    int N;
    int obj;

    int minTime = Integer.MAX_VALUE;
    int minTimeCount = 0;

    public static void main(String[] args) throws IOException {
        B12851 b12851 = new B12851();
        b12851.solve();
    }


    int[] num = {-1,1,2};
    private void solve() throws IOException{

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        N = Integer.parseInt(s.split(" ")[0]);
        obj = Integer.parseInt(s.split(" ")[1]);

        if (N >= obj) {
            System.out.println((N-obj) + "\n1");
            return;
        }

        int[] visit = new int[100001];

//        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0,N));

        while (!queue.isEmpty()){
            Node node = queue.poll();
            if(node.time > minTime) continue;

            for(int i=0;i<3;i++){
                int nextNum = 0;
                if(i != 2){
                    nextNum = node.val + num[i];
                }else{
                    nextNum = node.val * 2;
                }

                if(nextNum == obj){
                    if(minTime > node.time+1){
                        minTime = node.time+1;
                        minTimeCount = 0;
                    }
                    minTimeCount++;
                    continue;
                }

                if(nextNum < 0 || nextNum > 100000) continue;


                if(visit[nextNum] == 0 || visit[nextNum] >= node.time+1){
                    visit[nextNum] = node.time+1;

                    if(node.time+1 <= minTime){
                        System.out.println(nextNum);
                        queue.add(new Node(node.time+1,nextNum));
                    }
                }
            }
        }
        System.out.println(minTime);
        System.out.println(minTimeCount );
    }

}
