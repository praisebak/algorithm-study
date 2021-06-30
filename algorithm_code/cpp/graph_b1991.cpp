#include <iostream>

using namespace std;

int tree[26][2];

void init()
{
	int n = 0;
	char rootVal,leftVal,rightVal;
	cin >> n;
	for(int i=0;i<n;i++)
	{
		cin >> rootVal >> leftVal >> rightVal;
		tree[rootVal-'A'][0] = leftVal;	
		tree[rootVal-'A'][1] = rightVal;
	}
}

void preOrder(char root)
{
	if(root == '.')
	{
		return;
	}
	cout << root;
	preOrder(tree[root-'A'][0]);
	preOrder(tree[root-'A'][1]);
}

void inOrder(char root)
{
	if(root == '.')
	{
		return;
	}
	inOrder(tree[root-'A'][0]);
	cout << root;
	inOrder(tree[root-'A'][1]);
}

void postOrder(char root)
{
	if(root == '.')
	{
		return;
	}
	postOrder(tree[root-'A'][0]);
	postOrder(tree[root-'A'][1]);
	cout << root;
}


void solve()
{
	preOrder('A');
	cout << "\n";
	inOrder('A');
	cout << "\n";
	postOrder('A');


}

int main()
{
	init();
	solve();

}