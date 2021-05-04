#include <iostream>
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
}

void solve()
{
	int N;
	cin >> N;
	for(int i=0;i<N;i++)
	{
		for(int j=0;j<N;j++)
		{
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
