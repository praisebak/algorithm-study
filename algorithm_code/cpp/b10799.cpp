#include <iostream>
using namespace std;
void solve()
{
	string problem;
	cin >> problem;
	int result = 0;
	int leftCount = 0;
	int cutedSize = 0;
	for(int i=0;i<problem.length()-1;i++)
	{	

		if(problem[i] == '(' && problem[i+1] != ')')
		{
			//cout << "start idx = " << i << " \n"; 
			leftCount = 1;
			cutedSize = 0;
			int j = i+1;
			for(;leftCount!=0;j++)
			{

				if(problem[j] == '(' && problem[j+1] == ')')
				{
					//cout << "중복된 개수 = " << leftCount << "\n";
					cutedSize += leftCount;
					//cout << "잘린 개수 = " << cutedSize << "\n";

					j++;
					continue;
				}

				if(problem[j] == '(')
				{
					leftCount++;
					continue;
				}
				else
				{
					leftCount--;
					cutedSize++;
					continue;
				}


				

			}
			result += cutedSize;
			i=j-1;
			//cout << "end idx = " << j <<"\n";
			
			
		}




	}

	cout << result;

}

int main()
{
	solve();
}