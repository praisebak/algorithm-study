	#include <iostream>
	#include <vector>
	#include <algorithm>
	 using namespace std;

	int N;
	vector<pair<int,int>> v;
	vector<pair<int,int>> startV;

	void wrongSolve()
	{
		cin >> N;
		int buf1,buf2,count = 1;
		for(int i=0;i<N;i++)
		{
			cin >> buf1 >> buf2;
			startV.push_back({buf1,buf2});
		}		
		sort(startV.begin(),startV.end());

		int selConfStart = startV[0].first;
		int selConfEnd = startV[0].second;
		//cout << "시작 : " << selConfStart << " " << selConfEnd << endl;

		for (int i = 1; i < startV.size(); ++i)
		{
			//지금 제일 작은 애
			//하이재킹
			if(selConfEnd > startV[i].second)
			{
				selConfStart = startV[i].first;
				selConfEnd = startV[i].second;
				//cout << selConfStart << " " << selConfEnd << endl;
			}
			//종료
			else if(selConfEnd <= startV[i].first)
			{
				selConfStart = startV[i].first	;
				selConfEnd = startV[i].second;
				count++;
				//cout << selConfStart << " " << selConfEnd << endl;
			}
		}
		cout << count;



	}

	bool sortbysec(const pair<int,int> &a,const pair<int,int> &b)
	{
		return (a.second < b.second);
	}

	void rightSolve()
	{
		cin >> N;
		int buf1,buf2;
		int count = 0;
		for(int i=0;i<N;i++)
		{
			cin >> buf1 >> buf2; 
			v.push_back(make_pair(buf1,buf2));
		}
		sort(v.begin(),v.end());
		sort(v.begin(),v.end(),sortbysec);
		int min = v[0].second;
		count++;
		for(int i=1;i<N;i++)
		{
			if(v[i].first >= min)
			{
				min = v[i].second;
				count++;
			}
		}

		cout << count;
	}



	int main()
	{
		wrongSolve();

	}