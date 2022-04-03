import java.util.*;

public class Solution {

    static int minUsedBat = 0;
    


    public int solution(int n) 
    {
        int minUsedBat = 0;
        while(n != 0)
        {
            if(n % 2 == 0)
            {
                n /= 2;
            }
            else
            {
                n -= 1;
                minUsedBat++;
            }

        }
        return minUsedBat;
    }
        
}