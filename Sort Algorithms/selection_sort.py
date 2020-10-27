
def selectionSort(list):
    for i in range(len(list) - 1):
        minIndex = i
        for j in range(i+1, len(list) - 1):
            if list[j] < list[minIndex]:
                minIndex = j
        list[i], list[minIndex] = list[minIndex], list[i]
