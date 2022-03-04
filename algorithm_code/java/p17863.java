import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class p17863
{  
    HashMap<String,Integer> dict = new HashMap<>();
    public int[] solution(String msg) 
    {
        StringBuffer sb = new StringBuffer(msg);
        int[] answer = {};
        ArrayList<Integer> tmpAnswer = new ArrayList<>();

        initDict(); 
        int startIdx = 0;
        while(sb.length() != 0)
        {   
            String longestSameStr = "";
            for(int i=sb.length();i>0;i--)
            {
                longestSameStr = sb.substring(0,i);
                Integer val = dict.get(longestSameStr);
                
                if(val != null)
                {
                    tmpAnswer.add(val);
                    sb = sb.delete(0,i);
                    if(sb.length() != 0)
                    {
                        dict.put(longestSameStr + sb.charAt(0), dict.size()+1);
                    }
                    
                    break;
                }
            }
        }
        

        return tmpAnswer.stream().mapToInt(i -> i).toArray();
    }

    private void initDict() 
    {   
        for(int i=(int)'A'; i <= (int)'Z' ;i++)
        {
            dict.put(Character.toString(i),dict.size()+1);
        }


    }
    public static void main(String[] args) 
    {
        p17863 solution = new p17863();
        solution.solution("KAKAO");
    }
}

