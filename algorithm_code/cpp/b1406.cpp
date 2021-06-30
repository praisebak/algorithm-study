#include <iostream>
#include <string>
#include <list>
using namespace std;
void printlist(list<char> l) {
    for(list<char>::iterator iter = l.begin(); iter != l.end(); iter++) {
        cout << *iter;
    }
    cout << endl;
}


void solve()
{
	string text;
	char command;
	int commandNum;
	int textSize = 0;
	cin >> text;
	list<char> editor(text.begin(), text.end());
	auto cursor = editor.end();

	cin >> commandNum;
	for(int i=1;i<=commandNum;i++)
	{
		cin >> command;
		if(command == 'P')
		{
			cin >> command;
			editor.insert(cursor,command);
		}
		else if(command == 'L' && cursor != editor.begin())
		{
			cursor--;
		}
		else if(command == 'D' && cursor != editor.end())
		{
			cursor++;
		}
		else if(command == 'B' && cursor != editor.begin())
		{
			cursor--;
			editor.erase(cursor);
			cursor++;
		}
	}

	printlist(editor);
}
int main()
{
	solve();
	return 0;
}