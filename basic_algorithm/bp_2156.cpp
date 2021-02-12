#include <iostream>
using namespace std;

/*
6
13
10
1
12
9
30

6
6
10
13
9
8
1

*/
void solve(int n)
{
	int numArr[n+3] = {0};
	int continuousOccur = 1;
	int sum = 0;
	
	for(int i=1;i<=n;++i)
	{
		cin >> numArr[i];
	}

	sum = numArr[1];

	for(int i=2;i<=n;++i)
	{
		++continuousOccur;
		if(continuousOccur == 3)
		{
			if(numArr[i] + numArr[i-1] >  numArr[i] + numArr[i-2])
			{
				
			}
			else
			{
				continuousOccur = 0;
			}
			
			continue;
		}
		sum += numArr[i];
	}
	cout << sum;

}

int main()
{
	int n = 0;
	cin >> n;	
	solve(n);


}

