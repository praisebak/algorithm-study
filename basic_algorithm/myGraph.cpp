#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <typeinfo>
using namespace std;


class Graph
{
private:
	int n;
	int v;
	vector<vector<int>> graph;
	queue<int> que;

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
	void initGraph(int v,int edge,int startV);
	void add(int val,int edge);                
	void del(int,int);
	void print();
	void DFS();
	void BFS(int startV);

};

void Graph::add(int val,int edge)
{
	graph[val].push_back(edge);
	graph[edge].push_back(val);
	

}

void Graph::del(int val,int edge)
{

}

void Graph::print()
{
	
	for(int i=0;i<v;i++)
	{
		if(graph[i].size() == 0)
		{
			continue;
		}

		for(auto iter : graph[i])
		{
			cout << "idx: " << i << " to " << iter << "\n";

		}
}



}



void Graph::DFS()
{

}

void Graph::BFS(int startV)
{
	bool visit[v+1];
	fill_n(visit,v+1,false);

	que.push(startV);
	cout << "*큐 추가 시작 IDX : "<< startV << " -----\n";
	for(auto iter: graph[startV])
	{
		que.push(iter);
		cout << iter << "\n";
	}
	cout << "*큐 추가 끝-----\n";

	while(que.size() != 0)
	{
		int val = que.front();
		que.pop();
		if(visit[val] == true)
		{
			continue;
		}
		cout << val << " ";
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



void Graph::initGraph(int v, int edge, int startV)
{
	int e;

	cout << "V , E\n";
	for(int k=1;k<=edge;k++)
	{
		cin >> v >> e;
		cout << v << " , " << e << "\n";
	
		this->add(v,e);

	}
	cout << "\n";
	cout << find(this->graph.begin(),this->graph.end())


	for(auto i : graph.front())
	{
		cout << i;
	}
		for(auto j : graph[0])
		{
			cout << j << " ";
		}
		cout << "\n";



	//this->BFS(startV);
	//graph.print();

}

int main()
{
	int v,edge,startV;

	cin >> v >> edge >> startV;
	
	Graph g = Graph(v);
	g.initGraph(v,edge,startV);
}