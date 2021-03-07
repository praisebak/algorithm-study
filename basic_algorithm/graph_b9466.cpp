#include <iostream>
#include <stack>
using namespace std;
const int MAX = 100001;

/*
배열로 구현 해보기

*/


class Graph
{
public:
	int haveTeamStudent = 0;
	bool done[MAX];
	int graph[MAX];
	Graph()
	{	
		fill_n(done,MAX,false);
		fill_n(graph,MAX,0);


	}
	void DFS(int startV);
};


void Graph :: DFS(int startV) 
{
	int curV = 0;
	bool visit[MAX];
	stack<int> s;
	s.push(startV);
	fill_n(visit,MAX,false);
	cout << "시작점 : " << startV <<"\n";
	while(s.size()!= 0)
	{
		curV = s.top();
		s.pop();
		/*
		2가지.
		자기 자신을 향할 때
		향하지 않을 때
		*/
		if(done[curV])
		{
			return;
		}
		if(visit[curV])
		{
			if(curV == graph[curV])
			{
				done[curV] = true;
				haveTeamStudent++;
				return;
			}
			int studentCount = 1;
			cout << "시작 정점 " << startV <<" 현재 정점 : " << graph[curV] << " 목표 : " << curV << "\n";
			for(int iter = graph[curV];iter!=curV;iter = graph[iter])
			{
				cout << startV << "시정 " << iter << " " << curV << "\n";
				studentCount++;
			}
			cout << "\n 팀원 수 : " << studentCount << "\n";
			haveTeamStudent += studentCount;
			return;

		}
		visit[curV] = true;
		s.push(graph[curV]);

	}





}

void solve()
{
	int testCase = 0;
	int studentNum = 0;
	cin >> testCase;
	for(int i=0;i<testCase;i++)
	{
		if(i != 0)
		{
			cout << "\n";
		}
		Graph g = Graph();
		cin >> studentNum;
		for(int student=1;student <=studentNum;student++)
		{
			cin >> g.graph[student];
		}
		for(int student=1;student <= studentNum;student++)
		{
			if(g.haveTeamStudent == studentNum)
			{
				break;
			}
			if(g.done[student])
			{
				continue;
			}
			g.DFS(student);
		}
		//cout << g.haveTeamStudent << "남음\n";
		cout << studentNum - g.haveTeamStudent;
	}


}

int main()
{
	solve();

}

