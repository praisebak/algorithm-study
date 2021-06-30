#include <iostream>
using namespace std;

int x[100001] = {0};
int y[100001] = {0};
int sortedX[100001] = {0};
int sortedY[100001] = {0};



void merge(int start,int end)
{

	int mid = (start+end)/2;
	int i = start;
	int j = mid+1;
	int k = start;

	
	while(i<=mid && j<=end)
	{


		if(x[i] > x[j])
		{
			sortedX[k] = x[j];
			sortedY[k++] = y[j++];
		}
		else if(x[i] < x[j])
		{
			sortedX[k] = x[i];
			sortedY[k++] = y[i++];
		}
		else
		{
			if(y[i] > y[j])
			{
				sortedX[k] = x[j];
				sortedY[k++] = y[j++];
			}
			else
			{
				sortedX[k] = x[i];
				sortedY[k++] = y[i++];
			}
		}

		

	}

	

	if(i > mid)
	{
		for(int l=j;l<=end;l++)
		{
			sortedX[k] = x[l];
			sortedY[k++] = y[l];
		}
	}


	if(j > end)
	{
		
		for(int l=i;l<=mid;l++)
		{
			sortedX[k] = x[l];
			sortedY[k++] = y[l];
		}
	}

	
	for(int i=start;i<=end;i++)
	{	
		x[i] = sortedX[i];
		y[i] = sortedY[i];
		
	}

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

int main()
{
	int n = 0;
	cin >> n;
	int ansIdx[n+1] = {0};
	for(int i=1;i<=n;i++)
	{
		cin >> x[i] >> y[i];
	}

	mergeSort(1,n);

	
	for(int i=1;i<=n;i++)
	{
		cout << x[i] << " " << y[i]  << '\n';
	}
}
