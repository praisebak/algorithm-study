#include <iostream>
using namespace std;


void solve(int n)
{
	int num[n+1] = {0};
	int sum[n+1] = {0};
	for(int i=1;i<=n;i++)
	{
		cin >> num[i];
	}
	int max = 1;
	int obj = 0;
	int cur = 0;
	int count = 0;
	sum[1] = 1;

	for(int i=2;i<=n;i++)
	{
		obj = num[i];
		count = 0;
		for(int j=1;j<i;j++)
		{ 
			cur = num[j];
			if(cur > obj)
			{
				if(count < sum[j])
				{
					count = sum[j];
				}
				
			}
		}
		sum[i] = count + 1;
		if(sum[i] > max)
		{
			
			max = sum[i];
		}
	}

	cout << max;

}

int main()
{
	int n=0;
	cin >> n;
	solve(n);
}