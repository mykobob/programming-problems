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

string correct;
int correctNum;
int length;
string received;
int receivedNum;

int total = 0;

void solve (int num, int idx) {
  if (idx == length) {
    if (num == correctNum) {
      total++;
    }
    return;
  }
  solve (num + 1, idx + 1);
  solve (num - 1, idx + 1);
}

int main(){
  cin >> correct;
  cin >> received;
  length = correct.length();
  sort (correct.begin(), correct.end());
  sort (received.begin(), received.end());
  int startIdx = received.find ("?");
  for (int i = 0; i < length;i ++) {
    correctNum += correct[i] == '+' ? 1 : -1;
    if (received[i] != '?') {
      receivedNum += received[i] == '+' ? 1 : -1;
    }
  }
  if (startIdx == -1) {
    if (correctNum == receivedNum) {
      printf("%.12f\n", 1.0);
    } else {
      printf("%.12f\n", 0.0);
    }
  } else {
    solve (receivedNum, startIdx);
    long all_total = 1 << (length - startIdx);
    printf("%.12f\n", 1.0 * total / all_total);
  }

 return 0;

}
