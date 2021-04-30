#include <iostream>
using namespace std;


void solve(int n)
{
	int maxPrice[n+1] = {0};
	int price[n+1] = {0};
	int numSum = 0;
	int priceSum = 0;
	int addedVal = 0;
	int ans = 0;
	for(int i=1;i<=n;i++)
	{
		cin >> price[i];
	}
	//3
	/*
	4
	1 5 6 7
	*/	
	ans = price[n];
	for(int i=n-1;i>=1;i--)
	{
		numSum = i;
		//3
		priceSum = price[i];
		//cout << "stan Val = " << i <<endl;
		//1
		for(int j= i;j>=1;j--)
		{
			if(i + j > n)
			{
				continue;
			}

			addedVal = j;
			numSum = i + j;
			priceSum = price[i];
			//cout << "started obj Val = " << j  << endl;
			while(1)
			{
				if(numSum == n)
				{
					priceSum += price[addedVal];
					maxPrice[i] = max(maxPrice[i],priceSum);
					break;
				}
				numSum = numSum + addedVal;
				//3 + 1
				if(numSum > n)
				{
					numSum = numSum - addedVal;
					addedVal--;
					continue;
				}
				//cout << "NS: " << numSum << endl;
				priceSum += price[addedVal];



			}
			//cout << "pS = " << priceSum << endl;

		}
		if(ans < maxPrice[i])
		{
			ans = maxPrice[i];
		}
	}
	cout << ans;

}

int main()
{
	int num = 0;
	cin >> num;
	solve(num);

}