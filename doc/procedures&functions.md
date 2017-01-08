# Procedures & Functions

Like in others programming languages, procedures and functions work in a copy of the data memory.

## Procedures
Procedures can be defined anywhere in the program, they have to be defined this way :
```
void <name>([args,]){
}
```

The arguments are memory cells numbers, and when you are write those arguments in the procedure code, the next operations will be on this memory cell.

Example :
```
void ff(x,y,z)     {
	x
    ++
	y
    --.
}

```

In this procedure, the memory pointer goes at the x cell and this cell gets incremented twice, then it moves to the y cell and the cell gets decremented twice and printed.<br>
At the end of the procedure, the memory restores the state it had before the procedure call.


## Functions

Functions can also be defined anywhere in the program, they have to be defined this way :
```
func <name>([args,]){
    return n
}
```
  
They work the same way as the procedures, they only thing that changes is the return clause,
 the return is always a cell number or an argument (which is also a cell number).<br>
The cell designated by the number returned will take the value it had at the end of the function.
 
Example :
```
func maf(x){
x 
+++
return x
}

maf(2);
```

2 will take the value it had before the call of the function+3.