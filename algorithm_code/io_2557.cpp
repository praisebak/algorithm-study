#include <iostream>
using namespace std;

int main()
{
	char str[101];
	int n;
	int result = 0;
	cin >> n;
	cin >> str;
	for(int i=0;i<n;i++)
	{
		result += str[i] - '0';
	}

	cout << result;

}
