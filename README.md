---
title: Graph B7576
tag: algorithm
category: algorithm

---

# 개요

백준 문제 7576번을 풀면서 어려웠던 점과 코드를 정리한다.  
소요 시간 : 약 5시간  
https://www.acmicpc.net/problem/7576    

# 문제  
철수의 토마토 농장에서는 토마토를 보관하는 큰 창고를 가지고 있다. 토마토는 아래의 그림과 같이 격자 모양 상자의 칸에 하나씩 넣어서 창고에 보관한다. 



창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만, 아직 익지 않은 토마토들도 있을 수 있다. 보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다. 하나의 토마토의 인접한 곳은 왼쪽, 오른쪽, 앞, 뒤 네 방향에 있는 토마토를 의미한다. 대각선 방향에 있는 토마토들에게는 영향을 주지 못하며, 토마토가 혼자 저절로 익는 경우는 없다고 가정한다. 철수는 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지, 그 최소 일수를 알고 싶어 한다.

토마토를 창고에 보관하는 격자모양의 상자들의 크기와 익은 토마토들과 익지 않은 토마토들의 정보가 주어졌을 때, 며칠이 지나면 토마토들이 모두 익는지, 그 최소 일수를 구하는 프로그램을 작성하라. 단, 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.

# 입력
첫 줄에는 상자의 크기를 나타내는 두 정수 M,N이 주어진다. M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수를 나타낸다. 단, 2 ≤ M,N ≤ 1,000 이다. 둘째 줄부터는 하나의 상자에 저장된 토마토들의 정보가 주어진다. 즉, 둘째 줄부터 N개의 줄에는 상자에 담긴 토마토의 정보가 주어진다. 하나의 줄에는 상자 가로줄에 들어있는 토마토의 상태가 M개의 정수로 주어진다. 정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다.

토마토가 하나 이상 있는 경우만 입력으로 주어진다.

# 출력
여러분은 토마토가 모두 익을 때까지의 최소 날짜를 출력해야 한다. 만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고, 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야 한다.  

​	

# 계획한 풀이법  
문제 해결을 위한 개괄적인 풀이방법은 단순하다.      
- 익은 토마토라면 주변의 인접한 토마토를 보고 0이라면 그 토마토도 1로 바꾼다.  
- 위 과정을 전체 토마트에 반복하며 날짜를 계산한다.  
- 위 방법을 생각해보니 그래프가 필요한가? 싶어서 2차원 배열로 한번 풀어봤었다.  
- 2차원 배열로 풀이하니 시간 초과가 발생했다.  



 

# 오래걸렸던 이유   

- 그래프 연결 구현을 너무 복잡하게 구현하려고 함.
  - 복잡하기만 한 구현에 아무런 이점도 없는데도 그냥 고집으로 3차원 벡터를 사용할려고 함
- BFS를 쓰지 않고 구현 
  - BFS를 안쓰고 구현할 수 있지 않을까? 하는 생각에 고집부리면서 BFS 안쓸려고함



# 속도 개선

- 고집을 부리느라 문제를 많이 못품
  - 문제 푼 수 = 경험 = 실력
- 1시간 반 이상 소요시간이 넘어가면 풀던 것도 멈추고 풀이 방식이 맞는지 찾아보도록 해야함
- 고집 부리지말고 가장 쉽고 빠르게 구현할 수 있는 방법을 사용하도록 하자


# 코드    

```c++
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

const int MAX = 1001;
queue<pair<int,int>> q;
int graph[MAX][MAX];
int M = 0;
int N = 0;


bool leftCheck(int x,int y)
{
	if(x != 0 && graph[y][x-1] == 0)
	{
		return true;
	}
	return false;
}


bool rightCheck(int x,int y)
{
	if(x != N-1 && graph[y][x+1] == 0)
	{
		return true;
	}
	return false;

}


bool upCheck(int x,int y)
{
	if(y!=0 && graph[y-1][x] == 0)
	{
		return true;
	}
	return false;
}


bool downCheck(int x,int y)
{
	if(y+1 != M && graph[y+1][x] == 0)
	{
		return true;
	}
	return false;
}


void checkAndMark(int x,int y,int curDay)
{
	//cout << y+1 << " , " << x+1 << " : " << curDay << endl;
	if(leftCheck(x,y))
	{
		graph[y][x-1] = curDay+1;
		q.push(make_pair(y,x-1));
 	}
	if(rightCheck(x,y))
	{	
		graph[y][x+1] = curDay+1;
		q.push(make_pair(y,x+1));
	}
	if(upCheck(x,y))
	{	
		graph[y-1][x] = curDay+1;
		q.push(make_pair(y-1,x));
	}
	if(downCheck(x,y))
	{
		graph[y+1][x] = curDay+1;
		q.push(make_pair(y+1,x));
	}
}

void BFS()
{
	int curDay = 1;

	int x,y;

	while(q.size()!=0)
	{	
		y = q.front().first;
		x = q.front().second;
		q.pop();
		if(graph[y][x] == curDay)
		{
			checkAndMark(x,y,curDay);
		}
		else
		{
			curDay++;
			checkAndMark(x,y,curDay);
		}
	}
	for(int i=0;i<M;i++)
	{
		for(int j=0;j<N;j++)
		{
			if(graph[i][j] == 0)
			{
				cout << -1;
				return;
			}
			//cout << graph[i][j] << " ";
		}
		//cout << "\n";

	}
	cout << curDay-1;

}

int main()
{
	//가로 = N
	//세로 = M
	cin >> N >> M;
	for(int i=0;i<M;i++)
	{
		for(int j=0;j<N;j++)
		{
			cin >> graph[i][j];
			if(graph[i][j] == 1)
			{
				q.push(make_pair(i,j));

			}
		}
	}

	BFS();


}
```

