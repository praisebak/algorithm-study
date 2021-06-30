#include <iostream>
using namespace std;
const long long MAX = 10000;
long long line[MAX];
long long count = 0;

long long resultLen = 0;

long long binarySearch(long long start,long long end,long long objVal)
{
	long long divUnit = (start + end) / 2;
	long long lineCount = 0;
	if(start > end)
	{
		return 0;
	}
	for(long long i=0;i<count;i++)
	{
		lineCount += (line[i] / divUnit);
	}

	if(lineCount >= objVal)
	{
		if(resultLen < divUnit)
		{
			resultLen = divUnit;
		}

		binarySearch(divUnit+1,end,objVal);
		return divUnit;
	}
	else
	{
		return binarySearch(start,divUnit-1,objVal);
	}
}

void solve()
{
	long long haveLine,makeLine;
	cin >> haveLine >> makeLine;
	long long maxLen = 0;
	long long result = 0;
	for(count=0;count<haveLine;count++)
	{
		cin >> line[count];
		result += line[count];
		maxLen = max(maxLen,line[count]);
	}
	binarySearch(1,maxLen,makeLine);
	cout << resultLen;
}


int main()
{	
	solve();

}