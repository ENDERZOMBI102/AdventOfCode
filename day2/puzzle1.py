valid: int = 0

for line in open('./input', 'r'):
	policy, passwd = line.split(':')
	meta, char = policy.split(' ')
	minc, maxc = meta.split('-')
	if ( passwd.count(char) <= int(maxc) ) and ( passwd.count(char) >= int( minc ) ):
		valid += 1
print(valid)

