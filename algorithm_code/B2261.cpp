#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
const int MAX = 100000;

int N;
double minResult = 987654321;

vector <pair<int,int>> v;

double getMinDistanceInRange(int start,int end)
{

}


void binCal(int start,int end)
{	
	int result = 0;
	if(start >= end)
	{
		return;
	}
	int mid= (start + end)/2;

	binCal(start,mid);
	binCal(mid+1,end);

}




void solve()
{
	cin >> N;
	int x,y;
	for(int i=0;i<N;i++)
	{
		cin >> x >> y;
		v.push_back(pair<int,int>(x,y));
	}
	sort(v.begin(),v.end());
	v.erase(unique(v.begin(),v.end()),v.end());
	binCal(0,v.size()-1);

}

int main()
{
	solve();
}