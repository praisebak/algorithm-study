import java.util.HashMap;
import java.util.HashSet;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int l = 0;
        int r = 0;        
        int curMinSize = Integer.MAX_VALUE;

        HashMap<String,Integer> gemMap = new HashMap<>();
        HashSet<String> gemSet = new HashSet<>();

        for(String gem : gems)
        {
            gemSet.add(gem);
        }
        
        while(true)
        {
            if(gemSet.size() == gemMap.size())
            {
                System.out.println("a1");
                int val2 = gemMap.get(gems[l]);
                gemMap.put(gems[l],val2-1);
                if(val2-1 == 0)
                {
                    gemMap.remove(gems[l]);
                }
                l++;
            }
            //가지수가 부족할 때 r 증가
            else if(r == gems.length)
            {
                break;
            }
            else 
            {
                gemMap.put(gems[r], gemMap.getOrDefault(gems[r], 0) + 1);
                r++;
            }

             if(gemSet.size() == gemMap.size())
            {
                System.out.println("a2");
                System.out.println();
                if(curMinSize > r-l)
                {
                    curMinSize = r - l;
                    answer[0] = l+1;
                    answer[1] = r;
                }
            }
        }

        return answer;
    }
}

/* 
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int l = 0;
        int r = 0;        
        int curMinSize = Integer.MAX_VALUE;
        int nonDupliJewel = 0;

        HashMap<String,Integer> gemMap = new HashMap<>();
        for(String gem : gems)
        {
            gemMap.put(gem, 0);
        }
        
        while(true)
        {
            int val = gemMap.get(gems[r]);
            if(val == 0)
            {
                nonDupliJewel++;
            }
            gemMap.put(gems[r],val+1);

            if(nonDupliJewel == gemMap.size())
            {
                int val2 = gemMap.get(gems[l]);
                gemMap.put(gems[l],val2-1);
                if(val2-1 == 0)
                {
                    nonDupliJewel--;
                }
                l++;
            }
            //가지수가 부족할 때 r 증가
            else if(r == gems.length)
            {
                break;
            }
            else if(nonDupliJewel < gemMap.size())
            {
                r++;
            }
            if(nonDupliJewel == gemMap.size())
            {
                if(curMinSize > r-l)
                {
                    curMinSize = r - l;
                    answer[0] = l+1;
                    answer[1] = r+1;
                }
            }
        }

        System.out.println(answer[0] + "," + answer[1]);
        return answer;
    }
}

*/