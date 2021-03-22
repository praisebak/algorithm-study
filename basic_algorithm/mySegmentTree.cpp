#include <iostream>
using namespace std;

const int N = 10;

int testArr[] = {3,2,1,4,5,6,9,8,7,10};
int tree[4 * N];

int segmentTreeInit(int start,int end,int node)
{
	if(start == end)
	{
		return tree[node] = testArr[start];
	}
	int mid = (start + end) / 2;
	//왼쪽 + 오른쪽
	return tree[node] = segmentTreeInit(start,mid,node * 2) + segmentTreeInit(mid+1,end,node*2 +1);
}


void treeInorder(int idx)
{
	if(tree[idx] == -1)
	{                                                     
		return;
	}
	cout << tree[idx] << "\n";

	treeInorder(idx*2);
	treeInorder(idx*2+1);
}


int main()
{
	fill_n(tree,4 * N, -1);
	segmentTreeInit(1,N-1,1);

	for(int i=0;i<N;i++)
	{
		cout << tree[i] << " ";
	}
	treeInorder(1);
}
