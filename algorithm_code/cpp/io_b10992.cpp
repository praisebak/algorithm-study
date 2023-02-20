#include <iostream>
using namespace std;

void prtStar(int n)
{
	int starCount = 0;
	for(int i=1;i<=n;i++)
	{
		starCount = i*2 - 1;
		for(int k = 0;k<n-i;k++)
		{
			cout << ' ';
		}
		if(i != n && i != 1)
		{
			starCount = 2;
		}

		for(int j=0;j<starCount;j++)
		{

			cout << '*';
			if((i!=n) && (i!=1) && j==0)
			{
				for(int l=0;l<(i-1)*2-1;l++)
				{
					cout << ' ';
				}
				
			}
		}
		cout << '\n';
	}
}

int main()
{
	int n;
	cin >> n;
	prtStar(n);
}