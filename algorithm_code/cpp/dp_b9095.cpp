#include <iostream> 
using namespace std;

int numCount[12];

void initFibo()
{
	numCount[0] = 0;
	numCount[1] = 1;
	numCount[2] = 2;
	numCount[3] = 4;
}

int countAddCase(int num)
{	
	int result =0;
	if(numCount[num] != 0)
	{
		return numCount[num];
	}
	numCount[num] = countAddCase(num-3)+countAddCase(num-2)+countAddCase(num-1);
	return numCount[num];
}


int main()
{
	int testCase = 0;
	cin >> testCase;

	int originArr[testCase];
	int minArr[testCase];
	int minIdx=0;
	int num;

	for(int i=0;i<testCase;i++)
	{
		cin >> num;
		originArr[i] = num;
	}

	initFibo();
	for(int i=0;i<testCase;i++)
	{
		countAddCase(originArr[i]);
		cout << numCount[originArr[i]] <<'\n';
	}	

}