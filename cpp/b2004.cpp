#include <iostream>
using namespace std;

long long getCountFromFact(long long n)
{
	long long five = 0;
	for(long long i=5;i<=n;i*=5)
	{
		five += n/i;
	}
	return five;
}

long long getCountFromFactTwo(long long n)
{
	long long two = 0;
	for(long long i=2;i<=n;i*=2)
	{
		two += n/i;
	}
	return two;
}


int getCountFromNum(string str)
{
	int count = 0;
	for(int i=str.size()-1;i>=0;i--)
	{
		if(str[i] != '0')
		{
			return count;
		}
		count++;
	}
}


void solve()
{
	long long n,c;
	cin >> n >> c;
	c = min(n-c,c);
	long long upper,mid;
	long long upperTwo,midTwo;
	if(c == 0)
	{
		cout << 0;
	}
	else if(c == 1)
	{
		cout << getCountFromNum(to_string(n));
	}
	else
	{
		upper = getCountFromFact(n);
		mid = getCountFromFact(n-c) + getCountFromFact(c);
		upperTwo = getCountFromFactTwo(n);
		midTwo = getCountFromFactTwo(n-c) + getCountFromFactTwo(c);

		cout << min((upper - mid),(upperTwo-midTwo));
	}
	
}


int main()
{
	solve(); 
}