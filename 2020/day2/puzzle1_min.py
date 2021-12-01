valid: int = 0
for line in open('./input', 'r'):
	policy, passwd = line.split(':')
	passwd = passwd.strip().strip('\n')
	meta, char = policy.split(' ')
	x, y = passwd[ int( meta.split('-')[0] ) - 1 ] == char, passwd[ int( meta.split('-')[1] ) - 1 ] == char
	if ( not ( x and y ) ) and ( x or y ): valid += 1
print(valid)