class Solution
{
    public static int solution(int [][]board)
    {
        int answer = board[0][0];
        int r = board.length;
        int c = board[0].length;
        for(int i=1;i<r;i++)
        {
            for(int j=1;j<c;j++)
            {
                if(board[i][j] == 1)
                {
                    board[i][j] = Math.min(board[i-1][j],board[i-1][j-1]);
                    board[i][j] = Math.min(board[i][j-1],board[i][j]) + 1;
                    answer = Math.max(answer,board[i][j]);
                }

            }
        }   
        return (int)Math.pow(answer,2);
    }
    
    
    
}