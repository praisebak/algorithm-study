#include <iostream>
#include <vector>
using namespace std;

int N;
const int MAX = 2500;
int arr[MAX][MAX];
int zeroCount = 0;
int oneCount = 0;
int minusOneCount = 0;

int div(int unit,int i,int j)
{
	if(unit == 1)
	{
		return arr[i][j];
	}
	else
	{
		check(isSameVal)
		{

		}

		for(int i=0;i<9;i++)
		{
			unit = unit / 3;
			for(int j=0;j<unit;j++)
			{
				div(unit,i*unit,)
			}

		}
		for(int i=0; i<)
		{

		}
		div(unit/9,i,i+j)
	}

}

void solve()
{
	cin >> N;
	for(int i=0;i<N;i++)
	{
		for(int j=0;j<N;j++)
		{
			cin >> arr[i][j];
		}
	}
	count = N * N;
	div(unit,N-1,N-1);
}


int main()
{


}