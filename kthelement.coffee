#Given an array of integers find the kth element in the sorted order (not the kth distinct element). So, if the array is [3, 1, 2, 1, 4] and k is 3 then the result is 2, because it’s the 3rd element in sorted order (but the 3rd distinct element is 3).
# - See more at: http://www.ardendertat.com/2011/10/27/programming-interview-questions-10-kth-largest-element-in-array/#sthash.Bv60UnCf.dpuf


kthelement = (source, kth) -> #(0 nlogN) (due to sort)
  source.sort (a,b) -> a - b
  source[kth]

kthelementFast = (source, kth) ->
  swap = (array, indexSource, indexDestination) ->
    tmp = array[indexDestination]
    array[indexDestination] = array[indexSource]
    array[indexSource] = tmp

  partition = (start, end, kth) ->
    pivotIndex = start + Math.floor((end-start)/2)
    pivotValue = source[pivotIndex]
    swap source, pivotIndex, end

    insertIndex = start

    for index in [start...end]
      if source[index] < pivotValue
        swap source, index, insertIndex
        insertIndex++
    swap source, insertIndex, end
    if kth == insertIndex
      return pivotValue
    else
      if kth < insertIndex
        return partition start, insertIndex - 1, kth
      else
        return partition insertIndex + 1, end, kth

  return partition 0, source.length - 1, kth - 1


console.log kthelementFast [8,5,1,3,2,1,4], 3
