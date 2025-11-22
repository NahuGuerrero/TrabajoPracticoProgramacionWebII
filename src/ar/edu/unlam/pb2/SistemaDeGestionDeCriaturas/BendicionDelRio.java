package ar.edu.unlam.pb2.SistemaDeGestionDeCriaturas;

public class BendicionDelRio extends Transformacion {

    public BendicionDelRio(Criatura objetivo) {
        super(objetivo);
    }

    @Override
    public int getEnergia() {
        int energiaDuplicada = objetivo.getEnergia() * 2;

        if (energiaDuplicada >= 180){
            return 180;
        }else {
            return energiaDuplicada;
        }



    }

    @Override
    public void entrenar() {
        objetivo.entrenar();
    }
}