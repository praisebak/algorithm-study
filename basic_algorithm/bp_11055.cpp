#include <iostream>
using namespace std;

void solve(int n)
{
	int max = 0;
	int num[n+1] = {0};
	int sum[n+1] = {0};

	for(int i=1;i<=n;i++)
	{
		cin >> num[i];
	}
	sum[1] = num[1];
	max = sum[1];
	int sumTmp = 0;
	int curVal = 0;
	int subVal = 0;
	for(int i=2;i<=n;i++)
	{
		sumTmp = 0;
		subVal = num[i];
		for(int j=1;j<=i;j++)
		{
			curVal = num[j];
			if(curVal < subVal)
			{
				if(sumTmp < sum[j])
				{
					sumTmp = sum[j];
				}
			}
			
		}
		sum[i] = sumTmp + num[i];
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