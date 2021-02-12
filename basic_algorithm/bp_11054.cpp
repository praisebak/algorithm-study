#include <iostream>
using namespace std;

void solve(int n)
{
	int num[n+1] = {0};
	int ascend[n+1] = {0};
	int ascRightcend[n+1] = {0};
	
	for(int i=1;i<=n;++i)
	{
		cin >> num[i];
	}
	ascend[1] = 1;
	ascRightcend[1] = 1;

	int obj = 0;
	int cur = 0;
	int ascCount = 0;
	int ascRightCount = 0;
	int max = 1;

	for(int i=2;i<=n;i++)
	{	
		obj = num[i];
		
		ascCount =0;
		ascRightCount = 0;
		for(int j=1;j<i;j++)
		{
			cur = num[j];
			if(obj > cur)
			{
				if(ascCount < ascend[j])
				{
					ascCount = ascend[j];
				}
			}
		}

		ascend[i] = ascCount + 1;
		
		for(int j=i+1;j<=n;j++)
		{
			cur = num[j];
			if(obj > cur)
			{
				if(ascRightCount < ascRightcend[j])
				{
					ascRightCount = ascRightcend[j];
				}
			}
		}
		ascRightcend[i] = ascRightCount + 1;

		if(max < ascRightcend[i] + ascend[i])
		{
			max = ascRightcend[i] + ascend[i];
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