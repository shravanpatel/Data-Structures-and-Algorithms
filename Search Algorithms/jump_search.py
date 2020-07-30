import math

def jumpSearch(array, x):
    left = 0
    n = len(array)
    step = int(math.sqrt(n))
    for i in range(0, n, step):
        if array[i] < x:
            left = i
        elif array[i] < x:
            return i
        else:
            break

    for j in array[left:]:
        if j == x:
            return left
        left += 1
    return -1
