
import java.util.*;
import java.io.*;
class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int oneCount = countOrigin(n);
        
        for(int i=n+1;i<=Integer.MAX_VALUE;i++){
            if(countOrigin(i) == oneCount){
                return i;
            }
            
        }
        return 0;

    }
    
    public int countOrigin(int num){
        String s = Integer.toBinaryString(num);
        int count = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == '1'){
                count++;
            }
        }
        return count;
    }
}