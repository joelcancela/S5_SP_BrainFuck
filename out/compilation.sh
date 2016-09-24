#!/bin/bash
#--------------------------------------------------------------------#
#                   bfck.jar to bfck linux executable                #
#--------------------------------------------------------------------#
#              By Aghiles Dziri, Tanguy Invernizzi,                  #
#              Pierre Rainero and Joel Cancela Vaz                   #
#                                                                    #
# This file is used to simulate the bfck linux executable from the   #
# bfck jar file. To use it, just generate your bfck.jar, create      #
# a symbolic link to this script with the command "ln -s" in the     #
# root folder of the project and launch the link as if it was a      #
# linux executable                                                   #
#--------------------------------------------------------------------#

# Name of the jar file to compile
jarFileToLaunch="bfck.jar"

# Insert here your additional java parameters
extraJavaArgs=""

# Name of the path to the jar to launch
jarFile="./out/$jarFileToLaunch"

#--------------------------------------------------------------------

# Restore the arguments
strArgs=''
spaces="[[:space:]]"
for i in "$@"
do
    if [[ $i =~ $spaces ]]; then
        strArgs="$strArgs \"$i\""
        continue
    fi
    strArgs="$strArgs ${i}"
done

# Launch the application
bash -c "java $extraJavaArgs -jar $jarFile $strArgs"
exit $?
