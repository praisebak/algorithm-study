#include <iostream>
#define MAX 100
using namespace std;
int arr[MAX][MAX];


int getSum(int startY,int startX,int endY,int endX)
{
    int sum = 0;
    for(int i=startY ;i<=endY;i++)
    {
        for(int j=startX;j<=endX;j++)
        {   
            sum += arr[i][j];
        }
    }
    return sum;
}



void solve()
{
    int N,M;
    cin >> N >> M;
    string input;
    for(int i=0;i<N;i++)
    {
        cin >> input;
        for(int j=0;j<M;j++)
        {
            arr[i][j] = input[j]-'0';
        }
    }
    long long maxVal = 0;
    for(int i=0;i<N-2;i++)
    {
        for(int j=i+1;j<N-1;j++)
        {
            long long squareA = getSum(0,0,i,M-1);
            long long squareB = getSum(i+1,0,j,M-1);
            long long squareC = getSum(j+1,0,N-1,M-1);
            maxVal = max(maxVal,squareA*squareB*squareC);
        }
    }
    for(int i=0;i<M-2;i++)
    {
        for(int j=i+1;j<M-1;j++)
        {
            long long squareA = getSum(0,0,N-1,i);
            long long squareB = getSum(0,i+1,N-1,j);
            long long squareC = getSum(0,j+1,N-1,M-1);
            maxVal = max(maxVal,squareA*squareB*squareC);
        }

    }
    for(int i=0;i<M-2;i++)
    {
        for(int j=i+1;j<M-1;j++)
        {
            long long squareA = getSum(0,0,N-1,i);
            long long squareB = getSum(0,i+1,N-1,j);
            long long squareC = getSum(0,j+1,N-1,M-1);
            maxVal = max(maxVal,squareA*squareB*squareC);
        }
    }
    for(int i=M-1; i > 0; i--)
    {
        for(int j=0; j < N-1;j++)
        {
            long long squareA = getSum(0,i,N-1,M-1);
            long long squareB = getSum(0,0,j,i-1);
            long long squareC = getSum(j+1,0,N-1,i-1);
            maxVal = max(maxVal,squareA*squareB*squareC);
        }
    }
    
    for(int i=0;i<M-1;i++)
    {
        for(int j=N-1;j>0;j--)
        {
            long long squareA = getSum(0,0,N-1,i);
            long long squareB = getSum(0,i+1,j,M-1);
            long long squareC = getSum(j+1,i+1,N-1,M-1);
            maxVal = max(maxVal,squareA*squareB*squareC);
        }
    }

    for(int i=0;i<N-1;i++)
    {
        for(int j=0;j<M-1;j++)
        {
            long long squareA = getSum(0,0,i,M-1);
            long long squareB = getSum(i+1,0,N-1,j);
            long long squareC = getSum(i+1,j+1,N-1,M-1);
            maxVal = max(maxVal,squareA*squareB*squareC);
        }
    }

    for(int i=N-1; i>0; i--)
    {
        for(int j=0;j<M -1;j++)
        {
            long long squareA = getSum(i,0,N-1 ,M-1);
            long long squareB = getSum(0,0,i-1,j);
            long long squareC = getSum(0,j+1,i-1,M-1);

            maxVal = max(maxVal,squareA*squareB*squareC);
        }
    }
    cout << maxVal;

}

int main()
{
    solve();
    return 0;
}