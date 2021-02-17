#include <iostream>
using namespace std;
void solve(int n)
{
	int price[n+1] = {0};
	int maxPrice[n+1] = {0};
	int ans = 0;
	int priceSum = 0;
	for(int i=1;i<=n;i++)
	{
		cin >> price[i];
	}

	for(int i=1; i<=n;i++)
	{
		for(int j=1;j<=i;j++)
		{
			//i-j의 모든 계산된 값 + 현재 가격
			//현재 가격이 j자나 엄자식아

			priceSum = maxPrice[i-j] + price[j];
			if(maxPrice[i] < priceSum)
			{
				maxPrice[i] = priceSum;
			}
		}
	}

	cout << maxPrice[n];

}

int main()
{

	int num = 0;
	cin >> num;
	solve(num);

}