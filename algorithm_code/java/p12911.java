class Solution
{

    boolean isCollectOne(int n,int originCount)
    {
        if(countOne(n) == originCount)
        {
            return true;
        }
        return false;
    }
    
    int countOne(int n)
    {
        int count = 0;
        
        char[] bit = (Integer.toBinaryString(n)).toCharArray();
    
        
        for(int i=0;i<bit.length;i++)
        {
            if(bit[i] == '1')
            {
                count++;
            }
        }

        return count;
    }


    public int solution(int n) {
        int answer = 0;
        int num = n+1;
        int originCount = countOne(n);
        
        while(!isCollectOne(num++,originCount))
        {
        }
        answer = num-1;
        return answer;
        
    }

    
}