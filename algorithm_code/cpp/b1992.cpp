#include <iostream>
#include <string>
using namespace std;
const int MAX = 64;
int input[MAX][MAX];
string tmp = "";
bool allPixelSame(int startRow,int startCal,int divUnit)
{
	int comparePixel = input[startRow][startCal];
	for(int i=0;i<divUnit;i++)
	{
		for(int j=0;j<divUnit;j++)
		{
			if(input[startRow+i][startCal+j] != comparePixel)
			{
				return false;
			}

		}
	}
	return true;


}

void quadTree(int startRow,int startCal,int divUnit)
{

	if(divUnit == 1 || allPixelSame(startRow,startCal,divUnit))
	{
		tmp.append(to_string(input[startRow][startCal]));
	}
	else
	{
		tmp.append("(");
		for(int i=0;i<divUnit;i+= divUnit/2)
		{
			for(int j=0;j<divUnit;j+=divUnit/2)
			{
				quadTree(startRow+i,startCal+j,divUnit/2);
			}
		}
		tmp.append(")");
	}

}

void solve()
{
	int N;
	char tmpInput;
	cin >> N;
	for(int i=0;i<N;i++)
	{
		for(int j=0;j<N;j++)
		{
			cin >> tmpInput;
			input[i][j] = tmpInput - '0';
		}
	}
	quadTree(0,0,N);
	cout << tmp;

}

int main()
{
	solve();
}