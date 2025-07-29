import java.util.*;
import java.io.*;
class Solution {
    public int solution(int[] citations) {
        
        int answer = 0;
        for(int i=1;i<10001;i++){
            int val = i;
            int minH = 0;
            int maxH = 0;
            for(int j=0;j<citations.length;j++){
                if(val <= citations[j]) maxH++;
                if(val >= citations[j]) minH++;
            }
            
            if(maxH >= val && minH <= val){
                answer = Math.max(val,answer);
            }
            
        }
        return answer;
    }
}