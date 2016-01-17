set term pngcairo enhanced size 1366,768 font "arial,10" 
set output "Solucao Grafica.png"
set parametric
set style fill empty
set title "Solução Gráfica"
set xl "X1"
set yl "X2"
set key outside right 
set grid front
f01(t)= t
f02(t)= (11.0--1.0*t )/1.0
f11(t)= t
f12(t)= (27.0-1.0*t )/1.0
f21(t)= t
f22(t)= 90.0/7.0
g01(t)= t
g02(t)= (108.0-4.0*t )/6.0
g11(t)= t
g12(t)= (146.0-4.0*t )/6.0
set xrange [0<*:29.700000000000003]
set yrange [0<*:29.700000000000003]
set trange [0<*:594.0]
set object 1 rect from 0.000, 0.000 to 29.700000000000003,29.700000000000003
set object 1 back clip lw 1.0 dashtype solid fc "black" fillstyle transparent solid 0.8 border lt -1
set grid ytics mytics lt 1 lc rgb "black" lw 0.4
set grid xtics mxtics lt 1 lc rgb "black" lw 0.4
set mxtics 2
set mytics 2
plot \
f01(t),f02(t) ti "" w filledcurves above x1 lt 1 lc "white",\
f11(t),f12(t) ti "" w filledcurves above x1 lt 1 lc "white",\
f21(t),f22(t) ti "" w filledcurves above x1 lt 1 lc "white",\
f01(t),f02(t) ti "restrição 1" lt 1 lw 3.5,\
f11(t),f12(t) ti "restrição 2" lt 1 lw 3.5,\
f21(t),f22(t) ti "restrição 3" lt 1 lw 3.5,\
g01(t),g02(t) ti "iteração 1" with lines linewidth 4,\
g11(t),g12(t) ti "iteração 2"with lines linewidth 4
set terminal wxt
set output
replot
