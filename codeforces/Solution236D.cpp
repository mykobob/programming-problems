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

#define MAX_N 10000
#define HIT 0
#define MISS 1

int N;
int p[MAX_N];
int expected_value[2][MAX_N];

int main(){
  // freopen("in.in", "r", stdin);
  // ifstream fin ("test.in");
  // freopen("out.out", "w", stdout);
  // ofstream fout ("test.out");
  cin >> N;
  for (int i = 0;i < N;i++)
    cin >> p[i];

  expected_value[0] = p[0];

  for (int i = 1;i < N;i++) {
    int expected_i = 0;
    for (int j = 0;j < i;j++) {
      expected_i += expected_value[HIT][j] * (i - j) * (i - j);
      expected_i += expected_value[MISS][j];
    }
    expected_value[i] = expected_i;
  }
  printf("%.12")

 return 0;

}
