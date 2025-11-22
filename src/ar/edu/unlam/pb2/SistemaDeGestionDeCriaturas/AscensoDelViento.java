package ar.edu.unlam.pb2.SistemaDeGestionDeCriaturas;

public class AscensoDelViento extends Transformacion{

    public AscensoDelViento(Criatura objetivo) {
        super(objetivo);
    }

    @Override
    public AfinidadElemental getAfinidad() {

        if (objetivo.getAfinidad() != AfinidadElemental.AIRE) {
            return AfinidadElemental.AIRE;
        } else {
            return objetivo.getAfinidad();
        }
    }


    @Override
    public void entrenar() {
        objetivo.entrenar();


    }

}
