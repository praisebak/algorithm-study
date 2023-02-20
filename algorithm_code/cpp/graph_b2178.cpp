#include <iostream>
#include <vector>
#include <queue>
using namespace std;
const int MAX = 100;
int map[MAX][MAX];
int visit[MAX][MAX];
queue<pair<int,int>> q;
//left right up down
int insideCheckArr[4][2] = {{0,-1},{0,1},{-1,0},{1,0}};
//N = 세로 M = 가로
int N, M;



void checkInsideNMark(int startX,int startY)
{
	for(int i=0;i<4;i++)
	{
		int nextX = startX + insideCheckArr[i][1];
		int nextY = startY + insideCheckArr[i][0];
		if(!visit[nextY][nextX] && map[nextY][nextX])
		{
			if((nextX >= 0 &&  nextX < M) && (nextY >= 0 && nextY <N))
			{
				//cout << "응애\n";
				visit[nextY][nextX] = visit[startY][startX] + 1;
				q.push(make_pair(nextX,nextY));
			}
		}
		

	}
}



void BFS()
{
	int startX = 0,startY = 0;
	visit[0][0] = 1;
	q.push(make_pair(startX,startY));

	while(q.size()!= 0)
	{	
		startX = q.front().first;
		startY = q.front().second;
		q.pop();
		checkInsideNMark(startX,startY);
	}
	cout << visit[N-1][M-1];


}

void graphInit()
{
	cin >> N >> M;

	for(int i=0;i<N;++i)
	{
		string str;
		cin >> str;
		for(int j=0;j<str.size();++j)
		{
			visit[i][j] = 0;
			map[i][j] = str[j] - '0';
		}
	}
	//cout << "입력한거 봐라 응애\n";
	for(int i=0;i<N;++i)
	{
		for(int j=0;j<M;++j)
		{
			//cout << map[i][j] << " ";

		}
		//cout << "\n";
	}

}


void solve()
{
	graphInit();
	BFS();

}



int main()
{
	solve();

}

