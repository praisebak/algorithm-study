class Solution {
    public String solution(String s) 
    {
        char[] charArr = s.toCharArray();
        boolean prevBlank = true;
        StringBuilder answer = new StringBuilder();
        for(int i=0;i<charArr.length;i++)
        {
            if(charArr[i] == ' ')
            {
                answer.append(' ');
                prevBlank = true;
            }
            else
            {
                if(prevBlank)
                {
                    prevBlank = false;
                    charArr[i] = Character.toUpperCase(charArr[i]);
                }
                else
                {
                    charArr[i] = Character.toLowerCase(charArr[i]);
                }
                answer.append(charArr[i]);
            }
        }

        return answer.toString();
    }

}