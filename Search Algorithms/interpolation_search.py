
def interpolationSearchRecursive(array, first, last, x):
    if (first <= last and x >= array[first] and x <= array[last]):
        pos = first + ((last - first) // (array[last] - array[first]) * (x - array[first]))
        if array[pos] == x:
            return pos
        elif array[pos] < x:
            return interpolationSearchRecursive(array, pos + 1, last, x)
        elif array[pos] > x:
            return interpolationSearchRecursive(array, first, pos - 1, x)
    return -1

def interpolationSearchIterative(array, first, last, x):
    while first <= last and x >= array[first] and x <= array[last]:
        if first == last:
            if array[first] == x:
                return first
            return -1
        pos = first + int(((float(last - first) / (array[last] - array[first])) * (x - array[first])))
        if array[pos] == x:
            return pos
        elif array[pos] > x:
            last = pos - 1
        else:
            first = pos + 1
    return -1
