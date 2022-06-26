class Solution {
    public int solution(int n) {
        int answer = 0;
        int mod = 1000000007;
        long[] arr = new long[5001];
        arr[2] = 3;
        arr[4] = 11;
        long tmp = 0;
        
        for(int i=6;i<=n;i+=2)
        {
            tmp += (arr[i-4] * 2);
            arr[i] = (arr[i-2] * 3 + tmp + 2) % mod;
        }
        
        answer = (int)arr[n];
        return answer;
    }
}