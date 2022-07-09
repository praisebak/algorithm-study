import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
public class p64064 {
    HashSet<String> strSet = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        dfs(user_id,banned_id,0,new boolean[user_id.length]);
        return strSet.size();
    }

    private void dfs(String[] user_id, String[] banned_id,int idx,boolean[] isVisit) 
    {
        if(idx == banned_id.length)
        {
            StringBuilder user_idxs = new StringBuilder();
            for(int i = 0; i < user_id.length; i++) {
                if(isVisit[i]) {
                    user_idxs.append(i); // 모든 인덱스를 이어붙이자
                }
            }
            strSet.add(user_idxs.toString());
            return;
        }

        for(int i=0;i<user_id.length;i++)
        {
            if(isVisit[i])
            {
                continue;
            }

            if(user_id[i].length() != banned_id[idx].length())
            {
                continue;
            }

            boolean flag= false;
            for(int j=0;j<user_id[i].length();j++)
            {
                if(banned_id[idx].charAt(j) == '*')
                {
                    continue;
                }
                    
                if(banned_id[idx].charAt(j) != user_id[i].charAt(j))
                {
                    flag = true;
                    break;
                }
            }

            if(!flag)
            {
                isVisit[i] = true;
                dfs(user_id,banned_id,idx+1,isVisit);
                isVisit[i] = false;
            }
        }
        
    }
}
