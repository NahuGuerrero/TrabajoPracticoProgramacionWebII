package ar.edu.unlam.pb2.SistemaDeGestionDeCriaturas;

public class LlamaInterna extends Transformacion {

    public LlamaInterna(Criatura objetivo) {
        super(objetivo);
    }

    @Override
    public int getEnergia() {

        if (objetivo.getAfinidad() == AfinidadElemental.FUEGO) {
            int energiaAumentada = objetivo.getEnergia() + 30;

            if(energiaAumentada >= 200) {
                return 200;
            }else {
                return energiaAumentada;
            }


        }

        return objetivo.getEnergia();
    }

    @Override
    public EstadoEmocional getEstado() {

        if (objetivo.getAfinidad() != AfinidadElemental.FUEGO) {
            return EstadoEmocional.INESTABLE;
        }
      
        return objetivo.getEstado();
    }

    @Override
    public void entrenar() {
        objetivo.entrenar();
    }
}
