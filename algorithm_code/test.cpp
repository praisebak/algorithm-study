#include <iostream>
#include <fstream>
#include <string>
#include <vector>

using namespace std;

<<<<<<< HEAD

vector split(string s, string divid) {
	vector v;
	char* c = strtok((char*)s.c_str(), divid.c_str());
	while (c) {
		v.push_back(c);
		c = strtok(NULL, divid.c_str());
	}
	return v;
}
=======
 

const int MAX = 1000;

const int INF = 987654321;

 

int arr[MAX][MAX];

string result;
 
>>>>>>> 80a1d423936d4d03b11a4c22ba3ea52040469d60

double getDistanceFromZero(int x,int y)
{  
    return 0.0;
}

string init()
{
    string initString = "&Animal 2.0\ntitle \"Demo of Animal Script Features\"\nauthor \"Lee, Su-Hyun <e-mail@changwon.ac.kr>\"\n";
    return initString;
}

<<<<<<< HEAD
string getConvertedString(string s)
{
    vector result;
    result = split(s," ");
    

    int x,y;
    x = stoi(result[0]);
    y = stoi(result[1]);
    cout << x << " " << y << endl;
=======
        ios_base::sync_with_stdio(0);

        cin.tie(0); //cin 실행속도 향상

        int R, C;

        cin >> R >> C;

 

        //세로가 홀수

        if (R % 2)

        {

                 for (int y = 0; y < R; y++)

                 {

                         for (int x = 0; x < C - 1; x++)

                                 if (y % 2 == 0)

                                          result += 'R';

                                 else

                                          result += 'L';

                         if (y != R - 1)

                                 result += 'D';

                 }

        }

        //세로가 짝수 가로가 홀수

        else if (!(R % 2) && C % 2)

        {

                 for (int x = 0; x < C; x++)

                 {

                         for (int y = 0; y < R - 1; y++)

                                 if (x % 2 == 0)

                                          result += 'D';

                                 else

                                          result += 'U';

                         if(x != C-1)

                                 result += 'R';

                 }

        }

        //세로가 짝수 가로가 짝수

        else if (!(R%2) && !(C%2))

        {

                 pair<int, int> minHappy; //검은 칸이면서 지나지 말아야 할 지점

                 int minScore = INF;

                 for (int y = 0; y<R; y++)

                         for (int x = 0; x < C; x++)

                         {

                                 cin >> arr[y][x];

                                 //검은칸이고 최소점수

                                 if ((y + x) % 2 && minScore > arr[y][x])

                                 {

                                          minScore = arr[y][x];

                                          minHappy = { y, x };

                                 }

                         }

 

                 //지나지 말아야 할 좌표의 최좌측으로 내려오고

                 int newR = minHappy.first % 2 ? minHappy.first - 1 : minHappy.first;

                 for (int y = 0; y < newR; y++)

                 {

                         for (int x = 0; x < C - 1; x++)

                                 if (y % 2 == 0)

                                          result += 'R';

                                 else

                                          result += 'L';

                         result += 'D';

                 }

                 //지나지 말아야 할 좌표의 대각선 아래까지 이동하고(↙)

                 int newC = minHappy.second;

                 for (int x = 0; x < newC; x++)

                         if (x % 2 == 0)

                                 result += "DR";

                         else

                                 result += "UR";

 

                 //지나지 말아야 할 좌표의 최우측으로 이동

                 for (int x = newC; x < C - 1; x++)

                         if (x % 2 == 0)
                                 result += "RD";

                         else

                                 result += "RU";

 

                 //목적지까지

                 if (minHappy.first % 2 == 0)

                         newR = R - (minHappy.first + 2);

                 else

                         newR = R - (minHappy.first + 1);

 

                 for (int y = 0; y < newR; y++)

                 {

                         result += 'D';

                         for (int x = 0; x < C - 1; x++)

                                 if (y % 2 == 0)

                                          result += 'L';

                                 else

                                          result += 'R';

                 }

        }

>>>>>>> 80a1d423936d4d03b11a4c22ba3ea52040469d60


    
    y = s[2] - '0';
    cout << x << " " << s[0] << " " << s << endl;
}

void convert()
{  
    ifstream readFile;
    string tmp;
    int x = 0,y = 0;
    readFile.open("point.txt");
    
    while (!readFile.eof())
    {
        getline(readFile,tmp);
        getConvertedString(tmp);

    }
    string s = init();

}

int main()
{
    convert();
}