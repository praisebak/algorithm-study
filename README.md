# 알고리즘 공부법  
> ## 개요 & 목표
> 코딩테스트 대비 및 '개발자'가 되기 위한 알고리즘 공부 계획 수립  

> ## 공부 계획
> 0. 즐겨
>
> 1. C++ 시작  
> 2. 큐, 스택 구현  
> 3. 완전 탐색 구현  
> 4. 다이내믹 프로그래밍 연습  
> 5. DFS 구현  
> 6. BFS 구현  
> 7. 탐욕법 구현  
> 8. 하루에 문제 한 문제 이상 풀 것. 
>
> 

# Memo

스택 해쉬 맵 등 자료구조 사용 방법 익히기

문제에서 직관적인 방법이 비효율적일 수 있음 다른 방법도 생각할 것



1시간 반 이내에 못풀면 모르는 문제임

DP같은 경우에는 문제에서 어떤 점화식을 찾아내는 것이 관건임

​	어떻게 풀 것인지 식은 어떻게 되는지 생각 먼저 할 것

​	숫자를 계산하는 것이 아니라 코드로 문제를 어떻게 계산할건지



# Code Level Memo

### 숫자 <-> 문자열  

​	숫자 to 문자열

​		to_string()

​	문자열 to 숫자

​		stoi()

​	숫자 to 문자

​		숫자 + '0'

​	문자 to 숫자

​		문자 - '0'

​	

### 오버플로우

​	자료형 수정

​		long long 등

​	알고리즘 수정

​		구조적으로 오버플로우 안나는 방안 탐색

### MIN,MAX

```c++
#include<algorithm>
min(1, 2);
max(1, 2);
max({1, 2, 3});
```

### stoll 사용법

```
string num1;
string num2;
string num3;
string num4;
long long resultNum1;
long long resultNum2;
num1 = num1 + num2;
num3 = num3 + num4;
resultNum1 = stoll(num1);
resultNum2 = stoll(num3);
```

### vector, sort 사용법

```
//string일때 정렬 구현 한번 생각해보자
#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
using namespace std;
void solve()
{
	string str;
	cin >> str;
	vector<string> v;
    for (int i = 0; i < str.length(); i++)
    {
		v.push_back(str.substr(i, str.length()));
    }
    sort(v.begin(),v.end());
    for(int i=0;i<str.length();i++)
    {
    	cout << v[i] << '\n';
    }
}

```





