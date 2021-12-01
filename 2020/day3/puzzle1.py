pattern = [ [ x for x in line ] for line in open( 'inputt', 'r' ) ]

# prepare map
for z in range( len( pattern ) ):
	if pattern[z].__contains__('\n'):
		pattern[z].remove('\n')


def work(horiHop, vertHop) -> int:
	map = pattern.copy()
	trees, x, y = 0, 0, 0
	while y < len( map ):
		if map[y][x] == '#':
			trees += 1
		# check indexes
		while x + horiHop > len( map[ 0 ] ) - 1:
			for i in range( len( map ) ):
				map[i].extend( pattern[i] )
		# step indexes
		x += horiHop
		y += vertHop
	return trees


print(
	work(1, 1) *
	work(3, 1) *
	work(5, 1) *
	work(7, 1) *
	work(1, 2)
)
