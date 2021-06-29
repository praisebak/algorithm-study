#include <iostream>
#include <cmath>
using namespace std;


int N;
int ans = 987654321;
int arr[21][21];
//스타트링크가 i
bool visit[21];

int getStat(int* array) 
{
    int result = 0;
    for(int i=1;i<=N;i++)
    {
        for(int j=i+1;j<=N;j++)
        {
            result += arr[ array[i] ][ array[j] ];
            result += arr[array[j]][array[i]];

        }
    }
    return result;
}

void divideTeam() {
    int start[N/2+1];
    int link[N/2 +1];
    int startIdx= 0;
    int linkIdx = 0;
    for(int i=1;i<=N;i++)
    {
        if(visit[i])
        {
            start[startIdx++] = i;
        }
        else
        {
            link[linkIdx++] = i;
        }
    }
    
    int startStat =  getStat(start);
    int endStat = getStat(link);
    int diff = abs(startStat - endStat);
    if (ans > diff)
    {
        ans = diff;
    }
}
 



void dfs(int v,int count)
{
    if(count != N/2)
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
    else
    {
        divideTeam();
    }
    visit[v] = false;
}

void solve()
{   
    dfs(0,0);        
}

int main()
{
    cin >> N;
    for(int i=1;i<=N;i++)    
    {
        for(int j=1;j<=N;j++)
        {
            cin >> arr[i][j];
        }
    }

    solve();
    cout << ans;

}