#include <iostream>
using namespace std;

void solve()
{
	int n1,n2,n3;
	cin >> n1 >> n2 >> n3;
	cout << (n1+n2) % n3 << "\n";
	cout << ((n1 % n3) + (n2 % n3)) % n3 << "\n";
	cout << (n1 * n2) % n3 << "\n";
	cout << ((n1 % n3) * (n2 % n3)) % n3; 

}

int main()
{
	solve();
}