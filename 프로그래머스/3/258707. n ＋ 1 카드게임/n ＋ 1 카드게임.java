import java.util.*;
class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 0;

        Set<Integer> set = new HashSet<>();
        Set<Integer> discard = new HashSet<>();
        
        int n = cards.length;
        for(int i=0;i<n / 3;i++){
            set.add(cards[i]);
        }
        
        int obj = n+1;
        int count = 1;
        
        for(int i=n/3;i<cards.length;i+=2){
            discard.add(cards[i]);
            discard.add(cards[i+1]);
                
            boolean isNext = false;

            List<Integer> values = new ArrayList<>(set);
            
            for(int num : values){
                if(set.contains(obj - num)){
                    set.remove(obj-num);
                    set.remove(num);
                    isNext = true;
                }else if(discard.contains(obj-num) && coin >= 1){
                    set.remove(num);
                    discard.remove(obj-num);
                    coin--;
                    isNext = true;
                }
                if(isNext){
                    break;
                }
            }

            if(!isNext){
                List<Integer> dValues = new ArrayList<>(discard);

                if(coin >= 2){
                    for(int num : dValues){
                        if(discard.contains(obj - num)){
                            discard.remove(num);
                            discard.remove(obj -num);
                            isNext = true;
                            coin -=2;                            
                            break;    
                        }
                    }

                }
            }
            
            if(!isNext){
                break;
            }
            
            count++;
        }

        return count;
    }
}