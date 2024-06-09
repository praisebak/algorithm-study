package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B12851_V2 {

    public static void main(String[] args) throws IOException{
        B12851_V2 b12851V2 = new B12851_V2();
        b12851V2.solve();
    }



    class Node{
        public Node(int time, int num) {
            this.time = time;
            this.num = num;
        }

        int time;
        int num;
    }


    int[] minTime;
    int MAX = 200000;


    int count = 0;
    private void solve() throws IOException{
        Scanner scanner = new Scanner(System.in);
        Integer START = scanner.nextInt();
        Integer OBJ = scanner.nextInt();

        if (START >= OBJ) {
            System.out.println((START-OBJ) + "\n1");
            return;
        }

        minTime = new int[MAX];
        Arrays.fill(minTime,Integer.MAX_VALUE);

        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(0,START));
        while (!queue.isEmpty()){
            Node cur = queue.poll();

            if(cur.time <= 4){
//                System.out.println(cur.time + "," + cur.num);
            }
            if(OBJ == cur.num){

//                System.out.println("SAME");
//                System.out.println(minTime[cur.num] + "," + cur.time);
                //현재 시간과 같다
                if(minTime[cur.num] == cur.time){
                    count++;
                }else if(minTime[cur.num] > cur.time){
                    minTime[cur.num] = cur.time;
//                    System.out.println(minTime[cur.time] + "," + cur.time);
                    count = 0;
                }else{
                    continue;
                }
            }
            for(int i=0;i<3;i++){
                int nextNum = cur.num;
                if(i == 0){
                    nextNum+=1;
                }else if(i == 1){
                    nextNum-=1;
                }else{
                    nextNum *= 2;
                }
                //여기가 좀 마음에 걸리네 200000까지는 갈 수 있게할까
                if(nextNum < 0 || nextNum >= MAX){
                    continue;
                }
                if(minTime[nextNum] >= cur.time+1){
                    minTime[nextNum] = cur.time+1;
                    queue.add(new Node(cur.time+1,nextNum));
                }
            }
        }
        System.out.println(minTime[OBJ]);
        System.out.println(count);




    }
}
