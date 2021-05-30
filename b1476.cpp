#include <iostream>

using namespace std; 

void solve()
{
    int answerE = 0,answerS = 0,answerM = 0;
    int eMax = 15;
    int sMax = 28;
    int mMax = 19;
    int year = 0;
    int curE = 0;
    int curS = 0;
    int curM = 0;
    cin >> answerE >> answerS >> answerM;
    while(1)
    {
        curE++;
        curS++;
        curM++;
        year++;
        if(curE > eMax)
        {
            curE = 1;
        }
        if(curS > sMax)
        {
            curS = 1;
        }
        if(curM > mMax)
        {
            curM = 1;
        }
        if(answerE == curE && answerS == curS && answerM == curM)
        {
            cout << year;
            break;
        }
    }
    


}

int main()
{
    solve();
}