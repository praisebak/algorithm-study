#include <iostream>
#include <vector>
using namespace std;

void solve()
{
	vector <int> v;
	long long num,digit;
	int result;
	cin >> num >> digit;
	while(num != 0)
	{
		v.push_back(num % digit);
		num /= digit;
	}

	vector<int>::reverse_iterator iter;

	for(iter = v.rbegin();iter != v.rend();iter++)
	{
 		if(*iter >= 10)
 		{
 			cout << (char(*iter - 10+'A'));
 		}
 		else
 		{
 			cout << *iter;
 		}
	}


}


int main()
{
	solve();

}