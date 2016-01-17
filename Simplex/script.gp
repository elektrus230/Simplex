set term postscript enhanced landscape color "arial" 14 
set output "Solucao Grafica.eps"
set parametric
set style fill empty
set title "Solução Gráfica"
set xl "X1"
set yl "X2"
set key outside right 
set grid front
f01(t)= t
f02(t)= (400.0-40.0*t )/25.0
f11(t)= t
f12(t)= (360.0-24.0*t )/30.0
g01(t)= t
g02(t)= (5200.0-520.0*t )/1.0
set xrange [0<*:16.5]
set yrange [0<*:17.6]
set trange [0<*:352.0]
set object 1 rect from 0.000, 0.000 to 16.5,17.6
set object 1 back clip lw 1.0 dashtype solid fc "black" fillstyle transparent solid 0.8 border lt -1
set grid ytics mytics lt 1 lc rgb "black" lw 0.4
set grid xtics mxtics lt 1 lc rgb "black" lw 0.4
set mxtics 2
set mytics 2
plot \
f01(t),f02(t) ti "" w filledcurves below x2 lt 1 lc "white",\
f11(t),f12(t) ti "" w filledcurves below x2 lt 1 lc "white",\
f01(t),f02(t) ti "restrição 1" lt 1 lw 3.5,\
f11(t),f12(t) ti "restrição 2" lt 1 lw 3.5,\
g01(t),g02(t) ti "iteração 1"with lines linewidth 4
set terminal wxt
set output
replot
