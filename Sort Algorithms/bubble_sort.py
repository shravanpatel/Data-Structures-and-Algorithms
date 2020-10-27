
def optimizedBubbleSort(list):
    hasSwapped = True
    iterations = 0

    while(hasSwapped):
        hasSwapped = False
        for i in range(len(list) - iterations - 1):
            if list[i] > list[i+1]:
                list[i], list[i+1] = list[i+1], list[i]
                hasSwapped = True
        iterations += 1

def bubbleSortIterative(list):
    for i in range(len(list)):
        for j in range(len(list) - 1):
            if list[j] > list[j+1]:
                list[j], list[j+1] = list[j+1], list[j]

def bubbleSortRecursive(list, length):
    for i in range(length-1):
        if list[i] > list[i+1]:
                list[i], list[i+1] = list[i+1], list[i]
    if length-1 > 1:
        bubbleSortRecursive(list, length-1)

