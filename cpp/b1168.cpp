#include <iostream>
using namespace std;
const int MAX = 10001;
int data[MAX];
int N,K;
int * tree;

void initData()
{
	cin >> N >> K;
	int * tmp = (int *)malloc(sizeof(int) * ((N+1)* 4));
	fill_n(tmp,(N+1)*4,-1);
	tree = tmp;

}

int initTree(int start,int end,int node,int * tree)
{	
	if(start == end)
	{
		return tree[node] = 1;
	}
	int mid = (start + end) / 2;
	return tree[node] = initTree(start,mid,node*2,tree) + initTree(mid+1,end,node*2+1,tree);
}

int getIdx(int start,int end,int node,int K)
{
	if(start == end)
	{
		return start;
	}
	int mid = (start + end) / 2;
	if(K <= tree[node * 2])
	{
		return getIdx(start,mid,node * 2,K);
	}
	else
	{
		return getIdx(mid+1,end,node * 2 + 1,K - tree[node * 2]);
	}
}

void updateTree(int start,int end,int node,int idx)
{
	if(idx < start || idx > end)
	{
		return;
	}
	tree[node]--;
	if(start == end)
	{
		return;
	}
	int mid = (start + end) / 2;
	updateTree(start,mid,node*2,idx);
	updateTree(mid+1,end,node*2+1,idx);
}	

void solve()
{	
	int deleteIdx = 0;
	int findIndex = 1;
	int remainNum = 0;

	initData();
	initTree(1,N,1,tree);
	cout << '<';
	for(int i=0;i<N;i++)
	{
		remainNum = N - i;
		findIndex += K - 1;
		if(findIndex % remainNum == 0)
		{
			findIndex = remainNum;
		}
		else if(findIndex > remainNum)
		{
			findIndex = findIndex % remainNum;
		}
		deleteIdx = getIdx(1,N,1,findIndex);
		updateTree(1,N,1,deleteIdx);
		cout << deleteIdx;
		if(i != N-1)
		{
			cout << ", ";
		}
		else
		{
			cout << ">";
		}
	}


}

int main()
{
	initData();
	solve();

}