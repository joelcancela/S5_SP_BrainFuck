#!/usr/bin/env bash
#cd must be project root
#Procedure
clear
echo cat examples/L4/demoProcedure.bf
read
cat examples/L4/demoProcedure.bf
read
echo ./bfck -p examples/L4/demoProcedure.bf
read
./bfck -p examples/L4/demoProcedure.bf
echo
read
#Function
clear
echo cat examples/L4/functionDemo.bf
read
cat examples/L4/functionDemo.bf
read
echo ./bfck -p examples/L4/functionDemo.bf
read
./bfck -p examples/L4/functionDemo.bf
echo
read
#C Gen Helloworld
clear
echo cat examples/L2/helloworld.bf
read
cat examples/L2/helloworld.bf
echo
read
echo ./bfck -p examples/L2/helloworld.bf --cgen
read
./bfck -p examples/L2/helloworld.bf --cgen
echo cat examples/L2/helloworld.c
read
cat examples/L2/helloworld.c
read
echo
echo gcc -std=c99 -o examples/L3/prog examples/L2/helloworld.c
read
gcc -std=c99 -w -o examples/L3/prog examples/L2/helloworld.c
echo
echo ./examples/L3/prog
read
./examples/L3/prog
read
#C Gen Macro
clear
echo cat examples/L3/macros_example1.bf
read
cat examples/L3/macros_example1.bf
echo
read
echo ./bfck -p examples/L3/macros_example1.bf --cgen
read
./bfck -p examples/L3/macros_example1.bf --cgen
echo cat examples/L3/macros_example1.c
read
cat examples/L3/macros_example1.c
read
echo
echo gcc -std=c99 -o examples/L3/prog examples/L3/macros_example1.c
echo
read
gcc -std=c99 -w -o examples/L3/prog examples/L3/macros_example1.c
echo
echo ./examples/L3/prog
read
./examples/L3/prog
read
##END
echo
rm examples/L3/macros_example1.c
rm examples/L2/helloworld.c
read