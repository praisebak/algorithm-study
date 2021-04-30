#include <iostream>
#include <stack>
#include <vector>
using namespace std;
const int MAX = 1001;


bool visit[MAX];

class Graph
{
public:
	int maxV = 0;
	vector<int> graph[MAX];
	void init();
	void add(int vertex,int edge);
	void DFS(int startV);
	void getConnectedComponent();
};


void Graph :: DFS(int startV)
{
	stack<int> s;
	int curV = 0;
	visit[startV] = true;
	s.push(startV);
	while(s.size() != 0)
	{           
		curV = s.top();
		s.pop();

		visit[curV] = true;
		for(int i=0;i<graph[curV].size();i++)
		{
			if(visit[graph[curV][i]])
			{
				continue;
			}
			s.push(graph[curV][i]);
		}
	}
}

void Graph :: init()
{
	int n,m,e,v;
	cin >> n >> m;
	this->maxV = n; 
	for(int i=1;i<=m;i++)
	{
		cin >> v >> e;
		this->graph[v].push_back(e);
		this->graph[e].push_back(v);
	}

 	
}


void Graph :: getConnectedComponent()
{
	int vertex = 0;
	int result = 0;
	this-> init(); 

	for(int i=1;i<=this->maxV;i++)
	{
		if(!visit[i])
		{
			DFS(i);
			result++;

		}
	}
	cout << result;


}



int main()
{
	Graph g = Graph();
	g.getConnectedComponent();
}

