#include <iostream>
<<<<<<< HEAD
#include <string>
#include <algorithm> 
=======
>>>>>>> 44f1baa2ac0e841c042475a8b35f700ee36683c2
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

<<<<<<< HEAD
const int INF = 987654321;
const int MAX = 1000;

=======
	for(int i=4;i<N;i+=2)
	{
		for(int j=1;j<N;j+=2)
		{
>>>>>>> 44f1baa2ac0e841c042475a8b35f700ee36683c2

		}
	}
	//짝수 홀수만 봐야함
	
}


int arr[MAX][MAX];
string result = "";

void findMinHappy()
{
	pair<int,int> minHappy;

	int minScore = INF;
	for(int y=0;y<row;y++)
		for(int x=0;x<cal;x++)
		{
			cin >> arr[y][x];
			//row가 홀수번째여야함
			if((y+x) % 2 && minScore > arr[y][x])
			{
				minScore = arr[y][x];
				minHappy = {y,x};
			}

		}
		//
		int newR = minHappy.first % 2 ? minHappy.first - 1 : minHappy.first;

		//좌표의 바로 왼쪽으로 이동
		for(int y=0;y<newR;y++)
		{
			for(int x=0;x<cal-1;x++)
				if(y%2 == 0)
				{
					result += 'R'; 
				}
				else
				{
					result += 'L';
				}
			result += 'D';
		}
		//지나지 말아야 할 좌표의 대각선 아래까지 이동

		int newC = minHappy.second;
		for(int x = 0; x< newC; x++)
			if(x % 2 == 0)
				result += "DR";
			else
				result+= "UR";

		//바로 오른쪽으로 이동
		for(int x = newC; x< cal-1;x++)
			if(x % 2 == 0)
				result+= "RD";
			else
				result += "RU";

		//자 드가자~
		if(minHappy.first % 2 == 0)
			newR = row - minHappy.first - 2;
		else
			newR = row - minHappy.first - 1;

		for(int y = 0;y<newR;y++)
		{
			result += 'D';
			for(int x=0;x<cal-1;x++)
				if(y % 2 == 0)
					result += 'L';
				else
					result += 'R';

		}
}


void solve()
{
	cin >> row >> cal;
	fill(&arr[0],&arr[0])
	
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
			}
			if(y != row - 1)
			{
				result += 'D'; 
			}
		}
	}
	//row는 짝수인데 가로가 홀수
	else if((row % 2 == 0) && (cal % 2))
	{	
		for(int x=0;x<cal;x++)
		{
			for(int y=0;y<row-1;y++)
			{
				if(x % 2 == 0 )
				{
					result += 'D';
				}
				else
				{
					result += 'U';
				}
			}
			if(x != cal-1)
			{
				result+= 'R';
			}
		}
	}
<<<<<<< HEAD
	//둘다 짝수인 레후
	//포기할 한 점을 찾아야함
	else if(!(row%2) && !(cal%2))
	{
		findMinHappy();

=======
	//둘다 짝수인 경우
	else
	{
		setMinHappyPair();
>>>>>>> 44f1baa2ac0e841c042475a8b35f700ee36683c2
	}

	cout << result;


}



int main()
{
	ios_base::sync_with_stdio(0);
    cin.tie(0); //cin 실행속도 향상
	solve();

}