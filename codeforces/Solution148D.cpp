/*
ID: your_id_here
PROG: test
LANG: C++                  (<-- or C++11 if you prefer)
*/
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
typedef double ld; // long double doesn't work that well on codeforces??
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

#define N 1005
map<ii, double> memo;

ld solve (int W, int B) {
  if (W <= 0) { // no more white, so dragon won
    return 0;
  }
  if (B <= 0) { // no more invalid ones, so princess won
    return 1;
  }
  ii pair = make_pair (W, B);
  if (memo.find(pair) != memo.end()) {
    return memo[pair];
  }

  ld ans = (1.0 * W / (W + B)); // princess choosing the white
  // printf("ans %.5f, %.5f\n", ans, (1.0 * W / (W + B)));
  ld multiply = (1.0 * B / (W + B)); // princess choosing the black
  multiply *= (1.0 * (B - 1) / (W + B - 1)); // dragon choosing the black
  // printf("multiply: %.9f\n", multiply);

  if (multiply > 1e-10) {
    ld whiteJump = solve (W - 1, B - 2) * (1.0 * W / (W + B - 2));
    ld blackJump = solve (W, B - 3) * (1.0 * (B - 2) / (W + B - 2));
    ld add = multiply * (whiteJump + blackJump);
    // printf("add: %.9f... white: %.9f... black: %.9f\n", add, whiteJump, blackJump);
    ans += add;
  }
  memo[pair] = ans;
  // printf("%d %d %.5f\n", W, B, ans);
  return ans;
}

int main(){
  // freopen("in.in", "r", stdin);
  // ifstream fin ("test.in");
  // freopen("out.out", "w", stdout);
  // ofstream fout ("test.out");
  int w, b;
  cin >> w >> b;
  ld ans = solve(w, b);

  printf("%.9f\n", ans);

 return 0;

}
