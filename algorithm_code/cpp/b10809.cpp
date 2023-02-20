#include <iostream>

using namespace std;

int num[27];


void solve()
{
	fill_n(num,27,-1);
	string str;
	cin >> str;

	for(int i=0;i<str.length();i++)
	{
		int idx = str[i] - '0' - 48;
		if(num[idx] == -1)
		{
			num[idx] = i;
		}
	}
	for(int i=1;i<=26;i++)
	{
		cout << num[i] << " ";
	}


}

int main()
{
	solve();
}