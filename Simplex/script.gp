set term postscript enhanced landscape color "arial" 14 
set output "Solucao Grafica.eps"
set parametric
set style fill empty
set title "Solucao Grafica"
set xl "X1"
set yl "X2"
set key outside right 
set grid front
f01(t)= t
f02(t)= (28.0-7.0*t )/2.0
f11(t)= t
f12(t)= (24.0-2.0*t )/12.0
g01(t)= t
g02(t)= (200000.0-50000.0*t )/100000.0
g11(t)= t
g12(t)= (320000.0-50000.0*t )/100000.0
set xrange [0<*:13.200000000000001]
set yrange [0<*:15.400000000000002]
set trange [0<*:308.00000000000006]
set object 1 rect from 0.000, 0.000 to 13.200000000000001,15.400000000000002
set object 1 back clip lw 1.0 dashtype solid fc "black" fillstyle transparent solid 0.8 border lt -1
set grid ytics mytics lt 1 lc rgb "black" lw 0.4
set grid xtics mxtics lt 1 lc rgb "black" lw 0.4
set mxtics 2
set mytics 2
plot \
f01(t),f02(t) ti "" w filledcurves above x1 lt 1 lc "white",\
f11(t),f12(t) ti "" w filledcurves above x1 lt 1 lc "white",\
f01(t),f02(t) ti "restricao 1" lt 1 lw 3.5,\
f11(t),f12(t) ti "restricao 2" lt 1 lw 3.5,\
g01(t),g02(t) ti "iteracao 1" with lines linewidth 4,\
g11(t),g12(t) ti "iteracao 2"with lines linewidth 4
set terminal wxt
set output
replot
