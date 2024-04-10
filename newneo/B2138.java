package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class B2138 {

    int N;
    String s;
    String ans;

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        s = bufferedReader.readLine();
        ans = bufferedReader.readLine();
        greedy();
    }




    public char getReversedSwitch(char c){
        if(c == '1'){
            return '0';
        }else{
            return '1';
        }
    }

    public boolean[] clone(boolean[] arr){
        boolean[] newArr = new boolean[arr.length];
        for(int i=0;i<arr.length;i++){
            newArr[i] = arr[i];
        }
        return newArr;
    }



    public boolean[] changeSwitch(int i, boolean[] cur){
        cur[i] = !cur[i];
        if(i == 0){
            cur[i+1] = !cur[i+1];
        }else if(i == cur.length-1){
            cur[i-1] = !cur[i-1];
        }else{
            cur[i-1] = !cur[i-1];
            cur[i+1] = !cur[i+1];
        }
        return cur;
    }


    class Node{
        boolean[] arr;
        int time;

        Node(int time,boolean[] arr){
            this.arr = arr;
            this.time = time;
        }
    }


    public void greedy(){
        boolean[] A = new boolean[N];
        boolean[] B = new boolean[N];
        boolean[] result = new boolean[N];
        int aSwitchCount = 1;
        int bSwitchCount = 0;

        //초기값지정

        for(int i=0;i<N;i++){
            if(ans.charAt(i) == '1'){
                result[i] = true;
            }else{
                result[i] = false;
            }

            if(s.charAt(i) == '1'){
                A[i] = true;
                B[i] = true;
            }else{
                A[i] = false;
                B[i] = false;
            }
        }

        //A는 누르고 B는 누르지 않음 초기값
        A = changeSwitch(0,A);

        for(int i=1;i<N;i++){
            int left = i-1;
            //이전값이 정답의 것과 다르면 스위치킴
            if(A[left] != result[left]){
                A = changeSwitch(i,A);
                aSwitchCount++;
            }
            if(B[left] != result[left]){
                B = changeSwitch(i,B);
                bSwitchCount++;
            }

            //A와 B가 동일함
            if(Arrays.equals(A,B)){
                if(Arrays.equals(A,result)){
                    System.out.println(Math.min(aSwitchCount,bSwitchCount));
                    return;
                }
            }else{
                if(Arrays.equals(A,result)){
                    System.out.println(aSwitchCount);
                    return;
                }else if(Arrays.equals(B,result)){
                    System.out.println(bSwitchCount);
                    return;
                }
            }
        }

        System.out.println(-1);
    }


}

