package java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


class Main{
    public static void main(String[] args) throws IOException {
        new B2138().solve();
    }
}

class B2138 {

    int N;
    String s;
    String ans;
    StringBuilder sb = new StringBuilder();
    HashSet<String> set = new HashSet<>();

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        s = bufferedReader.readLine();
        ans = bufferedReader.readLine();

        bfs();
    }




    public char getReversedSwitch(char c){
        if(c == '1'){
            return '0';
        }else{
            return '1';
        }
    }



    public String changeSwitch(int i, String cur){
        StringBuilder sb = new StringBuilder(cur);
        if(i == 0){
            sb.setCharAt(0, getReversedSwitch(cur.charAt(0)));
            sb.setCharAt(1, getReversedSwitch(cur.charAt(1)));
        }else if(i == cur.length()-1){
            sb.setCharAt(i, getReversedSwitch(cur.charAt(i)));
            sb.setCharAt(i-1, getReversedSwitch(cur.charAt(i-1)));
        }else{
            sb.setCharAt(i, getReversedSwitch(cur.charAt(i)));
            sb.setCharAt(i-1, getReversedSwitch(cur.charAt(i-1)));
            sb.setCharAt(i+1, getReversedSwitch(cur.charAt(i+1)));
        }
        return sb.toString();
    }


    class Node{
        String str;
        int time;

        Node(int time,String str){
            this.str = str;
            this.time = time;
        }
    }


    public void bfs(){

        Queue<Node> que = new LinkedList<>();
        que.add(new Node(0,s));
        set.add(s);


        while(!que.isEmpty()){
            Node cur = que.poll();
            for(int i=0;i<cur.str.length();i++){
                String result = changeSwitch(i,cur.str);
                if(!set.contains(result)){
                    set.add(result);
                    que.add(new Node(cur.time+1,result));
                }

                if(result.equals(ans)){
                    System.out.println(cur.time+1);
                    return;
                }
            }
        }

        System.out.println(-1);
    }
}

