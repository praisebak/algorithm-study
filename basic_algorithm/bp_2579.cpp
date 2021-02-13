#include <iostream>
using namespace std;

void solve(int n)
{
	int num[n+1] = {0};
	int sum[n+1] = {0};
	int ans = 0;
	for(int i=1;i<=n;i++)
	{
		cin >> num[i];
	}

	sum[1] = num[1];
	if(n== 1)
	{
		cout << num[1];
		return;
	}
	sum[2] = num[2] + num[1];
	for(int i=3;i<=n;i++)
	{	
		sum[i] = sum[i-2]+num[i];
		sum[i] = max(sum[i],sum[i-3] + num[i] + num[i-1]);
	}

	cout << sum[n];

}

int main()
{

	int n = 0;
	cin >> n ;
	solve(n);

}
