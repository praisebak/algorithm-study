import java.util.*;
class Solution {

    public int betterSolution(int[] A,int[] B)
    {
        int answer = 0;
        Arrays.sort(B);
        Arrays.sort(A);

    
        int bIdx = A.length-1;
        for(int i=A.length-1; i>=0;i--)
        {
            if(B[bIdx] > A[i])
            {
                bIdx--;
                answer++;
            }

        }
        return answer;
    }

    public int mySolution(int[] A, int[] B) {

        ArrayList<Integer> bList = new ArrayList<>();
        Arrays.sort(B);

        for(Integer i : B)
            bList.add(i);

        Arrays.sort(A);

        for(int i=A.length-1;i>=0;i--)
        {
            int answer = 0;
            boolean winFlag = false;
            int winIdx =0;
            for(int j=bList.size()-1;j>=0;j--)
            {
                if(bList.get(j) - A[i] > 0)
                {
                    winIdx = j;
                    winFlag = true;
                }
                else
                    break;
            }
            
            if(!winFlag)
            {
                bList.remove(0);
            }
            else
            {
                bList.remove(winIdx);
                answer++; 
            }
        }
        
        return answer;
    }
}