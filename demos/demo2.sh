#!/usr/bin/env bash
#cd must be project root
clear
echo cat src/test/resources/L2/usual/jumpInternalLoop2.bf
./bfck -p src/test/resources/L2/usual/jumpInternalLoop2.bf --rewrite
echo
read
echo ./bfck -p src/test/resources/L2/usual/jumpInternalLoop2.bf --translate
./bfck -p src/test/resources/L2/usual/jumpInternalLoop2.bf --translate
start outputTranslate.bmp

read
clear
echo cat examples/L3/demoMetricsPetit.bf
cat examples/L3/demoMetricsPetit.bf
echo
read
echo ./bfck -p examples/L3/demoMetricsPetit.bf
./bfck -p examples/L3/demoMetricsPetit.bf
echo
echo

read
clear
echo cat examples/L2/helloworld.bf
cat examples/L2/helloworld.bf
echo
read
echo ./bfck -p examples/l2/helloworld.bf
./bfck -p examples/L2/helloworld.bf
#Not proven that helloworld in bmp and bf do the same thing

read
clear
echo cat examples/L2/JUMP2.bf
cat examples/L2/JUMP2.bf
echo
read
echo ./bfck -p examples/L2/JUMP2.bf --trace
./bfck -p examples/L2/JUMP2.bf --trace
read
echo cat examples/L2/JUMP2.log
cat examples/L2/JUMP2.log

read
clear
echo cat examples/L2/JUMPBACK6.bf
cat examples/L2/JUMPBACK6.bf
echo
read
echo ./bfck -p examples/L2/JUMPBACK6.bf --trace
./bfck -p examples/L2/JUMPBACK6.bf --trace
read
echo cat examples/L2/JUMPBACK6.log
cat examples/L2/JUMPBACK6.log

read
clear
echo cat examples/L3/prog_helloCOM.bf
cat examples/L3/prog_helloCOM.bf
echo
read
echo ./bfck -p examples/L3/prog_helloCOM.bf
./bfck -p examples/L3/prog_helloCOM.bf

read
clear
echo cat examples/L3/demoMacros.bf
cat examples/L3/demoMacros.bf
echo
read
echo ./bfck -p examples/L3/demoMacros.bf
./bfck -p examples/L3/demoMacros.bf

read
clear
echo cat examples/L3/demoMacrosTO_DIGITetMULTI_DECR.bf
cat examples/L3/demoMacrosTO_DIGITetMULTI_DECR.bf
echo
read
echo ./bfck -p examples/L3/demoMacrosTO_DIGITetMULTI_DECR.bf
./bfck -p examples/L3/demoMacrosTO_DIGITetMULTI_DECR.bf
read