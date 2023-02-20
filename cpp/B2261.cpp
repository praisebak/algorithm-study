#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
using namespace std;
const int MAX = 100000;
const int INF = 987654321;
int N;
vector <pair<int,int>> v;


int getCalDistance(int aIdx,int bIdx)
{
	int ax = v.at(aIdx).first;
	int ay = v.at(aIdx).second;
	int bx = v.at(bIdx).first;
	int by = v.at(bIdx).second;
	return pow(bx - ax,2) + pow(by-ay,2);
}

int getMinDistanceInRange(int start,int end)
{
	int result = INF;
	for(int i=start;i<=end;i++)
	{
		for(int j=i+1;j<=end;j++)
		{
			if(i!=j)
			{
				result = min(result,getCalDistance(i,j));
			}
		}
	}
	return result;
}

int dist(pair<int, int> p1, pair<int, int> p2) {
	return pow(p1.first - p2.first, 2) + pow(p1.second - p2.second, 2);
}


int getIdxFromDLeft(int d,int mid)
{
	int midX = v.at(mid).first -d;
	for(int i=0;i<v.size();i++)
	{
		if(v.at(i).first > midX)
		{
			return i;
		}
	}
	return -1;

}

int getIdxFromDRight(int d,int mid)
{
	int midX = v.at(mid).first +d;
	for(int i=v.size()-1;i>=0;i--)
	{
		if(v.at(i).first < midX)
		{
			return i;
		}
	}
	return -1;
}


int binCal(int start,int end)
{
	int result = INF;
	if(end - start + 1 <= 3)
	{
		for (int i=start; i<end; i++)
		{
	    	for (int j=i+1; j<=end; j++)
	    	{
	    		result = min(result,dist(v[i], v[j]));
	      	}
	    }
	}
	else
	{
		int mid= (start + end)/2;
		result = min(binCal(start,mid-1),binCal(mid+1,end));
		vector<pair<int, int>> tmp;
   		tmp.push_back({v[mid].second, v[mid].first});

   		for (int i=mid-1; i>=start; i--) {
	    	if (dist({v[mid].first, 0}, {v[i].first, 0})  >= result)
	    	{
	    		break;
	    	}
	    	tmp.push_back({v[i].second, v[i].first});// y축 순으로 정렬
    	}

    	for (int i=mid+1; i<=end; i++) {
	    	if (dist({v[mid].first, 0}, {v[i].first, 0})  >= result)
	    	{
	    		break;
	    	}
	    	tmp.push_back({v[i].second, v[i].first});// y축 순으로 정렬
    	}
    	sort(tmp.begin(), tmp.end());// y축 정렬

    	for (int i=0; i<tmp.size()-1; i++) {
      		for (int j=i+1; j<tmp.size(); j++) {
       			if (pow(tmp[i].first - tmp[j].first, 2) >= result)
       			{
       				break;
       			}
        		result = min(result, dist(tmp[i], tmp[j]));
        	}
    	}
	}
	return result;
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
	cout << binCal(0,N-1);
}

int main()
{
	solve();
}

/*
1. mid에 걸쳐있는 것 구하는 알고리즘이 좀 잘못됐음
1.1 일단 d를 기준으로 x축에서 한번 걸러냄
1.2 걸러낸 것들 중에서 y축에서 한번 더 걸러냄
1.3 걸러낸 것들의 거리 계산하여 이전에 계산된 min과 비교하여 정답 추출

*/