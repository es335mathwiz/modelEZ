#File Locations
templateHOME = /msu/home/m1wlg01/economistprojects/anderson/simpleSparseAMAExample/willschrissparseama/sparseAMA

FSRCS = $(patsubst %.f, %.o, $(wildcard $(templateHOME)/src/main/ffiles/*.f))
FOBJS = $(FSRCS:.f=.o) 
POBJS = ./chrisSparseAMAExample.o  $(templateHOME)/getmat.o  $(templateHOME)/cprintsparsewrapper.o $(templateHOME)/conversionwrapper.o  $(templateHOME)/sparseamawrapper.o  $(templateHOME)/obtainsparsewrapper.o  $(templateHOME)/sparskit2.o  $(templateHOME)/sparseAMA.o  ./$(MODNAME)_AMA_matrices.o  $(templateHOME)/getmatwrapper.o  $(templateHOME)/csrdnswrapper.o
OBJS = $(FOBJS) $(POBJS)
LOBJS = chrisSparseAMAExample.o $(MODNAME)_AMA_matrices.o

#Flags, Compilers, Linkers
LINK = ifort
FC = ifort
CC = icc
LIBMATIO = -L/msu/res1/Software/matio-1.5.1/src/.libs -lmatio


chrismodel: $(LOBJS)  
	$(LINK) -g $(OBJS) $(LIBMATIO) -lm -assume nounderscore -L/opt/atlas/lib/ -lcblas -lf77blas -latlas -llapack -o chrismodel 

chrisSparseAMAExample.o: chrisSparseAMAExample.f90
	$(FC)  -c -g chrisSparseAMAExample.f90 -fPIC

$(MODNAME)_AMA_matrices.o : $(MODNAME)_AMA_matrices.c
	$(CC) -c -g $(MODNAME)_AMA_matrices.c -I$(templateHOME)/src/main/include -shared -fPIC -o $(MODNAME)_AMA_matrices.o
