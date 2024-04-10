package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

class Main{
    public static void main(String[] args) throws IOException {
        B1863 b1863 = new B1863();
        b1863.solve();
    }
}

class B1863{
    int N;
    int[] arr;

    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine().split(" ")[1]);
        }

        int answer = 0;
        for(int i=0;i<N;i++) {
            int cur = arr[i];
            if(stack.isEmpty()){
                stack.push(cur);
            }else{
                while (!stack.isEmpty() && stack.peek() > cur){
                    stack.pop();
                    answer++;
                }

                if(stack.isEmpty()){
                    stack.push(cur);
                    continue;
                }else if(stack.peek() == cur){
                    continue;
                }
                stack.push(cur);
            }
        }

        while (!stack.isEmpty()){
            if(stack.pop() != 0) answer++;
        }

        System.out.println(answer);
    }

}