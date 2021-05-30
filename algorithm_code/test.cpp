#include <stdio.h>
#include <math.h>


int power(int a, int b)
{

     return pow(a, b);

}
int main()
{
    int a, b, sum = 0, i;

    printf("n 과 k를 입력하시오. ");
    scanf(" %d %d", &a, &b);

    for (i = 1; i <= a; i++)
    {
        sum = sum + power(a, b);
    }


    printf("1~n까지 모두 k승을 하여 더한 값 = %d", sum);

    return 0;
}


