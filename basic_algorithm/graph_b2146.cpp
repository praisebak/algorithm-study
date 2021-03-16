#include <iostream>
#include <queue>
#include <cstring>
using namespace std;

const int MAX = 100;
const int INF = 987654321;
int mapSize = 0;
int map[MAX][MAX];
bool visit[MAX][MAX];

//왼 오 상 하
int check[4][2] = {{0,-1},{0,1},{-1,0},{1,0}};
//섬 마킹




void BFSMarking(int startX,int startY,int numberOfLand)
{
	queue<pair<int,int>> q;
	q.push(make_pair(startX,startY));

	int nextX = 0;
	int nextY = 0;
	visit[startY][startX] = 1;
	map[startY][startX] = numberOfLand;
	
	while(q.size()!=0)
	{
		startX = q.front().first;
		startY = q.front().second;
		q.pop();
		map[startY][startX] = numberOfLand;
		visit[startY][startX] = 1;
		
		for(int i=0;i<4;i++)
		{
			nextY = startY + check[i][0];
			nextX = startX + check[i][1];
			if((nextX >= 0 && nextX <mapSize) && (nextY >= 0 && nextY <mapSize))
			{
				if(map[nextY][nextX] && !visit[nextY][nextX])
				{
					q.push(make_pair(nextX,nextY));
				}
			}
			
		}
	}

}

int BFS(int land)
{
	int count = 0;
	queue<pair<int,int>> q;
	int nextX,nextY,startX,startY;

	for(int i=0;i<mapSize;i++)
	{
		for(int j=0;j<mapSize;j++)
		{
			if(map[i][j] == land)
			{
				q.push(make_pair(j,i));
				//cout << "값 : " << map[i][j] << " land : " << land << "\n";
				//cout << i << " " << j << "pair 추가\n";
				visit[i][j] = 1;
			}
		}
	}	


	int qSize = 0;
	while(q.size() != 0)
	{
		qSize = q.size();
		for(int k=0;k<qSize;k++)
		{

			startX = q.front().first;
			startY = q.front().second;
			q.pop();
			for(int i=0;i<4;i++)
			{
				nextX = startX + check[i][1];
				nextY = startY + check[i][0];
				if((nextX >= 0 && nextX < mapSize) && (nextY >= 0 && nextY < mapSize))
				{
					
					if(map[nextY][nextX] && map[nextY][nextX] != land)
					{
						return count;
					}
					else if(!(map[nextY][nextX]) && !(visit[nextY][nextX]))
					{
						visit[nextY][nextX] = 1;
						q.push(make_pair(nextX,nextY));
					}
				}
			}
		}
		count++;
	} 




}

void init()
{
	cin >> mapSize;
	for(int i=0;i<mapSize;i++)
	{
		for(int j=0;j<mapSize;j++)
		{
			visit[i][j] = 0;
			cin >> map[i][j];
		}
	}
}

void solve()
{
	init();
	int curX,curY,numberOfLand = 1,result = INF;

	for(int i=0;i<mapSize;i++)
	{
		for(int j=0;j<mapSize;j++)
		{
			if(!visit[i][j] && map[i][j])
			{
				BFSMarking(j,i,numberOfLand);
				numberOfLand++;
			}

		}
	}


	for(int land=1; land<numberOfLand;land++)
	{
		fill(&visit[0][0], &visit[MAX-1][MAX] ,0);
		result = min(result,BFS(land));
	}
	cout << result;
}

int main()
{
	solve();

}


