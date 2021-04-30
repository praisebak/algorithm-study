#include <iostream>
#include<vector>
using namespace std;


class Graph
{
public:
	vector<vector<int>> graph;
	int * visit;
	int arrSize = 0;
	Graph(int n)
	{
		this->arrSize = n;
		for(int i=0;i<=n;i++)
		{
			vector<int> tmp;
			graph.push_back(tmp);

		}
	}

	void add(int v,int e);
	void solveTravel();

};

void Graph :: add(int v ,int e)
{
	graph[v].push_back(e);

}
void Graph :: solveTravel()
{
	int visitCount = 0;
	int checkEndVertex[this->arrSize+1];
	int next = 0;
	int result = 0;
	fill_n(checkEndVertex,this->arrSize+1,0);
	for(int i=1;i<=graph.size()-1;i++)
	{
		if(visit[i])
		{
			continue;
		}
		visit[i] = 1;
		next = graph[i][0];
		//cout << "사이클 시작 :\n" << i << "\n";

		while(visitCount != graph.size()-1)
		{
			if(visit[next])
			{
				if(checkEndVertex[next])
				{
					break;
				}
				result++;
				//cout << "사이클 끝\n";
				checkEndVertex[next] = 1;
				break;
			}

			//cout << next << "\n";
			visit[next] = 1;
			visitCount++;
			next = graph[next][0];

		}

	}

	cout << result << "\n";
	


}

void solve()
{
	int testCase = 0;
	int val = 0;
	cin >> testCase;
	for(int i=0;i<testCase;i++)
	{
		int num = 0;
		
		cin >> num;
		Graph g = Graph(num);

		for(int idx=1;idx<=num;idx++)
		{
			cin >> val;
			g.add(idx,val);
		}
		int tmpVisit[num+1];
		fill_n(tmpVisit,num+1,0);
		g.visit = tmpVisit;
		g.solveTravel();

	}

}


int main()
{

	solve();

}