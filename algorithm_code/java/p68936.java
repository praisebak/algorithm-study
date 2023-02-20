class Solution 
{
    int zeroCount = 0;
    int oneCount = 0;
    public int[] solution(int[][] arr) 
    {
        int startRow = 0;
        int startCal = 0;
        recursiveQuadCal(arr,startRow,startCal,arr.length);
        int[] answer = new int[2];
        answer[0] = zeroCount;
        answer[1] = oneCount;

        return answer;
    }

    private void recursiveQuadCal(int[][] arr, int startRow, int startCal, int length) 
    {
        if(isAllSameNum(arr,startRow,startCal,length))
        {
            if(arr[startRow][startCal] == 0)
            {
                zeroCount++;
            }
            else
            {
                oneCount++;
            }
        }
        else
        {
            recursiveQuadCal(arr, startRow, startCal, length/2);
            recursiveQuadCal(arr, startRow, startCal + length/2, length/2);
            recursiveQuadCal(arr, startRow + length/2, startCal, length/2);
            recursiveQuadCal(arr, startRow + length/2, startCal + length/2, length/2);
        }

    }

    private boolean isAllSameNum(int[][] arr, int startRow, int startCal, int length) {
        int originVal = arr[startRow][startCal];
        for(int i=startRow;i<startRow+length;i++)
        {
            for(int j=startCal;j<startCal+length;j++)
            {
                if(originVal != arr[i][j])
                {
                    return false;
                }
            }
        }
        return true;
    }
}