class Solution {
    public int solution(int n, int k) {
        String cn = convertToN(n,k);
        
        String[] sArr = cn.split("0");
        
        int answer = 0;
        
        for(String s : sArr){
            if(s.equals("")) continue;
                        
            long num = Long.parseLong(s);

            
            if(!isPrime(num)) continue;

            answer++;
        }
        
        return answer;
    }
 
    public boolean isPrime (long a){
        if(a<2) return false;
        
        for(int i=2;i<=Math.sqrt(a);i++){
            if(a%i==0){
                return false;
            }
        }
        return true;
    }
    
    public String convertToN(int n,int k){

        StringBuilder sb = new StringBuilder();
        while(n != 0){
            int leftNum = n % k;
            n = n / k;
            sb.append(leftNum);
        }
        
        return sb.reverse().toString();
    }
}