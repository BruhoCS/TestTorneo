package pps.ud1.torneo;

import java.util.Objects;

/**
 * Tipo de participante que refleja a un jugador individual
 */
public class JugadorIndividual extends Participante {
    /**
     * Edad del participante
     */
    private int edad;

    /**
     * Crea un jugador individual.
     * @param id
     * @param nombre
     * @param edad Deberá tener un valor entre 16 y 99, en caso contrario, deberá lanzar una IllegalArgumentException
     */
    public JugadorIndividual(int id, String nombre, int edad) {
        super(id, nombre);
        this.edad = edad;
    }

    /**
     * El cálculo total supone la suma total de puntos.
     * @return
     */
    @Override
    public double calcularPuntuacionFinal() {
        return calcularTotalPuntos();
    }

    /**
     * El tipo de torneo para esta clase será siempre individual
     * @return
     */
    @Override
    public TipoTorneo getTipoTorneo() {
        return TipoTorneo.INDIVIDUAL;
    }

    /**
     * Permite consultar la edad del Jugador
     * @return entero de la edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Dos jugadores son iguales si tienen el mismo identificador
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof JugadorIndividual)) return false;
        JugadorIndividual that = (JugadorIndividual) o;
        return this.getId() == that.getId();
    }

}
