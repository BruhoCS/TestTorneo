package pps.ud1.torneo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Liga {
    private final String nombre;
    private final int capacidadMaximaTorneos;
    private final List<Torneo> torneos = new ArrayList<>();

    public Liga(String nombre, int capacidadMaximaTorneos) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre de liga obligatorio");
        }
        if (capacidadMaximaTorneos <= 0) {
            throw new IllegalArgumentException("La capacidad de torneos debe ser positiva");
        }
        this.nombre = nombre;
        this.capacidadMaximaTorneos = capacidadMaximaTorneos;
    }

    /**
     * Dos torneos no se puede solapar en fechas, no pueden ser nulos, no deben sobrepasar el límite máximo, ni se pueden duplicar
     * @param torneo
     */
    public void agregarTorneo(Torneo torneo) {
        if (torneo == null) {
            throw new IllegalArgumentException("Torneo nulo");
        }
        if (torneos.size() >= capacidadMaximaTorneos) {
            throw new IllegalStateException("La liga ha alcanzado el máximo de torneos");
        }
        if (torneos.contains(torneo)) {
            throw new IllegalArgumentException("El torneo ya está registrado en la liga");
        }
        torneos.add(torneo);
    }

    /**
     * Devuelve la lista ordenada por puntuación de todos los participantes de la liga en función del tipo de torneo
     * @param tipo
     * @return
     */
    public List<Participante> obtenerClasificacionGlobal(TipoTorneo tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de clasificación obligatorio");
        }
        List<Participante> clasificaiconFinal = new ArrayList<Participante>();
        if (tipo == TipoTorneo.INDIVIDUAL){
            for(Torneo t: this.torneos){
                if (t.getTipo() == TipoTorneo.INDIVIDUAL){
                    clasificaiconFinal.addAll(t.obtenerClasificacion());
                    clasificaiconFinal.sort(Comparator.comparingDouble(Participante::calcularPuntuacionFinal));
                }
            }
        }
        else if (tipo == TipoTorneo.EQUIPOS){
            for(Torneo t: this.torneos){
                if (t.getTipo() == TipoTorneo.EQUIPOS){

                    clasificaiconFinal.addAll(t.obtenerClasificacion());
                    clasificaiconFinal.sort(Comparator.comparingDouble(Participante::calcularPuntuacionFinal));
                }
            }
        }
        return clasificaiconFinal;
    }

    /**
     * Obtiene el ganador de la liga
     * @param tipo
     * @return
     */
    public Participante obtenerGanadorGlobal(TipoTorneo tipo) {
        return obtenerClasificacionGlobal(tipo).stream().findFirst()
            .orElseThrow(() -> new IllegalStateException(
                "No hay resultados finalizados para esta modalidad"));
    }

    public List<Torneo> getTorneos() {
        return List.copyOf(torneos);
    }

    public int getNumeroTorneos() {
        return torneos.size();
    }

    public String getNombre() {
        return nombre;
    }

}
