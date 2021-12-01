with open('input', 'r') as file:
	depths = [ int(depth) for depth in file.readlines() ]

count = 0
lastDepth = depths[0]
for depth in depths[1:]:
	count += depth > lastDepth
	lastDepth = depth

print(count)
