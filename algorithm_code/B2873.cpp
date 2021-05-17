#include <iostream>
using namespace std;
int row,cal;
const int MAX = 1001;
int arr[MAX][MAX];
int N;
pair<int,int> minHappyPair = {0,0};
	
void setMinHappyPair()
{
	//짝수라는 말은 적어도 row,cal >= 2
	minHappyPair = make_pair(1,1);
	int minVal = arr[1][1];

	for(int i=4;i<N;i+=2)
	{
		for(int j=1;j<N;j+=2)
		{

		}
	}
	//짝수 홀수만 봐야함
	
}

void solve()
{
	cin >> row >> cal;
	fill(&arr[0],&arr[0])
	
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
	//둘다 짝수인 경우
	else
	{
		setMinHappyPair();
	}


}



int main()
{
	solve();

}