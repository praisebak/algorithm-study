#include <iostream>
using namespace std;


void solve(int n)
{

	int leftCount = 0;
	int rightCount = 0;
	string testCase[n+1];


	for(int i=1;i<=n;i++)
	{
		leftCount = 0;
		rightCount = 0;
		cin >> testCase[i];
		for(int idx=0;idx<testCase[i].length();idx++)
		{
			if(rightCount > leftCount)
			{
				leftCount = -1;
				break;


			}
			if(testCase[i][idx]=='(')
			{
				leftCount++;
			}
			if(leftCount == 0)
			{
				leftCount = -1;
				break;
			}
			if(testCase[i][idx] == ')')
			{
				rightCount++;
			} 


		}

		if(leftCount == rightCount)
		{
			cout << "YES\n";
		}
		else
		{
			cout << "NO\n";
		}




	}

}


int main()
{
	int n = 0;
	cin >> n;
	solve(n);

}