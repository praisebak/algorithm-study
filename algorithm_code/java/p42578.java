import java.util.HashMap;
import java.util.Map.Entry;
public class p42578 {
    
    public static int solution(String[][] clothes) {
        String cloth;
        String key;
        HashMap <String,String> map = new HashMap<String,String>();
        for(int i=0;i<clothes.length;i++)
        {
            cloth = clothes[i][0];
            key = clothes[i][1];
            map.put(key, cloth);
        }

        for(Entry<String,String> entry : map.entrySet())
        {
            System.out.println(entry.getKey());
            
        }

        int answer = 0;
        return answer;
    }

    public static void main(String[] args)
    {
        String[][] testCase = {{"crowmask","face"},{"bluesunglasses","face"}};
        solution(testCase);
    }
    

}
