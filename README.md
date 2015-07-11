# Data Structures

## Array

Probably the most basic data structure, the array is instantiated to a fixed length by allocating 
`array-length * element-length` addresses of memory. All the elements must be the same length and an item can be 
retrieved by index at the address `array-offset + index * element-length`.

A multi-dimensional array is an array of arrays (possibly of other arrays), items can still be accessed in constant time 
looking up the value at index `[i1,i2,i3]` in a 3-dimensional array with dimension lengths `l1,l2,l3` is the same 
operation as looking up index `[i1 * l2 * l3 + i2 * i3 + i3]` in a single dimension array of length `l1 * l2 * l3`.

Features:
* indexed get / set O(1)
* fixed size
* traversable

Array is so primitive that I don't believe this is re-implementable in java without using itself or some native 
interfaces, so this is the one collection structure which I will only wrap.


## Dynamic array

An array is of fixed size as it depends on contiguous memory addresses for its indexing. Dynamic arrays allow elements 
to be added to the end of the array, usually this will be done by having an underlying array with a larger capacity, if
the underlying array fills up then the array can be _grown_ by allocating a larger underlying array and copying all of 
the elements over.

Features:
* indexed get / set O(1)
* elements can be added/removed at the end with amortised cost O(1)
* wastes space O(n) with size
* traversable

The efficient add / remove from the end means a dynamic array can be used as a stack, where these operations are called 
push and pop


## Linked list

A linked list consists of nodes, each contains a value and a pointer to the next node in the list. Insertions are easy 
as they involve only creating a new node, pointing from it to the node which should be after and to it from the node 
which should be before. Removals are similarly efficient. For a singularly linked list, any operations are more 
efficient to do at the beginning of the list, as first the list needs to be traversed to the required node.

Features:
* indexed get / set / insertion O(1) after search time which is O(n) with index
* elements can be added/removed from the beginning O(1)
* insertion is O(1) after search time
* traversable

The efficient add / remove at the beginning means it can be used as a stack.


## Doubly linked list

By storing node pointers in both directions a doubly linked list is traversable in both directions and can be insert / 
remove efficiently from both the beginning and the end. Often sentinel nodes are used as a terminal to simplify insertion.

Features:
* as linked list but searches can begin from the end too

Inserting at one end and removing from the other means doubly linked lists can be used as a queue.

