#include <iostream>
using namespace std;


void solve(char *num,int len)
{

	//dp[i] 
	//자리수가 i개 일 때 경우의 수
	//연속으로 2자리씩만 올 수 있고 연속으로 2자리 왔을 때 값 val은 1<=val<=26
	int dp[len] = {0};
	int tmp = 0;
	dp[0] = 1;
	if(len ==  1)
	{
		cout << dp[0];
		return;
	}


	for(int i=1;i<len;i++)
	{
		tmp = ((num[i-1] -'0') * 10) + num[i] - '0';
		dp[i] = dp[i-1];
		if(tmp <= 26)
		{
			dp[i]++;
		}
	}
	cout << dp[len-1];
}



int main()
{
	string strNum;
	char num[5001];
	cin >> strNum;

	int i=0;
	while(strNum[i] != NULL)
	{
		num[i] = strNum[i];
		i++;
	}	
	solve(num,i);


}