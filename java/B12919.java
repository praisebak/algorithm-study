package java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *
 * 문제에서 주어진대로 그대로 구현할려고하는건 위험하다
 * s에서 T를 만든다고 문제에서 적혀져있는데 이를 그대로 따라가면 안됨
 * T에서 s를 만들면 더 빠르고 효율적임
 */
class B12919 {
    private String S;
    private String T;

    private boolean isAnswer = false;

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        S = bufferedReader.readLine();
        T = bufferedReader.readLine();

        topDown(T);
        if(isAnswer) System.out.println(1);
        else System.out.println(0);
    }

    public void topDown(String s){
        if(S.equals(s)){
            isAnswer = true;
            return;
        }

        if(S.length() >= s.length()) return;

        //A를 지운다
        if(s.charAt(s.length()-1) == 'A'){
            topDown(s.substring(0,s.length()-1));
        }

        if(s.charAt(0) == 'B'){
            topDown(new StringBuilder(s.substring(1,s.length())).reverse().toString());
        }

    }


}

