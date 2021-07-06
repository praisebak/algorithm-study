class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int startCount = 0;

        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i) == ')')
            {
                startCount++;
            }
            else
            {
                if(startCount < 1)
                {
                    return false;
                }
                startCount--;
            }
        }
        if(startCount != 0)
        {
            answer = false;
        }
        return answer;
    }

}