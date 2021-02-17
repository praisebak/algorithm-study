#include <iostream>
using namespace std;
void solve(int n)
{
	int num[n+1] = {0};
	int dp[n+1] = {0};
	int maxVal = 0;
	for(int i=1;i<=n;i++)
	{
		cin >> num[i];
	}

	for(int i=1;i<=n;i++)
	{	
		//1 6 2 5 7 3 5 6
			//1부터 시작
			//5
		dp[i] = 1;
		for(int j=i-1;j>=1;j--)
		{	
			if(dp[j]+1 > dp[i] && num[i] > num[j])
			{
				dp[i] = dp[j]+1;
			}

		}
		if(dp[i] > maxVal)
		{
			maxVal = dp[i];
		}
	}
	cout << maxVal;

}


int main()
{
	int n =0;
	cin >> n;
	solve(n);
}