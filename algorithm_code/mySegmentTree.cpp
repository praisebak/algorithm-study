#include <iostream>
const int MAX = 10001;
int N;
int arr[MAX];
int tree[MAX];
using namespace std;

void init()
{
	cin >> N;
	for(int i=0;i<N;i++)
	{
		cin >> arr[i];
	}
}

int initTree(int start,int end,int node)
{
	if(start == end)
	{
		return tree[node] = arr[start];
	}
	int mid = (start + end)/2;
	return tree[node] = initTree(start,mid,node*2) + initTree(mid+1,end,node*2+1);

}

void sum(int start, int end, int node, int left,int right)
{
	if(left > end || right < start)
	{
		return 0;
	}
	if(left <= start && end <= right)
	{
		return tree[node];
	}
	sum(start,mid,node * 2,left,right) + sum(mid+1,end,node*2 + 1,left,right);
}

void updateTree(int start,int end,int node,int idx,int changeVal)
{
<<<<<<< HEAD:basic_algorithm/mySegmentTree.cpp
	if(idx < start || idx > end)
	{
=======
	if(tree[idx] == -1)
	{                                                     
>>>>>>> f8210aa42ca46c45265f7c76884a4fb866301fa2:algorithm_code/mySegmentTree.cpp
		return;
	}
	tree[node] += changeVal;
	if(start == end)
	{
		return;
	}
	int mid = (start + end)/2;
	update(start,mid,node * 2,idx,changeVal);
	update(mid+1,end,node * 2 + 1,idx,changeVal);



}

int main()
{
	init();
	initTree(0,N-1,1);
}