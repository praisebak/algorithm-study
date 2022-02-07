import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

class Solution 
{
    String[] getResult(String s)
    {
        ArrayList<String> list = new ArrayList<>();
        //개수순 정렬하면됨
        int length = s.split("\\{").length -2;
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        s = s.replace("{","");
        s = s.replace("}","");
        
        String[] tmpStrArr = s.split(",");
        String[] result = new String[length];

        for(int i=0;i<tmpStrArr.length;i++)
        {
            map.put(tmpStrArr[i],map.getOrDefault(tmpStrArr[i],0)+1);
        }
        for(String str : map.keySet())
        {
            result[length - map.get(str)] = str;
        }
        return result;
        
    }

    public int[] solution(String s) 
    {
        String[] strArr;
        strArr = getResult(s);
        int[] answer = new int[strArr.length];

        for(int i=0;i<answer.length;i++)
        {
            //System.out.println(strArr[i]);
            answer[i] = Integer.parseInt(strArr[i]);
        }

        return answer;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
    }
}