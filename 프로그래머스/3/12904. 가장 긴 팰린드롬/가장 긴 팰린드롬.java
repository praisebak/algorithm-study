class Solution
{
    public int solution(String s)
    {
        int answer = 1;
        
        
        for (int start = 0; start < s.length(); start++) {
            for (int end = start; end < s.length(); end++) {
                if (isPalindrome(s, start, end)) {
                    answer = Math.max(answer, end - start + 1);
                }
            }
        }

        return answer;
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start++) != s.charAt(end--)) return false;
        }
        return true;
    }
}