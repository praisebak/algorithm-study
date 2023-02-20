import java.util.Scanner;

public class b14899
{
    static int MAX = 21;
    static int arr[][];
    static boolean visit[];
    static int N = 0;
    static int ans = 987654321;

    static int getScore(int array[])
    {
        int result = 0;
        int len = N/2;
        for(int i=1;i<=len;i++)
        {
            for(int j=i+1;j<=len;j++)
            {
                result += arr[array[i]][array[j]];
                result += arr[array[j]][array[i]];
            }
        }
        return result;
    }
    
    static void setDiff()
    {
        int start[] = new int[N/2+1];
        int link[] = new int[N/2+1];
        int startIdx = 1;
        int linkIdx = 1;
        for(int i=1;i<=N;i++)
        {
            if(visit[i])
            {
                start[startIdx++]= i;
            }
            else
            {
                link[linkIdx++]= i;
            }
        }
        int startScore = getScore(start);
        int linkScore = getScore(link);
        int result = Math.abs(startScore - linkScore);
        if(result < ans)
        {
            ans = result;
        }
    }

    static void dfs(int v,int count)
    {
        if(count == N/2)
        {
            setDiff();
        }
        else
        {
            for(int i=v+1;i<=N;i++)
            {
                if(!visit[i])
                {
                    visit[i] = true;
                    dfs(i,count+1);
                }
            }
        }
        visit[v] = false;
    }   


    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();


        arr = new int[MAX][MAX];
        visit = new boolean[MAX];

        for(int i=1;i<=N;i++)
        {
            for(int j=1;j<=N;j++)
            {
                arr[i][j] = sc.nextInt();
            }
        }

        sc.close();
        dfs(0,0);
        System.out.println(ans);
    }


}