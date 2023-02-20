#include <iostream>
using namespace std;

void solve(int n)
{
	long long pinary[91][2] = {0};
	
	pinary[1][1] = 1;
	pinary[2][0] = 1;

	for(int i=3; i<=n;i++)
	{
		pinary[i][0] = pinary[i-1][0] +pinary[i-1][1];
		pinary[i][1] = pinary[i-1][0];
	}

	cout << pinary[n][0] + pinary[n][1];

}

int main()
{
	int n=0;
	cin >> n;
	solve(n);
}