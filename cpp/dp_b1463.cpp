#include <iostream>
using namespace std;

int minArr[10000001];



int check_min(int a, int b)
{
	return (a >= b) ? b : a;
}


int makeOne(int i,int n)
{	
	int findMinArr[3];

	if(n % 6 == 0)
	{
		findMinArr[]
	}
	if(n % 2 == 0)
	{
		findMinArr[1] = minArr[n/2];
	}
	findMinArr[2] = minArr[n-1];
	
	min = findMin(findMinArr);
	if(min != 0)
	{
		minArr[n] = min + 1;
	}

}




int main()
{
	int n = 0;
	cin >> n;
	makeOne(2, n);
	cout << minArr[n];
	


}

