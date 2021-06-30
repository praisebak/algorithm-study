#include <iostream>
using namespace std;

char star[3072][6144];
int N;
void printStarResult()
{
	for(int i=0;i<N;i++)
	{
		for(int j=0;j<N*2-1;j++)
		{
			cout << star[i][j];
		}
		cout << "\n";
	}
}

void printStar(int height,int x,int y)
{

	if(height == 3)
	{
		star[y][x] = '*';
		star[y+1][x-1] = '*';
		star[y+1][x+1] = '*';
		star[y+2][x-2] = '*';
		star[y+2][x-1] = '*';
		star[y+2][x] = '*';
		star[y+2][x+1] = '*';
		star[y+2][x+2] = '*';
		//printStarResult(); 
		return;
	}
	printStar(height/2,x,y);
	printStar(height/2,x-(height/2),y+(height/2));
	printStar(height/2,x+(height/2),y+(height/2));


}

void solve()
{
	cin >> N;
	for(int i=0;i<N;++i)
	{
		for (int j = 0; j < 2* N; ++j)
		{
			if(j==2 * N -1)
			{
				star[i][j] = '\0';
			}
			else
			{
				star[i][j] = ' ';
			}
		}
	}
	printStar(N,N-1,0);

	printStarResult(); 

}


int main()
{
	solve(); 
}








