#include <iostream>
using namespace std;

void solve(int n)
{
	int num[n+1] = {0};
	int sum[n+1] = {-1001};
	int ans = 0;
	int tmpSum=0;
	int check = 0;

	for(int i=1;i<=n;i++)
	{
		cin >> num[i];
	}

	sum[1] = num[1];
	ans = num[1];

	//cout << endl;
	for(int i=2;i<=n;i++)
	{
		sum[i] = max(num[i],sum[i-1]+num[i]);
		if(ans < sum[i])
		{
			ans = sum[i];
		}
	}	

	cout << ans;
	if(check == 0)
	{
		ans = -1001;
		for(int i=1;i<=n;i++)
		{
			if(ans < num[i])
			{
				ans = num[i];
			}
		}
	}
	

}

int main()
{
	int n = 0;
	cin >> n;
	solve(n);


}