public class p64062 {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int min = 1;
        int max = 200000000;
        while(min <= max)
        {
            int mid = (min + max) / 2;
            if(isValid(mid,stones,k))
            {
                
                min = mid+1;
                answer = Math.max(mid,answer);
            }
            else
            {
                max = mid-1;
            }
        }
        return answer;
    }

    private boolean isValid(int people, int[] stones, int k) {
        int pass = 0;

        for(int stone : stones)
        {
            if(stone - people < 0)
            {
                pass++;
            }
            else
            {
                pass = 0;
            }
            if(pass == k)
            {
                return false;
            }
        }
        return true;
    }

    
}
