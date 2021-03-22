#include <iostream>
#include <vector>
using namespace std;
int const MAX = 100001;
int N,K;
vector<int> graph;
int answer[MAX];

void init()
{
	cin >> N >> K;
	for(int i=0;i<=N;i++)
	{

		graph.push_back(i+1);
		if(i == N)
		{
			graph[N] = 1;
		}
	}
	for(int i=1;i<=N;i++)
	{
		cout << graph[i] << "\n";
	}
	
}
//2 3 4 5 6 7 1

void solve()
{

	int curV = 1;
	int preV = 1;
	int count = 0;
	cout << "<\n";
	while(count != N)
	{
		for(int i=0;i<K;i++)
		{
			if(count == 0)
			{
				i++;
			}
			
			preV = curV;
			curV = graph[curV];
			cout << "curV : " << curV << endl;		
		}

		cout << "* " << curV << "\n";
		//3
		int backup = curV;
		//4
		curV = graph[curV];
		graph[preV] = curV;
		graph.erase(graph.begin() + backup+1);
		cout << preV << endl << endl;
		
		count++;
	}
	cout << ">";
}

int main()
{
	init();
	solve();

}