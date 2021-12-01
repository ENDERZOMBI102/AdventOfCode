with open('input', 'r') as file:
	depths = [ int(depth) for depth in file.readlines() ]


count = 0
lastIncreased = False
lastSum = sum( depths[:3] )


for index in range( 1, len( depths ) ):
	cSum = sum( depths[ index : index + 3 ] )
	increased = cSum > lastSum
	
	if lastSum == cSum:
		continue
	if increased:
		count += 1
	
	lastSum = cSum


print(count)
