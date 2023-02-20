#include <iostream>
#include <sstream>
#include <sstream>
using namespace std;

void solve()
{
	string num1;
	string num2;
	string num3;
	string num4;

	long long resultNum1;
	long long resultNum2;

	cin >> num1 >> num2 >> num3 >> num4;

	num1 = num1 + num2;
	num3 = num3 + num4;
	resultNum1 = stoll(num1);
	resultNum2 = stoll(num3);

	cout << resultNum1 + resultNum2;
}
int main()
{
	solve();
}