import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = {};
        Map<String,String> ref = new HashMap<>();
        
        for(int i=0;i<enroll.length;i++){
            String e = enroll[i];
            String r = referral[i];
            //enroll은 referral에게 추천받았다.
            ref.put(e,r); 
        }
        
        Map<String,Integer> idxMap = new HashMap<>();
        for(int i=0;i<enroll.length;i++){
            idxMap.put(enroll[i],i);
        }
        
        Map<String,Integer> result = new HashMap<>();
        
        for(int i=0;i<seller.length;i++){
            String sellerName = seller[i];
            int money = amount[i] * 100;
            //돈가져았고 내 ref한테 돈주자
            while(ref.get(sellerName) != null){
                int giveMoney = money / 10;
                
                money -= giveMoney;
                String refName = ref.get(sellerName);

                result.put(sellerName,result.getOrDefault(sellerName,0) + money); 
                if(giveMoney == 0){
                    break;
                }
                money = giveMoney;
                sellerName = refName;
            }
        }
        

        int[] newResult = new int[enroll.length];
        for(int i=0;i<enroll.length;i++){
            String enrollName = enroll[i];
            newResult[i] = result.getOrDefault(enrollName,0);
        }
        
        return newResult;
    }
}