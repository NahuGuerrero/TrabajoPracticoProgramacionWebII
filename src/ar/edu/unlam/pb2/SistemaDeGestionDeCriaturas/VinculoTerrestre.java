package ar.edu.unlam.pb2.SistemaDeGestionDeCriaturas;

public class VinculoTerrestre extends Transformacion{

    public VinculoTerrestre(Criatura objetivo) {
        super(objetivo);
    }


    @Override
    public int getEnergia() {

        int energiaObjetivo = objetivo.getEnergia();

        if (energiaObjetivo< 50) {
            energiaObjetivo = 50;
            return energiaObjetivo;
        }else {
            return energiaObjetivo;
        }


    }


    @Override
    public void entrenar() {
        objetivo.entrenar();
    }


}

