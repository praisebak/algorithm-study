import jdk.swing.interop.SwingInterOpUtils;

import java.util.Arrays;

public class JavaTask
{
    public static void main(String[] args)
    {
        Solution solution = new Solution();
        solution.solution( 5,new int[]{4,2},new int[]{3,5});
    }
}

class Solution {
    //자신 번호의 바로 앞, 바로 뒤에 학생한테만 빌려줄수있음
    //
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;

        Arrays.sort(lost);
        Arrays.sort(reserve);

        for(int i=0;i<reserve.length;i++)
        {
            for(int j=0;j< lost.length;j++)
            {
                if(lost[j] == reserve[i] )
                {
                    reserve[i] = -10;
                    lost[j] = -10;
                    answer++;
                    break;
                }
            }
        }


        for(int i=0;i<reserve.length;i++)
        {
            if(reserve[i] == -10)
            {
                continue;
            }

            for(int j=0;j<lost.length;j++)
            {
                if(lost[j] != -10)
                {
                    if(reserve[i] == lost[j] -1 || reserve[i] == lost[j] + 1)
                    {
                        lost[j] = -10;
                        answer++;
                        break;
                    }
                }

            }
        }
        return answer;
    }
}