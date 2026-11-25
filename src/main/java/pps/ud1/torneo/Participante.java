package pps.ud1.torneo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta que define a los participantes de un torneo.
 * Podrán ser Jugadores individuales o equipos.
 */
public abstract class Participante {
    /**
     * Identificador del participante. Debe ser positivo
     */
    private int id;
    /**
     * Nombre del participante: individual o equipo. No puede ser nulo ni estar vacío.
     */
    private String nombre;
    /**
     * Lista de puntuaciones conseguidas
     */
    private List<Integer> puntuaciones;

    /**
     * Creación del participante.
     * Las puntuaciones se crearán como una lista vacía.
     * @param id
     * @param nombre
     */
    protected Participante(int id, String nombre) {
        if (id <= 0) {
            throw new IllegalArgumentException("El identificador debe ser positivo");
        }
        if (nombre == null ) {
                throw new IllegalArgumentException("El nombre es obligatorio");
        }
        this.id = id;
        this.nombre = nombre;
        this.puntuaciones = new ArrayList<>();
    }

    /**
     * Se añade una puntuación entre 0 y 100.
     * Fuera de este rango se lanza una IllegalArgumetnException.
     * @param puntuacion
     */
    public void registrarPuntuacion(int puntuacion) {
        if (puntuacion < 0 || puntuacion > 100) {
            throw new IllegalArgumentException("Puntuación fuera de rango");
        }
        puntuaciones.add(puntuacion);
    }

    /**
     * Devuelve una copia de la lista de las puntuaciones del participante.
     * @return
     */
    public List<Integer> getPuntuaciones() {
        return List.copyOf(puntuaciones);
    }

    /**
     * Realiza la suma de todos los componentes de la lista de puntuaciones.
     * @return Suma de las puntuaciones conseguidas
     */
    protected int calcularTotalPuntos() {

        int totalPuntos = 0;
        for (int punto: this.puntuaciones){
            totalPuntos += punto;
        }
        return totalPuntos;
        /* CÓDIGO ALTERNATIVO
        return puntuaciones.stream().mapToInt(Integer::intValue).sum();*/

    }

    /**
     * Permite acceder al identificador del participante
     * @return entero identificador.
     */
    public int getId() {
        return id;
    }

    /**
     * Permite acceder al nombre del participante
     * @return String nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Dependiendo del tipo de participante tomará un valor u otro. Ayuda a conocer el tipo de participante.
     * @return Tipo de Torneo en el que puede participar.
     */
    public abstract TipoTorneo getTipoTorneo();

    /**
     * Realiza el cómputo total de la puntuación obtenida por el participante.
     * Dependiendo del tipo de participante se calculará de una forma u otra.
     * @return double resultante de tener en cuenta todas las puntuaciones.
     */
    public abstract double calcularPuntuacionFinal();

    @Override
    public String toString() {
        return "Participante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

