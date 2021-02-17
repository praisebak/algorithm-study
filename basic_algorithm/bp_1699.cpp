#include <iostream>
using namespace std;
void solve(int num)
{
	int dp[num+1] = {0};
	int maxVal = 2;
	int sum = 0;
	int tmp = 0;
	int count = 0;
	int minCount = 100001;

	fill_n(dp,num+1,100001);
	while(num >= maxVal * maxVal)
	{
		maxVal += 1;
	}
	maxVal -= 1;

	for(int i = maxVal;i>=1;i--)
	{
		sum = i * i;
		if(sum == num)
		{
			dp[i] = 1;
			minCount = 1;
		}
		//cout << "현재 기준 값 : " << i << endl;
		for(int val = i-1; val >= 1; val--)
		{	
			count = 1;
			tmp = val;
			if(sum + val * val <= num)
			{
				//cout << "V: " << val << endl;
				while(1)
				{ 
					if(sum + tmp * tmp > num)
					{
						tmp = tmp - 1;
						continue;
					}
					sum = sum + tmp * tmp;
					count++;

					if(sum == num)
					{
						break;
					}
				}
				dp[i] = min(count,dp[i]);
				if(minCount > dp[i])
				{
					minCount = dp[i];
				}
				sum = i * i;
			}
		}
		//cout << "COUNT: "<< dp[i] << endl;

	}
	if(maxVal == 1)
	{
		cout << num;
	}
	else
	{
		cout << minCount;	
	}

}

int main()
{
	int num = 0;
	cin >> num;
	solve(num);

}

