#include <iostream>
using namespace std;
string name[100001];
int korean[100001] = {0};
int english[100001] = {0};
int math[100001] = {0};

string sortedName[100001];
int sortedKorean[100001] = {0};
int sortedEnglish[100001] = {0};
int sortedMath[100001] = {0};
/*
2
Soong 50 60 90
Taewhan 50 60 90

2
Taewhan 50 60 90
Soong 50 60 90

*/


void merge(int start,int end)
{
	int i = start;
	int mid = (start+end)/2;
	int j = mid+1;
	int sortedIdx = start;
	while(i<=mid && j<=end)
	{
		if(korean[i] == korean[j] && english[i] == english[j] && math[i] == math[j])
		{
			if(name[i] > name[j])
			{
				sortedKorean[sortedIdx] = korean[j];
				sortedEnglish[sortedIdx] = english[j];
				sortedMath[sortedIdx] = math[j];
				sortedName[sortedIdx++] = name[j++];
			}
			else
			{	
				sortedKorean[sortedIdx] = korean[i];
				sortedEnglish[sortedIdx] = english[i];
				sortedMath[sortedIdx] = math[i];
				sortedName[sortedIdx++] = name[i++];
			}
		}

		else if(korean[i] == korean[j] && english[i] == english[j])
		{
			if(math[i] >  math[j])
			{
				sortedKorean[sortedIdx] = korean[i];
				sortedEnglish[sortedIdx] = english[i];
				sortedMath[sortedIdx] = math[i];
				sortedName[sortedIdx++] = name[i++];
			}
			if(math[i] < math[j])
			{
				sortedKorean[sortedIdx] = korean[j];
				sortedEnglish[sortedIdx] = english[j];
				sortedMath[sortedIdx] = math[j];
				sortedName[sortedIdx++] = name[j++];
			}

		}
		else if(korean[i] == korean[j])
		{
			if(english[i] <  english[j])
			{
				sortedKorean[sortedIdx] = korean[i];
				sortedEnglish[sortedIdx] = english[i];
				sortedMath[sortedIdx] = math[i];
				sortedName[sortedIdx++] = name[i++];
			}
			if(english[i] > english[j])
			{
				sortedKorean[sortedIdx] = korean[j];
				sortedEnglish[sortedIdx] = english[j];
				sortedMath[sortedIdx] = math[j];
				sortedName[sortedIdx++] = name[j++];
			}

		}
		else
		{
			if(korean[i] > korean[j])
			{
				sortedKorean[sortedIdx] = korean[i];
				sortedEnglish[sortedIdx] = english[i];
				sortedMath[sortedIdx] = math[i];
				sortedName[sortedIdx++] = name[i++];
			}
			else
			{
				sortedKorean[sortedIdx] = korean[j];
				sortedEnglish[sortedIdx] = english[j];
				sortedMath[sortedIdx] = math[j];
				sortedName[sortedIdx++] = name[j++];
			}
			
		}

	}

	if(i > mid)
	{
		while(j<=end)
		{
			sortedKorean[sortedIdx] = korean[j];
			sortedEnglish[sortedIdx] = english[j];
			sortedMath[sortedIdx] = math[j];
			sortedName[sortedIdx++] = name[j++];
		}
	}
	if(j > end)
	{
		while(i<=mid)
		{
			sortedKorean[sortedIdx] = korean[i];
			sortedEnglish[sortedIdx] = english[i];
			sortedMath[sortedIdx] = math[i];
			sortedName[sortedIdx++] = name[i++];
		}
	}

	for(int i=start;i<=end;i++)
	{
		name[i] = sortedName[i];
		korean[i] = sortedKorean[i];
		math[i] = sortedMath[i]; 
		english[i] = sortedEnglish[i];
	}



}

void mergeSort(int start,int end)
{
	if(end > start)
	{
		int mid = (start+end)/2;
		mergeSort(start,mid);
		mergeSort(mid+1,end);
		merge(start,end);
	}
}



int main()
{
	int num=0;
	cin >> num;	

	for(int i=1;i<=num;i++)
	{
		cin >> name[i] >> korean[i] >> english[i] >> math[i];
	}
	mergeSort(1,num);


	for(int i=1;i<=num;i++)
	{
		cout << name[i] <<"\n";
	}
	
}
