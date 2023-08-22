package com.study;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class B2493
{
    public static void main(String[] args) throws IOException
    {
        SolveB2493 solveB2493 = new SolveB2493();
        solveB2493.solve();
    }
}

class SolveB2493{
    int N;

    void solve() throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int val = 0;
        Stack<Tower> stack = new Stack<>();

        for (int i = 0; i < N; i++)
        {
            val = Integer.parseInt(stringTokenizer.nextToken());
            if(stack.isEmpty()){
                stack.add(new Tower(val,i+1));
                bufferedWriter.write(0 + " ");
            }
            else{
                while (!stack.isEmpty() && stack.peek().h < val){
                    stack.pop();
                }
                if(stack.isEmpty()){
                    stack.push(new Tower(val,i+1));
                    bufferedWriter.write(0 + " ");
                }else if(stack.peek().h >= val){
                    bufferedWriter.write(stack.peek().i + " ");
                    stack.push(new Tower(val,i+1));
                }
            }

        }


        bufferedWriter.flush();
    }


    private static class Tower
    {
        int h;
        int i;
        Tower(int h,int i){
            this.h = h;
            this.i = i;
        }
    }
}
