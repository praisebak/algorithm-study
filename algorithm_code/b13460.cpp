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

bool redVisit[10][10];
bool blueVisit[10][10];
char arr[10][10];
int N,M;
pair<int,int> curBlue;
pair<int,int> curRed;
pair<int,int> obj;
queue<pair<int,int> > blueQue;
queue<pair<int,int> > redQue;
void inputFromIO();
void setVisit(int blueR,int blueC,int redR,int redC);
bool isVisited(int blueR,int blueC,int redR,int redC);
bool isHasPath(int r,int c);
bool isHasAnswer(int r,int c);
bool isHasBlocked(int r,int c);


void solve()
{
    
    int count = 0;
    int dir = 0;
    int redRow,redCal;
    int blueRow,blueCal;
    int dirRow,dirCal;
    int tmpBRow,tmpBCal;
    int tmpRRow,tmpRCal;
    bool meetAnswer;
    inputFromIO();
    blueRow = curBlue.first;
    blueCal = curBlue.second;
    redRow = curRed.first;
    redCal = curRed.second;
    setVisit(blueRow,blueCal,redRow,redCal);
    //동시에 들어가는 케이스
    //개수가 하나 더 나오는 케이스
    //군휴학 병무청 신청
    while(count != 10)
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
        count++;
        
        tmpBRow = blueRow;
        tmpBCal = blueCal;
        tmpRRow = redRow;
        tmpRCal = redCal;
        for(dir=0;dir<4;dir++)
        {
            redRow = tmpRRow;
            redCal = tmpRCal;
            blueRow = tmpBRow;
            blueCal = tmpBCal;
            dirRow = direction[dir].first;
            dirCal = direction[dir].second;
            meetAnswer = false;
            
            //파란색은 구멍으로 빠지면 안되잖아
            if(isHasAnswer(blueRow + dirRow,blueCal + dirCal))
            {
                continue;
            }
            //길 있으면
            if(isHasPath(blueRow + dirRow,blueCal + dirCal))
            {
                //막힐때까지 그방향으로 이동
                while(!isHasBlocked(blueRow+dirRow,blueCal+dirCal))
                {
                    //그 방향으로 이동했는데 구멍으로 빠졌냐
                    if(isHasAnswer(blueRow + dirRow,blueCal + dirCal))
                    {
                        meetAnswer = true;
                        break;
                    }
                    blueRow += dirRow;
                    blueCal += dirCal;
                }   
                if(meetAnswer)
                {
                    continue;
                }
            }

            //빨간색이 구멍에 빠졌는가
            if(isHasAnswer(redRow + dirRow,redCal + dirCal))
            {
                cout << count;
                return;
            }

            //길이 있는가
            if(isHasPath(redRow + dirRow,redCal + dirCal))
            {
                //막힐때까지 그방향으로 이동
                while(!isHasBlocked(redRow+dirRow,redCal+dirCal))
                {
                    //그 방향으로 이동했는데 구멍으로 빠졌냐
                    if(isHasAnswer(redRow + dirRow,redCal + dirCal))
                    {
                        cout << count;
                        return;
                    }
                    redRow += dirRow;
                    redCal += dirCal;
                }

                if(!isVisited(blueRow,blueCal,redRow,redCal))
                {
                    blueQue.push(make_pair(blueRow,blueCal));
                    redQue.push(make_pair(redRow,redCal));
                    setVisit(blueRow,blueCal,redRow,redCal);
                }
            }
        
           

        }   
    }

    cout << -1;
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
            redVisit[i][j] = false;
            blueVisit[i][j]= false;
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
            }
            if(val == 'R')
            {
                curRed = make_pair(i,j);
                redQue.push(make_pair(i,j));
            }
            if(val == 'O')
            {
                obj = make_pair(i,j);
            }
        }
    }
}


void setVisit(int blueR,int blueC,int redR,int redC)
{
    redVisit[redR][redC] = true;
    blueVisit[blueR][blueC]= true;
}

bool isVisited(int blueR,int blueC,int redR,int redC)
{
    if(blueVisit[blueR][blueC] && redVisit[redR][redC])
    {
        return true;
    }
    return false;
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