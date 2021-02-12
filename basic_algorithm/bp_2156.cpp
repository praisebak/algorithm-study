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
	int scoreArr[n+1] = {0};
	int sumArr[n+1] = {0};
	for(int i=1;i<=n;i++)
	{
		cin >> scoreArr[i];
	}

	sumArr[1] = scoreArr[1];
	sumArr[2] = scoreArr[1] + scoreArr[2];


	for(int i=3;i<=n;i++)
	{
		sumArr[i] = scoreArr[i-1] + scoreArr[i] + sumArr[i-3];
		sumArr[i] = max(sumArr[i], sumArr[i-2] + scoreArr[i]);
		sumArr[i] = max(sumArr[i], sumArr[i-1]);
	}
	cout << sumArr[n];


}

int main()
{
	int n = 0;
	cin >> n;	
	solve(n);

}

