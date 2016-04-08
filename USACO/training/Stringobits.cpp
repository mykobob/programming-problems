/*
ID: michael138
PROG: kimbits
LANG: C++
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

#define MAX_N 32
ll combo[MAX_N][MAX_N];

void print_combo (void);
void init_combo(void);
int total (ll N, ll R);
string solve (ll N, ll L, ll I);

int main(){
  init_combo();
  // print_combo();
  ifstream fin ("kimbits.in");
  ofstream fout ("kimbits.out");
  ll N, L, I;
  fin >> N >> L >> I;
  string ans = solve (N, L, I - 1);
  fout << ans << endl;
  return 0;
}

void print_combo () {
  for (int i = 0; i < MAX_N; i++) {
    for (int j = 0; j <= i; j++) {
      printf("%lld ", combo[i][j]);
    }
    printf("\n");
  }
}

void init_combo() {
  for (int i = 0; i < MAX_N; i++) {
    for (int j = 0; j < MAX_N; j++) {
      if (i == j || j == 0) {
        combo[i][j] = 1;
      } else {
        combo[i][j] = combo[i - 1][j] + combo[i-1][j-1];
      }
    }
  }
}

string solve (ll N, ll L, ll I) {
  if (N == 0) {
    return "";
  }
  int half = total (N - 1, L);
  // printf("N, %d and L, %d and I, %d is %d\n", N, L, I, half);
  if (I >= half) {
    string got = solve (N - 1, L - 1, I - half);
    return "1" + got;
  } else {
    string got = solve (N - 1, L, I);
    return "0" + got;
  }
}

int total (ll N, ll R) {
  int sum = 0;
  for (int i = 0; i <= min(R, N); ++i) {
    sum += combo[N][i];
  }
  return sum;
}
