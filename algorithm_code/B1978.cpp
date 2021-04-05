#include <iostream>
const int MAX = 1001;
using namespace std;


void solve()
{
	int N,curNum,count = 0;
	int objNum;
	cin >> N; 
	for(int i=0;i<N;i++)
	{
		cin >> curNum;
		if(curNum == 2)
		{
			count++;
		}
		for(int i=2;i<curNum;i++)
		{
			if(curNum % i == 0)
			{
				break;
			}
			if(i+1 == curNum)
			{
				count++;
			}
		}
	}
	cout << count;

}
int main()
{
	solve();
}	