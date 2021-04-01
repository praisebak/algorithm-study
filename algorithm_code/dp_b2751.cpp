#include <iostream>
#define _CRT_SECURE_NO_WARNINGS
using namespace std;


/*
10
2 7 1 3 4 0 8 5 9 6

8
21 10 12 20 25 13 15 22
*/

int sorted[1000000];

void merge(int * arr,int start,int end)
{
	int mid = (start+end)/2;
	int i = start;
	int j = mid+1;
	int k = start;
	while(i <= mid && j <= end)
	{
		if(arr[i] > arr[j])
		{
			sorted[k++] = arr[j++];
		}
		else
		{
			sorted[k++] = arr[i++];
		}
	}

	if(i > mid)
	{
		while(j <= end)
		{
			sorted[k++] = arr[j++];
		}
	}
	if(j > end)
	{
		while(i <= mid)
		{
			sorted[k++] = arr[i++];
		}
	}
	for(int i=start;i<=end;i++)
	{
		arr[i] = sorted[i];
	}
}


void mergeSort(int * arr,int start,int end)
{		

	
	int mid = (end + start)/2 ;
	if(start < end)
	{
		mergeSort(arr,start,mid);
		mergeSort(arr,mid+1,end);
		merge(arr,start,end);
	}
	

}

void solve(int n)
{
	int start = 1;
	int end = 1;
	int num[n+1] = {0};
	for(int i=1;i<=n;i++)
	{
		cin >> num[i];
	}
	mergeSort(num,1,n);
	for(int i=1;i<=n;i++)
	{
		cout << num[i] <<'\n';
	}
}


int main()
{
	cin.tie(NULL);
    ios::sync_with_stdio(false);
	int n = 0;
	cin >> n;
	solve(n);
}