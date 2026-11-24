package pps.ud1.torneo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un participante de un torneo que es un equipo.
 * Por lo tanto, estár compuesto por jugadores individuales.
 */
public class Equipo extends Participante {
    /**
     * Factor que interviene en el cálculo de la puntuación total del equipo
     */
    private static final double MULTIPLICADOR = 1.10;
    /**
     * Número máximo de participantes permitidos
     */
    private int capacidadMaxima;
    /**
     * Lista de jugadores individuales integrantes del equipo
     */
    private List<JugadorIndividual> integrantes;

    /**Constructor del Equipo que en la creación no forma parte de ningún torneo.
     *
     * @param id Debe ser positivo.
     * @param nombre No puede ser nulo, ni estar vacío.
     * @param capacidadMaxima El límite superior de particpantes es 20.
     * @throws IllegalArgumentException Cuando el identificador no es positivo, cuando el nombre el nulo o vacío,
     * cuando la capacidad máxima no está en los límites.
     */
    public Equipo (int id, String nombre, int capacidadMaxima)
    throws IllegalArgumentException{
        super(id, nombre);
        if ((capacidadMaxima <= 0) || (capacidadMaxima > 20)) {
            throw new IllegalArgumentException(
                    "La capacidad debe ser positiva");
        }
        this.capacidadMaxima = capacidadMaxima;
        this.integrantes = new ArrayList<>();
    }
    /**
     * Añade un jugador al equipo cuando el equipo no está participando en un torneo.
     * @param participante
     * @throws IllegalArgumentException Si el participante es nulo o duplicado.
     * @throws IllegalStateException Si el equipo está completo o participando en un torneo
     */
    public void agregarIntegrante(JugadorIndividual participante)
    throws IllegalArgumentException, IllegalStateException{
        if (participante == null) {
            throw new IllegalArgumentException("Participante nulo");
        }
        /* CÓDIGO ALTERNATIVO
        if (this.integrantes.stream().anyMatch(p -> p.getId() == participante.getId())) {
            throw new IllegalArgumentException("Participante duplicado");
        }
        */
        boolean existe = false;
        for(Participante p : this.integrantes){
            if(p.getId() == participante.getId()){
                existe = true;
            }
        }
        if (existe) {
            throw new IllegalArgumentException(
                    "Participante duplicado");
        }
        this.integrantes.add(participante);
    }

    /**
     * Se elimina un participante del equipo cuando el equipo no está participando en un torneo.
     * @param idParticipante
     * @throws IllegalArgumentException Si el particpante no existe en el equipo
     * @throws IllegalStateException Si el equipo está participando en un torneo
     */
    public void eliminarIntegrante(int idParticipante)
            throws IllegalArgumentException{
        boolean eliminado = false;
        for (Participante p: this.getIntegrantes()){
            if (p.getId() == idParticipante)
                eliminado = true;
        }
        /* CÓDIGO ALTERNATIVO
        boolean eliminado = this.integrantes.removeIf(
                p -> p.getId() == idParticipante);
        */
    }

    /**
     * Busca un participante en la lista de miembros del equipo
     * @param idParticipante
     * @return
     */
    public boolean contieneParticipante(int idParticipante) {
        for (JugadorIndividual j: this.integrantes){
            if (j.getId() == idParticipante){
                return true;
            }
        }
        return true;
        /*CÓDIGO ALTERNATIVO
        return this.integrantes.stream().anyMatch(
                p -> p.getId() == idParticipante);*/
    }

    /**
     * Devuelve una copia de la lista de miembros del equipo
     * @return
     */
    public List<JugadorIndividual> getIntegrantes() {
        return List.copyOf(this.integrantes);
    }

    /**
     * Permite conocer cuántos miembros tiene un equipo
     * @return
     */
    public int getNumeroIntegrantes() {
        return this.integrantes.size();
    }

    /**
     * Permite conocer el número máximo de miembros que acepta un equipo
     * @return
     */
    public int getCapacidadMaxima() {
        return this.capacidadMaxima;
    }

    /**
     * Calcula la puntuación final teniendo en cuenta el factor de multiplicación.
     * @return
     */
    @Override
    public double calcularPuntuacionFinal() {
        double total = 0;
        for (Participante integrante : this.integrantes){
            total += integrante.calcularPuntuacionFinal();
        }
        return total;
        /* CÓDIGO ALTERNATIVO
        return integrantes.stream()
            .mapToDouble(Participante::calcularPuntuacionFinal)
            .sum();
        */
    }

    /**
     * En este caso, el tipo de torneo al que puede acceder, son de equipo.
     * @return
     */
    @Override
    public TipoTorneo getTipoTorneo() {
        return TipoTorneo.INDIVIDUAL;
    }

    @Override
    public String toString() {
        String str = super.toString();
        return str + " Equipo{" +
                "integrantes=" + integrantes +
                '}';
    }
}
