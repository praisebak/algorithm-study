#include <iostream>
#include <stack>
using namespace std;

void solve()
{
	string binary;
	int countThree = 0; 
	int digit = 1;
	int result = 0;
	cin >> binary;
	stack<int> s;
	for(int i=binary.size()-1;i>=0;--i)
	{
		result += (binary[i] - '0') * digit;
		countThree++;
		digit*=2;
		if(countThree == 3)
		{
			s.push(result % 8);
			countThree = 0;
			result = 0;
			digit = 1;
		}
	}
	if(countThree != 0)
	{
		s.push(result % 8);
	}
	while(s.size()!= 0)
	{
		cout << s.top();
		s.pop();
	}

}

int main()
{
	ios_base :: sync_with_stdio(0);
	cin.tie(0);
	solve();
}