#!/usr/bin/env bash
#cd must be project root
clear
echo cat examples/L2/JUMP2.bf
cat examples/L2/JUMP2.bf
echo
read
echo ./bfck -p examples/l2/jump2.bf --rewrite
./bfck -p examples/L2/jump2.bf --rewrite
echo
echo

read
echo cat examples/L2/JUMP2.bf
cat examples/L2/JUMP2.bf
echo
read
echo ./bfck -p examples/l2/jump2.bf --check
./bfck -p examples/L2/jump2.bf --check
echo
echo

read
echo cat examples/L2/bonjour.bf
cat examples/L2/bonjour.bf
echo
read
rm bonjour
echo ./bfck -p examples/l2/bonjour.bf -o bonjour
./bfck -p examples/L2/bonjour.bf -o bonjour
echo
read
echo cat bonjour
cat bonjour

read
clear
read
echo cat examples/L2/helloworld.bf
cat examples/L2/helloworld.bf
echo
read
echo ./bfck -p examples/l2/helloworld.bf
./bfck -p examples/L2/helloworld.bf

echo
echo
read
echo AFFICHAGE DE HELLOWORLD.BMP
open examples/images/helloworld.bmp
echo
read
echo ./bfck -p examples/images/helloworld.bmp
./bfck -p examples/images/helloworld.bmp
