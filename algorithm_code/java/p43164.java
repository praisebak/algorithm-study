
import java.util.*;

class Solution {
    ArrayList<String> list = new ArrayList<>();
    boolean visit[];
    public String[] solution(String[][] tickets) {
        visit = new boolean[tickets.length];
        String[] answer;
        dfs(tickets,"ICN","ICN",0);
        Collections.sort(list);
        answer = list.get(0).split(" ");
        return answer;
    }

    private void dfs(String[][] tickets, String startPoint, String result, int count)
    {
        if(count == tickets.length)
        {
            list.add(result);
            return;
        }

        for(int i=0;i<tickets.length;i++)
        {
            if(!visit[i] && tickets[i][0].equals(startPoint))
            {
                visit[i] = true;
                dfs(tickets,tickets[i][1],result + " " + tickets[i][1],count+1);
                visit[i] = false;
            }
        }

    }

}