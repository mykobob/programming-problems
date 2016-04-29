#include <algorithm>
#include <iostream>
#include <iterator>
#include <numeric>
#include <sstream>
#include <fstream>
#include <cassert>
#include <climits>
#include <cstdlib>
#include <cstring>
#include <string>
#include <cstdio>
#include <vector>
#include <bitset>
#include <cmath>
#include <queue>
#include <deque>
#include <stack>
#include <list>
#include <map>
#include <set>

using namespace std;

typedef long long ll;
typedef pair<int,int> ii;
typedef pair<string,int> si;
typedef pair<int,ii> iii;
typedef vector <si> vsi;
typedef vector <ii> vii;
typedef vector <int> vi;
typedef vector <char> vc;
typedef vector <string> vs;
typedef map <string,vs> msvs;
typedef map <string,int> msi;
typedef map <string,string> mss;
#define INF 1000000000

char original[50][50];

int main(){
  ofstream fout ("skener.out");
  int r, c, zr, zc;
  cin >> r >> c >> zr >> zc;
  for (int i = 0; i < r; ++i) {
    string str;
    cin >> str;
    for (int j = 0; j < c; ++j) {
        original[i][j] = str[j];
    }
  }
  for (int i = 0; i < r * zr; ++i) {
    for (int j = 0; j < c * zc; ++j) {
        cout << original[i/zr][j/zc];                
    }
    cout << endl;
  }
  return 0;
}
