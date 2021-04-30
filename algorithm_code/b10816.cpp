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
	j = obj;
	while(i <= j)
	{
		mid = (i + j)/2;
		if(mid == obj)
		{
			cout << "\nobj 발견 : " << obj;
			if(obj < 0)
			{
				minusNum[obj * -1] += 1;
			}
			else
			{
				plusNum[obj] += 1;
			}
			return;		
		}
		else if(mid > obj)
		{
			i = mid + 1;
		}
		else if(mid < obj)
		{
			j = mid - 1;
		}

	}
	cout << "cant found : " << obj << "\n";
}


void solve()
{
	int N,M;
	for(int i=0;i<N;i++)
	{
		cin >> num[i];
	}
	cin >> M;
	for(int i=0;i<M;i++)
	{
		cin >> marking[i];
		sortedMark[i] = marking[i];
	}
	fill_n(plusNum,numMax,0);
	fill_n(minusNum,numMax,0);
	sort(num,num+N);
	cout << "sortedMark :\n";
	for(int i=0;i<M;i++)
	{
		cout << sortedMark[i] << " ";
	}
	sort(sortedMark,sortedMark+M);
	cout << "\n";	
	for(int i=0;i<M;i++)
	{
		cout << sortedMark[i] << " try to find " << endl;
		binSearch(N,sortedMark[i]);
	}
	cout << "값 출력)\n";
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
	cout << "\n";
	

}

int main()
{

	ios_base::sync_with_stdio(0);
	cin.tie(0);
	solve();

}
