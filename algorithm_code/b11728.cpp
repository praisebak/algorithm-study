#include <iostream>
using namespace std;
const int MAX =	1000000;
int arrA[MAX],arrB[MAX];
int resultArr[MAX];

void merge(int N,int M)
{
	int k = 0;
	int i,j;
	for(i=0,j=0;i < N && j < M;)
	{
		if(arrA[i] > arrB[j])
		{
			resultArr[k++] = arrB[j++];
		}
		else
		{
			resultArr[k++] = arrA[i++];
		}

	}
	while(i < N)
	{
		resultArr[k++] = arrA[i++];
	}
	while(j < M)
	{
		resultArr[k++] = arrB[j++];
	}


}

void solve()
{
	int N,M;
	cin >> N >> M;
	for(int i=0;i<N;i++)
	{
		cin >> arrA[i];
	}
	for(int i=0;i<M;i++)
	{
		cin >> arrB[i];
	}
	merge(N,M); 
	for(int i=0;i<N+M;i++)
	{
		cout << resultArr[i];
		if(i+1 != N+M)
		{


			cout << " ";
		}

	}

}


int main()
{
	ios_base :: sync_with_stdio(0);
	cin.tie(0);
	solve();

}