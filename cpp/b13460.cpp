#include <iostream>
#include <queue>
using namespace std;

pair<int,int> direction[4]={
    //왼 오 위 아래
    make_pair(0,-1),
    make_pair(0,1),
    make_pair(-1,0),
    make_pair(1,0)
};

bool visited[10][10][10][10];
int countCheck[10][10][10][10];
char arr[10][10];
int N,M;
pair<int,int> curBlue;
pair<int,int> curRed;
pair<int,int> obj;
queue<pair<int,int> > blueQue;
queue<pair<int,int> > redQue;
void inputFromIO();
void setVisit(int blueR,int blueC,int redR,int redC,int count);
bool isVisited(int blueR,int blueC,int redR,int redC);
bool isHasPath(int r,int c);
bool isHasAnswer(int r,int c);
bool isHasBlocked(int r,int c);


int getDistance(int beforeR,int afterR,int beforeC,int afterC)
{
    return abs(afterR - beforeR) + abs(afterC - beforeC);
}

void solve()
{
    
    int count = 0;
    int dir = 0;
    int ans = 987654321;
    int redRow,redCal;
    int blueRow,blueCal;
    int dirRow,dirCal;
    int tmpBRow,tmpBCal;
    int tmpRRow,tmpRCal;
    bool hasBlueMeetAnswer;
    bool hasRedMeetAnswer;
    inputFromIO();
    blueRow = curBlue.first;
    blueCal = curBlue.second;
    redRow = curRed.first;
    redCal = curRed.second;
    setVisit(blueRow,blueCal,redRow,redCal,count);
    //동시에 들어가는 케이스
    //개수가 하나 더 나오는 케이스
    //군휴학 병무청 신청
    while(count <= 10)
    {
        if(redQue.empty())
        {
            break;
        }
        redRow = redQue.front().first;
        redCal = redQue.front().second;
        blueRow = blueQue.front().first;
        blueCal = blueQue.front().second;
        redQue.pop();
        blueQue.pop();
        count = countCheck[redRow][redCal][blueRow][blueCal];
        
        tmpBRow = blueRow;
        tmpBCal = blueCal;
        tmpRRow = redRow;
        tmpRCal = redCal;

        for(dir=0;dir<4;dir++)
        {
            dirRow = direction[dir].first;
            dirCal = direction[dir].second;
            redRow = tmpRRow + dirRow;
            redCal = tmpRCal + dirCal;
            blueRow = tmpBRow + dirRow;
            blueCal = tmpBCal + dirCal;
            hasBlueMeetAnswer = false;
            hasRedMeetAnswer = false;

            while(1)
            {
                if(isHasBlocked(blueRow,blueCal))
                {
                    break;
                }
                if(isHasAnswer(blueRow,blueCal))
                {
                    hasBlueMeetAnswer = true;
                    break;
                }
                blueRow += dirRow;
                blueCal += dirCal;
            }
            blueRow -= dirRow;
            blueCal -= dirCal;

            while(1)
            {
                if(isHasBlocked(redRow,redCal))
                {
                   break;
                }
                if(isHasAnswer(redRow,redCal))
                {
                    hasRedMeetAnswer = true;
                    break;
                }
                redRow += dirRow;
                redCal += dirCal;
            }
            redRow -= dirRow;
            redCal -= dirCal;

            if(redRow == blueRow && redCal == blueCal)
            {
                //cout << "same place\n";
                //cout << redRow << "," << redCal  << "," << blueRow << "," << blueCal << endl;
                int redDistance = getDistance(tmpRRow,redRow,tmpRCal,redCal);
                int blueDistance = getDistance(tmpBRow,blueRow,tmpBCal,blueCal);
                if(redDistance > blueDistance)
                {
                    redRow -= dirRow;
                    redCal -= dirCal;
                }
                else
                {
                    blueRow -= dirRow;
                    blueCal -= dirCal;
                }
                //cout << redRow << "," << redCal << "," << blueRow << "," << blueCal << endl << endl;
            }

            if(hasBlueMeetAnswer)
            {
                continue;
            }
            else
            {
                if(hasRedMeetAnswer)
                {
                    ans = min(ans,count+1);
                }
            }

            if(!isVisited(blueRow,blueCal,redRow,redCal))
            {
                blueQue.push(make_pair(blueRow,blueCal));
                redQue.push(make_pair(redRow,redCal));
                setVisit(blueRow,blueCal,redRow,redCal,count+1);
            }

        }   
    }
    if(ans > 10 || ans == 987654321)
    {
        cout << -1;
    }
    else
    {
        cout << ans;
    }

}

int main()
{  
    solve();
}




void inputFromIO()
{
    char val;
    cin >> N >> M;

    for(int i=0;i<10;i++)
    {
        for(int j=0;j<10;j++)
        {
            for(int k=0;i<10;i++)
            {
                for(int l=0;l<10;l++)
                {
                    visited[i][j][k][l] = false;
                }
            }
        }
    }

    for(int i=0;i<N;i++)
    {
        for(int j=0;j<M;j++)
        {  
            cin >> val;
            arr[i][j] = val;
            if(val == 'B')
            {
                curBlue = make_pair(i,j);
                blueQue.push(make_pair(i,j));
                arr[i][j] = '.';
            }
            if(val == 'R')
            {
                curRed = make_pair(i,j);
                redQue.push(make_pair(i,j));
                arr[i][j] = '.';
            }
            if(val == 'O')
            {
                obj = make_pair(i,j);
            }
        }
    }
}


void setVisit(int blueR,int blueC,int redR,int redC,int count)
{
    visited[redR][redC][blueR][blueC] = true ;
    countCheck[redR][redC][blueR][blueC] = count;
    
}

bool isVisited(int blueR,int blueC,int redR,int redC)
{
    return visited[redR][redC][blueR][blueC];
}

bool isHasPath(int r,int c)
{
    if(arr[r][c] == '.')
    {
        return true;
    }
    return false;
}

bool isHasAnswer(int r,int c)
{
    if(arr[r][c] == 'O')
    {
        return true;
    }
    return false;
}

bool isHasBlocked(int r,int c)
{
    if(arr[r][c] == '#')
    {
        return true;
    }
    return false;
}