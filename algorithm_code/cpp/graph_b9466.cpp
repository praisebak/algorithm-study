#include <iostream>
#include <queue>
using namespace std;
//coutnst int MAX = 100001;

/*
배열로 구현 해보기

*/


class Graph
{
public:
	int haveTeamStudent = 0;
	bool *done;
	bool *visit;
	int *graph;
	int studentNumber = 0;
	Graph()
	{	

	}
	void DFS(int startV);
};


void Graph :: DFS(int startV) 
{
	int curV = startV;
	//coutut << "시작 "<<curV << "\n";
	queue<int> tmp;
	while(1)
	{
		if(done[curV])
			return;
		if(visit[curV])
		{
			//coutut << "중복" << curV << "\n";
			if(graph[curV] == curV)
			{
				//coutut << "1 추가\n";
				haveTeamStudent++;
				done[curV] = true;
				return;
			}

			while(tmp.size()!=0)
			{
				done[tmp.front()] = true;
				//coutut << tmp.front() << " ";
				if(tmp.front() == curV)
				{
					haveTeamStudent += tmp.size();
					//coutut << tmp.size() <<" 추가 \n";
					return;
				}

				tmp.pop();
			}
			return;
		}

		tmp.push(curV);
		visit[curV] = true;
		curV = graph[curV];

	}

	



}

void solve()
{
	int testCase = 1;
	int studentNum = 0;
	cin >> testCase;
	for(int i=0;i<testCase;i++)
	{
		if(i != 0)
		{
			////////////coutut << "\n";
		}
		Graph g = Graph();
		cin >> studentNum;

		int tmpArr[studentNum+1];
		bool tmpArr2[studentNum+1];
		bool tmpArr3[studentNum+1];
		g.graph = tmpArr;
		g.done = tmpArr2;
		g.visit = tmpArr3;
		g.studentNumber = studentNum;

		for(int student=1;student <=studentNum;student++)
		{
			cin >> g.graph[student];
			tmpArr2[student] = false;
			tmpArr3[student] = false;
		}

		for(int student=1;student <= studentNum;student++)
		{
			if(g.haveTeamStudent == studentNum)
			{
				break;
			}
			if(g.done[student])
			{
				//coutntinue;
			}
			g.DFS(student);
		}
		cout << studentNum - g.haveTeamStudent << "\n";
	}


}

int main()
{
	solve();

}

