#!/bin/bash
#--------------------------------------------------------------------
# Lanceur d'applications Java
#--------------------------------------------------------------------

# Nom du fichier jar executable à lancer
jarFileToLaunch="monapplication.jar"

# Version minimum de Java
minJavaVersion="1.6.0"

# Paramètres Java additionnels
extraJavaArgs=""

#--------------------------------------------------------------------

# Le fichier jar existe ?
thisDir="$(dirname "$(readlink -f "$0")")"
jarFile="$thisDir/$jarFileToLaunch"
if [ ! -f $jarFile ]; then
        echo "Error: Invalid or corrupt jarfile $jarFile"
        exit 1
fi

# Java existe dans le $PATH ?
command -v java >/dev/null
if [ $? -eq 1 ]; then
    echo "Error: Java not found in \$PATH"
    exit 1
fi

# La version de Java est correcte ?
javaVersion=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
if [[ "$javaVersion" < "$minJavaVersion" ]]; then
    echo "Error: Requires a Java version greater than or equal to $minJavaVersion"
    exit 1
fi

# Reconstruction de la ligne de commande (préserve les '"')
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

# Lancement de l'application
bash -c "java $extraJavaArgs -jar $jarFile $strArgs"
exit $?