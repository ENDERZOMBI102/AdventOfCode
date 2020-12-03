nums = [ int( line.replace('\n', '') ) for line in open('./input', 'r') ]
for i in nums:
    for j in nums:
        if i + j == 2020: print( i * j )