
import java.util.Arrays;
class Solution {

    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int cur = 0;
        
        for(int i=0; i< people.length;i++)
        {
            cur += people[i];
            if(cur > limit)
            {
                cur = 0;
                answer++;
            }            
            
        }
        
        if(cur < limit)
        {
            answer++;
        }

        return answer;
    }

}