import java.util.Arrays;

class p42747
{
    public static int solution(int[] citations) {
        int answer = 0;
        int tmp[] = citations.clone();
        int count = 0;
        Arrays.sort(tmp);
        
        for(int i=tmp.length-1;i >= 0; i--)
        {
            if(tmp[i] != 0)
            {
                if(tmp[i] > count)
                {
                    count++;
                    answer++;
                }
            }
        }

        return answer;
    }

    public static void main(String []args)
    {
        int testCase[] = {2,2,2,2,2};
        System.out.println(solution(testCase));

    }
}