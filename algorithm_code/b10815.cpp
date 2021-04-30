#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
vector<int> num;
void binSearch(int N)
{
	int i,j;
	int M = 0;
	int mid = 0;
	int obj;
	int searched = 0;
	cin >> M;
	for(int k=0;k<M;k++)
	{
		cin >> obj;
		if(k != 0)
		{
			cout << " ";
		}
		i = 0;
		j = N-1;
		searched = false;
		while(i <= j)
		{
			mid = (i + j)/2;
			if(obj == num[mid])
			{
				searched = 1;
				break;
			}
			else if(obj > num[mid])
			{
				i = mid+1;
			}
			else
			{
				j = mid-1;
			}
		}
		cout << searched;
	}

}
void solve()
{
	int obj;
	int val = 0;
	int N;

	cin >> N;
	for(int i=0;i<N;i++)
	{
		cin >> val;
		num.push_back(val);
	}
	sort(num.begin(),num.end());
	binSearch(N);

}

int main()
{
	ios_base::sync_with_stdio(0);
    cin.tie(0);
	solve();
}