#!/bin/bash
parserJarLoc=/msu/home/m1gsa00/git/verifyModelEZaddC/bin/modelEZtoC.jar
javaCmd=java
CC=gcc

$javaCmd -cp $parserJarLoc gov.frb.ma.msu.toC.AMAtoC $1
rm -f $1_AMA_data.c
$CC -c $1_AMA_matrices.c
if [ -e $1_AMA_matrices.o ]
then
	echo "Created a compilable h-matrix generator."
else
	echo "Could not create a compilable h-matrix generator."
	if [ -e $1_AMA_matrices.c ]
		then
		echo "Model file name probably contains illegal characters. No periods please."
	fi
fi
