#include <iostream>
#include <stack>
using namespace std;

void solve()
{
	string inputStr;
	int digit;
	long long result = 0;
	int curNum = 0;
	stack<int> s;

	cin >> inputStr >> digit;

	for(int i=0;i<inputStr.size();i++)
	{
		if (inputStr[i] >= '0' && inputStr[i] <= '9') 
		{
			curNum = inputStr[i] - '0';
		}
		else
		{
			curNum = inputStr[i] - 'A' + 10;
		}
		s.push(curNum);
	}


	int stackSize = s.size();
	int digitTmp = 1;
	for(int i=0;i<stackSize;i++)
	{
		curNum = s.top();
		s.pop();
		result = result + curNum * digitTmp;
		digitTmp *= digit;
	}
	cout << result;
}

int main()
{
	solve();
}