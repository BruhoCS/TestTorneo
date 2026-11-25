package pps.ud1.torneo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.time.LocalDate;


public class JugadorIndividualTest {
    @BeforeEach
    void setUp() {
        // objetos que usen todos los tests
    }

    //Aquí van los @Test

    // Punto 1: El identificador del participante debe ser postivo.
        // Id Positivo
    @Test
    void testIdCeroLanzaExcepcion() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new JugadorIndividual(0, "Ana", 20));
    }

        // Id Negativo
    @Test
    void testIdNegativoLanzaExcepcion() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new JugadorIndividual(-2, "Ana", 20));
    }

    /* Punto 2: Cada participante puede participar en un tipo de torneo (individual
    o en equipo), en función de la clase a la que pertenezca.*/
        //Tipo individual
    @Test
    void testJugadorEsDeTipoIndividual() {
        JugadorIndividual jugador = new JugadorIndividual(1, "Josefina", 45);
        Assertions.assertEquals(CategoriaJuego.INDIVIDUAL, jugador.getTipoTorneo());
    }
}
