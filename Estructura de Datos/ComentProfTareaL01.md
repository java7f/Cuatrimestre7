# COMENTARIOS DEL PROFESOR SOBRE LA TAREA 1 DE LABORATORIO ([Contenidos del tema 1](http://ac.orizondo.org/isc213/isc213TP01.html))

Este archivo que está leyendo solamente es un resumen de cada uno de los aspectos evaluados que aparecen
debajo. Los detalles sobre ellos los puede encontrar como comentarios en el proyecto recibido.

**Máximo:** 100 %

De acuerdo al grupo a que pertenezca, este porciento se aplicará al máximo (en puntos). El resultado será su nota en puntos y reflejada en la página de calificaciones.

## 1. Archivo Doxygen de configuración. (10%)

- En la línea 83 del fichero _templateJava.doxyfile_ recibido se declara que el encoding de los fuentes es _utf-8_ Sin embargo, se detectan ficheros que aparentemente en algún momento fueron editados con un encoding distinto, lo que da lugar a que en Eclipse aparezcan "pajaritos" y también en la documentación generada. Esto se presenta en los ficheros _MenorN.java_ y _Main.java_. Naturalmente, estos "pajaritos" también aparecen en la documentación generada.

**Nota:** 8

## 2. Diseño de la clase *MenorN*. (40%)

Se desglosan los siguientes elementos específicos.

### a. Diseño general de la clase. (10%)

- El atributo _minorNumber_ debió definirse como variable local del método _getMinorNumber_ pues el uso que se le da en la clase.
- Puesto que el retorno del método _getMinorNumber_ no depende del parámetro, lo más adecuado hubiese sido declararla como _static_.

**Nota:** 8

### b. Calidad de la signature (retorno y parámetros) del método encargado del cálculo de la cantidad de operaciones durante el cálculo de la respuesta al [ejercicio 2 de la tarea 1 de teoría](http://ac.orizondo.org/isc213/isc213T01.html#ejercicio2). (10%)

Adecuada.

**Nota:** 10

### c. Calidad de la realización del método aludido en el item anterior. (20%)

Se tomará muy en cuenta la calidad de la documentación adecuada del método y la clase *MenorN*, además de la realización en sí.

- La clase _MenorN_ no tiene descripción.
- El empleo del método _Math.pow_ es ineficiente para un cálculo tan simple.
- El cálculo del cociente empleado en _result_ introduce una operación adicional y, además, provoca el uso de una condición de parada artificiosa.

**Nota:** 17

## 3. Aplicación de consola que haga uso de la clase *MenorN* (40%)

Correcta.

Analice la simplificación que obtendría definiendo el método _MenorN.getMinorNumber_ como _static_ en la clase correspondiente.

**Nota:** 40

## 4. Documentación generada por Doxygen del proyecto. (10%)

Se valora principalmente la calidad de la documentación de los elementos del proyecto y el uso apropiado de Doxygen para generarlo.

- No se ha documentado el contenido de ningún archivo elaborado por Ud.
- La descripción de los paquetes _minorNumber_ y _apps_ no es adecuada.
- No hay descripción de la clase _MenorN_ ni de la aplicación _Main_.

No se considera la presencia de "pajaritos" porque eso fue valorado antes.

**Nota:** 7

# PENALIZACIONES

Las posibles penalizaciones se refieren a violaciones de lo especificado:

1. Envío del correo desde la cuenta de estudiante de la PUCMM.
2. Asunto del correo.
3. Nombres de los archivos remitidos.

Por cada penalización se descuenta un 5% del máximo. No hay penalizaciones.


**Nota final**:  8 + 8 + 10 + 17 + 40 + 7 = 90%

**Puntos**: 4.5

