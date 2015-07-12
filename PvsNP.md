# P vs NP

I am not a computer scientist and this has not been checked by one, this is my understanding of this subject.

If any problem is in NP-complete and P then all NP-problems are in P as all NP can be transformed into any NP-complete 
 (within a domain).


## P

P is the set of problems in a domain which can be solved in Polynomial time by a deterministic Turing machine.


### Deterministic Turing machine (DTM)

Examples of this are all of what we consider to be computers today, a deterministic turing machine has a finite number
of internal states and an infinite tape which it can move in either direction, read and write. On each execution cycle 
the machine reads from the tape and depending upon the read value and the current state may perform a write to the 
current tape position, move the head and change state.

It is used as an abstraction for computer science problems and any 


### Polynomial time

The key to the whole area is solving problems in polynomial time, which is <= O(n^k)


## NP

The set of problems in a domain which can be solved in Polynomial time by a non-deterministic Turing machine.


### Non-deterministic Turing machine (NTM)

The same structure as a Turing machine but that after each read, the machine may branch to try multiple different 
instructions. The final result is chosen by the first completed branch. An NTM can solve any DTM problem as it has the
option to not branch and therefore all P problems are NP problems.

There have been suggestions that quantum computers could perform as NTMs but it is more likely that they will not be 
as powerful and will be able to solve a set in polynomial time which is larger than P but not all of NP (unless P = NP).

An NTM can be simulated in a DTM by doing a breadth-first search and therefore all NP-problems can be solved by a DTM
in exponential time.


## NP-hard

A problem is NP-hard when every problem in NP can be reduced to it in polynomial time, NP-hard problems are not 
necessarily NP.


## NP-complete

Those NP-hard problems which are also NP are NP-complete.



Something about verification being P for NP-complete problems.