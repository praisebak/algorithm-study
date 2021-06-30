#include <iostream>
using namespace std;

int num[10000001];
int numFreq[10000001];

void countingSort()
{

	
}

int main()
{
	int n = 0;
	cin >> n;
	for(int i=1;i<=n;i++)
	{
		cin >> num[i];
		numFreq[num[i]]++;
	}
}
