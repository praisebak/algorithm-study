#include <iostream>
#include <algorithm>

using namespace std;

vector<int> v;

void solve()
{
	int N;	
	cin >> N;
	for(int i=0;i<N;i++)
	{
		int time;
		cin >> time;
		v.push_back(time);
	}
	sort(v.begin(),v.end());
	//소요시간
	int sumTime = 0;
	//소요시간 + 현재 소요 시간
	int resultTime = 0;
	for(int i=0;i<N;i++)
	{
		sumTime += v[i];
		resultTime += sumTime;
	}
	cout << resultTime;



}

int main()
{
	solve();
	

}