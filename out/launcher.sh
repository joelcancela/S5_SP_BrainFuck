#!/bin/bash
#--------------------------------------------------------------------#
#                   bfck.jar to bfck linux executable                #
#--------------------------------------------------------------------#
#              By Aghiles DZIRI, Tanguy INVERNIZZI,                  #
#              Pierre RAINERO and Joel CANCELA VAZ                   #
#                                                                    #
# This file is used to simulate the bfck linux executable from the   #
# bfck jar file. To use it, just generate your bfck.jar, create      #
# a symbolic link to this script with the command "ln -s" in the     #
# root folder of the project and launch the link as if it was a      #
# linux executable                                                   #
#--------------------------------------------------------------------#

# Name of the jar compiled
jarFileToLaunch="bfck.jar"

# Name of the path to the jar to launch
jarFile="./out/$jarFileToLaunch"

#--------------------------------------------------------------------

# Transfer the link arguments to the java command
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
bash -c "java -jar $jarFile $strArgs"
exit $?
