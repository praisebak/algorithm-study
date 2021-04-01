#include <iostream>
#include <stack>
using namespace std;

void solve()
{
	stack<int> s;
	int dec;
	int result = 0;
	cin >> dec;

	while(dec != 0)
	{
		if(dec % -2 == 0)
		{
			s.push(dec % -2);
			dec /= -2;
		}
		else
		{
			s.push(1);
			dec = (dec-1) / -2;
		}
	}
	while(s.size() != 0)
	{
		cout << s.top();
		s.pop();
	}
}

int main()
{
	solve();

}