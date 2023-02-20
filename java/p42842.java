public class p42842 {
    public int[] solution(int brown, int yellow) 
    {
        int[] answer = new int[2];
        int curCal = 3;
        while(true)
        {
            int tmpRow = 1;
            int tmpBrown = 0;
            int tmpYellow = 0;

            while(true)
            {
                //첫줄이거나 yellow가 다 충족된 경우 마지막줄임
                if(tmpRow == 1 || tmpYellow >= yellow)
                {
                    tmpBrown += curCal;
                    if(tmpYellow >= yellow)
                    {
                        break;
                    }
                    
                }
                else
                {
                    tmpBrown+=2;
                    tmpYellow += curCal - 2;
                }
                tmpRow++;
            }
            if(brown == tmpBrown &&  tmpYellow == yellow)
            {
                answer[0] = tmpRow;
                answer[1] = curCal;
                break;
            }
            curCal++;
        }
        return answer;
    }    

}
