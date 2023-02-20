#include <iostream>
#include <cmath>

using namespace std;

typedef struct
{
	double x;
	double y;
}point;

double calDistance(point A,point B)
{
	return pow(A.x-B.x,2) + pow(A.y-B.y,2);
}
void setPoint(point * A,point * B,point * C,point * D)
{
	int Ax,Bx,Cx,Dx;
	int Ay,By,Cy,Dy;
	cin >> Ax >> Ay >> Bx >> By;
	cin >> Cx >> Cy >> Dx >> Dy;
	A->x = Ax;
	A->y = Ay;
	B->x = Bx;
	B->y = By;
	C->x = Cx;
	C->y = Cy;
	D->x = Dx;
	D->y = Dy;
}

point getPoint(double k,point A,point B)
{
	point resultPoint;
	resultPoint.x = A.x + (B.x - A.x) * (k/100);
	resultPoint.y = A.y + (B.y - A.y) * (k/100);
	return resultPoint; 
}


void solve()
{
	point A,B,C,D;
	setPoint(&A,&B,&C,&D);
	double minResult = 987654321;
	double fp = 0,lp=100,limit=2e9;
	double distA,distB;
	while(lp-fp>=1e-10)
	{
		double k1 = (fp*2 + lp) / 3;
		double k2 = (fp + lp * 2) / 3;
		point curPointAB_1 = getPoint(k1,A,B);
		point curPointCD_1 = getPoint(k1,C,D);
		point curPointAB_2 = getPoint(k2,A,B);
		point curPointCD_2 = getPoint(k2,C,D);
		distA = calDistance(curPointAB_1,curPointCD_1);
		distB = calDistance(curPointAB_2,curPointCD_2);
		minResult = min(minResult,min(distA,distB));
		if(distA >= distB)
		{
			fp = k1;
		}
		else
		{
			lp = k2;
		}


	}
	cout << sqrt(minResult);


}

int main()
{
	cout << fixed;
	cout.precision(6);	
	solve();

}