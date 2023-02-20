import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes,(int[] r1,int[] r2) -> r1[1] - r2[1]);
        int prevCam = Integer.MIN_VALUE;
        int answer = 0;

        for(int i=0;i<routes.length;i++)
        {
            if(routes[i][0] <= prevCam)
            {
                continue;
            }
            prevCam = routes[i][1];
            answer++;
        }


        return answer;
    }
}