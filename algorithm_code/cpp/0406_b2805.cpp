#include <iostream>
using namespace std;
const long long MAX = 1000000;
const long long INF = 987654321;
long long tree[MAX];
long long count;
long long maxCuttingHeight = 0;

bool isCutted(long long idx,long long cuttingHeight)
{
	if(tree[idx] > cuttingHeight)
	{
		return true;
	}
	return false;
}

void binarySearch(long long start,long long end,long long wantedHeight)
{

	if(start > end)
	{
		cout << maxCuttingHeight;
		return;
	}
	long long cuttingHeight = (start + end) / 2;
	long long cutResult = 0;
	for(long long i=0;i<count;i++)
	{
		if(isCutted(i,cuttingHeight))
		{
			cutResult += tree[i] - cuttingHeight;
		}
	}
	if(wantedHeight <= cutResult)
	{
		maxCuttingHeight = max(maxCuttingHeight,cuttingHeight);
		binarySearch(cuttingHeight+1,end,wantedHeight);
	}
	else
	{
		binarySearch(start,cuttingHeight-1,wantedHeight);
	}




}


void solve()
{
	long long N,wantedHeight;
	long long maxHeight = 0;
	cin >> N >> wantedHeight;

	for(count=0;count<N;count++)
	{
		cin >> tree[count];
		maxHeight = max(maxHeight,tree[count]);
	}
	binarySearch(0,maxHeight,wantedHeight);
}

int main()
{
	solve();
}