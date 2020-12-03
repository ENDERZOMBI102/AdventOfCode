pattern = [ [ x for x in line ] for line in open( 'inputt', 'r' ) ]
trees, x, y = 0, 0, 0


for z in range( len( pattern ) ):
	if pattern[z].__contains__('\n'):
		pattern[z].remove('\n')


map = pattern.copy()


while y < len( map ):
	if map[y][x] == '#':
		trees += 1
	# step indexes
	if x + 3 > len( map[ 0 ] ) - 1:
		for i in range( len( map ) ):
			map[i].extend( pattern[i] )
	x += 3
	y += 1

print(trees)