# Data Structures

General guidance:
* No UnsupportedOperationExceptions
* No attempt to support nulls in collections
* No using the library

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


## Binary search tree

Trees enable searching, inserting and deleting of ordered keys in a reasonable time. Whereas list inserts and deletes
are generally O(n) and a binary search is generally O(log n) (with and indexable list). Binary tree inserts, deletes and 
searches are all on average O(log n) with O(n) as the worst case. The first element inserted is the root of the tree 
with the greater elements added afterwards creating a subtree to the right and the lesser elements creating a subtree to 
the left. This means that the shape of the tree is dependant on the order in which the elements are added. The worst
case is if they are added in sorted order, in which case the tree resembles a linked list. After random insertion / 
deletion the tree height (and therefore the worst case search for a random tree) approaches sqrt(n), which grows much 
faster than the optimum height of log(n).

Features:
* traversable in order from any searched element
* searchable O(log n) average
* sorted inserts O(log n) average
* can be used to form a double ended priority queue
* Worst case shape has O(n) operations


## Red-black self-balancing binary search tree

In order to self-balance a binary search tree, this structure contains 1 extra bit of data per node, which is whether 
the node is red or black. In some implementations no extra memory is required over the BST.

Rules:
* the root is black
* all leaves are black
* every red node must have 2 black children and therefore must have a black parent
* every path from a node to its descendant leaf nodes must contain the same number of black nodes 

The last 2 rules mean that the longest path can be twice the length of the shortest path as the shortest path as they 
both have the same number of blacks and cannot have 2 consecutive reds.

Features:
* All of the binary search tree
* Maintains balance so the worst case shape of the tree still has O(log n) operations


## HashTable

A hashtable consists of a linear data structure of buckets. A hash function is used to choose which bucket a key should 
be placed in / searched for in. Typically a hash is generated and the modulo of it with the number of buckets is taken.
Several entries could have the same hash and so some form of collision handling is necessary, a typical method is to 
have a linked list in the bucket. Collisions slow the HashSet so ideally there wont be any, though making the hashtable
large enough to avoid them causes memory to be wasted.


## Adjacency list (Graph)

A representation of a graph as a collection of unordered lists, each one representing a vertex in the graph, each list 
describes the set of neighbours of the vertex. The OO adjacency list proposed by Goodrich and Tamassia consists of 
Vertex objects which contain a collection of Edge objects, each of which has a pointer to both Vertices it connects. The
object can also store extra information about the edge.