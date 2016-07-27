N, M = map(int, raw_input().split())

xs = set()
ys = set()

for i in range(0, M):
    x,y = map(int, raw_input().split())
    xs.add(x)
    ys.add(y)
    print N * N - (len(xs) * N + len(ys) * N - len(xs) * len(ys)),
print
