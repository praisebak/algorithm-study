#include <tuple>
#include <string>
#include <iostream>
#include <vector>

using namespace std;

int main()
{
	// make tuple variable.
	typedef std::tuple<int, std::string, bool> OddOrEven;
	OddOrEven myNumber = std::make_tuple(10, std::string("Even"), true);

	// get tuple size
	std::cout << "size : " << std::tuple_size<decltype(myNumber)>::value << std::endl;

	// get each value and get type using std::tuple_element, auto keyword.
	std::tuple_element<0, decltype(myNumber)>::type nNum = std::get<0>(myNumber);
	auto szVal = std::get<1>(myNumber);
	bool bEven = std::get<2>(myNumber);

	std::cout << nNum << ", " << szVal << ", " << std::boolalpha << bEven << std::endl;

	return 0;
}