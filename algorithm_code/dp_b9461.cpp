#include <iostream>
using namespace std;

long long dp[101] = {0};
long long solve(int n)
{
	if(dp[n] != 0)
	{
		return dp[n];
	}
	dp[n] = solve(n-3) + solve(n-2);
	return dp[n];
}

int main()
{
	int testCase = 1;
	
	cin >> testCase;
	int n[testCase+1] = {0};
	for(int i=1;i<=3;i++)
	{
		dp[i] = 1;
	}

	for(int i=1;i<=testCase;i++)
	{
		cin >> n[i];
		solve(n[i]);
		
	}
	for(int i=1;i<=testCase;i++)
	{
		cout << dp[n[i]] << endl;
	}
	
	
}