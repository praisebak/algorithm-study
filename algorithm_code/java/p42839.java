import java.util.HashMap;
public class p42839 {
    static HashMap<Integer,Boolean> primeCheckMap = new HashMap<Integer,Boolean>();
    static boolean visit[] = new boolean[7];    
    static int count = 0;
    public static int solution(String numbers) {

        for(int i=0;i<numbers.length();i++)
        {
            visit[i] = true;
            setFromBacktracking(numbers, "", i);
            visit[i] = false;
        }  

        return count;
        
    }

    
    private static void setFromBacktracking(String numbers, String prevStr,int idx) 
    {
        String curStr = prevStr + numbers.charAt(idx);
        visit[idx] = true;
        if(isPrimeNum(curStr))
        {
            int curStrInt = Integer.parseInt(curStr);
            if(primeCheckMap.get(curStrInt) == null)
            {
                primeCheckMap.put(curStrInt, true);
                count++;
            }
        }
        for(int i=0;i<numbers.length();i++)
        {
            if(!visit[i])
            {
                setFromBacktracking(numbers, curStr, i);
            }
        }

        visit[idx] = false;

    }




    private static boolean isPrimeNum(String curStr) {
        int num = Integer.parseInt(curStr);
        for(int i=2; i<=num/2; i++){
            if(num % i == 0) return false;
        }
        if(num <= 1)
        {
            return false;
        }
        
        return true;
    }


    public static void main(String[] args) {
        System.out.println(solution("011"));
        
    }        

}
