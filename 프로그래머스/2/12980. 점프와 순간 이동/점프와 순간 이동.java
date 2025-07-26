import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;

        while(n != 0){
            if(n % 2 == 0){
                n = n/2;
            }else{
                n-=1;
                ans++;
            }

        }
        
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.

        return ans;
    }
}