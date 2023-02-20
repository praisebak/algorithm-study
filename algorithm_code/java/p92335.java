import java.util.ArrayList;

class Solution 
{
    int result = 0;
    
    public int solution(int n, int k) 
    {
        String numStr = convertNDigit(n,k);
        getResultNum(numStr);
        int answer = result;
        return answer;
    }

    private void getResultNum(String numStr) 
    {
        int i = 0;
        int j = 0;
        for(i=0;i<numStr.length(); i=j )
        {
            for (j = i+1; j < numStr.length() && numStr.charAt(j) != '0'; j++) ;
            String curNumStr = numStr.substring(i, j);
            if(isPrimeNum(curNumStr))
            {
                this.result++;
            }  
                
            
        }
        
    }

    
    private boolean isPrimeNum(String numStr) 
    {
        boolean flag = true;
        long val = Long.parseLong(numStr);
        System.out.println(numStr);
        if(val <= 1)
        {
            flag = false;

        }
        else
        {
            for(int i=2;i<=Math.sqrt(val);i++)
            {
                if(val == 2)
                {
                    break;
                }
                if(val % i == 0)
                {
                    flag = false;
                    break;
                }
    
            }
        }
        System.out.println(numStr + " -> " + flag);
        return flag;
    }


    private String convertNDigit(int n, int k) 
    {
        StringBuilder sb = new StringBuilder();

        while(n != 0)
        {
            sb.append(Integer.toString(n % k));
            n /= k;
        }

        return sb.reverse().toString();
    }


    public static void main(String[] args) 
    {
        Solution solution = new Solution();
        solution.solution(437674, 3);
    }
}