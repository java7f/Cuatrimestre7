---
title: Solución de la parte teórica del laboratorio 4
---

El contenido se corresponde con la solución de la parte teórica del [laboratorio 4](http://ac.orizondo.org/isc213/isc213l04.html).

_Nótese que la estructura de las respuestas es la misma. Sin embargo, ellas serán diferentes debido a lo que se solicita._

# 1. Algoritmo efectivo de cálculo de $x^n$ de la operación $\circ$. ¿Cuál sería el costo del cálculo de $x^n$ empleando su algoritmo?

**Entrada:** _x_ y _n_

**Resultado:** $pot = x^n$

**Algoritmo:**

1. Hacer $pot \leftarrow x$
2. **para** $i \leftarrow 2$ **hasta** $n$
3. $pot \leftarrow pot \circ x$
4. **fin para**
4. Retornar $pot$

## Costo

La operación $\circ$ se ejecuta $n-1$ veces en el ciclo 2 --- 4. Por lo tanto, el costo será $(n - 1) \times p$

# 2. Algoritmo eficiente para el cálculo de $x^n$. ¿Cuál sería el costo?

**Entrada:** _x_ y _n_

**Resultado:** $pot = x^n$

**Algoritmo:**



## Costo

