class p77484 {
    
    static boolean isThereAnswer(int[] win_nums,int val)
    {
        for(int i=0;i<win_nums.length;i++)
        {
            if(win_nums[i] == val)
            {
                return true;
            }
        }
        return false;
    }
    
    
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int maxWinRate = 7;
        int minWinRate = 7;
        for(int i=0;i<6;i++)
        {
            if(lottos[i] == 0)
            {
                maxWinRate--;
                
            }
            else
            {
                if(isThereAnswer(win_nums,lottos[i]))
                {
                    maxWinRate--;
                    minWinRate--;
                }
            }
                
        }
        if(maxWinRate >= 6)
        {
            maxWinRate = 6;
        }
        if(minWinRate >= 6)
        {
            minWinRate = 6;
        }
        answer[0] = maxWinRate;
        answer[1] = minWinRate;
        
        return answer;
        
        
        
        
    }
}