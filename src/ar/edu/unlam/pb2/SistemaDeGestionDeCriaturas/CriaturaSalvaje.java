package ar.edu.unlam.pb2.SistemaDeGestionDeCriaturas;

import java.util.Random;

public class CriaturaSalvaje extends Criatura {

	private Random random = new Random();

	public CriaturaSalvaje(String nombre, int energia, AfinidadElemental afinidad) {
		super(nombre, energia, afinidad);
	
	}

	@Override
    public void entrenar() {

        int energiaGanada = random.nextInt(21) + 5; 

        if (getEnergia() + energiaGanada > 200) {
            throw new RuntimeException("La criatura salvaje excedió el límite de energía permitido");
        }

        modificarEnergia(energiaGanada);

        if (random.nextDouble() < 0.30) {
            setEstado(EstadoEmocional.INESTABLE);
        }
    }
}

