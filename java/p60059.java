<<<<<<< HEAD
public 
=======
//re solve
>>>>>>> dff76fc01f3e961883add20f65c50a3938079658
class Solution
{
    boolean solution(int[][] key,int[][] lock)
    {
        int offset = key.length-1;
        for(int row=0;row<offset + lock.length;row++)
        {
            for(int c=0;c<offset + lock.length;c++)
            {
                for(int rot = 0; rot < 4;rot++)
                {
                    int[][] expandedArr = new int[lock.length + key.length * 2][lock.length + key.length * 2];
                    
                    for(int i=0;i<lock.length;i++)
                    {
                        for(int j=0;j<lock.length;j++)
                        {
                            expandedArr[offset+i][offset+j] = lock[i][j];
                        }
                    }

                    fillKey(expandedArr,row,c,rot,key);
                    if(check(expandedArr,offset,lock.length))
                    {
                        return true;
                    }
                }
            }

        }
        System.out.println("false");
        return false;
    }

    private boolean check(int[][] expandedArr, int offset,int len) 
    {
        
        for(int i=0;i<len;i++)
        {
            for (int j = 0; j < len; j++) 
            {
                if(expandedArr[i+offset][j+offset] != 1)
                {
                    return false;
                }
            }  
            System.out.println();
        }
        return true;

    }

    private void fillKey(int[][] expandedArr, int row, int c, int rota,int[][] key) 
    {
        int n = key.length;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(rota == 0){
                    expandedArr[i+row][j+c] += key[i][j];       
                }else if(rota == 1 ){
                    expandedArr[i+row][j+c] += key[j][n-i-1];
                }else if(rota == 2){
                    expandedArr[i+row][j+c] += key[n-i-1][n-j-1];
                }else{
                    expandedArr[i+row][j+c] += key[n-j-1][i];
                }
            }
            
        }
    }


    public static void main(String[] args) 
    {
        Solution solu = new Solution();
        int[][] key = {{0,0,0},{1,0,0},{0,1,1}};
        int[][] lock = {{1,1,1},{1,1,0},{1,0,1}};
        solu.solution(key, lock);
    }

    
<<<<<<< HEAD
}class p60059 {
    
}
=======
}

/*
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

*/
>>>>>>> dff76fc01f3e961883add20f65c50a3938079658
