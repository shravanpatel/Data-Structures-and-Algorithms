
def binarySearchRecursive(array, first, last, x):
    if last >= first:
        mid = first + (last - first) // 2;
        if array[mid] == x:
            return mid;
        elif array[mid] > x:
            return binarySearchRecursive(array, first, mid - 1, x);
        else:
            return binarySearchRecursive(array, mid + 1, last, x);
    else:
        return -1;

def binarySearchIterative(array, first, last, x):
    while first <= last:
        mid = first + (last - first) // 2;
        if array[mid] == x:
            return mid;
        elif array[mid] < x:
            first = mid + 1;
        else:
            last = mid - 1;
    return -1;
