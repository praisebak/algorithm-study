#include <stdio.h>

/*
def calBiggerThanTen():
	for i in range(data):
		if data[i] > 10:
			addDataToResult()
	printResult()

*/

void cal()
{

	int data[ ] = {20, 9, 5, 8, 28, 30, 3, 11, 4, 17};
	int sum = 0;
	int i = 0;
	for(i=0;i<sizeof(data)/sizeof(int);i++)
	{
		if(data[i] > 10)
		{
			sum += data[i];
		}
	}

	printf("합 : %d",sum);	
}

int main()
{
	cal();
	return 0;

}

