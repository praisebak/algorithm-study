
import java.util.*;


//답지 보고 푼 문제
//프로그래머스에는 제출하지 않았으니 다시 풀어보자
//아이디어,내가 생각한 방법은 시간초과
class Solution 
{
    public long solution(int n, int[] times) 
    {
        Arrays.sort(times);
        long start = 0;
        long end = Long.MAX_VALUE;
        long sum = 0;
        long mid = 0;
        long answer = 0; 

        while(start <= end)
        {
            mid = (start + end) / 2;
            for(int i=0;i<times.length;i++)
            {
                sum += mid / times[i];
                if(sum >= n)
                {
                    break;
                }
            }

            if(sum >= n)
            {
                end = mid-1;
                answer = Math.min(answer,mid);
            }else{
                start = mid+1;
            }


            
        }
        return answer;





    }
}