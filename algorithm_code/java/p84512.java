import java.util.*;
class Solution
{
    HashMap<String,String> connectedMap = new HashMap<>();
    HashSet<String> checkMap = new HashSet<>();
    String word;
    int solution(String word)
    {
        connectedMap.put("A","E");
        connectedMap.put("E","I");
        connectedMap.put("I","O");
        connectedMap.put("O","U");
        connectedMap.put("U","");
        this.word = word;
        int answer = findByDFS(1,new StringBuffer("A"));
        return answer;
    }

    private int findByDFS(int count, StringBuffer sb) 
    {
        
        if(sb.toString().equals( word))
        {
            return count;
        }
        if(sb.length() < 5 && !checkMap.contains(sb.toString() + "A"))
        {
            sb.append("A");
        }
        else
        {
            String lastStr = sb.substring(sb.length()-1);
            while(lastStr.equals("U"))
            {
                //마지막글자 삭제
                sb = sb.deleteCharAt(sb.length()-1);
                //다음 마지막글자에
                lastStr = sb.substring(sb.length()-1);
            }
            sb = sb.deleteCharAt(sb.length()-1);
            sb.append(connectedMap.get(lastStr));
            checkMap.add(sb.toString());
        }
        return findByDFS(count+1, sb);
    }



    public static void main(String[] args)
    {
        Solution solution = new Solution();
        System.out.println(solution.solution("EIO"));
    }
}