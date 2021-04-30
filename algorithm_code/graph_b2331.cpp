#include <iostream>
#include <vector>
#define MAX 250000
using namespace std;

class Graph
{
public:
	int visit[MAX]; 
	int count = 0;
	Graph()
	{
	}

	int visitCheck(int val);
	

};

int Graph:: visitCheck(int val)
{
	if(visit[val] == 0)
	{
		visit[val] = ++count;
		return -1;
	}
	return visit[val]-1;
}

int getDiv(int num)
{
	int div = 1;
	do
	{
		div *= 10;
	}while(num / div != 0);
	return div/10;
}

int getA(int a, int p)
{
	int result = 0;
	int div = getDiv(a);
	int digitNum = 0;
	int tmp = 0;
	//573을 예로 들면 
	//div = 100
	//573 에서 573 나누기 100은? 5
	//다음은 73 봐야함 73볼려면 573에서 mod 100 해야함
	for(div;div!=0;a%=div,div/=10)
	{
		
		digitNum = a/div;
		tmp = digitNum;
		for(int i=1;i<p;i++)
		{
			digitNum *= tmp;
		}
		result += digitNum;
	}
	//cout << "A : " << result << "\n";
	return result;
}



void solve()
{
	int a = 0;
	int p = 0;
	int result = 0;
	Graph g = Graph();
	cin >> a >> p;

	while(1)
	{
		result = g.visitCheck(a);
		if(result != -1)
		{
			cout << result;
			break;
		}
		a = getA(a,p);
	}




}

int main()
{
	solve();

}