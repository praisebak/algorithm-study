#include <iostream>
using namespace std;


void solve()
{
	int num = 0;
	cin >> num;
	int five= 0;
	int two = 0;
	for(int i=5;i<=num;i*=5)
	{
		five += num / i;
	}
	cout << five;
}



int main()
{
	solve();
}