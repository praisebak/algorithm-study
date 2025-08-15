class Solution {
    char[] tmp = new char[]{'A','B','C','D','E','F'};
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        int num = 0;
        
        p--;
        int curTurn = 0;

        StringBuilder sb = new StringBuilder();

        
        while(true){
            //숫자 가져오기 
            String curNumber = getNumber(num++,n);
            //숫자만큼 더하면서
            for(int j=0;j<curNumber.length();j++){
                if(curTurn == p){
                    sb.append(curNumber.charAt(j));
                }
                curTurn++;
                curTurn = (curTurn % m);
                if(sb.length() == t){
                    return sb.toString();
                }
            }            
        }
        
    }
    
    public String getNumber(int num,int n){
        if(num == 0){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while(num != 0){
            if(num % n >= 10){
                char next = tmp[(num % n) - 10];
                sb.append(next); 
            }else{
                sb.append(num % n);                
            }
            
            num = num / n;
        }
    
        return sb.reverse().toString();
    }
}