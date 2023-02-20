#include <iostream>
#include <stdlib.h>
using namespace std;
int num[5000001];
int sorted[5000001];

void mergeSort(int start,int end);
void merge(int start,int end);
void solve()
{
	int n = 0;
	int k = 0;
	cin >> n >> k;
	for(int i=1;i<=n;i++)
	{
		cin >> num[i];
	}

	mergeSort(1,n);

	cout << num[k];
}

void mergeSort(int start,int end)
{
	if(end > start)
	{
		int mid = (start + end) / 2;
		mergeSort(start,mid);
		mergeSort(mid+1,end);
		merge(start,end);
	}
}

void merge(int start,int end)
{
	int i = start;
	int k = start;
	int mid = (start+end)/2;
	int j = mid+1;

	while(i<=mid && j<=end)
	{
		if(num[i] > num[j])
		{
			sorted[k++] = num[j++];
		}
		else
		{
			sorted[k++] = num[i++];
		}

	}

	if(i > mid)
	{
		while(j<=end)
		{
			sorted[k++] = num[j++];
		}
	}
	else
	{
		while(i<=mid)
		{
			sorted[k++] = num[i++]; 
		}

	}

	for(int l=start;l<=end;l++)
	{
		num[l] = sorted[l];
	}
}


int main()
{
	cin.tie(NULL);	
	ios_base::sync_with_stdio(false);
	
	solve();
}