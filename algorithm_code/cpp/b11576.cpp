#include <iostream>
#include <stack>
using namespace std;


int getTenDigitNum(int subjDigit)
{
	int N;
	cin >> N; 
	int curDigitNum = 1;
	int result = 0;
	int curNum = 0;
	stack <int> s;

	for(int i=0;i<N;i++)
	{
		cin >> curNum;
		s.push(curNum);
	}
	while(s.size() != 0)
	{
		curNum = s.top();
		s.pop();
		result += curNum * curDigitNum;
		curDigitNum *= subjDigit;
	}

	//cout << "십진수 변환 결과 :  " << result << endl;
	return result;
}

int getNDigitNum(int objDigit,int tenDigit)
{
	stack <int> s;
	int maxObjDigit = objDigit;
	

	while(maxObjDigit <= tenDigit)
	{
		maxObjDigit *= objDigit;
	}
	maxObjDigit /= objDigit;

	while(1)
	{
		if(tenDigit == 0)
		{
			cout << 0 << " ";
		}
		else
		{
			cout << tenDigit / maxObjDigit << " ";
		}

		tenDigit %= maxObjDigit;
		maxObjDigit /= objDigit;

		if(maxObjDigit == 1)
		{
			cout << tenDigit;
			break;
		}

	}
	return 0;

}

void solve()
{
	int objDigit,subjDigit;
	cin  >> subjDigit >> objDigit;
	int tenDigitNum;
	tenDigitNum = getTenDigitNum(subjDigit);
	if(tenDigitNum == 0)
	{
		cout << 0;
		return;
	}
	getNDigitNum(objDigit,tenDigitNum);


	

}


int main()
{
	solve();
}