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
