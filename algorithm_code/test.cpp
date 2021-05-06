#include <stdio.h>
<<<<<<< HEAD
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

=======
#include <math.h>

void hanoi(int n, int from, int by, int to) {
	if (n == 1)
		printf("%d %d\n", from, to);
	else {
		hanoi(n - 1, from, to, by);
		printf("%d %d\n", from, to);
		hanoi(n - 1, by, from, to);
	}
}

int main() {
	int N;
	int K, A, B;

	scanf("%d", &N);
	
	K = pow(2, N) - 1;
	printf("%d\n", K);

	hanoi(N, 1, 2, 3);

	return 0;
}
>>>>>>> e69e709efa1a20f6f43a052254af85f783cba7e6
