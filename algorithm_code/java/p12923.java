import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    int MAX = 10000000;
    public int[] solution(long begin2, long end2) {
        int begin = (int) begin2;
        int end = (int) end2;
        int answer[] = new int[end - begin + 1];

        for(int i=begin;i<=end;i++)
        {
            answer[i-begin] = getMinor(i);
        }

        return answer;
    }

    private int getMinor(int num) 
    {
        if(num == 1)
        {
            return 0;
        }
        for(int i=2;i<=Math.sqrt(num);i++)
        {
            if(num % i == 0 && (num/i) <= MAX/2)
            {
                return num/i;
            }

        }
        return 1;
    }

    //효율성 통과 실패 아리스토텔레스의 채 이용
    public int[] mySolution(long begin2, long end2) {

        int begin = (int) begin2;
        int end = (int) end2;

		int n = (int) ((end - begin)+1);
		ArrayList<Integer> primeList = new ArrayList<>();

		// n <= 1 일 때 종료
		if(n > 1) 
        {
            primeList.add(-1);
            if(begin == 1)
                primeList.add(0);
            else
                primeList.add(1);

            for(long i=begin+1; i<=end; i++ )
                primeList.add(1);
            
            for(long i=2; i<=end/2; i++){
                for(int startNum = (int) (begin - (begin % i)); startNum<=end; startNum+=i) 
                {
                    int arrayIdx = (int) (startNum - begin+1);
                    if(arrayIdx <= 0)
                        continue;
                    
                    //2는 2번째 블록에 들어가지 않는다
                    if(i == startNum)
                        continue;

                    if(startNum >= 2 && startNum <= end)
                    {
                        primeList.set((int) (arrayIdx),(int) i);
                    }
                }
            }
        }
        primeList.remove(0);
        int[] answer = primeList.stream().mapToInt(i -> i).toArray();
        return answer;
    }

}
