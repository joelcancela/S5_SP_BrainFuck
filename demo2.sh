clear
echo cat src/test/resources/L2/usual/jumpInternalLoop2.bf
./bfck -p src/test/resources/L2/usual/jumpInternalLoop2.bf --rewrite
echo
read
echo ./bfck -p src/test/resources/L2/usual/jumpInternalLoop2.bf --translate
./bfck -p src/test/resources/L2/usual/jumpInternalLoop2.bf --translate
start outputTranslate.bmp
read
echo
echo

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
read
echo cat examples/L2/helloworld.bf
cat examples/L2/helloworld.bf
echo
read
echo ./bfck -p examples/l2/helloworld.bf
./bfck -p examples/L2/helloworld.bf

read
clear
read
echo cat examples/L2/JUMP2.bf
cat examples/L2/JUMP2.bf
echo
read
echo ./bfck -p examples/l2/JUMP2.bf --trace
./bfck -p examples/L2/JUMP2.bf --trace
read
echo cat examples/L2/JUMP2.log
cat examples/L2/JUMP2.log
