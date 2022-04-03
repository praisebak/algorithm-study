import java.util.*;

class Solution
{
    //N번째 묶음(2명 단위), 둘 중 큰 수
    //앞에서부터
    //A,B의 원래 순번
    HashMap<Integer,Integer> originNumMap = new HashMap<>();
    public int solution(int n, int a, int b)
    {
        int answer = 0;
        int maxNum = Math.max(a, b);
        int minNum = Math.min(a,b);

        originNumMap.put(a, minNum);
        originNumMap.put(b, maxNum);

        answer = untilMeetFight(n,1,a,b);

        return answer;
    }

    int untilMeetFight(int n,int count,int a,int b)
    {
        int curA = originNumMap.get(a);
        int curB = originNumMap.get(b);

        for(int i=2;i <= n;i+=2)
        {
            //만났다
            if(i-1 == curA && i == curB)
            {
                return count;
            }
        }
        originNumMap.put(a, curA / 2 + curA % 2);
        originNumMap.put(b, curB / 2 + curB % 2);


        return untilMeetFight(n/2, count+1, a, b);
    }    
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(8, 4, 7));
        
    }

}
