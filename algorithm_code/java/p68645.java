import java.util.ArrayList;

class Solution68645 
{
    //아래 오른쪽 왼대각선위
    int[] dx = {0,1,-1};
    int[] dy = {1,0,-1};
    int[][] arr;
    public int[] solution(int n) 
    {
        ArrayList<Integer> list = new ArrayList<>();

        triSnail(n);
        int count = 0;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(arr[i][j] == 0)
                {
                    continue;
                }
                list.add(arr[i][j]);
            }
        }
        
        return list.stream().mapToInt(i -> i).toArray();
    }

    private void triSnail(int n) 
    {
        int moveLeft = n;
        arr = new int[n][n];
        int moveX = 0;
        int moveY = -1;
        int idx = 0;
        int count = 1;
        int directionLeft = n;
        while(moveLeft != 0)
        {
            moveX += dx[idx];
            moveY += dy[idx];
            arr[moveY][moveX] = count++;
            directionLeft--;
            if(directionLeft == 0)
            {
                moveLeft--;
                idx = (idx + 1) % 3;
                directionLeft = moveLeft;
            }
        }
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }



    }



}