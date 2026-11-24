package pps.ud1.torneo;

import java.time.LocalDate;

public class App {
    public static void main(String[] args){
        JugadorIndividual j1 = new JugadorIndividual(1, "Paco", 18);
        JugadorIndividual j2 = new JugadorIndividual(2, "Pepe", 19);
        JugadorIndividual j3 = new JugadorIndividual(3, "Ana", 20);
        JugadorIndividual j4 = new JugadorIndividual(4, "Rosa", 21);

        Equipo e1 = new Equipo(100, "E1", 2);
        Equipo e2 = new Equipo(101, "E2", 3);
        e1.agregarIntegrante(j1);
        e1.agregarIntegrante(j2);
        try{
            e1.agregarIntegrante(j3);
        } catch (IllegalStateException e) {
            System.out.println("No se añadio j3");
        }
        e2.agregarIntegrante(j3);
        e2.agregarIntegrante(j4);
        e2.agregarIntegrante(j1);

        LocalDate inicio = LocalDate.of(2026,11,25);
        LocalDate fin = LocalDate.of(2027,1,30);

        Torneo t1 = new Torneo("T1", TipoTorneo.EQUIPOS, 2, inicio, fin);
        System.out.println(t1.inscribir(e1));
        System.out.println(t1.inscribir(e2));
        System.out.println(t1.getParticipantes());
    }
}
