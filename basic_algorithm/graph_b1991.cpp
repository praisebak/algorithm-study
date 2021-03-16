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
	preOrder(root  )
}

void inOrder(char root)
{

}

void postOrder(char root)
{

}


void solve()
{
	preOrder('A');
	inOrder('A');
	postOrder('A');


}

int main()
{
	init();
	solve();

}