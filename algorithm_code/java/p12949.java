

class Solution
{
    int[][] solution(int[][] arr1, int[][] arr2)
    {
        int maxRow = Math.max( arr1.length, arr2.length);
        int maxCal = Math.max( arr1[0].length,arr2[0].length);
        
        int[][] answer = new int[arr1.length][arr2[0].length];

        int result = 0;

        for(int i=0;i<arr1.length;i++)
        {
            for(int j=0;j<arr2[0].length;j++)
            {
                result = 0; 
                for(int k=0;k<arr1[0].length;k++)
                {
                   result += arr1[i][k] * arr2[k][j];
                }
                answer[i][j] = result;
            }
            
        }
        return answer;
    }
    
}

