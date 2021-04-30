#include <iostream>
#include <stack>
using namespace std;

void solve()
{
	stack<int> s;
	string input;
	int count = 0;
	cin >> input;
	for(int i=input.size()-1;i>=0;i--)
	{
		int val = input[i] - '0';
		if(val == 0 && i != 0)
		{
			s.push(0);
		}
		while(val != 0)
		{
			s.push(val % 2);			
			val /= 2;
		}
		
		while(s.size() % 3 != 0 && i != 0)
		{
			s.push(0);
		}
		
	}
	if(s.size() == 0)
	{
		cout << 0;
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