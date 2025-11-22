package ar.edu.unlam.pb2.SistemaDeGestionDeCriaturas;

public abstract class Transformacion extends Criatura {

	protected Criatura objetivo;

    public Transformacion(Criatura objetivo) {
        super(
            objetivo.getNombre(),
            objetivo.getEnergia(),
            objetivo.getAfinidad()
        );
        this.objetivo = objetivo;
    }

    @Override
    public int getEnergia() {
        return objetivo.getEnergia();
    }

    @Override
    public String getNombre() {
        return objetivo.getNombre();
    }

    @Override
    public AfinidadElemental getAfinidad() {
        return objetivo.getAfinidad();
    }

    @Override
    public EstadoEmocional getEstado() {
        return objetivo.getEstado();
    }


}
