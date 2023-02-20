#include <iostream>
#include <vector>
#include <queue>
using namespace std;

const int MAX = 1001;
queue<pair<int,int>> q;
int graph[MAX][MAX];
int M = 0;
int N = 0;


bool leftCheck(int x,int y)
{
	if(x != 0 && graph[y][x-1] == 0)
	{
		return true;
	}
	return false;
}



bool rightCheck(int x,int y)
{
	if(x != N-1 && graph[y][x+1] == 0)
	{
		return true;
	}
	return false;

}


bool upCheck(int x,int y)
{
	if(y!=0 && graph[y-1][x] == 0)
	{
		return true;
	}
	return false;
}


bool downCheck(int x,int y)
{
	if(y+1 != M && graph[y+1][x] == 0)
	{
		return true;
	}
	return false;
}


void checkAndMark(int x,int y,int curDay)
{
	//cout << y+1 << " , " << x+1 << " : " << curDay << endl;
	if(leftCheck(x,y))
	{
		graph[y][x-1] = curDay+1;
		q.push(make_pair(y,x-1));
 	}
	if(rightCheck(x,y))
	{	
		graph[y][x+1] = curDay+1;
		q.push(make_pair(y,x+1));
	}
	if(upCheck(x,y))
	{	
		graph[y-1][x] = curDay+1;
		q.push(make_pair(y-1,x));
	}
	if(downCheck(x,y))
	{
		graph[y+1][x] = curDay+1;
		q.push(make_pair(y+1,x));
	}
}

void BFS()
{
	int curDay = 1;

	int x,y;

	while(q.size()!=0)
	{	
		y = q.front().first;
		x = q.front().second;
		q.pop();
		if(graph[y][x] == curDay)
		{
			checkAndMark(x,y,curDay);
		}
		else
		{
			curDay++;
			checkAndMark(x,y,curDay);
		}
	}
	for(int i=0;i<M;i++)
	{
		for(int j=0;j<N;j++)
		{
			if(graph[i][j] == 0)
			{
				cout << -1;
				return;
			}
			//cout << graph[i][j] << " ";
		}
		//cout << "\n";

	}
	cout << curDay-1;

}

int main()
{
	//가로 = N
	//세로 = M
	cin >> N >> M;
	for(int i=0;i<M;i++)
	{
		for(int j=0;j<N;j++)
		{
			cin >> graph[i][j];
			if(graph[i][j] == 1)
			{
				q.push(make_pair(i,j));

			}
		}
	}

	BFS();


}