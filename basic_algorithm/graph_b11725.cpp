//PM 6:17
// 구상~ 
//PM 6:34
// 구현~
//PM 6:55
//완

#include <iostream>
#include <vector>
#include <queue>
using namespace std;
const int MAX = 100001;

vector<int> graph[MAX];
int visit[MAX];
int N;

void init()
{
	cin >> N;
	for(int i=0;i<N+1;i++)
	{
		visit[i] = 0;
	}
}

void edgeBiConnect(int v,int e)
{
	graph[v].push_back(e);
	graph[e].push_back(v);


}
//루트가 있을때 root -> e
void edgeOneConnect(int v)
{
	graph[1].push_back(v);
}

void BFS(int startV)
{
	queue<int> q;
	int curV;


	for(int i=0;i<graph[1].size();i++)
	{
		q.push(graph[1][i]);
		visit[graph[1][i]] = 1;
	}

	while(q.size() != 0)
	{
		curV = q.front();
		q.pop();

		for(int i=0;i<graph[curV].size();i++)
		{

			if(visit[graph[curV][i]] == 0)
			{
				q.push(graph[curV][i]);
				visit[graph[curV][i]] = curV;
			}
			
		}
	}


}

void solve()
{
	int v,e;
	for(int i=0;i<N;i++)
	{
		cin >> v >> e;
		if(v == 1)
		{
			edgeOneConnect(e);
		}
		else if(e == 1)
		{
			edgeOneConnect(v);
		}
		else
		{
			edgeBiConnect(v,e);
		}
	}
	BFS(1);
	for(int i=2;i<=N;i++)
	{
		cout << visit[i] << "\n";
	}
}


int main()
{
	init();
	solve();

}
