#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;
const int MAX = 1001;
class Graph
{
public:
	int visit[MAX];
	vector<int> houseSet;
	vector<vector<int>> graph;
	Graph()
	{
	}
	void connectEdge(int v,int e);

	void BFS(int startV);


};



void Graph::connectEdge(int v,int e)
{
	
	graph[v].push_back(e);
	if(v == e)
	{
		return;
	}
	graph[e].push_back(v);
}



void Graph:: BFS(int startV)
{
	queue<int> que;
	que.push(startV);
	int curV = 0;
	int notVisited = 1;
	int houseCount = 1;
	//cout << "BFS 시작점 : " << startV<< "\n연결된 개수 = ";
	//cout << graph[startV].size() << "\n";
	visit[startV] = true;

	if(graph[startV].size() == 1)
	{

		houseSet.push_back(houseCount);
		return;
	}
	while(que.size()!=0)
	{
		curV = que.front();
		que.pop();
		////cout << "현재 정점 : " << curV << "\n";
		for(int i=0;i<graph[curV].size();i++)
		{
			
			if(visit[graph[curV][i]] == 1)
			{
				continue;
			}
			////cout << "이어진 점 : " << graph[curV][i] << "\n";

			notVisited = 0;
			que.push(graph[curV][i]);
			visit[graph[curV][i]] = true;

			////cout << "카운트 증가 : "<< graph[curV][i] << "\n\n";
			houseCount++;
		}
	}
	////cout << "집단의 집 수 : " << houseCount << "\n";
	if(!notVisited)
	{
		houseSet.push_back(houseCount);
		//doSomeThing(); 
	}

}


void solve()
{
	int n = 0;
	cin >> n;
	Graph g = Graph();
	fill_n(g.visit,MAX,0);
	int maxSize = n*n;
	int num[maxSize];
	for(int i=0;i<maxSize;i++)
	{
		vector<int> nodeTmp;
		g.graph.push_back(nodeTmp);
	}

	int idx = 0;

	for(int row = 0;row < n;row++)
	{
		char input[n];
		char prevRow[n];

		//////cout << "*" << row+1 << "번째 줄\n";
		for(int cal = 0;cal<n;cal++,idx++)
		{
			cin >> input[cal];
			//가로 
			//현재가 1
			if(input[cal] - '0')
			{
				//맨 앞부분
				if(cal == 0)
				{
					//cout << "사이클 만듦\n";
					g.connectEdge(idx,idx);
				}
				else if(input[cal-1] - '0')
				{
					g.connectEdge(idx,idx-1);
				}
				else
				{
					//cout << "사이클 만듦2\n";
					g.connectEdge(idx,idx);
				}
				//cout << idx <<"입니다\n";
			}
			
			if(row != 0)
			{
				if(prevRow[cal]  - '0' && input[cal] - '0')
				{
					g.connectEdge(idx - n,idx);
				}

			}
			//위만 봐줌
		}

		for(int i=0;i<n;i++)
		{
			prevRow[i] = input[i];
		}

	}
	for(int i=0;i<maxSize;i++)
	{
		if(g.visit[i])
		{
			continue;
		}
		g.BFS(i);
	}
	cout << g.houseSet.size() <<"\n";
	sort(g.houseSet.begin(),g.houseSet.end());
	if(g.houseSet.size() == 0)
	{
		cout << "0";
	}
	for(int i=0;i<g.houseSet.size();i++)
	{
		cout << g.houseSet[i] << "\n";
	}

}

int main()
{
	solve();

}
