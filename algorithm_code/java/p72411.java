


    import java.util.*;

    import javax.sound.sampled.SourceDataLine;

    //map을 course별로 초기화해야지 정답이 된다라
    //애초에 문제를 어떻게 접근??

    class Solution {
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        boolean visit[];

        

        public String[] solution(String[] orders, int[] course) 
        {
            String[] answer;
            List<String> list = new ArrayList<>();
            
            for(int k=0; k< course.length;k++)
            {
                map = new HashMap<>();

                for(int j=0; j < orders.length;j++)
                {
                    String curOrder = orders[j];
                    dfs(curOrder,"",0,course[k],0);
                }
                int max = -1;
                for(String key : map.keySet()) {
                    max = Math.max(max,map.get(key));
                }

                for (String key : map.keySet()) {
                    if (map.get(key) >= 2)
                    {
                        if (max == map.get(key))
                        {
                            list.add(key);  
                        } 
                    }
                        
                }
            }
            list.sort(Comparator.naturalOrder());
            return list.toArray(new String[list.size()]);
        }

        private void dfs(String curOrder,String str,int startIndex,int maxDepth,int curDepth)
        {
            if(curDepth == maxDepth)
            {
                char[] tmpStr = str.toCharArray();
                Arrays.sort(tmpStr);
                str = String.valueOf(tmpStr);
                map.put(str,map.getOrDefault(str, 0)+1);
                return;
            }
            for(int i=startIndex;i<curOrder.length();i++)
            {
                String tmpStr = str + curOrder.charAt(i) + "";
                dfs(curOrder,tmpStr,i+1,maxDepth,curDepth+1);
            }
        
        }
        public static void main(String [] args)
        {
            Solution solution = new Solution();
            String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
            int[] course = {2,3,5};
            String[] answer = solution.solution(orders, course);
            for(int i=0;i<answer.length;i++)
            {
                System.out.println(answer[i]);
            }
        }
    }