#include <iostream>
using namespace std;

int init()
{
	int num;
	cin >> num;
	if(num == 0)
	{
		cout << 0;
	}


	return num;
}

void solve(int num)
{

	if(num == 0)
	{
		return;
	}

	if(num % -2 == 0)
	{
		solve(num / -2);
		cout << 0;
	}
	else
	{
		solve((num-1) / -2);
		cout << 1;
	}


	

}

int main()
{

	solve(init());



}





