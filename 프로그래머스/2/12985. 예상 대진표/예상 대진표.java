class Solution
{
    //12 34 56 78 
    public int solution(int n, int a, int b)
    {
        int answer = 0;
        int c = a;
        a = Math.min(a,b);
        b = Math.max(c,b);
        if(a == 1 && b ==2){
            return 1;
        }
        
        while(a != b){
            a = (a + 1) / 2;
            b = (b + 1) / 2;
            answer++;
        }
        
        return answer;
    }
}