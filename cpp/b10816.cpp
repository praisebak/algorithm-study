#include <algorithm>
#include <iostream>
using namespace std;
const int arrMax = 500000;
const int numMax = 10000001;
int marking[arrMax];
int sortedMark[arrMax];
int num[arrMax];
int plusNum[numMax];
int minusNum[numMax]; // think as just * -1
//1. 숫자 입력받음 
//

void binSearch(int N,int obj)
{
	int i=0,j,mid;
	j = N;
	while(i <= j)
	{
		mid = (i + j)/2;
		if(num[mid] == obj)
		{
			if(obj < 0)
			{
				cout << minusNum[obj * -1];
			}
			else
			{
				cout << plusNum[obj];
			}
			return;		
		}
		else if(num[mid] > obj)
		{
			j = mid -1;
		}
		else if(num[mid] < obj)
		{
			i = mid + 1;
		}

	}
	cout << 0;
}


void solve()
{
	int N,M;
	cin >> N;
	fill_n(plusNum,numMax,0);
	fill_n(minusNum,numMax,0);
	for(int i=0;i<N;i++)
	{
		cin >> num[i];
		if(num[i] < 0)
		{
			minusNum[num[i] * -1] += 1;
		}
		else
		{
			plusNum[num[i]] += 1;
		}
	}
	cin >> M;
	for(int i=0;i<M;i++)
	{
		cin >> marking[i];
		sortedMark[i] = marking[i];
	}
	
	sort(num,num+N);
	for(int i=0;i<M;i++)
	{
		if(i != 0)
		{
			cout << " ";
		}
		binSearch(N,marking[i]);
		
	}

	/*
	for(int i=0;i<M;i++)
	{
		if(marking[i] < 0)
		{
			cout << minusNum[-1 * marking[i]];
		}
		else
		{
			cout << plusNum[marking[i]];
		}
		if(i != M-1)
		{
			cout << " ";
		}
	}		
	*/
	

}

int main()
{

	ios_base::sync_with_stdio(0);
	cin.tie(0);
	solve();

}
