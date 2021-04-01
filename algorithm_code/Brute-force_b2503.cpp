#include <iostream>

using namespace std;

//2시간? 3시간 소요
//문제 파악이 제일 중요함,,
//C++ 문법 익힐겸
//그.. 그냥 막 풀었는데 좀 깔끔하게 풀기,,ㅋ
class StrLib{
	public:
		int getStrLen(string s){
			int strLen = 0;
			while(s[strLen] != NULL){
				strLen++;
			}
			return strLen;
		}

		int strToInt(string s){
			int strLen = getStrLen(s);
			int i=0;
			int mul = 1;
			int resultInt = 0;

			for(i=1;i<strLen;i++){
				mul *= 10;
			}
			for(i=0;i<strLen;i++){
				resultInt += (s[i]- '0') * mul;
				mul /= 10;
		 	}

		 	return resultInt;
		}

		string intToString(int num){
			char numStr[4];
			sprintf(numStr,"%d",num); 
			string result(numStr);
			return result;
		}
};

bool strikeCheck(int strike,string objStr, string subStr){
	int compStrike = 0;

	for(int i=0;i<3;i++){
		if(objStr[i] == subStr[i]){
			compStrike++;
		}
	}

	return strike==compStrike;
}

bool ballCheck(int ball,string objStr, string subStr){
	int ballCount = 0;

	for(int i=0;i<3;i++){
		for(int j=0;j<3;j++){
			if(i!=j && (objStr[i]==subStr[j]))
			{
				ballCount++;
			}
		}
	}

	return ball==ballCount;
}



int main()
{
	StrLib slib = StrLib();
	string s;
	int forInt = 0;
	int i=0;
	int possibleCount=0;
	cin >> s;
	forInt = slib.strToInt(s);
	string question[forInt];
	string strike[forInt];
	string ball[forInt];


	for(i=0;i<forInt;i++){
		cin >> question[i]; 
		cin >> strike[i];
		cin >> ball[i];

		if(strike[i] == "3"){
			cout << 1;
			return 0;
		}
	}
	int answerCount=0;
	int j=0;
	for(i=101;i<999;i++){

		string iStr = slib.intToString(i);
		if(iStr[0] == iStr[1] || iStr[0] == iStr[2] || iStr[1] == iStr[2] ||
			iStr[0] =='0' || iStr[1] == '0' || iStr[2] == '0' ){
			continue;
		}


		for(j=0;j<forInt;j++){


			if(strikeCheck(slib.strToInt(strike[j]),iStr,question[j]) && ballCheck(slib.strToInt(ball[j]),iStr,question[j])){

				if (ball[j] == "0" && strike[j] == "0"){
					for(int k=0;k<forInt;k++){
						if(question[j][k] == iStr[0] || question[j][k] == iStr[1] || question[j][k] == iStr[2]){
							break;
						}
					}
					
				}

			}else{
				break;
			}
		}

		if(j == forInt){
			answerCount++;
		}


	}
	cout << answerCount;
	return 0;

}