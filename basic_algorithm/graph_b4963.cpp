#include <iostream>
#include <vector>
#include <stack>
using namespace std;
const int MAX = 2500;

vector<vector<int>> graph;
int map[MAX][MAX];
int visit[MAX];

//전역변수 써서 코드 간결하게 짜보기


void init(int row,int cal)
{	
	int maxIdx = row * cal;
	for(int i = 0;i<row;i++)
	{
		for(int j= 0;j<cal;j++)
		{
			cin >> map[i][j];
		}
	}
	for(int i=0;i<maxIdx;i++)
	{
		vector<int> tmp;
		graph.push_back(tmp);
	}
}

void connectEdge(int v,int e)
{
	//cout << "V와 E 연결\n";
	//cout << v << " " << e <<"\n";
	graph[v].push_back(e);
	graph[e].push_back(v);
}

int DFS(int startV)
{
	int onesCheck = 0;
	stack <int> s;
	s.push(startV);
	int curV = 0;
	while(s.size()!= 0)
	{
		curV = s.top();
		//cout << "V : " << curV << "\n";
		s.pop();
		
		visit[curV] = true;
		for(int i=0;i<graph[curV].size();i++)
		{
			if(!visit[graph[curV][i]])
			{
				onesCheck = 1;
				s.push(graph[curV][i]);
			}			
		}



	}	
	
	if(onesCheck)
	{
		return 1;
	}

	return 0;



}



void edgeConnect(int width,int height)
{
	int idx = 0;
	int isLeftLand = 0;
	int isCurLand = 0;
	int isUpLand = 0;
	int isLeftDiagonalLand = 0;
	int isRightDiagonalLand = 0;


	for(int row = 0; row < height; row++)
	{
		for(int cal = 0;cal < width; cal++,idx++)
		{
			isCurLand = map[row][cal];
			if(isCurLand)
			{
				//cout << idx << "번째 연결 시도\n";
				int isConnectedOnce = 0;
				if(cal-1 >= 0)
				{
					//왼쪽 연결
					isLeftLand = map[row][cal-1];
					if(isLeftLand)
					{
						//cout << "왼쪽 연결\n";
						connectEdge(idx,idx-1);
						isConnectedOnce = 1;
					}
				}
				if(row != 0)
				{
					//위 연결
					isUpLand = map[row-1][cal];
					if(isUpLand)
					{
						//cout << "위 연결\n";

						connectEdge(idx,idx-width);
						isConnectedOnce = 1;

					}

					//왼쪽 대각선 연결
					if(cal != 0)
					{
						isLeftDiagonalLand = map[row-1][cal-1];
						if(isLeftDiagonalLand)
						{
							//cout << "왼쪽 위 대각선 연결\n";

							connectEdge(idx,idx-1-width);
							isConnectedOnce = 1;

						}
					}

					//오른 대각선 연결
					if(cal+1 != width)
					{
						isRightDiagonalLand = map[row-1][cal+1];
						if(isRightDiagonalLand)
						{
							//cout << "오른 위 대각선 연결\n";
							connectEdge(idx,idx+1-width);
							isConnectedOnce = 1;

						}
					}
				}

				if(!isConnectedOnce)
				{
					//cout << "자기자신 연결\n";
					connectEdge(idx,idx);
				}

			}
		}
	}
}



void solve()
{


	int width = -1;
	int height = -1;
	int maxIdx = 0;
	int landCount = 0;
	int count = 0;
	while(1)
	{
		//cout << ++count << "번째 테스트케이스\n";
		landCount = 0;
		cin >> width >> height;
		maxIdx = width * height;
		if(width == 0 && height == 0)
		{
			break;
		}
		
		fill_n(visit,MAX,0);
		fill(&map[0][0],&map[MAX-1][MAX],0);
		fill(graph.begin(),graph.end(),vector<int>(MAX,0));

		init(height,width);

		if(width == 1 && height == 1)
		{
			////cout << map[0][0] << "\n";
			continue;
		}
		
		edgeConnect(width,height);
		
		for(int i = 0;i<maxIdx;i++)
		{
			if(!visit[i])
			{
				int result = 0;
				result = DFS(i);
				if(result)
				{
					//cout << i << "번째 DFS의 탐색 완료\n";
				}
				landCount += result;

			}
		}
		cout << "결과 : " << landCount << "\n";
	}



}

int main()
{
	solve();
}