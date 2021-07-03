<<<<<<< HEAD
public class p12951 {

    public static String solution(String s) 
    {
        String answer = "";
        String tmp[] = s.split(" ");
        StringBuffer tmpAnswer= new StringBuffer();
        for(int i=0;i<tmp.length;i++)
        {
            String front = tmp[i].substring(0,1).toUpperCase();
            String back = tmp[i].substring(1).toLowerCase();
            tmpAnswer.append(front +back);
            if(i+1 != tmp.length)
            {
                tmpAnswer.append(" ");
            }
        }
        
        answer = tmpAnswer.toString();
        return answer;
    }

    public static void main(String [] args)
    {
        System.out.println(solution("asdf "));
    }

}
=======
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
>>>>>>> 4ab2ce232ae40dd96c1f05989f88d4e5dc4a1075
