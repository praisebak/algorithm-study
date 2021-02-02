#include <iostream> 
using namespace std;
//23 시작

int tile[1001];


void tiling(int n)
{
	tile[n] = (tile[n-1] + tile[n-2])% 10007;
}

int main()
{	

	int n = 0;
	cin >> n;
	tile[1] = 1;
	tile[2] = 2;
	for(int i=3;i<=n;i++)
	{
		tiling(i);
	}
	cout << tile[n];
}