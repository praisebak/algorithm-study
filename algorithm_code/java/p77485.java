class Solution {
    static int[][] getInput(int rows,int columns)
    {
        int count = 1;
        int arr[][] = new int[rows+1][columns+1];
        for(int i=1;i<=rows;i++)
        {
            for(int j=1;j<=columns;j++)
            {
                arr[i][j] = count++;
            }
        }
        return arr;
    }
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int startRow,startCal,endRow,endCal;
        int arr[][] = getInput(rows,columns);
         
        for(int i=0;i<queries.length;i++)
        {
            startRow = queries[i][0];
            startCal = queries[i][1];
            endRow = queries[i][2];
            endCal = queries[i][3];
            //오른쪽으로
            int curCal,curRow = 0;
            int tmp = 0;
            int prev = arr[startRow][startCal];
            int min = 987654321;
            for(curCal = startCal; curCal<=endCal-1;curCal++)
            {
                min = Math.min(min,prev);
                tmp = arr[startRow][curCal+1];
                arr[startRow][curCal+1] = prev;
                prev = tmp;
            }

            //시작 row에서 맨 마지막칸 이전값 저장됨

            for(curRow = startRow; curRow<=endRow-1;curRow++)
            {
                min = Math.min(min,prev);
                tmp = arr[curRow+1][curCal];
                arr[curRow+1][curCal] = prev;
                prev = tmp;
            }
            
            for(;curCal>=startCal+1;curCal--)
            {
                min = Math.min(min,prev);
                tmp = arr[curRow][curCal-1];
                arr[curRow][curCal-1] = prev;
                prev = tmp;
            }
            
            for(;curRow>=startRow+1;curRow--)
            {
                min = Math.min(min,prev);
                tmp = arr[curRow-1][curCal];
                arr[curRow-1][curCal] = prev; 
                prev = tmp;
            }

            answer[i] = min;
        }
        return answer;
    }
}