package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


class B1816_second {
    class Node{
        int x;
        int y;
        int time;
        Node(int x,int y,int t){
            this.x = x;
            this.y = y;
            this.time = t;
        }
    }

    int N;
    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            String[] s = br.readLine().split(" ");
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[1]);
            arr[i] = y;
        }




        Stack<Integer> stack = new Stack<>();
        int count = 0;
        for(int i=0;i<N;i++){
            int curVal = arr[i];

            if(stack.isEmpty()){
                stack.push(curVal);
            }else{
                //건물은있는데 나보다 큰 건물들은 계산해도됨 (이미 그 이전 건물들은 영향력없음)
                while(!stack.isEmpty() && stack.peek() > curVal){
                    stack.pop();
                    count++;
                }
                if(stack.isEmpty()){
                    stack.push(curVal);

                } else if(!stack.isEmpty() && stack.peek() != curVal){
                    stack.push(curVal);
                }
            }
        }

        while(!stack.isEmpty()){
            if(stack.pop() != 0){
                count++;
            }
        }
        System.out.println(count);

    }

}



