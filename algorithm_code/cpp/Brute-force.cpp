#include <iostream>
#include <typeinfo>

using namespace std;

//C++ 문법 익힐겸
class StrLib{
	public:
		int getStrLen(string s){
			int strLen = 0;
			while(s[strLen] != nullptr){
				strLen++;
			}
			return str_len;
		}

		int strToInt(string s){
			int str_len = get_str_len(s);
			int i=0;
			int mul = 1;
			int result_int = 0;

			for(i=1;i<str_len;i++){
				mul *= 10;
			}
			for(i=0;i<str_len;i++){
				result_int += (s[i]- '0') * mul;
				mul /= 10;
		 	}

		 	return result_int;
		}
};


class Game



int main()
{
	//이번 목표는 한번에 
	str_lib slib = str_lib();
	string s;
	int for_int = 0;
	int i=0;
	int possible_count=0;
	cin >> s;
	for_int = slib.str_to_int(s);

	string question[for_int];
	string strike[for_int];
	string ball[for_int];


	for(i=0;i<for_int;i++){
		cin >> question[i]; 
		cin >> strike[i];
		cin >> ball[i];
		if(strike == 3){
			return 1;
		}
	}

	for(i=0;i<999;i++){
		for(int j=0;j<for_int;j++){

		}
		if(strike[i] == 3 && question[])


	}

	return 0;

}