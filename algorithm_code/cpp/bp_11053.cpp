#include <iostream>
using namespace std;

void solve(int n)
{
	int numArr[n+1] = {0};
	int lenArr[n+1] = {0};
	for(int i=1;i<=n;i++)
	{
		cin >> numArr[i];
	}

	//cout << "***\n";
	//처음 시작대상
	lenArr[1] = 1;
	int subVal = 0;
	int curVal = 0;
	int count = 0;
	int max = 1;
	for(int i=2;i<=n;i++)
	{
		subVal = numArr[i];
		count = 0;
		for(int j=1;j<=i;j++)
		{
			curVal = numArr[j];
			if(subVal > curVal)
			{
				if(count < lenArr[j])
				{
					count = lenArr[j];
				}
			}
		}
		lenArr[i] = count + 1;
		if(lenArr[i] > max)
		{
			max = lenArr[i];
		}
	}
	cout << max << endl;
}
/*
5
574 155 634 519 600

45
574 155 634 519 872 391 918 240 108 96 504 323 266 170 295 449 653 323 12 365 747 825 519 207 565 304 568 868 82 641 506 937 503 144 738 70 59 684 332 75 142 869 487 753 862
*/
int main()
{
	int n = 0;
	cin >> n;
	solve(n);

}