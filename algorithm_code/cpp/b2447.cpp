#include <iostream>
using namespace std;


void printStar(int i,int j,int n)
{
	if((i /n) % 3 == 1 && (j /n)% 3 ==1)
	{
		printf(" ");
	}
	else
	{
		if(n == 1)
		{
			printf("*");
		}
		else
		{
			printStar(i,j,n/3);
		}



	}

}

void solve()
{
	int n;
	cin >> n;
	for(int i=0;i<n;i++)
	{
		for(int j=0;j<n;j++)
		{
			printStar(i,j,n);
		}
		cout << endl;
	}


}

int main()
{
	solve();

}