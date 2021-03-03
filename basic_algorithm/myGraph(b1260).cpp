#include <iostream>
#include <vector>
#include <queue>
#include <stack>
#include <algorithm>
#include <typeinfo>
using namespace std;


class Graph
{
private:
	int n;
	int v;
	vector<vector<int>> graph;
	

public:
	Graph()
	{
	}
	Graph(int v)
	{
		this->v = v;
		for(int i=0;i<=v;i++)
		{
			vector<int> tmp;
			graph.push_back(tmp);
		}

	}
	void initGraph(int edge,int startV);
	void add(int val,int edge);                
	void DFS(int startV);
	void BFS(int startV);

};

void Graph::add(int val,int edge)
{
	graph[val].push_back(edge);
	graph[edge].push_back(val);
}

void Graph::DFS(int startV)
{
	stack<int> s;
	bool visit[v+1];
	int checked = 0;
	int curV = 0;
	int a = 0;
	fill_n(visit,v+1,false);

	s.push(startV);
	int first = 1;

	while(s.size() != 0)
	{
		
		curV = s.top();
		if(!first && visit[curV]==0)
		{
			cout << " " << curV;

		}
		else if(visit[curV] == 0)
		{
			cout << curV; 
		}
		first = 0;
		visit[curV] = true;
		checked = 0;
		for(auto iter : this->graph[curV])
		{
			if(visit[int(iter)])
			{
				continue;
			}
			s.push(int(iter));
			checked = 1;
			break;
		}
		if(!checked)
		{
			s.pop();
		}



	}



} 

void Graph::BFS(int startV)
{
	int isFirst = 1;
	bool visit[v+1];
	fill_n(visit,v+1,false);
	queue<int> que;
	cout << startV;
	visit[startV] = true;

	for(auto iter: graph[startV])
	{
		que.push(iter);
	}

	while(que.size() != 0)
	{
		
		int val = que.front();
		que.pop();
		if(visit[val] == true)
		{
			continue;
		}
		isFirst =  0;
		cout << " " << val;
		visit[val] = true;
		for(auto iter: graph[val])
		{
			if(visit[iter])
			{
				continue;
			}
			que.push(iter);
		}
	}
}



void Graph::initGraph(int edge, int startV)
{
	int e;
	int v = 0;
	//cout << "V , E\n";
	for(int k=1;k<=edge;k++)
	{
		cin >> v >> e;
		//cout << v << " , " << e << "\n";
		this->add(v,e);

	}

	for(int i=0;i<=this->v;i++)
	{
		if(graph[i].empty())
		{
			continue;
		}

		sort(graph[i].begin(),graph[i].end());

	}

	//cout << "**DFS**\n";
	this->DFS(startV);
 	cout << "\n";
	this->BFS(startV);
	cout << '\n';
	//graph.print();

}

int main()
{
	int v,edge,startV;

	cin >> v >> edge >> startV;
	if(v < 1 || edge < 1)
	{
		return 0;
	}
	if(v == 1 && edge <= 1)
	{
		cout << startV;
		return 0;
	}
	
	Graph g = Graph(v);
	g.initGraph(edge,startV);
}