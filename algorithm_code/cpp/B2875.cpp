#include <iostream>
using namespace std;

int man,woman,objIntern;
int minTeam = 0;


void greedy()
{
	int intern = 0;
	while(objIntern != intern)
	{
		//여자 한명 빼도 팀 충족
		if((woman-1)/2 >= minTeam)
		{
			woman--;
			intern++;
		}
		//남자 한명 빼도 팀 충족
		else if(man > minTeam)
		{
			man--;
			intern++;
		}
		else if(woman/2 > man)
		{
			//여자뺀다
			woman--;
			intern++;
			minTeam--;
		}
		else
		{
			man--;
			intern++;
			minTeam--;
		}
	}

	cout << minTeam;	
	


}


void solve()
{
	cin >> woman >> man >> objIntern;
	minTeam = min(man,woman/2);
	greedy();

}

int main()
{
	solve();


}