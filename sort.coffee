array = [1991,1,2,4,5,0,18,90,90,90,10,1973,42,8,34,2015,102,3]

bubbleSort = (source) -> #0(n^2)
  copy = source.slice()
  moved = true
  until !moved
    moved = false
    for index in [0...copy.length - 1]
      if copy[index] > copy[index+1]
        swap = copy[index]
        copy[index] = copy[index + 1]
        copy[index+1] = swap
        moved = true
    console.log copy  
  copy

quickSort = (source) -> #(O(n log(n)))
  swap = (src, tar) ->
    tmp = source[src]
    source[src] = source[tar]
    source[tar] = tmp

  sort = (start, end) ->
    if start < end
      pivotIndex = split start, end
      sort start, pivotIndex - 1
      console.log source
      sort pivotIndex + 1, end
      console.log source

  split = (start, end) ->
    pivotIndex = start + Math.round((end - start) / 2)
    swap pivotIndex, end
    insertIndex = start
    for index in [start..end - 1]
      if source[index] < source[end]
        swap index, insertIndex
        insertIndex++
    swap end, insertIndex
    return insertIndex

  sort 0, source.length - 1
  source

console.log "initial array"
console.log array
console.log "bubble"
console.log bubbleSort array
console.log "quick"
console.log quickSort array
