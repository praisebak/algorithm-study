#include <iostream>
using namespace std;
const int MAX = 10001;
int arr[MAX];
int N;
int tree[MAX];

void init()
{
	cin >> N;
	for(int i=0;i<N;i++)
	{
		cin >> arr[i];
	}
}

int makeSegmentTree(int start,int end,int node)
{
	if(start == end)
	{
		return tree[node] = arr[node];
	}
	int mid = (start+mid)/2;
	return tree[node] = makeSegmentTree(start,mid,node * 2) + makeSegmentTree(mid+1,end,node * 2 + 1);

}

int sumSegmentTree(int start,int end,int node,int left,int right)
{
	if(left > end || right < start)
	{
		return 0;
	}
	if(left <= start && end <= right)
	{
		return tree[node];
	}
	int mid = (start + end)/2;
	return sumSegmentTree(start,mid,node*2,left,right) + sumSegmentTree(mid+1,end,node*2+1,left,right);
}

void updateSegmentTree(int start,int end,int node,int changeVal,int idx)
{
	if(start > idx || end < idx)
	{
		return;
	}
	tree[node] += changeVal;

	if(start == end)
	{
		return;
	}
	int mid = (start + end) / 2;
	updateSegmentTree(start,mid,node*2,changeVal,idx);
	updateSegmentTree(mid+1,end,node*2+1,changeVal,idx);
}

int main()
{
	init();
	makeSegmentTree(0,N-1,1);

}