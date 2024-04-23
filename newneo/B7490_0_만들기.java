package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class B7490_Main{
    public static void main(String[] args) throws IOException {
        B7490_0_만들기 b7490 = new B7490_0_만들기();
        b7490.solve();
    }

}


/**
 * 다음엔 깔끔하게 풀어보기
 */

class B7490_0_만들기 {

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        int[] arr = new int[T];
        for(int i=0;i<T;i++){
            arr[i] =Integer.parseInt(bufferedReader.readLine());
        }
        for(int i=0;i<arr.length;i++){
            resultList = new ArrayList<>();
            dfs(arr[i],1,"1","1");

            Collections.sort(resultList);
            for(String tmp : resultList){
                System.out.println(tmp);
            }
            System.out.println();
        }
    }

    String[] next = {"+","-"," "};

    List<String> resultList = new ArrayList<>();
    private void dfs(int n, int num,String s,String originStr) {
        if(num >= n){
            if(cal(s) == 0){
                resultList.add(originStr);
            }
            return;
        }

        for(int i=0;i<3;i++){
            String tmp = originStr + next[i] + (num+1);
            String newStr = "";
            if(i == 2){
                newStr = s + "" + (num+1);
//                System.out.println(s + " -> " + tmp + " -> " + s + " " + (num+1) + " = " + newStr + " end " + tmp);
            }else{
                newStr = s + next[i] + (num+1) + "";
            }
            dfs(n,num+1,newStr,tmp);
        }
    }

    private int cal(String s) {
        int sum = 0;
        boolean isStart= true;
//        System.out.println(s);

        int leftNum = 0;
        for(int i=0;i<s.length();i++){

            int operIdx = findOper(i,s);
            if(operIdx == -1){
                return Integer.parseInt(s.substring(i,s.length()));
            }
            int nextOperIdx = findOper(operIdx+1,s);

            if(isStart){
                isStart = false;
                leftNum = Integer.parseInt(s.substring(i,operIdx));
            }else{
                leftNum = sum;
            }
            int rightNum = 0;

            //없음
            if(nextOperIdx == -1){
                rightNum = Integer.parseInt(s.substring(operIdx+1,s.length()));
            }else{
                rightNum = Integer.parseInt(s.substring(operIdx+1,nextOperIdx));
            }

//            System.out.println(leftNum + "," + rightNum + " " + i + "," + operIdx + "," + nextOperIdx);

            switch (s.charAt(operIdx)) {
                case '+':
                    sum = leftNum + rightNum;
//                    System.out.println("+ = " + sum + " by " +leftNum + "," + rightNum);

                    break;
                case '-':
                    sum = leftNum - rightNum;
//                    System.out.println("- = " + sum + " by " +leftNum + "," + rightNum);
                    break;
                default:
                    break;
            }

            if(nextOperIdx == -1){
                break;
            }

            i = nextOperIdx-1;
        }
//        System.out.println("sum = " + sum);
        return sum;
    }

    private int findOper(int i, String s) {
        for(int idx = i;idx<s.length();idx++){
            if(!Character.isDigit(s.charAt(idx))){
                return idx;
            }
        }
        return -1;
    }

}

