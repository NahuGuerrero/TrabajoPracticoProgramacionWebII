package ar.edu.unlam.pb2.SistemaDeGestionDeCriaturas;

public class CriaturaDomesticada extends Criatura {

	public CriaturaDomesticada(String nombre, int energia, AfinidadElemental afinidad) {
		super(nombre, energia, afinidad);
	}

	   @Override
	    public void entrenar() {
	        modificarEnergia(10);

	        setEstado(EstadoEmocional.TRANQUILA);
	    }
	}
