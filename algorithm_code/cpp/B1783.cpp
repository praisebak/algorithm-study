#include <iostream>

using namespace std;

int x = 0,y = 0;

void isntGreedy()
{
	int curX = 1;
	int curY = y;
	int count = 0;
	int first = 1;
	bool onesDown = false;
	bool onesUp = false;

	while(curX <= x && (curY <= y && curY >= 1))
	{
		count ++;
		first = 0;

		if(count > 4 && !onesUp)
		{
			onesUp = true;
			//cout << "위로 한칸\n";	
			curX+=2;
			curY--;
			continue;
		}
		else if(count > 4 && !onesDown)
		{
			onesDown = true;
			//cout << "아래로 한칸\n";
			curY++;
			curX+=2;
			continue;
		}

		//위로 두칸 가능
		if(curY - 2 >= 1)
		{

			//cout << "위로 두칸\n";
			curY -= 2;
		}
		//아래로 두칸 가능
		else if(curY + 2 <= y)
		{
			//cout << "아래로 두칸\n";
			curY += 2;
		}
		//위로 한칸만 가능하다
		else if(curY - 1 >= 1)
		{
			//cout << "위로 한칸\n";
			onesUp = true;
			curX++;
			curY--;
			}
		//아래로 한칸만 가능
		else
		{
			//cout << "아래로 한칸\n";
			onesDown = true;
			curY++;
			curX++;
		}
		curX++;
	}
	cout << count;
}

void greedy()
{
	int leftX = x;
	int moveY = y/2;
	int count = 0;
	int leftMove = 2;

	if(y == 1)
	{
		cout << 1;
	}
	else if(y == 2)
	{
		cout << min(4,(x+1) / 2);
	}
	else
	{
		if(x > 6)
		{
			cout << x - 2;			
		}
		else
		{	
			cout << min(x,4);

		}
	}


}

void solve()	
{
	cin >> y >> x;
	greedy();
}


int main()	
{
	solve();
}