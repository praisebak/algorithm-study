#include <stdio.h>

const double lightSpeed = 300000;
const double distance = 149600000;

void printLightArrive()
{

	printf("빛의 속도는 %.3f 이고\n태양과 지구와의 거리는 %.3f 일때,\n빛이 태양에서 지구에 도착하는 시간은 %.3f초 이다.\n"
		,lightSpeed,distance,distance/lightSpeed);

}

int main()
{
	printLightArrive();
	return 0;
}

