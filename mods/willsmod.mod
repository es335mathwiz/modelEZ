MODEL > willsmod

ENDOG >
         YA     0  _NOTD
         KA     0  _NOTD
         YB     0  _NOTD
         KB     0  _NOTD
         LAT    0  _NOTD
         AT     0  _NOTD
         SMALLA 0  _NOTD
         ONE    0  _DTRM


EQUATION > YA
EQTYPE   > IMPOSED
EQ       > YA = YAK* LAG(KA,1) +  YAE* AT +  YAB* YB + YAL* LAT

EQTYPE >   IMPOSED
EQ >       ONE = LAG(ONE,1)


END
