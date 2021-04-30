#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
const int MAX = 200001;
int home[MAX];
int maxLen = 0;
int countNum = 0;


int checkPosibleInstall(int distance)
{
	int count = 1;
	int curHome = home[0];
	for(int i=1;i<countNum;i++)
	{
		if(distance <= home[i] - curHome)
		{
			count++;
			curHome= home[i];
		}
	}
	return count;
}

void binarySearch(int start,int end,int haveToInstall)
{	
	if(start > end)
	{
		return;
	}
	int mid = (start + end) / 2;
	int installedCount = checkPosibleInstall(mid);
	if(installedCount >= haveToInstall)
	{
		maxLen = max(maxLen,mid);
		binarySearch(mid+1,end,haveToInstall);
	}
	else
	{
		binarySearch(start,mid-1,haveToInstall);		
	}



}

void solve()
{
	int N,installNum;
	cin >> N >> installNum;
	for(;countNum<N;countNum++)
	{
		cin >> home[countNum];
	}
	sort(home,home+countNum);
	int distance = home[countNum-1] - home[0];
	binarySearch(1,distance,installNum);
	cout << maxLen;
}


int main()
{
	solve();
}