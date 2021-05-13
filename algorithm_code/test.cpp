#include <cstdio>
#include <vector>
#include <utility>

using namespace std;
bool sortbysec(const pair<int,int> &a, const pair<int,int> &b) {
    return (a.second < b.second);
}
int main() {

    int N, i, n1, n2, min, cnt = 0;

    scanf("%d", &N);
    vector <pair <int, int> > v;

    for (i = 0; i < N; i++) {
        scanf("%d %d", &n1, &n2);
        v.push_back(make_pair(n1, n2));
    }
    sort(v.begin(), v.end());
    sort(v.begin(), v.end(), sortbysec);

    min = v[0].second;
    cnt++;

    for (i = 1; i < N; i++) {
        if (v[i].first >= min) {
            min = v[i].second;
            cnt++;
        }
    }

    printf("%d\n", cnt);
    return 0;
}