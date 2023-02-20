#include <iostream>
#include <list>
#include <string>
using namespace std;
void printlist(list<int> l) {
    for(list<int>::iterator iter = l.begin(); iter != l.end(); iter++) {
        //cout << *iter << " ";
    }
    //cout << endl;
}

void solve()
{
	int n = 0;
	int objectNum = 0;

	cin >> n >> objectNum;
	list<int> circle;
	list<int> result;
	for(int i=1;i<=n;i++)
	{
		circle.push_back(i);
	}
	auto iter = circle.begin();
	auto tmp = iter;
	for(int i=1;i<=n;i++)
	{
		//cout << "시작\n";
		printlist(circle);
		//cout << "LIST 출력\n";
		for(int j=1;j<objectNum;j++)
		{
			//cout << * iter << " ";
			if(*iter == circle.back())
			{
				//cout << "end에 도달함\n";
				iter = circle.begin();
			}
			else
			{
				iter++;

			}
		}
		//cout << "\n";
		result.push_back(*iter);
		tmp = iter;
		
		//cout << "* " << *tmp << "삭제\n";
		if(*iter == circle.back())
		{
			iter = circle.begin();
		}
		else
		{
			iter++;
		}		
		circle.erase(tmp);
		//cout << "결\n";
		printlist(circle);

	}
	//cout << "result:\n";
	cout << "<";
	for(list<int>::iterator iter2=result.begin();iter2!=result.end();iter2++)
	{
		cout << *iter2;
		if(*iter2 != result.back())
		{
			cout << ", ";
		}

	}
	cout << ">\n";

}

int main()
{
	solve();
}