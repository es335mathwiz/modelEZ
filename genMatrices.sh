#!/bin/bash
ML=matlab




DISPLAY=
$ML >& matlab.out <<EOF
date
SPSolvePath='~/sp_solve'
path
genMatrices
