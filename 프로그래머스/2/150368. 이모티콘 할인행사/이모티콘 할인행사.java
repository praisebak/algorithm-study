class Solution {
    
    public void bfs(int len){
        if(len >= N){    
            doCal();
            return;
        }
        
        for(int i=10;i<=40;i+=10){
            applys[len] = i;
            bfs(len+1);
        }
    }
    
    int N;
    int[] applys;

    int answerSub = 0;
    int answerSell = 0;
    
    public void doCal(){
        int sub = 0;
        int sell = 0;
        
        for(int j=0;j<users.length;j++){
            int apply = users[j][0];
            int objMoney = users[j][1];
            int curSum = 0;

            
            for(int i=0;i<N;i++){
                int curApply = applys[i];
                if(apply > curApply) continue;

                int num = emoticons[i] * (100 - curApply) / 100;
                
                curSum += num;
            }
            
            
            if(curSum >= objMoney){
                curSum = 0;
                sub++;
            }

            sell += curSum;
            
            
        }        
        if(sub > answerSub){
            answerSub = sub;
            answerSell = sell;
        }else if(sub == answerSub && sell > answerSell){
            answerSell = sell;
        }
    }
    
    int[][] users;
    int[] emoticons;
    public int[] solution(int[][] users, int[] emoticons) {
        
        N = emoticons.length;
        applys = new int[N];
        this.emoticons=emoticons;
        this.users = users;
        
        bfs(0); 
        
        return new int[]{answerSub,answerSell};
    }
    
    public int[] apply(int[] emoticons,int apply){
        int[] result = new int[emoticons.length];
        for(int i=0;i<result.length;i++){
            int num = emoticons[i] * (apply / 100);
            result[i] = emoticons[i] - num;    
        }
        return result;
    }
}