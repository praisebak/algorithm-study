#include <iostream>
<<<<<<< HEAD
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

=======
#include <cmath>
using namespace std;

const int MAX = 2200;
int arrNum[MAX][MAX];
int result[3];

bool isSameNumberPaper(int startRow,int startCal,int divUnit)
{

	int compareNum = arrNum[startRow][startCal];
	for(int i=0;i<divUnit;i++)
	{
		for(int j=0;j<divUnit;j++)
		{
			if(arrNum[startRow+i][startCal+j] != compareNum)
			{
				return false;
			}
		}
	}
	return true;
}

void divAndConquer(int startRow,int startCal, int divUnit)
{
	int val = arrNum[startRow][startCal];
	if(divUnit <= 1 || isSameNumberPaper(startRow,startCal,divUnit))
	{
		if(val == -1)
		{
			result[2] += 1;
		}
		else
		{
			result[val] += 1;
		}
	}
	else
	{
		for(int i=0;i< divUnit; i += divUnit/3)
		{
			for(int j=0;j< divUnit; j += divUnit/3)
			{
				divAndConquer(i+startRow,j+startCal,divUnit/3);
			}

		}
	}
>>>>>>> c2109cbec133a9cbcc9b758d5560901c42b46800
}

void solve()
{
<<<<<<< HEAD
=======
	int N;
>>>>>>> c2109cbec133a9cbcc9b758d5560901c42b46800
	cin >> N;
	for(int i=0;i<N;i++)
	{
		for(int j=0;j<N;j++)
		{
<<<<<<< HEAD
			cin >> arr[i][j];
		}
	}
	count = N * N;
	div(unit,N-1,N-1);
}


int main()
{


}
=======
			cin >> arrNum[i][j];
		}
	}
	divAndConquer(0,0,N);
	cout << result[2] << "\n";
	cout << result[0] << "\n";
	cout << result[1] << "\n";


}

int main()
{
	fill_n(result,3,0);
	solve();
}
>>>>>>> c2109cbec133a9cbcc9b758d5560901c42b46800
