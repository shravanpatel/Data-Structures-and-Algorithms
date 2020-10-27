
def insertionSortIterative(list):
    for i in range(1, len(list)):
        value = list[i]
        position = i
        while position > 0 and list[position-1] > value:
            list[position] = list[position-1]
            position -= 1
        list[position] = value

def insertionSortRecursive(list, length):
    if length <= 1:
        return
    insertionSortRecursive(list, length-1)
    last = list[length - 1]
    j = length - 2
    while (j >= 0 and list[j] > last):
        list[j + 1] = list[j]
        j = j - 1
    list[j + 1] = last
