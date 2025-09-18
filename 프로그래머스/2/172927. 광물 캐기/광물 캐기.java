class Solution {
    int[] curPicks;
    int answer = Integer.MAX_VALUE;
    String[] minerals;
    public int solution(int[] picks, String[] minerals) {
        this.minerals=  minerals;
        int sum = 0;
        for(int i=0;i<3;i++){
            sum += picks[i];
        }
        
        int maxLen = Math.min(minerals.length / 5,sum-1);
        this.curPicks = new int[maxLen+1]; 

        bfs(0,maxLen,picks);
        
        return answer;
    }
    
    public void cal(){
        int idx = 0;
        int result = 0;
        int count = 0;
        
        for(String s : minerals){
            if(idx >= curPicks.length){
                break;
            }
            int curPick = curPicks[idx];
            
            count++;
            if(count != 0 && count % 5 == 0){
                idx++;   
            }
            result += calMine(curPick,s);
        }
        
        answer = Math.min(result,answer);
    }
    
    public int calMine(int curPick,String s){
        if(curPick == 0){
            return 1;
        }
        if(curPick == 1 ){
            if(s.equals("diamond")){
                return 5;
            }
            return 1;
        }
        

        if(s.equals("diamond")){
            return 25;
        }else if(s.equals("iron")){
            return 5;
        }
        return 1;
    }
    
    public void bfs(int curLen,int minLen,int[] picks){
        if(curLen > minLen){
            cal();
            return;
        }
        
        for(int i=0;i<3;i++){
            if(picks[i] != 0){
                picks[i]--;
                curPicks[curLen] = i;
                bfs(curLen+1,minLen,picks);
                picks[i]++;
            }
        }        
    }
}