#include <iostream>

using namespace std;

int num[27] = {0}; 


void solve()
{
    string str;
    cin >> str;
    int idx = 0;
    for(int i=0;i<str.length();i++)
    {
    	idx = str[i] - '0' - 48;
    	num[idx] += 1;

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