#include <iostream>
using namespace std;

long long GCD(long long numA,long long numB)
{

	while(numB != 0)
	{
		long long r = numA % numB;
		numA = numB;
		numB = r;
	}

	return numA;

}

void solve()
{
	long long numA, numB;
	cin >> numA >> numB;
	for(long long i=0;i<GCD(numA,numB);i++)
	{
		cout << '1';
	}

}

int main()
{
	solve();

}