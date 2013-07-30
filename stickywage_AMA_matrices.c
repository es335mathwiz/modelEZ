void getnumrows_(int *rows)
{
*rows = 36;
}
void getnumcols_(int *cols)
{
*cols =108;
}
void getnumgcols_(int *gcols)
{
*gcols =72;
}


int stickywage_AMA_matrices(double *paramvalues, double *cofg, double *cofh) {
//     This script will compute the G and H matrices.
#include <math.h>

double gamtil;
gamtil= paramvalues[0];
double beta;
beta= paramvalues[1];
double ihabitswitch;
ihabitswitch= paramvalues[2];
double delta;
delta= paramvalues[3];
double Gz;
Gz= paramvalues[4];
double phii;
phii= paramvalues[5];
double intswitch;
intswitch= paramvalues[6];
double ap;
ap= paramvalues[7];
double kappap;
kappap= paramvalues[8];
double ep;
ep= paramvalues[9];
double sigmal;
sigmal= paramvalues[10];
double aw;
aw= paramvalues[11];
double bw;
bw= paramvalues[12];
double alpha;
alpha= paramvalues[13];
double gg;
gg= paramvalues[14];
double shrcy;
shrcy= paramvalues[15];
double shriy;
shriy= paramvalues[16];
double gam_rs;
gam_rs= paramvalues[17];
double gam_dp;
gam_dp= paramvalues[18];
double gamdy;
gamdy= paramvalues[19];
double rhotech;
rhotech= paramvalues[20];
double sdevtech;
sdevtech= paramvalues[21];
double rhog;
rhog= paramvalues[22];
double sdevg;
sdevg= paramvalues[23];
double rhoinv;
rhoinv= paramvalues[24];
double sdevinv;
sdevinv= paramvalues[25];
double rhoeta;
rhoeta= paramvalues[26];
double sdeveta;
sdeveta= paramvalues[27];
double rhomuc1;
rhomuc1= paramvalues[28];
double sdevmuc;
sdevmuc= paramvalues[29];
double rhoint;
rhoint= paramvalues[30];
double sdevint;
sdevint= paramvalues[31];
double rhomark;
rhomark= paramvalues[32];
double sdevmark;
sdevmark= paramvalues[33];
double rhomarkw;
rhomarkw= paramvalues[34];


double g[36*72];
double  h[36*108] ;

int i;
for(i = 0;i <36*72 ; i++ ){
g[i] = 0;
}
for(i = 0;i <36*108 ; i++ ){
h[i] = 0;
}
  g[1332] = g[1332] + 1;
  g[2161] = g[2161] + (-1.0*1);
  g[1297] = g[1297] - ((1.0*pow(((1.0+(-1.0*gamtil))*(1.0+(-1.0*((beta*gamtil)*ihabitswitch)))),-1.0))*(-1.0*1));
  g[1] = g[1] - ((1.0*pow(((1.0+(-1.0*gamtil))*(1.0+(-1.0*((beta*gamtil)*ihabitswitch)))),-1.0))*(gamtil*1));
  g[2017] = g[2017] - ((1.0*pow(((1.0+(-1.0*gamtil))*(1.0+(-1.0*((beta*gamtil)*ihabitswitch)))),-1.0))*(gamtil*(-1.0*1)));
  g[1981] = g[1981] - (-1.0*(ihabitswitch*1));
  g[1333] = g[1333] + 1;
  g[1801] = g[1801] - 1;
  g[2126] = g[2126] - (-1.0*1);
  h[2629] = h[2629] - 1;
  h[2810] = h[2810] - (-1.0*1);
  h[3314] = h[3314] - (-1.0*1);
  g[1371] = g[1371] + (1.0*1);
  g[2091] = g[2091] + (1.0*1);
  g[1371] = g[1371] + ((-1.0*(1.0*pow(Gz,-1.0)))*1);
  g[1371] = g[1371] + ((-1.0*((-1.0*delta)*pow(Gz,-1.0)))*1);
  g[2091] = g[2091] + ((-1.0*(1.0*pow(Gz,-1.0)))*1);
  g[2091] = g[2091] + ((-1.0*((-1.0*delta)*pow(Gz,-1.0)))*1);
  g[1442] = g[1442] - 1;
  g[147] = g[147] - (-1.0*((1.0*pow(Gz,-1.0))*1));
  g[2019] = g[2019] - (-1.0*((1.0*pow(Gz,-1.0))*(-1.0*1)));
  g[147] = g[147] - (-1.0*(((-1.0*delta)*pow(Gz,-1.0))*1));
  g[2019] = g[2019] - (-1.0*(((-1.0*delta)*pow(Gz,-1.0))*(-1.0*1)));
  g[1407] = g[1407] + 1;
  g[2092] = g[2092] - (-1.0*1);
  g[1372] = g[1372] - (phii*1);
  g[76] = g[76] - (-1.0*(phii*1));
  g[2020] = g[2020] - (phii*1);
  g[1948] = g[1948] - (intswitch*1);
  g[1408] = g[1408] + 1;
  g[1805] = g[1805] - (-1.0*1);
  g[2129] = g[2129] - (-1.0*1);
  h[2705] = h[2705] - (((1.0*beta)*pow(Gz,-1.0))*1);
  h[2705] = h[2705] - ((((-1.0*delta)*beta)*pow(Gz,-1.0))*1);
  h[2813] = h[2813] - (-1.0*(-1.0*1));
  h[2777] = h[2777] - (1.0*1);
  h[2777] = h[2777] - ((-1.0*((1.0*beta)*pow(Gz,-1.0)))*1);
  h[2777] = h[2777] - ((-1.0*(((-1.0*delta)*beta)*pow(Gz,-1.0)))*1);
  g[1481] = g[1481] + 1;
  g[1661] = g[1661] - 1;
  g[1697] = g[1697] - 1;
  g[150] = g[150] - (-1.0*1);
  g[2021] = g[2021] - 1;
  g[1518] = g[1518] + 1;
  g[223] = g[223] - ((1.0*pow((1.0+(beta*(1.0+(-1.0*ap)))),-1.0))*1);
  g[223] = g[223] - (((-1.0*ap)*pow((1.0+(beta*(1.0+(-1.0*ap)))),-1.0))*1);
  g[1555] = g[1555] - (kappap*1);
  g[2239] = g[2239] - ((kappap*pow((ep+(-1.0*1.0)),-1.0))*1);
  h[2815] = h[2815] - ((beta*pow((1.0+(beta*(1.0+(-1.0*ap)))),-1.0))*1);
  g[1555] = g[1555] + 1;
  g[1663] = g[1663] - 1;
  g[1592] = g[1592] - (-1.0*1);
  g[1592] = g[1592] + 1;
  g[1736] = g[1736] - 1;
  g[1701] = g[1701] - (-1.0*1);
  g[1629] = g[1629] + 1;
  g[1702] = g[1702] - (sigmal*1);
  g[1342] = g[1342] - (-1.0*1);
  g[1666] = g[1666] + 1;
  g[370] = g[370] - 1;
  g[1523] = g[1523] - (-1.0*1);
  g[227] = g[227] - (1.0*1);
  g[227] = g[227] - ((-1.0*aw)*1);
  g[2027] = g[2027] - (-1.0*(bw*1));
  g[1704] = g[1704] + (1.0*1);
  g[1704] = g[1704] + ((-1.0*alpha)*1);
  g[1739] = g[1739] - 1;
  g[156] = g[156] - (-1.0*(alpha*1));
  g[2028] = g[2028] - (-1.0*(alpha*(-1.0*1)));
  g[1740] = g[1740] + 1;
  g[1309] = g[1309] - ((gg*shrcy)*1);
  g[2064] = g[2064] - 1;
  g[1381] = g[1381] - ((gg*shriy)*1);
  g[1777] = g[1777] + 1;
  g[1813] = g[1813] - 1;
  h[2822] = h[2822] - (-1.0*1);
  g[1814] = g[1814] + 1;
  g[519] = g[519] - (gam_rs*1);
  g[1527] = g[1527] - (1.0*(gam_dp*1));
  g[1743] = g[1743] - (1.0*(gamdy*1));
  g[447] = g[447] - (1.0*(gamdy*(-1.0*1)));
  g[2031] = g[2031] - (1.0*(gamdy*1));
  g[1527] = g[1527] - ((-1.0*gam_rs)*(gam_dp*1));
  g[1743] = g[1743] - ((-1.0*gam_rs)*(gamdy*1));
  g[447] = g[447] - ((-1.0*gam_rs)*(gamdy*(-1.0*1)));
  g[2031] = g[2031] - ((-1.0*gam_rs)*(gamdy*1));
  g[2210] = g[2210] - 1;
  g[1852] = g[1852] + (1.0*1);
  g[1852] = g[1852] + ((-1.0*gamtil)*1);
  g[1312] = g[1312] - (-1.0*1);
  g[16] = g[16] - (gamtil*1);
  g[2032] = g[2032] - (gamtil*(-1.0*1));
  g[1888] = g[1888] + 1;
  g[1384] = g[1384] - 1;
  g[89] = g[89] - (-1.0*1);
  g[2032] = g[2032] - 1;
  g[1925] = g[1925] + 1;
  g[1529] = g[1529] - 1;
  g[234] = g[234] - (-1.0*(1.0*1));
  g[234] = g[234] - (-1.0*((-1.0*ap)*1));
  g[1962] = g[1962] + 1;
  g[1387] = g[1387] - ((beta*phii)*1);
  h[2683] = h[2683] - (-1.0*((beta*phii)*1));
  h[3331] = h[3331] - (-1.0*((beta*phii)*1));
  g[1999] = g[1999] + 1;
  g[2144] = g[2144] - (((-1.0*1.0)*pow(((1.0+(-1.0*gamtil))*(1.0+(-1.0*((beta*gamtil)*ihabitswitch)))),-1.0))*(((1.0*gamtil)*beta)*1));
  g[2144] = g[2144] - (((-1.0*1.0)*pow(((1.0+(-1.0*gamtil))*(1.0+(-1.0*((beta*gamtil)*ihabitswitch)))),-1.0))*((((-1.0*gamtil)*gamtil)*beta)*1));
  g[1316] = g[1316] - (((-1.0*1.0)*pow(((1.0+(-1.0*gamtil))*(1.0+(-1.0*((beta*gamtil)*ihabitswitch)))),-1.0))*(-1.0*(((gamtil*gamtil)*beta)*1)));
  h[2612] = h[2612] - (((-1.0*1.0)*pow(((1.0+(-1.0*gamtil))*(1.0+(-1.0*((beta*gamtil)*ihabitswitch)))),-1.0))*((gamtil*beta)*1));
  h[3332] = h[3332] - (((-1.0*1.0)*pow(((1.0+(-1.0*gamtil))*(1.0+(-1.0*((beta*gamtil)*ihabitswitch)))),-1.0))*((gamtil*beta)*1));
  g[2036] = g[2036] + 1;
  g[741] = g[741] - (rhotech*1);
  g[1029] = g[1029] - (sdevtech*1);
  g[2073] = g[2073] + 1;
  g[778] = g[778] - (rhog*1);
  g[1066] = g[1066] - (sdevg*1);
  g[2110] = g[2110] + 1;
  g[815] = g[815] - (rhoinv*1);
  g[1103] = g[1103] - (sdevinv*1);
  g[2147] = g[2147] + 1;
  g[852] = g[852] - (rhoeta*1);
  g[1140] = g[1140] - (sdeveta*1);
  g[2184] = g[2184] + 1;
  g[889] = g[889] - (rhomuc1*1);
  g[1177] = g[1177] - (sdevmuc*1);
  g[2221] = g[2221] + 1;
  g[926] = g[926] - (rhoint*1);
  g[1214] = g[1214] - (sdevint*1);
  g[2258] = g[2258] + 1;
  g[963] = g[963] - (rhomark*1);
  g[1251] = g[1251] - (sdevmark*1);
  g[2295] = g[2295] + 1;
  g[1000] = g[1000] - (rhomarkw*1);
  g[1288] = g[1288] - (sdevmark*1);
  g[2332] = g[2332] + 1;
  g[1037] = g[1037] - (0.0*1);
  g[2369] = g[2369] + 1;
  g[1074] = g[1074] - (0.0*1);
  g[2406] = g[2406] + 1;
  g[1111] = g[1111] - (0.0*1);
  g[2443] = g[2443] + 1;
  g[1148] = g[1148] - (0.0*1);
  g[2480] = g[2480] + 1;
  g[1185] = g[1185] - (0.0*1);
  g[2517] = g[2517] + 1;
  g[1222] = g[1222] - (0.0*1);
  g[2554] = g[2554] + 1;
  g[1259] = g[1259] - (0.0*1);
  g[2591] = g[2591] + 1;
  g[1296] = g[1296] - (0.0*1);

  cofg = g;
  cofh = h;

return(0);
}

int parserwrapper_(double *params,double *g, double *h,double*hmat,int *HROWS,int *HCOLS) {


  stickywage_AMA_matrices(params,g,h);
  
  int i;

  int k;


  int rows;
  int cols;
  int gcols;

  getnumrows_(&rows);
  getnumcols_(&cols);
  getnumgcols_(&gcols);

  hmat = h;

  for(i = 0;i < gcols;i++){
  for (k = 0;k < rows;k ++){
     hmat[rows*i+k] = hmat[rows*i+k] + g[rows*i + k];
   }
    
   }
  return(0);
}
