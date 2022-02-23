
class Solution 
{


    public long[] solution(long[] numbers) 
    {
        long[] answer = new long[numbers.length];
        for(int i=0;i<numbers.length;i++)
        {
            if(numbers[i] % 2 == 0)
            {
                answer[i] = numbers[i] + 1;
            }
            else
            {  
                String tmpStr = Long.toBinaryString(numbers[i]);
                StringBuffer sb;
                if(tmpStr.chars().filter(c -> c == '1').count() == tmpStr.length())
                {
                    tmpStr = "10" + tmpStr.substring(1);
                    sb = new StringBuffer(tmpStr);
                }
                else
                {
                    int lastZeroIdx = tmpStr.lastIndexOf("0");
                    int lastZeroNextFirstOne = tmpStr.indexOf("1", lastZeroIdx); 
                    sb = new StringBuffer(tmpStr);
                    sb = sb.replace(lastZeroIdx,lastZeroIdx+1 , "1");
                    sb = sb.replace(lastZeroNextFirstOne,lastZeroNextFirstOne+1 , "0");
                }
                answer[i] = Long.parseLong(sb.toString(),2);

                
            }

        }


        return answer;
    }
}