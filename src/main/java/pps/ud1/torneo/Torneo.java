package pps.ud1.torneo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Clase que representa un Torneo que puede jugarse individualmente o en equipos pero no ambos.
 * Un participante puede inscribirse en un torneo siempre que el torneo tenga abierta la inscripcción y
 * que el participante no forme parte de otro torneo.
 * Se podrá consultar diferente información relacionada con la clasificación del torneo.
 */
public class Torneo {
    /**
     * Nombre del Torneo. No puede ser nulo o estar en blanco
     */
    private String nombre;
    /**
     * Tipo de torneo por equipos o indivicual
     */
    private TipoTorneo tipo;
    /**
     * Se permite un número máximo de participantes
     */
    private int capacidadMaxima;
    /**
     * Lista de jugadores del torneo
     */
    private List<Participante> participantes;
    /**
     * Estado del torneo: creado, en curso, finalizado o cancelado
     */
    private EstadoTorneo estado;
    /**
     * Fecha de comienzo del torneo
     */
    private LocalDate comienzo;
    /**
     * Fecha de fin del torneo
     */
    private LocalDate fin;

    /**
     * Creación de un torneo. Debe proporcionársele un nombre, tipo y el número máximo de participantes.
     * El resto de atributos tomarán valores por defecto y el estado será CREADO
     * @param nombre
     * @param tipo
     * @param capacidadMaxima
     */
    public Torneo(String nombre, TipoTorneo tipo, int capacidadMaxima, LocalDate comienzo, LocalDate fin) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de torneo obligatorio");
        }
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser positiva");
        }
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidadMaxima = capacidadMaxima;
        this.participantes = new ArrayList<>();
        this.estado = EstadoTorneo.CREADO;
        this.comienzo = comienzo;
        this.fin = fin;
    }

    /**
     * Permite inscribir a un jugador/equipo en el torneo si cumple las condiciones.
     * Comprueba que el torneo esté creado pues es el único estado en el que se permite la inscripción.
     * @param participante No podrá ser nulo ni estar participando en otro torneo
     * @return
     * @throws IllegalStateException
     * @throws IllegalArgumentException
     */
    public boolean inscribir(Participante participante)
            throws IllegalStateException, IllegalArgumentException {
        if (participante == null) {
            throw new IllegalArgumentException("Competidor nulo");
        }
        if (participante.getTipoTorneo() != this.tipo) {
            throw new IllegalArgumentException("Tipo de competidor incompatible con el torneo");
        }
        if (this.participantes.size() >= this.capacidadMaxima) {
            throw new IllegalStateException("Torneo completo");
        }
        if (!estaInscrito(participante)){
            this.participantes.add(participante);
            return true;
        }
        return false;
    }

    /**
     * Cambia el estado del tornedo previa comprovación de si es correcto.
     * @param nuevoEstado
     * @throws IllegalArgumentException Estado nuevo nulo
     * @throws IllegalStateException Nuevo estado no permitido
     */
    public void cambiarEstado(EstadoTorneo nuevoEstado)
    throws IllegalArgumentException, IllegalStateException {
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo");
        }
        boolean permitido = switch (estado) {
            case CREADO -> nuevoEstado == EstadoTorneo.FINALIZADO
                || nuevoEstado == EstadoTorneo.CANCELADO;
            case EN_CURSO -> nuevoEstado == EstadoTorneo.CREADO
                || nuevoEstado == EstadoTorneo.CANCELADO;
            case FINALIZADO, CANCELADO -> false;
        };
        if (!permitido) {
            throw new IllegalStateException("Transición no permitida");
        }
        estado = nuevoEstado;
    }

    /**
     * Ordena las puntuaciones finales de los participantes
     * @return
     */
    public List<Participante> obtenerClasificacion() {
        return participantes.stream()
            .sorted(Comparator.comparingDouble(Participante::calcularPuntuacionFinal)
                .reversed().thenComparing(Participante::getNombre))
            .toList();
    }

    /**
     * Recupera el participante con mayor puntuación final
     * @return
     */
    public Participante obtenerGanador() {
        Participante ganador = this.participantes.get(0);
        for (Participante c : this.participantes){
            if (c.calcularPuntuacionFinal() > ganador.calcularPuntuacionFinal()){
                ganador = c;
            }
        }
        return ganador;
        /*CÓDIGO ALTERNATIVO
        return obtenerClasificacion().stream().findFirst()
            .orElseThrow(() -> new IllegalStateException("No hay participantes"));
         */
    }

    /**
     * Devieñve una sublista de tamaño 3 con los tres participantes con las mejores puntuaciones finales.
     * @return
     */
    public List<Participante> obtenerPodio() {
        return this.obtenerClasificacion().subList(0,2);
        /*CÓDIGO ALTERNATIVO
        return this.obtenerClasificacion().stream().limit(3).toList();
         */
    }

    /**
     * Calcula la media de las puntuaciones finales de todos los participantes
     * @return
     * @throws IllegalStateException Si no hay participantes
     */
    public double calcularMediaPuntuaciones()
    throws  IllegalStateException{
        if (this.participantes.isEmpty()) {
            throw new IllegalStateException("No hay participantes");
        }
        double suma = 0;
        for (Participante c : this.participantes){
            suma += c.calcularPuntuacionFinal();
        }
        return suma / this.participantes.size();
        /*CÓDIGO ALTERNATIVO
        return this.participantes.stream().mapToDouble(Competidor::calcularPuntuacionFinal)
            .average().orElseThrow();
         */
    }

    /**
     * Obtiene la puntuación del ganador del torneo.
     * @return
     * @throws IllegalStateException Si no hay participantes
     */
    public double obtenerPuntuacionMaxima()
    throws  IllegalStateException{
        if (this.participantes.isEmpty()){
            new IllegalStateException("No hay participantes");
        }
        return this.obtenerGanador().calcularPuntuacionFinal();
        /*CÓDIGO ALTERNATIVO
        return this.participantes.stream().mapToDouble(Competidor::calcularPuntuacionFinal)
            .max().orElseThrow(() -> new IllegalStateException("No hay participantes"));
         */
    }

    /**
     * Obtiene la puntuación del último clasificado
     * @return
     * @throws IllegalStateException Si no hay participantes
     */
    public double obtenerPuntuacionMinima()
    throws IllegalStateException{
        if (this.participantes.isEmpty()){
            new IllegalStateException("No hay participantes");
        }
        return this.obtenerClasificacion().get(this.participantes.size()).calcularPuntuacionFinal();
        /*CÓDIGO ALTERNATIVO
        return this.participantes.stream().mapToDouble(Competidor::calcularPuntuacionFinal)
            .min().orElseThrow(() -> new IllegalStateException("No hay participantes"));
            */
    }

    /**
     * Busca un participante dentro de la lista de inscritos.
     * @param nuevo
     * @return
     */
    public boolean estaInscrito(Participante nuevo) {
        for (Participante c: this.participantes){
            if (c.getId() == nuevo.getId()){
                return true;
            }
        }
        return false;
        /*CÓDIGO ALTERNATIVO
        return this.participantes.stream().anyMatch(p -> p.getId() == id);
         */
    }

    /**
     * Obtiene una copia de la lista de participantes
     * @return
     */
    public List<Participante> getParticipantes() {
        return List.copyOf(this.participantes);
    }

    public int getNumeroParticipantes() {
        return this.participantes.size();
    }

    public int getCapacidadMaxima() {
        return this.capacidadMaxima;
    }

    public String getNombre() {
        return this.nombre;
    }

    public TipoTorneo getTipo() {
        return this.tipo;
    }

    public EstadoTorneo getEstado() {
        return this.estado;
    }

    public LocalDate getComienzo() {
        return comienzo;
    }

    public LocalDate getFin() {
        return fin;
    }
}
