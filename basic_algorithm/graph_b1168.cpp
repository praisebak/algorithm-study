#include <iostream>
#include <vector>
using namespace std;

int N,K;
vector<int> graph;

void init()
{
	cin >> N >> K;
	for(int i=0;i<=N;i++)
	{
		graph.push_back(i);
	}
	for(int i=1;i<=N;i++)
	{
		if(i == N)
		{
			graph[i]=1;
		}
		else
		{

			graph[i]=i+1;
		}
	}

	
}

void solve()
{
	int curV = 1;
	while(graph.size() != 0)
	{
		for(int i=0;i<K;i++)
		{
			curV = graph[curV];
		}

		cout << "출력1\n";
		for(int i=1;i<graph.size();i++)
		{
			cout << graph[i] << "\n";
		}
		cout << "현재 : " << curV << "\n";
		graph.erase(graph.begin()+curV);
		break; 
	}

	cout << "출력\n";
	for(int i=1;i<graph.size();i++)
		{
			cout << graph[i] << "\n";
		}
}

int main()
{
	init();
	solve();

}