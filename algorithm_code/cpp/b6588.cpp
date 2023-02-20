#include <iostream>
using namespace std;
const int MAX = 1000001;
int problem[MAX];
bool check[MAX];
int frontVal[MAX];
int backVal[MAX];

void checkPrimeNum(int val)
{
	for(int i=2;i<=val;i++)
	{
		for(int j=2;i*j<=val;j++)
		{
			check[i*j] = true;
		}
	}
}


void solve()
{
	int val = -1;
	int count = 0;
	int maxVal = 0;
	fill_n(problem,MAX,0);
	fill_n(frontVal,MAX,0);
	fill_n(check,MAX,false);
	fill_n(backVal,MAX,0);
	do
	{
		cin >> val;
		if(val != 0)
		{
			problem[count++] = val;
		}
		if(maxVal < problem[count-1])
		{
			maxVal = problem[count-1];
		}

	}while(val != 0);
	
	checkPrimeNum(maxVal);

	for(int testCase=0;testCase<count;testCase++)
	{
		for(int iter = 3;iter < problem[testCase]; iter++)
		{
			if(!check[iter] && !check[problem[testCase] - iter])
			{
				cout << problem[testCase] << " = " << iter << " + " << problem[testCase] - iter<< "\n";
				break;
			}
		}
	}

	
}

int main()
{
	solve();
}