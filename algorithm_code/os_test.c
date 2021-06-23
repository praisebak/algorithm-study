#include <stdio.h>
#define KERNBASE 0x80000000

#define P2V(a) (((void *) (a)) + KERNBASE)
//#define KERNBASE 0x80000000

void solve()
{


}

int main()
{	

	unsigned int PD_MASK = 0b00110111000000;
	char * v = P2V(PD_MASK);



}