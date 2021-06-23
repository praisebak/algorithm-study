#include <stdio.h>

int main()
{
	FILE *fr = fopen("input.txt","r");
	char buf[100];
	fgets(buf,sizeof(buf),fr);
	printf("%s",buf);
	fgets(buf,sizeof(buf),fr);
	printf("%s",buf);




}