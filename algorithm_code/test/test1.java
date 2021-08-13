class Solution
{
    public int solution(int money, int minratio, int maxratio, int ranksize, int threshold, int months)
    {
        int curratio = 0;
        for(int i=0;i<months;i++)
        {
            money = (money/100) * 100;
            curratio = getratio(money);            
            if(!(maxratio >= curratio))
            {

            }





        }

        int answer = -1;
        
        return answer;

    }

    private int getratio(int money,int threshold,int ranksize) {
        int min = 0;
        int minCal = threshold;
        int maxCal = threshold + ranksize-1;
        if(money < threshold)
        {
            return 0;
        }
        else
        {
            while(1)
            {   
                leftCal = leftCal + ranksize;
                rightCal = rightCal + ranksize;




            }
            
        }



        return 0;
    }
}