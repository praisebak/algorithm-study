package java;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;



//간단한 bfs
class B13549{
    private int N;
    private int K;


    public void solve() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        bfs();

    }

    public void bfs(){
        int start = N;
        int end = K;


        boolean[] visit = new boolean[100001];

//        Queue<Node> que = new ArrayList<>();
        Deque<Node> que = new LinkedList<>();

        //우선순위 무조건 순간이동으로 해야함
        que.add(new Node(start,0));


        //visit을한다고하면 이미 방문한것의 시간이 더 빠를수도있잖아
        //그냥 visit안하는게 나은듯 아니면 시간까지 계산할수있긴한데 그럴려면
        //공간복잡도를 너무 많이 먹는다 시간이 2초니까 괜찮을거라 생각함
        while (!que.isEmpty()){
            Node cur = que.pop();
            if(visit[cur.val]) continue;

            visit[cur.val] = true;
            if(cur.val == end){
                System.out.println(cur.time);
                break;
            }

            for(int i=0;i<3;i++){
                if(i == 0){
                    if(cur.val -1 >= 0){
                        que.addLast(new Node(cur.val-1,cur.time+1));
                    }
                }else if(i == 1){
                    if(cur.val +1 <= 100000) {
                        que.addLast(new Node(cur.val + 1, cur.time + 1));
                    }
                    //순간이동
                }else{
                    if(cur.val * 2 <= 100000){
                        que.addFirst(new Node(cur.val * 2 , cur.time));
                    }
                }
            }

        }
    }

    private class Node {

        Node(int val,int time){
            this.val = val;
            this.time = time;
        }
        int val;
        int time;
    }
}