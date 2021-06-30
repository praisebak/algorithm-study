#include <iostream>
#include <deque>
using namespace std;





//1번 2번 4번
void caseOne()
{	
	char skill[101];
	int skillLen = 0;
	int contiSkillIdx = 1;
	int contiNum = 0;
	do
	{
		cin >> skill[++skillLen]; 

	}while(getc(stdin) == ' ');


	cin >> contiNum;

	deque <char> s[contiNum+1];
	char tmp;

	int notContiIdxArr[101] = {0};

	char notConti[101];
	int notContiIdx = 0;

	for(int i = 1;i<=contiNum;i++)
	{
		do
		{
			cin >> tmp;
			int j = 1;
			for(;j<=skillLen;j++)
			{
				if(tmp == skill[j])
				{
					notContiIdxArr[j] = 1;
					break;
				}
			}
			s[i].push_back(tmp);
		}while(getc(stdin) == ' ');

	}

	for(int i = 1;i<=skillLen;i++)
	{
		if(notContiIdxArr[i] == 0)
		{
			notConti[++notContiIdx] = skill[i];
		}
	}



	char tmpBackUp[101] = {' '};
	int tmpBackUpIdx = 0;
	int backup = 0;

		for(int i = 1;i <=contiNum;i++)
		{
			int check = 0;
			tmp = s[i].back();
			int j = i+1;
			int idx = 0;
			for(;j<=contiNum;j++)
			{
				if(tmp == s[j].front())
				{
					if(notContiIdx == 0)
					{
					
					
						idx = 0;	
						while(s[i].size() > idx)
						{
							cout << s[i].at(idx++) << " ";
						}

						idx = 1;
						

						while(idx < s[j].size())
						{
							cout << s[j].at(idx++) << " ";
						}
						cout << "\n";
					}
					for(int n = 1;n<=notContiIdx;n++)
					{
						idx = 0;
						cout << notConti[n] << " ";
						
						while(s[i].size() > idx)
						{
							cout << s[i].at(idx++) << " ";
						}

						idx = 1;
						

						while(idx < s[j].size())
						{
							cout << s[j].at(idx++) << " ";
						}
						cout << "\n";
					}
					
					check = 1;
					tmpBackUp[tmpBackUpIdx++] = s[i].front();
				}
			}
				if(j > contiNum && check != 1)
				{
					int j = i+1;
					for(int l=0; l<tmpBackUpIdx; l++)
					{
						if(tmpBackUp[l] == s[i].front())
						{
							if(notContiIdx == 0)
							{
								int idx = 0;
								while(idx < s[i].size())
								{
									cout << s[i].at(idx++) << " ";
								}
								cout << "\n";
							}
							for(int n = 1;n<=notContiIdx;n++)
							{	
								int idx = 0;
								cout << notConti[n] << " ";
								while(idx < s[i].size())
								{
									cout << s[i].at(idx++) << " ";
								}
								cout << "\n";
								
							}
							break;
						}

					}

				
				}


			}


			

	}
	



	

int main()
{

	caseOne();
}