import java.util.*;
import java.io.*;
class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        
        int count = 0;
        int lengthSum = 0;
        //0을 없애고, 이진수로 바꿔야함! 근데 이건 라이브러리쓰면될것같긴함.
        while(s.length() != 1){
            StringBuilder sb = new StringBuilder();
            
            for(int i=0;i<s.length();i++){
                if(s.charAt(i) == '1') {
                    sb.append("1");
                }
            }
            lengthSum += s.length() - sb.toString().length();
            int num = sb.toString().length();
            
            s = Integer.toBinaryString(num);

            count++;
            if(count > 10){
                break;
            }
            
        }
        answer = new int[]{count,lengthSum};
        
        
        
        return answer;
    }
}