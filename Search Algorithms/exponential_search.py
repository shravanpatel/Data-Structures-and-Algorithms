
def binarySearchRecursive(array, first, last, x):
    if last >= first:
        mid = first + (last - first) // 2
        if array[mid] == x:
            return mid
        elif array[mid] > x:
            return binarySearchRecursive(array, first, mid - 1, x)
        else:
            return binarySearchRecursive(array, mid + 1, last, x)
    else:
        return -1

def exponentialSearch(array, x):
    n = len(array)
    if array[0] == x:
        return 0
    i = 1
    while i < n and array[i] <= x:
        i = i * 2
    return binarySearchRecursive(array, int(i / 2), min(i, n), x)
