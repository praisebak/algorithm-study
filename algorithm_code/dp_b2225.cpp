#include <iostream>
using namespace std;


void solve(int n,int k)
{
	//[자리수][맨앞이 뭘로 시작하는지]
	long long dp[k+1][n+1];
	long long sum = 0;
	for(int i=0;i<=k;i++)
	{
		for(int j=0;j<=n;j++)
		{
			dp[i][j] = 0;
		}
	}
	
	dp[1][n] = 1;
	
	if(k == 1)
	{
		cout << 1;
		return;
	}

	for(int i=0;i<=n;i++)
	{
		dp[2][i] = 1; 
	}

	for(int i=3;i<=k;i++)
	{
		for(int j=0;j<=n;j++)
		{
			for(int l=j;l<=n;l++)
			{
				//cout << dp[i-1][k] << endl;
				dp[i][j] += dp[i-1][l] % 1000000000;
			}
			//cout << "*" << i << endl;
			
		}
	}

	//cout << "*************" <<endl;
	for(int i=0;i<=n;i++)
	{
		sum += dp[k][i] % 1000000000;
		//cout << dp[k][i] << endl;
	}
	cout << sum % 1000000000;

}

int main()
{
	int num = 0;
	int k = 0;
	cin >> num;
	cin >> k;
	solve(num,k);

}