#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
using namespace std;

void solve()
{
	string str;
	cin >> str;
	vector<string> v;
    for (int i = 0; i < str.length(); i++)
    {
		v.push_back(str.substr(i, str.length()));
    }
    sort(v.begin(),v.end());
    for(int i=0;i<str.length();i++)
    {
    	cout << v[i] << '\n';
    }

}
int main()
{
	solve();

}