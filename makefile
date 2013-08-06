#File Locations
SPAMADIR=../sparseAMAPostMerge

PARAMFILENAME=$(MODNAME)_AMA_SetAllParamsZero
LOBJS = $(MODNAME)_AMA_template.o $(MODNAME)_AMA_matrices.o $(PARAMFILENAME).o
FOBJS = $(patsubst %.f,%.o,$(wildcard $(SPAMADIR)/src/main/fortran/*.f))
COBJS = $(patsubst %.c,%.o,$(wildcard $(SPAMADIR)/src/main/c/*.c))



#Flags, Compilers, Linkers
#LINK = ifort
LINK = gfortran
#FC = ifort
FC = gfortran
FFLAGS = -c -g -I$(SPAMADIR)/src/main/include
#CC = icc
CC = gcc
CFLAGS = -c -g -I$(SPAMADIR)/src/main/include

RUN$(MODNAME): $(LOBJS) $(FOBJS) $(COBJS)
	$(LINK) -g $(LOBJS) $(FOBJS) $(COBJS)  -lm  -L/opt/atlas/lib/ -lcblas -lf77blas -latlas -llapack -o RUN$(MODNAME)

$(PARAMFILENAME).o: $(PARAMFILENAME).f90
	$(FC)  $(FFLAGS) $(PARAMFILENAME).f90 -fPIC

$(MODNAME)_AMA_template.o: $(MODNAME)_AMA_template.f90
	$(FC)  $(FFLAGS) $(MODNAME)_AMA_template.f90 -fPIC

$(MODNAME)_AMA_matrices.o : $(MODNAME)_AMA_matrices.c
	$(CC)  $(CFLAGS) $(MODNAME)_AMA_matrices.c -I$(SPAMADIR)/src/main/include -shared -fPIC -o $(MODNAME)_AMA_matrices.o


clean:
	rm -f $(LOBJS) $(FOBJS) $(COBJS)


