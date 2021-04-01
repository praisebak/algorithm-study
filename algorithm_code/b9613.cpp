#include <iostream>
using namespace std;
const int MAX = 100;

int GCD(int numA,int numB)
{
	if(numA < numB)
	{
		int tmp = numA;
		numA = numB;
		numB = tmp;
	}
	while(numB != 0)
	{
		int r = numA % numB;
		numA = numB;
		numB = r;
	}
	return numA;


}

void solve()
{
	int testCase;
	int numArr[MAX];
	fill_n(numArr,MAX,0);

	cin >> testCase;
	for(int i=0;i<testCase;i++)
	{
		int N = 0;
		long long sum = 0;
		cin >> N;
		for(int j=0;j<N;j++)
		{
			cin >> numArr[j];
		}

		for(int k=0;k<N;k++)
		{
			for(int l=k+1;l<N;l++)
			{
				sum += GCD(numArr[k],numArr[l]);
			}
		}
		cout << sum << "\n";

	}



}

int main()
{
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	solve();


}