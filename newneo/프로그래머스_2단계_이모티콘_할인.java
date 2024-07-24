import java.util.*;


// #디버깅하고 데이터베이스 쿼리 최적화 계획 세우기 (sql 등등 보고 group by를 어떻게 한다던가)
// # 캐싱을 한다던가 - redis를 써야한다?
// # 그거하고 이력서쓰고 ㅇㅇ    
class DiscountInfo{
    int idx;
    int discount;
    public DiscountInfo(int idx,int discount){
        this.idx = idx;
        this.discount = discount;
    }
}


class Solution {
    private List<String> discountStringList = new ArrayList<>();
        
    private void getDiscountInfo(int size,int depth,String s){
        if(depth == size){
            discountStringList.add(s);
            return;
        }
        
        for(int i=10;i<=40;i+=10){
            getDiscountInfo(size,depth+1,s + " " + i);    
        }
    }

    public void print(String[] s,String text){
    
        if(s[0].equals("40") && s[1].equals("25") && s.length == 2){
            System.out.println("----start----");
            

                
            System.out.println(text);
            System.out.println("----end----");
        }
    }
    
    
    //각 사용자들은 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매합니다.
    //각 사용자들은 자신의 기준에 따라 이모티콘 구매 비용의 합이 일정 가격 이상이 된다면, 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입합니다.
    public int[] solution(int[][] users, int[] emoticons) {        
        getDiscountInfo(emoticons.length,0,"");
        int maxPlusServiceUser = 0;
        int maxSails = 0;
        
        
        for(String s : discountStringList){
            String[] discountList = s.trim().split(" ");
            int curPlusServiceUser = 0;
            int sails = 0;    
            String tmpStr = "";
            
            for(int userIdx=0;userIdx<users.length;userIdx++){
                int percentage = users[userIdx][0];
                int leftMoney = users[userIdx][1];
                int curUserSails = 0;
                for(int i=0;i<discountList.length;i++){

                    int discount = Integer.parseInt(discountList[i]); // 할인율 (퍼센트로 입력된 값)

                    
                    
                    int price = emoticons[i] * (100 - discount) / 100; // 할인율 적용

                    

                    
                    tmpStr = tmpStr  + price + " ";
                    if(percentage <= discount){
                        leftMoney -= price;
                        if(leftMoney <= 0){
                            curPlusServiceUser++;
                            curUserSails = 0;
                            break;
                        } 
                        curUserSails += price;
                    }
                }    
                if(leftMoney <= 0){
                    
                }
                
                sails += curUserSails;
            }   

            if(maxPlusServiceUser == curPlusServiceUser){
                maxSails = Math.max(sails,maxSails);   
            }else if(maxPlusServiceUser < curPlusServiceUser){
                maxPlusServiceUser = curPlusServiceUser;
                maxSails = sails; 
            }
        }

        
        int[] answer = {maxPlusServiceUser,maxSails};
        return answer;
    }
}
