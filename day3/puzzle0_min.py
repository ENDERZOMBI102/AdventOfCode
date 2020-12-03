pattern = [ [ x for x in line ] for line in open( 'inputt', 'r' ) ]
for z in range( len( pattern ) ):
	if pattern[z].__contains__('\n'): pattern[z].remove('\n')
trees, x, y, map = 0, 0, 0, pattern.copy()
while y < len( map ):
	if map[y][x] == '#': trees += 1
	if x + 3 > len( map[ 0 ] ) - 1:
		for i in range( len( map ) ): map[i].extend( pattern[i] )
	x, y = x + 3, y + 1
print(trees)