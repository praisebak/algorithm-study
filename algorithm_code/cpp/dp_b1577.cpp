#include <iostream>
using namespace std;

//0 = x
//1 = y
int dp[101][101][2] = {0};
int constStreet[101][2] = {0};

//일단 공사하는 부분 제외하고 구현
void solve(int x,int y)
{
	int i=0,j = 0;
	dp[0][0] = 2;
	while(!(i-1 == x && j-1 == y))
	{
		
		//현재 좌표에서 x+1,y
		if(dp[i+1][0] > 0)
		{
			dp[i][j] += dp[i+1][0];
		}
		else if(i < x)
		{
			i++;
			dp[i][j]+=1;
			continue;
		}
		//현재 좌표에서 x,y+1
		if(dp[i+1][1] > 0)
		{
			dp[i][j] += dp[i+1][1];
		}
		else if(j < y)
		{
			j++;
		}

	}
	cout << dp[i-1][0] + dp[i-1][]


}

int main()
{
	int hori = 0;
	int verti = 0;
	int numOfConst = 0;
	cin >> hori;
	cin >> verti;
	cin >> numOfConst;
	for (int i = 0; i < numOfConst; ++i)
	{
		cin >> constStreet[i][0];
		cin >> constStreet[i][1];
	}

	solve(x,y);


}
