#!/bin/bash
parserJarLoc=/msu/home/m1gsa00/git/verifyModelEZaddC/modelEZtoC.jar
javaCmd=java
CC=gcc
command -v $CC >/dev/null 2>&1 || { echo >&2 "Could not find c compiler. Aborting."; exit 1; }
command -v $javaCmd >/dev/null 2>&1 || { echo >&2 "Could not find java command. Aborting."; exit 1; }
if [ -e $1 ]
then
echo "found model file"
else
echo  "did not find model file"
exit 1
fi
if [ -e $parserJarLoc ]
then
echo "found jar file"
else
echo  "did not find jar file"
exit 1
fi

$javaCmd -cp $parserJarLoc gov.frb.ma.msu.toC.AMAtoC $1
rm -f $1_AMA_data.c
if [ -e $1_AMA_matrices.c ]
then
echo "Created .c file for generating sparseAMA hmat. Now trying to compile it."
$CC -c $1_AMA_matrices.c
if [ -e $1_AMA_matrices.o ]
then
	echo "Compilation successful."
else
	echo "Could not create a compilable h-matrix generator."
	if [ -e $1_AMA_matrices.c ]
		then
		echo "Model file name probably contains illegal characters. No periods please."
	fi
fi
else
echo "parser failed to generate a .c file"
fi
