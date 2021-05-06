#include <stdio.h>
int main()
{
	unsigned int address = 0xFFFFFFFF;
	unsigned int SEG_MASK = 0xF0000000;
	unsigned int OFFSET_MASK = 0x0FFFFFFF;
	unsigned int shift = 28;
	unsigned int SEG = (address & SEG_MASK) >> shift;
	unsigned int OFFSET = address & OFFSET_MASK;

	printf("SEG : %.8x OFFSET : %.8x",SEG,OFFSET );

}

