	#include <iostream>
	#include <algorithm>
	using namespace std;

	string N;
	void greedy()
	{

	}

	void solve()
	{	
		cin >> N;
		vector<int> v;
		long long sum = 0;
		int checkOnesZero = 0;
		for(int i=0;i<N.size();i++)
		{
			v.push_back(N[i] - '0');
			sum += v[i];
			if(v[i] == 0)
			{
				checkOnesZero = 1;
			}
		}
		sort(v.begin(),v.end());
		if(!checkOnesZero || sum % 3 != 0)
		{
			cout << -1;
			
		}
		else
		{
			for(int i=v.size()-1;i> -1;i--)
			{
				cout << v[i];
			}
		}
		//greedy();

	}
	

	int main()
	{
		solve();
		return 0;
	}