#include <iostream>
#include <vector>
using namespace std;

int N,K;
vector<int> graph;

void init()
{
	cin >> N >> K;
	for(int i=1;i<=N;i++)
	{
		graph.push_back(i);
	}
}

void solve()
{
	int curV = -1;
	int maxN = N;
	cout << "<";
	for(int i=0;i<N;i++)
	{
		curV = (curV + K) % maxN;
		cout << graph[curV];

		graph.erase(graph.begin() + curV);
		curV--,maxN--;
		if(i+1 != N)
		{
			cout << ", ";
		}
	}
	cout << ">";

}

int main()
{ 
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	init();
	solve();

}