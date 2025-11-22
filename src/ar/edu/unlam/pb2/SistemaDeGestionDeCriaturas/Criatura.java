package ar.edu.unlam.pb2.SistemaDeGestionDeCriaturas;

public abstract class Criatura {

	private String nombre;
	private int energia;
	private AfinidadElemental afinidad;
	private EstadoEmocional estado;
	
	public Criatura (String nombre, int energia, AfinidadElemental afinidad) {
		this.nombre = nombre;
		this.energia = energia;
		this.afinidad = afinidad;
		this.estado = EstadoEmocional.TRANQUILA;
	}
	
	public abstract void entrenar();
	
	public void pacificar() {
		this.estado = EstadoEmocional.TRANQUILA;
	}
	
	protected void modificarEnergia (int energiaASumar) {
		this.energia += energiaASumar;
		if(this.energia < 0) {
			this.energia = 0;
		} 
		if (this.energia > 200) {
			this.energia = 200;
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EstadoEmocional getEstado() {
		return estado;
	}

	protected void setEstado(EstadoEmocional nuevoEstado) {
		this.estado = nuevoEstado;
	}

	public int getEnergia() {
		return energia;
	}

	public AfinidadElemental getAfinidad() {
		return afinidad;
	}
	
	
	
	
	
}
