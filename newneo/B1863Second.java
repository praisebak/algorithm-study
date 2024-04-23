package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


class B1863Main{
    public static void main(String[] args) throws IOException{
        B1863Second b1863Second = new B1863Second();
        b1863Second.solve();
    }
}

class B1863Second {
    int N;

    public void solve() throws IOException{
        BufferedReader bufferedReader =  new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(bufferedReader.readLine().split(" ")[1]);
        }

        int result = 0;

        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<N;i++){
            int height = arr[i];

            if(stack.isEmpty()){
                stack.push(height);
            }else{
                while(!stack.isEmpty() && stack.peek() > height){
                    result++;
                    stack.pop();
                }
                if(stack.isEmpty()){
                    stack.push(height);
                }else if(stack.peek() != height){
                    stack.push(height);
                }
            }
        }

        while (!stack.isEmpty()){
            if(stack.pop() != 0){
                result++;
            }
        }

        System.out.println(result);
    }





}



