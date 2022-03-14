class Solution {
    public boolean solution(int[][] key, int[][] lock) 
    {
        int dupliOffset = key.length-1;
        int n = key.length;
        
        for(int x=0;x<dupliOffset + lock.length;x++)
        {
            for(int y=0;y<dupliOffset + lock.length;y++)
            {
                for(int r=0;r<4;r++)
                {
                    int[][] expandedLock = new int[lock.length + n * 2][lock.length + n * 2];
                    for(int i=0;i<lock.length;i++)
                    {
                        for(int j=0;j<lock.length;j++)
                        {
                            expandedLock[i+dupliOffset][j+dupliOffset] = lock[i][j];
                        }
                    }
                    fillKey(expandedLock,key,x,y,r);                
                    if(check(expandedLock,dupliOffset,n))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private void fillKey(int[][] expandedLock,int[][] key,int x,int y,int r )
    {
        int n = key.length;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(r == 0)
                {
                    expandedLock[i+x][j+y] += key[i][j];
                }
                if(r == 1)
                {
                    expandedLock[i+x][j+y] += key[j][n-i-1];
                }
                if(r == 2)
                {
                    expandedLock[i+x][j+y] += key[n-i-1][n-j-1];
                }
                if(r == 3)
                {
                    expandedLock[i+x][j+y] += key[n-j-1][i];
                }
                
            }
        }
    
    }
    private boolean check(int[][] expandedKey,int dupliOffset,int n)
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(expandedKey[i+dupliOffset][j+dupliOffset] != 1)
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    
}
