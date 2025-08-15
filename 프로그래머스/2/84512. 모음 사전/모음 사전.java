class Solution {
    char[] chs = new char[]{'A','E','I','O','U'};
    //5^5 + 4^5 + 3^5 + 2^5 +1 ^5
    public int solution(String word) {

        dfs(0,"",word);
        
        return answer;
    }
    
    int curCount = 0;
    int answer = 0;
    public void dfs(int len,String str,String word){
        if(str.equals(word)){
            answer = curCount;
            return;
        }
        if(len == 5){
            return;
        }
        
        for(int i=0;i<5;i++){
            curCount++;
            dfs(len+1,str + chs[i],word);
        }
    }
}