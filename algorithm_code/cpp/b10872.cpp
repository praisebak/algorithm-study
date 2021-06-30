#include <iostream>
using namespace std;

void solve()
{
	int num;
	cin >> num;
	if(num == 0)
	{
		cout << 1;
		return;
	}
	else
	{
		long long result = 1;
		while(num != 1)
		{
			result *= num ;
			num--;
		}
		cout << result;
	}
}


int main()
{
	solve(); 
}