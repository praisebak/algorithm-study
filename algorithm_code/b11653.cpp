#include <iostream>
using namespace std;

void solve()
{
	int num;
	int modNum = 2;
	cin >> num;


	while(num != 1 && num != 0)
	{
		if(num % modNum == 0)
		{
			cout << modNum << endl;
			num /= modNum;
		}
		else
		{
			modNum++;
		}


	}

}

int main()
{
	solve();


}