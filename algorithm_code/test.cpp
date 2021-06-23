#include <iostream>
#include <fstream>
#include <string>
#include <vector>

using namespace std;


vector split(string s, string divid) {
	vector v;
	char* c = strtok((char*)s.c_str(), divid.c_str());
	while (c) {
		v.push_back(c);
		c = strtok(NULL, divid.c_str());
	}
	return v;
}

double getDistanceFromZero(int x,int y)
{  
    return 0.0;
}

string init()
{
    string initString = "&Animal 2.0\ntitle \"Demo of Animal Script Features\"\nauthor \"Lee, Su-Hyun <e-mail@changwon.ac.kr>\"\n";
    return initString;
}

string getConvertedString(string s)
{
    vector result;
    result = split(s," ");
    

    int x,y;
    x = stoi(result[0]);
    y = stoi(result[1]);
    cout << x << " " << y << endl;


    
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