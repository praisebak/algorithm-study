import javax.imageio.ImageReadParam;

//0312,0704 : 트라이 실패
//만약 a,b면 어떻게되나라는 의문이 있음에도 그냥 해보자 라는 식으로 접근해서 결국엔 틀림
//의문에 대한 답을 찾지못하면 모르는것임에도 막무가내로 시도하는 것은 좋은 성과를 얻기엔 잘못된 길일 가능성이 큼
//0705 : 답지보고 dfs 트라이
class Solution
{
    int maxResult = Integer.MIN_VALUE;
    int[] lion;
    int[] result = {-1};
    void dfs(int n,int count,int[] info)
    {
        if(n+1 == count)
        {
            calResult(info);
            return;
        }
        
        for(int i=0;i<lion.length && lion[i] <= info[i] ;i++)
        {
            lion[i]++;
            dfs(n,count+1,info);
            lion[i]--;
        }

            



    }




    private void calResult(int[] info) 
    {
        int lResult = 0;
        int aResult = 0;
        for(int i=0;i<info.length;i++)
        {
            if(info[i] !=0 || lion[i] != 0)
            {
                if(info[i] >= lion[i]) 
                {
                    aResult += 10-i;
                }
                else
                {
                    lResult += 10 - i;
                }
            
            }
        }
        if((lResult - aResult >= maxResult )&& lResult > aResult)
        {
            maxResult = lResult - aResult;
            result = lion.clone();
        }

    }




    public int[] solution(int n, int[] info) 
    {
        lion = new int[11];
        dfs(n,1,info);
        int[] answer = result;
        return answer;
    }






}