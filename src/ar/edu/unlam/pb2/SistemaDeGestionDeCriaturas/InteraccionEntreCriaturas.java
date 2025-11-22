package ar.edu.unlam.pb2.SistemaDeGestionDeCriaturas;

public class InteraccionEntreCriaturas {

    public static void interactuar(Criatura uno, Criatura dos) {

        if (uno.esAncestral() || dos.esAncestral()) {
            resolverDominioAncestral(uno, dos);
            return;
        }

        if (uno.getAfinidad() == dos.getAfinidad()) {
            uno.modificarEnergia(10);
            dos.modificarEnergia(10);
            return;
        }

        if (sonOpuestas(uno.getAfinidad(), dos.getAfinidad())) {
            uno.setEstado(EstadoEmocional.INESTABLE);
            dos.setEstado(EstadoEmocional.INESTABLE);
            return;
        }

    }

    private static boolean sonOpuestas(AfinidadElemental x, AfinidadElemental y) {
        if (x == AfinidadElemental.AGUA && y == AfinidadElemental.FUEGO) return true;
        if (x == AfinidadElemental.FUEGO && y == AfinidadElemental.AGUA) return true;

        if (x == AfinidadElemental.AIRE && y == AfinidadElemental.TIERRA) return true;
        if (x == AfinidadElemental.TIERRA && y == AfinidadElemental.AIRE) return true;

        return false;
    }

    private static void resolverDominioAncestral(Criatura a, Criatura b) {

        Criatura ancestral;
        Criatura otra;

        if (a.esAncestral()) {
            ancestral = a;
            otra = b;
        } else {
            ancestral = b;
            otra = a;
        }

        ancestral.modificarEnergia(20);  
        otra.modificarEnergia(-15);      
    }
}
