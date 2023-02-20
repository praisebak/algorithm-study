#include <iostream>
#include <vector>
#include <queue>
const int MAX = 20001;
using namespace std;

class Graph
{

public:
	int testCase = 0;
	vector<vector<int>> graph;
	int * color = NULL;
	int * mark = nullptr;
	char * TMP;
	int maxV = 0;
	Graph()
	{


	}
	Graph(int maxV)
	{
		this->maxV = maxV;
		for(int i=0 ;i<= maxV;i++)
		{
			vector<int> tmp;
			graph.push_back(tmp);
		}
	}

	int solve();
	int BFS(int startV,int curColor);
	void add(int v, int e);

};

int Graph :: BFS(int startV,int curColor)
{
	int curV = 0;
	int vertex = 0;
	queue<int> que;
	que.push(startV);
	//현재 시작정점의 인접 그래프의 색깔이

	color[startV] = curColor;

	mark[startV] = 1;

	while(que.size() != 0)
	{

		curV = que.front();
		que.pop();
		//cout << "\n";
		//cout << "현재 정점 : " << curV << "\n";
		//cout << " 정점 색깔 : " << color[curV] << "\n";
		//cout << "현재 정점과 연결된 정점 수 : " << graph[curV].size() <<"\n";
		for(int i=0;i<graph[curV].size();i++)
		{
			//cout << "연결된 vertex : " << graph[curV][i] << "\n";
			vertex = graph[curV][i];
			if(mark[vertex] && (color[vertex] == color[curV]))
			{
				//cout << "리턴 0\n";
				return 0;
			}
			else if(mark[vertex] == 0)
			{
				color[vertex] = !color[curV];
				//cout << "인접 정점 색깔 : " << color[vertex] << "\n";
				mark[vertex] = 1;
				que.push(vertex);
			}
			else
			{
				//cout << "그 외" << mark[vertex];
			}
			
		}
	}
	return 1;
}



void Graph :: add(int v, int e)
{
	////cout << "V,E 연결\n";
	////cout << v << ',' << e <<"\n";
	this->graph[v].push_back(e);
	this->graph[e].push_back(v);
}

void solve()
{
	int testCase = 0;
	int result = 0;
	int color = 1;
	Graph g = Graph();
	cin >> testCase;
	for(int i=0;i<testCase;i++)
	{
		int n,m = 0;
		int v,e = 0;
		cin >> n >> m;
		g = Graph(n);
		int markTmp[g.maxV+1];
		int colorTmp[g.maxV+1];
		fill_n(markTmp,g.maxV+1,0);
		fill_n(colorTmp,g.maxV+1,0);
		g.mark = markTmp;
		g.color = colorTmp;
		for(int j=0; j<m;j++)
		{
			cin >> v >> e;
			g.add(v,e);
		}
		for(int i=1;i<g.graph.size();i++)
		{
			if(g.graph[i].size() == 0 || g.mark[i])
			{
				continue;
			}

			result = g.BFS(i,color);
			if(!result)
			{
				cout << "NO\n";
				break;
			}
		}
		if(result)
		{
			cout << "YES\n";
		}
	}
}


int main()
{
	solve();
}