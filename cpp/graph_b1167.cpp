#include <iostream>
#include <vector>

using namespace std;
const int MAX = 100001;

vector<pair<int, int> > graph[MAX];
bool visit[MAX];
int maxCost = 0;
int maxCostNode = 0;

int DFS(int startV,int cost)
{
	if(visit[startV])
	{
		return -1;
	}
	int result;
	visit[startV] = true;

	for(int i=0;i<graph[startV].size();i++)
	{	
		result = DFS(graph[startV][i].first,graph[startV][i].second+cost);
		if(result > maxCost)
		{
			maxCost = result;
			maxCostNode = graph[startV][i].first;
		}
	}
	return cost;

}

void connectEdge(int v,int e,int length)
{
	graph[v].push_back(make_pair(e,length));
}

void solve()
{

	int N = 0;
	cin >> N;

	int v = 0,e = 0,len = 0;
	for(int i=0;i<N;i++)
	{
		v = 0,e = 0,len = 0;
		cin >> v >> e;

		while(e != -1)
		{
			cin >> len;
			connectEdge(v,e,len);
			cin >> e;
			
		}
	}

	fill_n(visit,MAX,false);

	for(int i=1;i<=N;i++)
	{
		DFS(i,0);
	}
	maxCost = 0;
	fill_n(visit,MAX,false);
	DFS(maxCostNode,0);


	cout << maxCost;

}

int main()
{
	//ios_base::sync_with_stdio(0);
	//cin.tie(0);
	solve();

}