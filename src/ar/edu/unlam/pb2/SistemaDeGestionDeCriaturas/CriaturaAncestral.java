package ar.edu.unlam.pb2.SistemaDeGestionDeCriaturas;

public class CriaturaAncestral extends Criatura{

	public CriaturaAncestral(String nombre, int energia, AfinidadElemental afinidad) {
		super(nombre, energia, afinidad);
	}

	@Override
    public void entrenar() {
        modificarEnergia(15);

        if (getEnergia() > 180) {
            setEstado(EstadoEmocional.INESTABLE);
        }
    }

    @Override
    protected void modificarEnergia(int energiaASumar) {
        super.modificarEnergia(energiaASumar);

        if (getEnergia() < 100) {
            super.modificarEnergia(100 - getEnergia());
        }
    }
}
