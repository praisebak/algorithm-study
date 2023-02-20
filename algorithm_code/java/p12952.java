
class Solution {
    int calResult = 0;
    boolean[][] arr;
    boolean[] visitCal;
    boolean check(int depth,int cal)
    {
        if(depth == 1)
        {
            return true;
        }

        int row = depth-2;
        int unit = 1;
        while(row >= 0) 
        {  
            if(cal-unit >= 0)
            {   
                if(arr[row][cal-unit])
                {
                    return false;
                }
            }
            if(cal+unit < arr.length)
            {
                if(arr[row][cal+unit])
                {
                    return false;
                }
            }
            unit++;
            row--;
        }
        return true;
    }

    void nQueen(int n,int depth,int prevCal)
    {
        if(!check(depth, prevCal))
        {
            return;
        }

        if(depth == n)
        {
            calResult++;
            return;
        }

        int row = depth;
        for(int i=0;i<n;i++)
        {
            if(!visitCal[i])
            {
                arr[row][i] = true; 
                visitCal[i] = true;
                nQueen(n,depth+1,i);
                visitCal[i] = false;
                arr[row][i] = false;
            }
        }
    }

    public int solution(int n) {
        arr = new boolean[n][n];
        visitCal = new boolean[n];
        for(int i=0;i<n;i++)
        {
            arr[0][i] = true;
            visitCal[i] = true;
            nQueen(n, 1,i);
            visitCal[i] = false;
            arr[0][i] = false;
        }
        return calResult;
    }
}