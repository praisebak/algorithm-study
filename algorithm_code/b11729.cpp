#include <iostream>
#include <cmath>
#define UM 13000
using namespace std;


void hanoi(int n,int from,int by,int to) 
{
	if(n == 1)
	{
		cout << from << " " << to << "\n";
	}
	else
	{
		hanoi(n-1,from,to,by);
		cout << from << " " << to << "\n";
		hanoi(n-1,by,from,to);

	}
}

void solve()
{
	int N;
	int K;
	cin >> N;
	K = pow(2,N) -1;

	cout << K << "\n";
	hanoi(N,1,2,3);
}

int main()
{
	//solve();
	UM =
 	cout << UM;

}