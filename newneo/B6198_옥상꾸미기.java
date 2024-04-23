package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


class B6198Main{
    public static void main(String[] args) throws IOException{
        B6198_옥상꾸미기 b6198 = new B6198_옥상꾸미기();
        b6198.solve();
    }

}

class B6198_옥상꾸미기 {

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            int num = Integer.parseInt(bufferedReader.readLine());
            arr[i] = num;
        }


        long sum = 0;

        for(int i=0;i<N;i++){
            int val = arr[i];

            if(stack.isEmpty()){
                stack.push(val);
            }else{

                while (!stack.isEmpty() && stack.peek() <= val){
                    stack.pop();
                }

                sum += stack.size();
                stack.push(val);
            }
        }

        System.out.println(sum);


    }

}




