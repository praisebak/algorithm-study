class Solution {

    public String getNDigitNum(int n,int digit)
    {
        StringBuilder sb = new StringBuilder();
        int curNumber = n;
        if(curNumber == 0)
        {
            return "0";
        }
        while(curNumber > 0)
        {
            if(curNumber % digit < 10)
            {
                sb.append(curNumber % digit);
            }
            else
            {
                sb.append((char)(curNumber % digit - 10 + 'A'));
            }
            curNumber /= digit;
        }
        return sb.reverse().toString();
    }
    //0, 1, 1, 0, 1, 1, 1, 0, 0
    //digit,number,people,count
    public String solution(int n, int t, int m, int p) T
                    if
        String nDigitString;
        StringBuilder answerStrBuild = new StringBuilder();
        int curT = 1;
        int num=0;
        int countT = 0;
        while(true)
        {
            nDigitString = getNDigitNum(num++,n);
            System.out.println(nDigitString);
            for(int idx=0;idx<nDigitString.length();idx++)
            {
                if(curT == p)
                {
                    answerStrBuild.append(nDigitString.charAt(idx));
                    countT++;
                    if(countT == t)
                    {
                        return answerStrBuild.toString();
                    }
                }
                curT++;
                if(curT > m)
                {
                    curT = 1;
                }
            }
        }
    }
}