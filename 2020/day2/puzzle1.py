import os
os.chdir( os.path.split(__file__)[0] )

def xor(a, b):
    x = not ( a and b )
    y = a or b
    return x and y


valid: int = 0

for line in open('./input', 'r'):
	policy, passwd = line.split(':')
	passwd:str
	passwd = passwd.strip().strip('\n')
	meta, char = policy.split(' ')
	pos0, pos1 = meta.split('-')
	pos0, pos1 = int( pos0 ) - 1, int( pos1 ) - 1
	x = passwd[ pos0 ] == char
	y = passwd[ pos1 ] == char
	if xor( x, y ):
		valid += 1
print(valid)