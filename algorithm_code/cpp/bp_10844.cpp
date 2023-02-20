#include <iostream>
using namespace std;


int solve(int n)
{
	long stairNumArr[101][11] = {0};
	for(int i=1;i<=9;i++)
	{
		stairNumArr[1][i] = 1;
	}

	for(int i=2;i<=n;i++)
	{
		stairNumArr[i][0] = stairNumArr[i-1][1];
		for(int j=1;j<=9;j++)
		{
			stairNumArr[i][j] = (stairNumArr[i-1][j-1] + stairNumArr[i-1][j+1]) % 1000000000;
		}
	}

	long sum = 0;
	for(int i=0;i<=9;i++)
	{
		sum += stairNumArr[n][i];
	}
	cout << sum % 1000000000;
	return 0;
}




int main()
{

	int n=0;
	cin >> n;
	solve(n);

	return 0;

}