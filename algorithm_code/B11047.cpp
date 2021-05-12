#include <iostream>

using namespace std;

const int MAX = 10;
int coinVal[MAX];
int N;
int objVal;
void greedy()
{
	int sum = 0;
	int valIdx = N-1;
	int countAddCoin = 0;
	while(objVal != sum)
	{
		if(sum + coinVal[valIdx] > objVal)
		{
			valIdx--;
		}
		else
		{
			sum += coinVal[valIdx];
			countAddCoin++;
		}
	}	
	cout << countAddCoin;
}


void solve()
{
	cin >> N >> objVal; 
	for(int i=0;i<N;i++)
	{
		cin >> coinVal[i];
	}
	greedy();
}	


int main()
{

	solve();
}