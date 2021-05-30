	#include <iostream>
	#include <algorithm>

	using namespace std;

	const int MAX = 10000;
	int num[MAX];

	void solve()
	{
		int N,i,endOfLeft;
		long long sum = 0;
		cin >> N;
		for(i=0;i<N;i++)
		{
			cin >> num[i];
		}
		sort(num,num+N);
		for(i=0;num[i] <= 0 && i < N;i++)
		{
			if(num[i] < 0 && i+1 < N && num[i+1] < 0)
			{
				sum += num[i] * num[i+1];
				//printf("마이너스 값 2개 곱하는레후: %d %d\n",num[i],num[i+1]); 
				i++;
			}
			else if(num[i] < 0 && i+1 < N && num[i+1] == 0)
			{
				sum += 0;
				//printf("마이너스 값 하나랑 0 곱하는 레후 : %d %d\n",num[i],num[i+1]);
				i++;
			}
			else
			{
				sum += num[i];
				//printf("그냥 %d 더한 레후\n",num[i]);
			}
		}

		

		endOfLeft = i-1;
		for(i=N-1;i>endOfLeft && num[i] > 0;i--)
		{
			if(num[i-1] > 1 && num[i] > 1)
			{
				sum += num[i] * num[i-1];
				//printf("양수 2개 : %d %d\n",num[i],num[i-1]);
				i--;
			}
			else
			{
				sum += num[i];
			}
		}
		cout << sum;

	}

	int main()
	{
		solve();

	}
