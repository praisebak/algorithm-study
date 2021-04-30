#include <iostream>
#include <vector>
#include <stack>



using namespace std;
const int MAX = 10001;
//메모리 초과
vector<pair<int,int>> graph[MAX];
int N = 0;
bool visit[MAX];
int maxCost = 0;

int DFS(int startV)
{
	stack<pair<int,int>> s;
	s.push(make_pair(startV,0));
	int curV = 0;
	bool allVisited = false;
	int curCost = 0;
	int maxV = 0;

	while(s.size() != 0)
	{
		curV = s.top().first;
		if(!visit[curV])
		{
			curCost += s.top().second;

		}
		visit[curV] = true;
		allVisited = true;
		if(maxCost < curCost)
		{
			maxCost = curCost;
			maxV = curV;
		}
		for(int j=0;j<graph[curV].size();j++)
		{
			int nextV = graph[curV][j].first;
			if(visit[nextV])
			{
				continue;
			}
			int prevCost = graph[curV][j].second;
			s.push(make_pair(nextV, prevCost));
			allVisited = false;
			break;
		}
		if(allVisited)
		{
			curCost -= s.top().second;
			s.pop();
		}

	}
	return maxV;



}

void connectEdge(int v,int e,int cost)
{
	//cout << "V , E 연결\n";
	//cout << v << "," << e << "\n";

	graph[v].push_back(make_pair(e,cost));
	graph[e].push_back(make_pair(v,cost));

}

void init()
{
	cin >> N;
	int v,edge,cost;
	for(int i=0;i<N-1;i++)
	{
		cin >> v >> edge >> cost;
		connectEdge(v,edge,cost);
	}

}

void solve()
{
	int maxV = DFS(1);
	fill_n(visit,MAX,0);
	DFS(maxV);
	cout << maxCost;
}


int main()
{
	init();
	solve();




}