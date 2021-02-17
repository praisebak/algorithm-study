#include <iostream>
using namespace std;


void solve(int num)
{
	int dp[num+1] = {0};
	dp[0] = 1;
	dp[2] = 3;Z
	for(int i=4;i<=num;i++)
	{
		dp[i] = dp[i-2] * 3;
		for(int j=4;j<=i;j += 2)
		{
			dp[i] += dp[i-j] * 2;
		}
	}
	cout << dp[num];


}

int main()
{
	int num = 0;
	cin >> num;
	solve(num);

}