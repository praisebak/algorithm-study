class Solution
{
    public int solution(String s)
    {
        int answer = 0;
        if(isInitPalind(s))
        {
            return s.length();
        }
        for(int i=0;i<s.length();i++)
        {
            answer = Math.max(findMaxPalindromeLen(i,s),answer);
            
        }
        
        return answer;
    }

    private boolean isInitPalind(String s) 
    {
        for(int i=0;i<s.length()/2;i++)
        {
            if(!(s.charAt(i) == s.charAt(s.length()-1-i)))
            {
                return false;
            }
        }
        return true;
    }

    private int findMaxPalindromeLen(int i, String s) 
    {
        int unit = 1;
        int result = 1;
        int resultB = 0;

        //mode == 0일때
        while(checkIsValid(i,s.length(),unit,0))
        {
            if(!isPalind(i,s,unit,0))
            {
                
                break;
            }
            //System.out.println();
            unit++;
            result+=2;
        }
        

        while(checkIsValid(i,s.length(),unit,1))
        {
            if(!isPalind(i,s,unit,1))
            {
                break;
            }
            unit++;
            resultB+=2;
        }

        return Math.max(result,resultB);
    }

    private boolean isPalind(int i, String s,int unit,int mode) 
    {
        if(mode == 0){
            if(s.charAt(i-unit) == s.charAt(i+unit))
            {
                return true;
            }
        }else{
            if(s.charAt(i-unit+1) == s.charAt(i+unit))
            {
                return true;
            }
        }

        return false;
    }

    private boolean checkIsValid(int i,int len,int unit,int mode) {
        if(mode == 1){
            if(i +1 - unit < 0 || i + unit >= len)
            {
                return false;
            }
        }else{
            if(i - unit < 0 || i + unit >= len){
                return false;
            }
        }
        return true;

    }
}