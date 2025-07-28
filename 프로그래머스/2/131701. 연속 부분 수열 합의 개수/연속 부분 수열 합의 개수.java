import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        int answer = 0;
        int[] doubledElements = new int[elements.length * 2 + 1];
        

        for(int i=1;i<=elements.length * 2;i++){
            doubledElements[i] = doubledElements[i-1] + elements[(i-1) % elements.length];
        }
        
        for(int len=1;len<=elements.length;len++){
            for(int start=0;start<elements.length;start++){
                set.add(doubledElements[len+start] - doubledElements[start]);
            }
        }
        
        return set.size();
    }
}