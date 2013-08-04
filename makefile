#File Locations
#templateHOME = /msu/home/m1wlg01/economistprojects/anderson/simpleSparseAMAExample/willschrissparseama/sparseAMA
SPAMADIR=/msu/home/m1gsa00/git/sparseAMAPostMerge
templateHOME = $(SPAMADIR)/target/nar/obj/amd64-Linux-g++

PARAMFILENAME=$(MODNAME)_AMA_SetAllParamsZero
LOBJS = $(MODNAME)_AMA_template.o $(MODNAME)_AMA_matrices.o $(PARAMFILENAME).o

FOBJS = $(patsubst %.f,%.o,$(wildcard $(templateHOME)/src/main/fortran/*.f))

POBJS =   $(templateHOME)/getmat.o  $(templateHOME)/cprintsparsewrapper.o $(templateHOME)/conversionwrapper.o  $(templateHOME)/sparseamawrapper.o  $(templateHOME)/obtainsparsewrapper.o  $(templateHOME)/sparskit2.o  $(templateHOME)/sparseAMA.o  $(templateHOME)/getmatwrapper.o  $(templateHOME)/csrdnswrapper.o $(LOBJS)
#OBJS = $(FOBJS) $(POBJS)
OBJS = $(wildcard $(templateHOME)/*.o)


#Flags, Compilers, Linkers
#LINK = ifort
LINK = gfortran
#FC = ifort
FC = gfortran
#CC = icc
CC = gcc
#LIBMATIO = -L/msu/res1/Software/matio-1.5.1/src/.libs -lmatio
LIBMATIO = 


#chrismodel: $(LOBJS)
#	$(LINK) -g $(OBJS)  $(LIBMATIO) -lm -assume nounderscore -L/opt/atlas/lib/ -lcblas -lf77blas -latlas -llapack -o chrismodel 

chrismodel: $(LOBJS)
	echo FSRCS
	echo ${FSRCS}
	echo SPAMADIR
	echo ${SPAMADIR}
	echo templateHOME
	echo ${templateHOME}
	echo FOBJS
	echo ${FOBJS}
	echo FOBJSXX
	echo ${POBJS}
	$(LINK) -g $(OBJS)  $(LIBMATIO) -lm  -L/opt/atlas/lib/ -lcblas -lf77blas -latlas -llapack -o chrismodel 

$(PARAMFILENAME).o: $(PARAMFILENAME).f90
	$(FC)  -c -g $(PARAMFILENAME).f90 -fPIC


$(MODNAME)_AMA_template.o: $(MODNAME)_AMA_template.f90
	$(FC)  -c -g $(MODNAME)_AMA_template.f90 -fPIC

$(MODNAME)_AMA_matrices.o : $(MODNAME)_AMA_matrices.c
	$(CC)  -c -g $(MODNAME)_AMA_matrices.c -I$(templateHOME)/src/main/include -shared -fPIC -o $(MODNAME)_AMA_matrices.o
