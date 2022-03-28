import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) 
    {
        long answer = 0;
        Arrays.sort(times);
        long left = 0;
        long right = times[times.length-1] * n;

        while(left < right)
        {
            long mid = (left + right)/2;
            int sum = 0;
            for(int i=0;i<times.length;i++)
            {
                sum += mid / times[i];
            }
            if(n >= sum)
            {
                answer = Math.min(answer, mid);
                right = mid-1;
            }
            else
            {
                left = mid+1;
            }



        }
        return answer;
    }
}