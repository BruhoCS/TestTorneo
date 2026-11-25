package pps.ud1.torneo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.time.LocalDate;

public class EquipoTest {
    @BeforeEach
    void setUp() {
        // objetos que usen todos los tests
    }

    //Aquí van los @Test
    @Test
    void testJugadorEsDeTipoIndividual() {
        Equipo equipo = new Equipo(1, "Rojos", 5);
        Assertions.assertEquals(CategoriaJuego.EQUIPOS, equipo.getTipoTorneo());
    }
}
