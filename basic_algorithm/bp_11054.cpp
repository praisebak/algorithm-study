#include <iostream>
using namespace std;

void solve(int n)
{
	int num[n+1] = {0};
	int ascend[n+1] = {0};
	int descend[n+1] = {0};
	int cur = 0;
	int obj = 0;
	int max = 1;
	int count=0;

	for(int i=1;i<=n;++i)
	{
		cin >> num[i];
	}

	ascend[1] = 1;
	descend[1] = 1;
	for(int i=2;i<=n;i++)
	{
		obj = num[i];
		ascend[i] = 1;
		for(int j=1;j<=i;j++)
		{
			cur = num[j];
			if(cur < obj)
			{	
				if(ascend[i] < ascend[j] + 1)
				{
					ascend[i] = ascend[j] + 1;
				}
			}
		}
	}


	for(int i=n;i>=1;i--)
	{
		obj = num[i];
		descend[i] = 1;
		for(int j=n;j>=i+1;j--)
		{
			cur = num[j];
			if(obj > cur)
			{
				if(descend[i] < descend[j] + 1)
				{
					descend[i] = descend[j] + 1;
				}
			}
		}
	}

	for(int i=1;i<=n;i++)
	{
		if(max < ascend[i] + descend[i] -1)
		{
			max = ascend[i] + descend[i] -1;
		}
		
	}
	cout << max;




}


int main()
{
	int n = 0;
	cin >> n;
	solve(n);
	return 0;
}