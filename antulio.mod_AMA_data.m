function [param_,np,modname,neq,nlag,nlead,eqname_,eqtype_,endog_,delay_,vtype_] = ...
     antulio.mod_AMA_data()

% antulio.mod_AMA_data()
%     This function will return various information about the AMA model,
%     but will not compute the G and H matrices.

  eqname = cell(8, 1);
  param = cell(20, 1);
  endog = cell(8, 1);
  delay = zeros(8, 1);
  vtype = zeros(8, 1);
  eqtype = zeros(8, 1);

  modname = 'antulio.mod';
  neq = 8;
  np = 20;
  nlag = 1;
  nlead = 1;

  eqname(1) = cellstr('YA');
  eqname(2) = cellstr('KA');
  eqname(3) = cellstr('YB');
  eqname(4) = cellstr('KB');
  eqname(5) = cellstr('LAT');
  eqname(6) = cellstr('AT');
  eqname(7) = cellstr('SMALLA');
  eqname(8) = cellstr('ONE');
  eqname_ = char(eqname);

  eqtype(1) = 1;     eqtype(2) = 1;     eqtype(3) = 1;   
  eqtype(4) = 1;     eqtype(5) = 1;     eqtype(6) = 1;   
  eqtype(7) = 1;     eqtype(8) = 1;   
  eqtype_ = eqtype;

  param(1) = cellstr('YAK');
  param(2) = cellstr('YAE');
  param(3) = cellstr('YAB');
  param(4) = cellstr('YAL');
  param(5) = cellstr('MAK');
  param(6) = cellstr('QA11');
  param(7) = cellstr('QA12');
  param(8) = cellstr('MAL');
  param(9) = cellstr('YBK');
  param(10) = cellstr('YBE');
  param(11) = cellstr('YBA');
  param(12) = cellstr('MBK');
  param(13) = cellstr('MBE');
  param(14) = cellstr('MBA');
  param(15) = cellstr('MUA2');
  param(16) = cellstr('P11');
  param(17) = cellstr('P21');
  param(18) = cellstr('P12');
  param(19) = cellstr('P22');
  param(20) = cellstr('RHO');
  param_ = char(param);

  endog(1) = cellstr('YA');
  endog(2) = cellstr('KA');
  endog(3) = cellstr('YB');
  endog(4) = cellstr('KB');
  endog(5) = cellstr('LAT');
  endog(6) = cellstr('AT');
  endog(7) = cellstr('SMALLA');
  endog(8) = cellstr('ONE');
  endog_ = char(endog);

  delay(1) = 0;     delay(2) = 0;     delay(3) = 0;   
  delay(4) = 0;     delay(5) = 0;     delay(6) = 0;   
  delay(7) = 0;     delay(8) = 0;   
  delay_ = delay;

  vtype(1) = 1;     vtype(2) = 1;     vtype(3) = 1;   
  vtype(4) = 1;     vtype(5) = 1;     vtype(6) = 1;   
  vtype(7) = 1;     vtype(8) = 2;   
  vtype_ = vtype;



