#include <iostream>
using namespace std;

void solve()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int n = 0;
	int num[10001] = {0}; 
	cin >> n >> k;
	for(int i=0;i<n;i++)
	{
		int tmp;
		cin >> tmp;
		num[tmp]+=1;
	}
	for(int i=1;i<=10000;i++)
	{
		for(int j=0;j<num[i];j++)
		{
			cout << i <<"\n";
		}
	}
}

int main()
{
	solve();
}