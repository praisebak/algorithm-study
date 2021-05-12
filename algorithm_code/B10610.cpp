#include <iostream>
#include <algorithm>
using namespace std;

const int MAX = 100000;
string N;
int num[MAX];
void greedy()
{


}

void solve()
{	
	cin >> N;
	int mul = 1;
	for(int i=0;i<N.size();i++)
	{
		num[i] = N[i] - '0';
		mul *= 10;
	}
	mul /= 10;
	sort(num,num+N.size());
	int result = 0;

	for(int i=N.size()-1;i> -1;i--)
	{
		result += mul * num[i];
		mul /= 10;
	}

	if(result % 30 != 0)
	{
		cout << -1;
		return;
	}
	cout << result;
	//greedy();


}

int main()
{
	solve();
	return 0;
}