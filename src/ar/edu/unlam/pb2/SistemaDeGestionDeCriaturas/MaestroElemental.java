package ar.edu.unlam.pb2.SistemaDeGestionDeCriaturas;

import java.util.HashMap;

public class MaestroElemental {

    private String nombre;
    private int nivelDeMaestria;
    private AfinidadElemental afinidadPrincipal;
    private HashMap<String, Criatura> criaturas;

    public MaestroElemental(String nombre, int nivelDeMaestria, AfinidadElemental afinidadPrincipal) {
        this.nombre = nombre;

        if (nivelDeMaestria < 1 || nivelDeMaestria > 50) {
            throw new IllegalArgumentException("El nivel de maestría debe estar entre 1 y 50");
        }
        this.nivelDeMaestria = nivelDeMaestria;

        this.afinidadPrincipal = afinidadPrincipal;
        this.criaturas = new HashMap<>();
    }

    public void agregarCriatura(Criatura criatura) {
        criaturas.put(criatura.getNombre(), criatura);
    }

    public void entrenarCriatura(String nombreCriatura) throws MaestriaInsuficienteException {

        Criatura objetivo = criaturas.get(nombreCriatura);

        if (objetivo == null) {
            throw new IllegalArgumentException("No existe la criatura indicada");
        }

        int maestriaNecesaria = objetivo.getEnergia() / 5;

        if (nivelDeMaestria < maestriaNecesaria) {
            throw new MaestriaInsuficienteException("Maestría insuficiente para entrenar esta criatura");
        }

        objetivo.entrenar();
    }

    public void pacificarCriatura(String nombreCriatura) {
        Criatura objetivo = criaturas.get(nombreCriatura);

        if (objetivo != null) {
            objetivo.pacificar();
        }
    }

    public HashMap<String, Criatura> getCriaturas() {
        return criaturas;
    }
}
	
	
	
	

