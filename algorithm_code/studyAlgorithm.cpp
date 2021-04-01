#include <iostream>

using namespace std;


int main()
{
	int arr[8] = {-7,4,-3,6,3,-8,3,4};

	int N = 8,ret = -1000;
	for(int i=0;i<N;++i)
	{
		for(int j=i;j<N;j++)
		{
			int sum = 0;
			for(int k =i; k<=j;++k)
			{
				sum += arr[k];

			}
			ret = max(ret,sum);

		}
	}
	cout << ret <<"\n";

}