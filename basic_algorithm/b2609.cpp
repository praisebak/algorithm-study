#include <iostream>
using namespace std;

void LCM(int num1,int num2,int gcd)
{
	cout << ((num1 * num2) / gcd) << "\n";
}

void GCD(int num1,int num2)
{	
	int tmpNum1 = num1;
	int tmpNum2 = num2;
	while(tmpNum2 != 0)
	{
		int r = tmpNum1 % tmpNum2;
		tmpNum1 = tmpNum2;
		tmpNum2 = r;
	}
	cout << tmpNum1 << endl;
	LCM(num1,num2,tmpNum1);
}


void solve()
{
	int num1,num2;
	cin >> num1 >> num2;
	GCD(num1,num2);


}

int main()
{
	solve();

}