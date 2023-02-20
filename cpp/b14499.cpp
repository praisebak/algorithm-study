#include <iostream>

using namespace std;
const int MAX = 15+1;
int day[MAX];
int price[MAX];

void solve()
{
    int N;
    cin >> N;
    for(int i=1;i<=N;i++)
    {
        cin >> day[i];
        cin >> price[i];
    }
    int resultPrice = 0;
    int maxPrice = 0;
    for(int i=1;i<=N;i++)
    {
        //cout << "start DAY : " << i << endl;
        resultPrice = 0;
        for(int j=i;j<=N;j++)
        {
            if(j + day[j]<=N+1)
            {
                //cout << "dAY : " << j << endl;
                resultPrice += price[j];
                //cout << "resultPrice : " << resultPrice << endl;
            }            
            j+= day[j] - 1;
        }
        maxPrice = max(maxPrice,resultPrice);
        //cout << endl;
    }
    cout << maxPrice;
}


int main()
{
    solve();
    
}