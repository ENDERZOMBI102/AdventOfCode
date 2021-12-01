nums = [ int( line.replace('\n', '') ) for line in open('./input', 'r') ]
for i in nums:
    for j in nums:
        for y in nums:
            if i + j + y == 2020: print( i * j * y )