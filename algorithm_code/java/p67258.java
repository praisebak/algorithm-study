import java.util.HashMap;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int l = 0;
        int r = 0;        
        int min = Integer.MAX_VALUE;
        int jewelType = 0;
        HashMap<String,Integer> map = new HashMap<>();

        for(String str : gems)
        {
            map.put(str, 0);
        }

        while(l < gems.length && r < gems.length)
        {
            String gem = gems[r];
            int curGemCount = map.put(gem, map.getOrDefault(gem, 0) + 1) + 1;
            if(curGemCount == 1)
            {
                jewelType++;
            }

            if(jewelType == map.size())
            {
                
                String subGem = gems[l];
                int subVal = map.put(subGem,map.get(subGem)-1)-1;
                int subValR = map.put(gem,curGemCount-1)-1;
                if(subVal == 0)
                {
                    jewelType--;
                }
                if(subValR == 0)
                {
                    jewelType--;
                }
                if(min > r - l)
                {
                    min = r - l;
                    answer[0] = l+1;
                    answer[1] = r+1;
                    if(l == r)
                    {
                        break;
                    }
                }
                l++;
            }
            else
            {
                r++;
            }
        }


        return answer;
    }
}

// public static void main(String[] args) {
//     Solution solution = new Solution();

// } p67258 {
    
//}
