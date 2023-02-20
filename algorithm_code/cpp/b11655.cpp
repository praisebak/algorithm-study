#include <iostream>
using namespace std;

int isAlphabet(char val)
{
	if((val >= 'A' && val <= 'Z')
			|| (val >= 'a' && val <='z'))
	{
		return 1;
	}
	return 0;
}

void solve()
{
	string str;
	char tmp;
	int idx = 0;

	getline(cin,str);	
	for(int i=0;i<str.length();i++)
	{
		if(str[i] >= 'A' && str[i] <= 'M'){

            str[i] = str[i]+13;

        }else if(str[i] >='N' && str[i] <='Z'){

            str[i] = str[i]-13;

        }else if(str[i] >='a' && str[i] <='m'){

            str[i] = str[i]+13;

        }else if(str[i] >= 'n' && str[i] <= 'z'){

            str[i] = str[i]-13;

        }
		cout << str[i];
	}

}

int main()
{
	solve();

}