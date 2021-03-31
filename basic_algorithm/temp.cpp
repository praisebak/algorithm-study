//2089.-2진수 (1984kb, 0ms)
#include<iostream>
using namespace std;
#define endl "\n"
#define div -2
long long n;

void find(long long a) {

	if (a == 1) // 1이면 출력
	{
		cout << 1;
		return;
	}

	if (a < 0 && a%div == -1) { // 음수를 음수로 나눌 때 나머지가 -1 인 경우 나머지가 -1이 아닌 1이 되도록 몫과 나머지를 조정해줘야 한다.
		a /= div;
		a += 1;
		find(a);
		cout << 1;
	}

	else { // 그 밖의 일반적인 경우
		long long temp = a % div;
		a /= div;
		find(a);
		cout << temp;
	}

}

int main() {

	ios::sync_with_stdio(false);
	cout.tie(NULL);
	cin.tie(NULL);

	cin >> n;

	if (n == 0) // 이 부분때문에 세시간을 썼다. 내 풀이의 결정적인 결함. 0을 예외처리 해줘야한다.
	{
		cout << 0 << endl;
		return 0;
	}

	find(n);

	cout << endl;

	return 0;
}