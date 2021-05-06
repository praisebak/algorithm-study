#include <stdio.h>

/*
num = getNumber();
if(num % 3 == 0 AND num % 6 == 0)
{
	

}
*/

int main()
{
	int num;
	scanf("%d",&num);

	if(num != 0 && num % 3 == 0 && num % 6 == 0)
	{
		printf("3의 배수이면서 6의 배수입니다.");

	}
	else
	{
		printf("3의 배수이면서 6의 배수가 아닙니다.");
	}

}