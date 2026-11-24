# Puesta en Producción Segura

## Práctica: Seguridad y pruebas en Java

## Objetivo

El objetivo de esta práctica es que el alumno comprenda las
características y elementos de los lenguajes de programación, los
fundamentos de las pruebas de unidad y la seguridad de los lenguajes de
programación.

Se trata de poner en funcionamiento el caso de ejemplo de los apuntes.
Por lo tanto, codificará con Java y se utilizarán las herramientas
Maven, JUnit5, IntelliJ y GitLab.

## Corrección de la práctica

El alumno deberá entregar una memoria con las soluciones a cada apartado
y los pasos detallados que ha seguido en el proceso. Se valorará:

-   La calidad de la memoria. Se deberán explicar y justificar los
    procedimientos y resultados, así como dar soporte a lo descrito
    mediante capturas de pantalla.
-   La corrección del proceso descrito.
-   La corrección de los comandos empleados y de las opciones.
-   La correcta justificación de los pasos seguidos.

**Forma de entrega:** Vía GitLab para la entrega de documentos y vía
Moodle para indicar la referencia SSH al proyecto.

Cada día desde el aula se deberá hacer un commit de lo trabajado en
clase. El siguiente día se volverá a hacer un commit y así
sucesivamente.

## Pruebas

El código corresponde a una aplicación de una empresa que organiza
torneos de juegos de mesa. El sistema contendrá las siguientes clases:

-   **Participante:** representa las características genéricas de los
    participantes del torneo. Cada uno tendrá asociado un identificador,
    un nombre, edad y una puntuación.
-   **JugadorIndividual:** tipo de jugador que calcula su puntuación
    sumando los puntos obtenidos en las diferentes pruebas.
-   **Equipo:** tipo de participante que está formado por varios
    integrantes que son jugadores. Habrá un número máximo de jugadores
    por equipo. Calculará su puntuación mediante la suma de las
    puntuaciones de sus integrantes, aplicando un multiplicador del
    1,10.
-   **Torneos:** cada torneo tendrá un nombre, unos participantes, un
    número máximo de participantes y un estado que podrá ser `CREADO`,
    `EN_CURSO`, `FINALIZADO` o `CANCELADO`. También tendrá una fecha de
    inicio y de finalización. Para cada torneo se podrán consultar datos
    referentes a la clasificación, ganador, podio...
-   **Liga:** contendrá una lista con los torneos. Se permite un número
    máximo de torneos en la liga. También permite consultar información
    sobre la clasificación de la liga.

Crea las clases de test necesarias para realizar las siguientes
comprobaciones y las que consideres necesarias añadir teniendo en cuenta
las descripciones proporcionadas:

1.  El identificador del participante debe ser positivo.
    (`IllegalArgumentException`)
2.  Cada participante puede participar en un tipo de torneo (individual
    o en equipo), en función de la clase a la que pertenezca.
3.  El nombre del participante, torneo o liga no puede estar vacío.
    (`IllegalArgumentException`)
4.  La edad del participante deberá estar comprendida entre 16 y 99
    años. (`IllegalArgumentException`)
5.  La puntuación inicial del participante será 0.
6.  La puntuación del participante no podrá ser negativa.
    (`IllegalArgumentException`)
7.  Un equipo se creará sin integrantes.
8.  Un equipo no debe tener integrantes nulos ni repetidos.
9.  Si se elimina un integrante que no existe, debe informar con
    `IllegalArgumentException`.
10. Se podrá calcular la puntuación total de un equipo en base a las
    puntuaciones de los integrantes y un coeficiente que debe ser 1,10.
11. Un torneo debe tener un nombre y un número máximo de participantes.
12. La fecha de inicio del torneo debe ser anterior a la fecha de fin.
13. Un torneo podrá ser individual o en equipo.
14. Las transiciones de estado de un torneo deberán ser las siguientes:
    -   `CREADO` → `EN_CURSO` → `FINALIZADO`
    -   `CREADO` → `EN_CURSO` → `CANCELADO`
    -   `CREADO` → `CANCELADO`
15. Las puntuaciones deberán estar comprendidas entre 0 y 100 puntos y
    siempre sumarán.
16. En un torneo no puede haber participantes repetidos. Tampoco los
    jugadores individuales que pertenezcan a equipos diferentes.
17. Solo se pueden inscribir participantes en un torneo si está en
    estado creado. (`IllegalStateException`)
18. En una liga los torneos no se pueden solapar.
19. Se deberá comprobar que todas las acciones sobre puntuaciones o
    sobre cálculo de la clasificación son las correctas.
