import java.util.*;

class Solution {
    HashSet<String> reportSet = new HashSet<>();
    HashMap<String,ArrayList<String>>reportedMap = new HashMap<>();
    HashMap<String,Integer>reportedCount = new HashMap<>();
    HashSet<String> reportResult = new HashSet<>();


    public int[] solution(String[] id_list, String[] report, int k) {

        int[] answer = new int[id_list.length];
        for(String str : report)
        {
            reportSet.add(str);
        }

        for(String str : reportSet)
        {
            String[] split = str.split(" ");
            String sub = split[0];
            String obj = split[1];
         
            //신고된 사람 : 신고한 사람 리스트
            ArrayList<String> array = reportedMap.getOrDefault(obj, new ArrayList<String>());
            
            array.add(sub);
            reportedMap.put(obj,array);

            //신고된 사람 : 카운트
            Integer count = reportedCount.getOrDefault(obj, 0)+1;
            reportedCount.put(obj, count);
            
            if(count >= k)
            {
                reportResult.add(obj);
            }
        }

        //신고된 유저
        for(String reportedUser : reportResult)
        {
            //신고한 유저
            ArrayList<String> reportUsers = reportedMap.get(reportedUser);
            //신고한 유저 리스트
            if(reportUsers == null)
            {
                continue;
            }

            //신고한 유저
            for(String reportUser : reportUsers)
            {
                for(int i=0;i<id_list.length;i++)
                {
                    String id = id_list[i];
                    if(id.equals(reportUser))
                    {
                        answer[i]++;
                    }
                }
            }                
        }

        return answer;
    }
}