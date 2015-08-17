class HashItem
  constructor: (@key, @value) ->

class HashMap
  constructor: (@hashFunction) ->
    @storage = []

  add: (key, value) ->
    index = @hashFunction key
    if @storage[index]?
      indexFound = i for item, i in @storage[index] when item.key == key
      if indexFound?
        @storage[index][indexFound] = new HashItem(key, value)
      else
        @storage[index].push new HashItem(key, value)
    else
      @storage[index] = [ new HashItem(key, value) ]

  get: (key) ->
    index = @hashFunction key
    if @storage[index]?
      found = item for item in @storage[index] when item.key == key
      return undefined unless found?
      return found.value
    else
      undefined

hashFunctionForString = (string) ->
    hash = 11
    hash = hash * 31 + string.charCodeAt(index) for index in [0...string.length]
    hash

# tinytest lib

assertEqual = (object1, object2) ->
  throw "#{object1} is not equal to #{object2}" unless object1 == object2

should = (name, callback) ->
  try
    callback()
  catch error
    console.log "fail : should #{name}"
    console.log "      >> #{error}"
    return
  console.log " ok  : should #{name}"

# tests

should "add new element", ->
  hashMap = new HashMap hashFunctionForString
  hashMap.add("foo","bar")
  assertEqual hashMap.get("foo"), "bar"

should "return undefined when element is not found", ->
  hashMap = new HashMap hashFunctionForString
  assertEqual hashMap.get "foo", undefined

should "add not erase new element" , ->
  hashMap = new HashMap hashFunctionForString
  hashMap.add("foo","bar")
  hashMap.add("bar", "foo")
  assertEqual hashMap.get("foo"), "bar"
  assertEqual hashMap.get("bar"), "foo"

should "erase element if key is same", ->
  hashMap = new HashMap hashFunctionForString
  hashMap.add("foo","bar")
  hashMap.add("foo", "qix")
  assertEqual hashMap.get("foo"), "qix"

should "avoid collision when collision occurs", ->
  hashMap = new HashMap -> 7
  hashMap.add("foo","bar")
  hashMap.add("bar", "foo")
  assertEqual hashMap.get("foo"), "bar"
  assertEqual hashMap.get("bar"), "foo"

should "replace the right element eventhoug a collision occurs", ->
  hashMap = new HashMap -> 7
  hashMap.add("foo","bar")
  hashMap.add("foo", "qiz")
  assertEqual hashMap.get("foo"), "qiz"
