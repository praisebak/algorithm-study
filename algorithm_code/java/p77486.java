import java.util.HashMap;
import java.util.Set;

class Solution{//p77486 {
    
    static int ans[];
    static HashMap<String,Integer> enrollIdxMap = new HashMap<String,Integer>();

    static boolean isDivideAble(int money)
    {
        if(money / 10 >= 1)
        {
            return true;
        }
        return false;
    }

    static void divideToRefer(String curSeller, int curMoney,String[] enroll,String[] referral)
    {
        
        int enrollIdx = getIdxFromEnroll(curSeller);
        if(enrollIdx == -1)
        {
            return;
        }
        if(curMoney / 10 < 1)
        {
            ans[enrollIdx] += curMoney;
        }
        else
        {
            ans[enrollIdx] += curMoney - curMoney / 10;
            divideToRefer(referral[enrollIdx],curMoney / 10,enroll,referral);
        }
    }


    static int getIdxFromEnroll(String curSellerString)
    {
        return enrollIdxMap.get(curSellerString);
    }

    static void setEnrollIdxMap(String[] enroll)
    {  
        for(int i=0;i<enroll.length;i++)
        {
            enrollIdxMap.put(enroll[i],i);
        }
        enrollIdxMap.put("-",-1);
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        //각 enroll이 받은 수익금
        int[] answer = new int[enroll.length];
        ans = new int[enroll.length];
        setEnrollIdxMap(enroll);
        for(int i=0;i<seller.length;i++)
        {
            int curMoney = amount[i] * 100;
            divideToRefer(seller[i], curMoney, enroll, referral);
        }
        answer = ans;
        return answer;
    }


    










}