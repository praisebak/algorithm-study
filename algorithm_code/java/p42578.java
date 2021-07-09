import java.util.HashMap;
import java.util.Map.Entry;
public class p42578 {
    
    public static int solution(String[][] clothes) {
        String key;
        HashMap <String,Integer> map = new HashMap<String,Integer>();
        
        for(int i=0;i<clothes.length;i++)
        {
            key = clothes[i][1];
            map.put(key, map.getOrDefault(key, 0)+1);
        }

        int answer = 1;
        for(Entry<String,Integer> entry : map.entrySet())
        {
            answer *= entry.getValue() + 1;
        }
        return answer - 1;
    }

    public static void main(String[] args)
    {
        String[][] testCase = {{"crowmask","face"},{"bluesunglasses","face"}};
        solution(testCase);
    }
    

}
