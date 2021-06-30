#include <iostream>
using namespace std;

void solve(int n)
{
	long ascendNumArr[1001][11] = {0};

	for(int i=0;i<=9;i++)
	{
		ascendNumArr[1][i] = 1;
	}

	for(int i=2;i<=n;i++)
	{
		for(int j=0;j<=9;j++)
		{
			for(int k=j;k<=9;k++)
			{
				ascendNumArr[i][j] = (ascendNumArr[i][j] + ascendNumArr[i-1][k]) % 10007;
			}
			
		}
	}

	long sum = 0;
	for(int i=0;i<=9;i++)
	{
		sum = (sum + ascendNumArr[n][i]) % 10007 ;
	}	
	cout << sum;

}


int main()
{
	int n=0;
	cin >> n;
	solve(n);
	return 0;
}
