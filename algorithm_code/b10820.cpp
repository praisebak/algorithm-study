#include <iostream>
using namespace std;


void solve()
{


	ios_base::sync_with_stdio(0);
	cin.tie(0);



	for(int i=1;i<=100;i++)
	{
		string str;
		getline(cin,str);

		if(str.length() == 0)
		{
			break;
		}
		int upper = 0;
		int lower = 0;
		int space = 0;
		int num = 0;
		for(int idx=0;idx<str.length();idx++)
		{
			

			if(str[idx] >= '0' && str[idx] <= '9')
			{
				num++;
			}
			if(str[idx] >= 'A' && str[idx] <= 'Z')
			{
				upper++;
			}
			if(str[idx] >= 'a' && str[idx] <= 'z')
			{
				lower++;
			}
			if(str[idx] == ' ')
			{
				space++;
			}

		}
		cout << lower << ' ' << upper << ' ' << num << ' ' << space << '\n';

	}

}

int main()
{
	solve();

}