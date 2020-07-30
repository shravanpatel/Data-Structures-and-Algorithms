
def linearSearch(array, x):
    n = len(array);
    for i in range (0, n):
        if(array[i] == x):
            return i;
    return -1;
