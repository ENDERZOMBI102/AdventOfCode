def IsValid( data ):
	return ('byr' in data) and \
			('iyr' in data) and \
			('eyr' in data) and \
			('hgt' in data) and \
			('hcl' in data) and \
			('ecl' in data) and \
			('pid' in data)


if __name__ == '__main__':
	buffer: str = ''
	valid: int = 0
	for line in open( 'input', 'r' ):
		if line == '\n':
			valid += 1 if IsValid( buffer ) else 0
			buffer = ''
		else:
			buffer += line
	valid += 1 if IsValid( buffer ) else 0
	print( valid )
