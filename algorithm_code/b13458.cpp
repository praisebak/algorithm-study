#include <iostream>
#include <vector>
using namespace std;

void solve()
{
    int N,admin,subAdmin,val;
    vector<int> people;
    cin >> N;
    for(int i=0;i<N;i++)
    {
        cin >> val;
        people.push_back(val);
    }
    cin >> admin >> subAdmin;
    //시험장
    long countAdmin = 0;
    for(int i=0;i<N;i++)
    {
        countAdmin++;
        people[i] = people[i] - admin;
        if(people[i] > 0)
        {
            countAdmin += people[i] / subAdmin;
            if(people[i] % subAdmin > 0)
            {
                countAdmin++;
            }
        }
    }
    cout << countAdmin;

}

int main()
{
    solve();
    return 0;
}