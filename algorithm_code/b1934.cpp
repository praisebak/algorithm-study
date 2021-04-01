#include <iostream>
using namespace std;

void LCM(int numA,int numB)
{
	int addedNum = numB;
	while(addedNum % numA != 0)
	{
		addedNum += numB;
	}
	cout << addedNum <<"\n";
}

void solve()
{
	int testCase,numA,numB;
	cin >> testCase;
	for(int i=0;i<testCase;i++)
	{
		cin >> numA >> numB;
		LCM(numA,numB);

	}

}

int main()
{
	solve();
}