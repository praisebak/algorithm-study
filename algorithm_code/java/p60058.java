class Solution {


    boolean isCompleStr(String p)
    {
        Character curCh = ' ';
        int openCount = 0;
        int closeCount = 0;
        //(()())()
        //완료된 괄호   
        for(int i=0;i<p.length();i++)
        {  
            curCh = p.charAt(i);

            if(curCh == '(')
            {
                openCount++;
            }
            else
            {
                closeCount++;
            }
            if(openCount < closeCount)
            {
                return false;
            }
            else if(openCount == closeCount)
            {
                if(i == p.length() -1)
                {
                    break;
                }
            }
        }
        if(openCount == closeCount)
        {
            return true;
        }
        return false;
    }

    
    
    String getConvertedStr(String str)
    {
        int openCount = 0;
        int closeCount = 0;
        Character curCh = ' ';
        for(int i=0;i<str.length();i++)
        {
            curCh = str.charAt(i);
            if(curCh == ')')
            {
                openCount++;
            }
            else
            {
                closeCount++;
            }

            if(openCount == closeCount)
            {
                String u = str.substring(0, i+1);
                String v = str.substring(i+1, str.length());

                if(isCompleStr(u))
                {
                    u += getConvertedStr(v);
                    return u;
                }
                else
                {
                    String tmpStr = "(";
                    tmpStr += getConvertedStr(v);
                    tmpStr += ")";
                    String reverseU = getReverseStr(u);
                    tmpStr += reverseU.substring(1,reverseU.length()-1);
                    return tmpStr;
                }
            }

        }
        return "error";

    }


    private String getReverseStr(String u) {
        String result = "";
        for(int i=0;i<u.length();i++)
        {
            if(u.charAt(i) == ')')
            {
                result+= "(";
            }
            else
            {
                result+= ")";
            }
        }
        return result;
    }



    public String solution(String p) 
    {
        if(isCompleStr(p))
        {
            return p;
        }
        String answer = getConvertedStr(p);
        return answer;
    }
}