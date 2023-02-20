#include <iostream>
const int MAX = 1000001;
bool check[MAX];
	
using namespace std;

void solve()
{
	fill_n(check,MAX,false);
	check[1] = true;
	int M,N;

	cin >> M >> N;
	for(int i=2;i<=N;i++)
	{
		for(int j=2; i*j <= N;j++)
		{
			check[i*j] = true;
		}
	}

	for(int i=M;i<=N;i++)
	{
		if(!check[i])
		{
			cout << i <<"\n";
		}
	}



}

int main()
{
	solve();
}