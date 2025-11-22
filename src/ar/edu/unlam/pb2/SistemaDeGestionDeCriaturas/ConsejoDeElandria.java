package ar.edu.unlam.pb2.SistemaDeGestionDeCriaturas;

import java.util.*;

public class ConsejoDeElandria {

    private List<MaestroElemental> maestros;

    public ConsejoDeElandria(List<MaestroElemental> maestros) {
        this.maestros = maestros;
    }

    public List<Criatura> listarTodasLasCriaturas() {
        List<Criatura> todas = new ArrayList<>();

        for (int i = 0; i < maestros.size(); i++) {
            MaestroElemental maestro = maestros.get(i);

            Collection<Criatura> valores = maestro.getCriaturas().values();
            List<Criatura> lista = new ArrayList<>(valores);

            for (int j = 0; j < lista.size(); j++) {
                todas.add(lista.get(j));
            }
        }

        return todas;
    }

    public Criatura obtenerCriaturaConMasEnergia() {
        List<Criatura> todas = listarTodasLasCriaturas();

        if (todas.isEmpty()) {
            return null;
        }

        Criatura mayor = todas.get(0);

        for (int i = 1; i < todas.size(); i++) {
            if (todas.get(i).getEnergia() > mayor.getEnergia()) {
                mayor = todas.get(i);
            }
        }

        return mayor;
    }

    public MaestroElemental maestroConMasCriaturasTransformadas() {
        MaestroElemental elegido = null;
        int max = -1;

        for (int i = 0; i < maestros.size(); i++) {
            MaestroElemental maestro = maestros.get(i);
            int cantidadTransformadas = contarTransformadas(maestro);

            if (cantidadTransformadas > max) {
                max = cantidadTransformadas;
                elegido = maestro;
            }
        }

        return elegido;
    }

    private int contarTransformadas(MaestroElemental maestro) {
        int contador = 0;

        Collection<Criatura> valores = maestro.getCriaturas().values();
        List<Criatura> lista = new ArrayList<>(valores);

        for (int i = 0; i < lista.size(); i++) {
            Criatura criatura = lista.get(i);

            if (criatura.esTransformacion()) {
                contador++;
            }
        }

        return contador;
    }

    public Map<AfinidadElemental, Integer> cantidadPorAfinidad() {
        Map<AfinidadElemental, Integer> mapa = new HashMap<>();

        List<Criatura> todas = listarTodasLasCriaturas();

        for (int i = 0; i < todas.size(); i++) {
            Criatura criatura = todas.get(i);
            AfinidadElemental afin = criatura.getAfinidad();

            Integer actual = mapa.get(afin);
            if (actual == null) {
                mapa.put(afin, 1);
            } else {
                mapa.put(afin, actual + 1);
            }
        }

        return mapa;
    }
}
