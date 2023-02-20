import java.util.*;

class Pair implements Comparable<Pair>
{
    Integer id;
    Integer value;
    Pair(Integer id,Integer value)
    {
        this.id = id;
        this.value = value;   
    }
    @Override
    public int compareTo(Pair o) {
        Pair obj = (Pair) o;
        if(value > obj.value)
        {
            return -1;
        }else if(value == obj.value)
        {
            return obj.id > id ? 1 : -1;
        }else
        {
            return 1;
        }
    }
}   



class Solution {
    HashMap<String,Integer> map = new HashMap<>();

    public int[] solution(String[] genres, int[] plays) 
    {
        List<Map.Entry<String,Integer>> entryList = new LinkedList<>();
        ArrayList<Integer> ansList = new ArrayList<>();
        for(int i=0;i<genres.length;i++)
        {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }
        entryList =  new LinkedList<>(map.entrySet());
        entryList.sort(Map.Entry.comparingByValue());
        Collections.reverse(entryList);
        

        for(Map.Entry<String,Integer> map : entryList)
        {
            String key = map.getKey();
            ArrayList<Pair> tmpList = new ArrayList<>();
            
            for(int i=0;i<plays.length;i++)
            {
                if(genres[i].equals(key))
                {
                    tmpList.add(new Pair(i,plays[i]));
                }
            }
            Collections.sort(tmpList);
            int count = 0;
            for(Pair pair : tmpList)
            {
                if(count == 2)
                {
                    break;
                }
                ansList.add(pair.id);
                count++;
            }
        }
        
        
        
        
        int[] answer = ansList.stream().mapToInt(i -> i).toArray();
        return answer;
    }
}