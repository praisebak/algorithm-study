#include <iostream>
const int MAX = 
using namespace std;


int row,cal;

void solve()
{
	cin >> row >> cal;
	
	string result = "";
	//세로가 홀수
	if(row % 2 != 0)
	{
		for(int y = 0;y< row; y++)
		{
			for(int x = 0; x< cal-1;x++)
			{
				if(y % 2 == 0)
				{
					result += 'R';
				}
				else
				{
					result += 'L';
				}

				if(y != row - 1)
				{
					result += 'D'; 
				}
			}
		}
	}
	//row는 짝수인데 가로가 홀수
	else if((row % 2 == 0) && (cal % 2))
	{	
		for(int i=0;i<x;i++)
		{
			for(int j=0;j<y;j++)
			{
				if(i%2 == 0 )
				{
					result += 'D';
				}
				else
				{
					result += 'U';
				}
				if(j == y-1)
				{
					result+= 'R';
				}
			}
		}
	}
	//둘다 짝수인 레후
	//포기할 한 점을 찾아야함
	else
	{


	}


}



int main()
{
	solve();

}