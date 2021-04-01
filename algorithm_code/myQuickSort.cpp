#include <iostream>
using namespace std;

void swap(int *num, int idx1,int idx2)
{
	int tmp = 0;
	tmp = num[idx1];
	num[idx1] = num[idx2];
	num[idx2] = tmp;
}
/*

10
2 7 1 3 4 0 8 5 9 6
*/
int quickSort(int * num,int right,int n)
{	
	int pivot = num[right];
	int i = 0;
	int j = right;

	if(j > 1)
	{
		while(i < j)
		{
			while(pivot > num[++i]);
			while(pivot < num[--j]);
			if(i >= j)
			{
				break;
			}
			
			swap(num,i,j);
		}
		swap(num,right,i);
		quickSort(num+i,right-i,n);
		quickSort(num,i-1,n);
	}

	

}

void solve(int n)
{
	int num[n+1] = {0};
	int tmp = 0;
	int pivotIdx = 0;
	int right = n;
 
 	for(int i=1;i<=n;i++)
	{
		cin >> num[i];
	}
	quickSort(num,n,n);
	for(int i=1;i<=n;i++)
	{
		cout << num[i] << " ";
	}
		
}


int main()
{
	int n = 0;
	cin >> n;
	solve(n);
}