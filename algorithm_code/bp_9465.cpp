#include <iostream>
#include <algorithm>
using namespace std;
/*
1
5
50 10 100 20 40
30 50 70 10 60
7
10 30 10 50 100 20 40
20 40 30 50 60 20 80
1
4
50 40 19 18
40 20 40 20
   
*/
void init(int * arr)
{
	for(int i=0;i<sizeof(arr);i++)
	{
		arr[i] = 0;
	}
}

void solve(int ** stickerArr,int col )
{	
	int prevZeroSum = 0;
	int prevOneSum = 0;
	prevZeroSum = stickerArr[0][0];
	prevOneSum = stickerArr[1][0];

	for(int i=1;i<col;i++)
	{
		int curZeroVal = 0;
		int curOneVal = 0;
		int sumWithCurZero=0;
		int sumWithCurOne=0;
		int prevSkipNSelCurOne=0;
		int prevSkipNSelCurZero=0;

		//cout << "***\n";
		curZeroVal = stickerArr[0][i];
		curOneVal = stickerArr[1][i];
		prevSkipNSelCurOne = prevOneSum - stickerArr[1][i-1] + curOneVal;
		prevSkipNSelCurZero = prevZeroSum - stickerArr[0][i-1] + curZeroVal;
		//cout << "PS1: " << prevSkipNSelCurOne << "VS" << curZeroVal + prevOneSum<<'\n';
		//cout << "PS2: " << prevSkipNSelCurZero << "VS" << curOneVal + prevZeroSum<<'\n';
		
		//cout << curZeroVal << ' ' << curOneVal << '\n';
		//cout << prevZeroSum << ' ' << prevOneSum << "\n\n";
		//cout << "ZEROSUM:" << curZeroVal << "+ "<< prevOneSum << "\n";
		//cout << "ONESUM:" << curOneVal << "+ "<< prevZeroSum << "\n";

		int tmp = prevZeroSum;
		prevZeroSum = curZeroVal + prevOneSum;
		prevOneSum = curOneVal + tmp;
		prevZeroSum = max(prevZeroSum,prevSkipNSelCurZero);
		prevOneSum = max(prevOneSum,prevSkipNSelCurOne);

	}
	cout << max(prevZeroSum,prevOneSum);	
}

int main()
{
	int n=0;
	int col=0;
	int **stickerArr;
	cin >> n;

	for(int i=0;i<n;i++)
	{
		cin >> col; 
		stickerArr = (int **)malloc(2*sizeof(*stickerArr));

		for(int row=0;row<2;++row)
		{
			stickerArr[row] = (int *)malloc(col* sizeof(**stickerArr));
			for(int c=0;c<col;++c)
			{
				cin >> stickerArr[row][c];
			}
		}
		
		solve(stickerArr,col);
		if(i!= n-1)
		cout << endl;
		for(int i=0;i<2;++i)
		{
			free(stickerArr[i]);
		}
		free(stickerArr);

	}

}