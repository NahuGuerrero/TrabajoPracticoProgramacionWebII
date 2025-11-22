package ar.edu.unlam.pb2.SistemaDeGestionDeCriaturas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.*;

import org.junit.Test;

public class TestSistemaDeGestionDeCriaturas {

	
	
	
	
	//PRUEBA CRIATURA
	@Test
    public void entrenarDebeAumentarLaEnergiaCuandoNoSupera200() {
        Criatura salvaje = new CriaturaSalvaje("Rocco", 100, AfinidadElemental.TIERRA);

        int energiaAntes = salvaje.getEnergia();
        salvaje.entrenar();
        int energiaDespues = salvaje.getEnergia();

        assertTrue(energiaDespues > energiaAntes);
    }
	
	@Test
    public void entrenarNuncaDebeDisminuirEnergia() {
        Criatura salvaje = new CriaturaSalvaje("Pelusa", 120, AfinidadElemental.AIRE);

        int energiaAntes = salvaje.getEnergia();
        salvaje.entrenar();
        int energiaDespues = salvaje.getEnergia();

        assertTrue(energiaDespues >= energiaAntes);
    }
	
	@Test
    public void entrenarPuedeVolverInestableALaCriatura() {
        Criatura salvaje = new CriaturaSalvaje("Bigote", 120, AfinidadElemental.AGUA);

        salvaje.entrenar();

        assertTrue( salvaje.getEstado() == EstadoEmocional.TRANQUILA || salvaje.getEstado() == EstadoEmocional.INESTABLE);
    }
	
	
	@Test
    public void laAfinidadSeMantieneCorrecta() {
        Criatura salvaje = new CriaturaSalvaje("Pipe", 150, AfinidadElemental.FUEGO);

        assertEquals(AfinidadElemental.FUEGO, salvaje.getAfinidad());
    }
	
	
	
	
	
	//CRIATURA DOMESTICADA
	@Test
    public void entrenarDebeAumentar10DeEnergia() {
        Criatura domesticada = new CriaturaDomesticada("Puppy", 100, AfinidadElemental.AGUA);

        domesticada.entrenar();

        assertEquals(110, domesticada.getEnergia());
    }

    @Test
    public void entrenarNuncaDebeSuperar200EnCriaturaDomesticada() {
        Criatura domesticada = new CriaturaDomesticada("Cabeza", 195, AfinidadElemental.FUEGO);

        domesticada.entrenar(); 

        assertEquals(200, domesticada.getEnergia());
    }

    @Test
    public void entrenarNuncaVuelveInestable() {
        Criatura domesticada = new CriaturaDomesticada("Tito", 120, AfinidadElemental.TIERRA);

        domesticada.setEstado(EstadoEmocional.INESTABLE);

        domesticada.entrenar();

        assertEquals(EstadoEmocional.TRANQUILA, domesticada.getEstado());
    }

    @Test
    public void laAfinidadDebeMantenerseCorrectaEnCriaturaDomesticada() {
        Criatura domesticada = new CriaturaDomesticada("Coco", 150, AfinidadElemental.AIRE);

        assertEquals(AfinidadElemental.AIRE, domesticada.getAfinidad());
    }
    
    
    
    
    
    //CRIATURA ANCESTRAL
    @Test
    public void entrenarDebeAumentar15DeEnergia() {
        Criatura ancestral = new CriaturaAncestral("Viejo", 120, AfinidadElemental.FUEGO);

        ancestral.entrenar();

        assertEquals(135, ancestral.getEnergia());
    }

    @Test
    public void entrenarVuelveInestableSiSupera180DeEnergia() {
        Criatura ancestral = new CriaturaAncestral("Teo", 170, AfinidadElemental.TIERRA);

        ancestral.entrenar(); 

        assertEquals(EstadoEmocional.INESTABLE, ancestral.getEstado());
    }

    @Test
    public void laEnergiaNuncaDebeBajarDeCien() {
        Criatura ancestral = new CriaturaAncestral("Leo", 120, AfinidadElemental.AGUA);

       
        ancestral.modificarEnergia(-150);

        assertEquals(100, ancestral.getEnergia()); 
    }

    @Test
    public void entrenarNuncaDebeSuperar200EnCriaturaAncestral() {
        Criatura ancestral = new CriaturaAncestral("Enzo", 195, AfinidadElemental.AIRE);

        ancestral.entrenar(); 

        assertEquals(200, ancestral.getEnergia());
    }

    @Test
    public void laAfinidadDebeMantenerseCorrectamenteEnCriaturaAncestral() {
        Criatura ancestral = new CriaturaAncestral("Pipo", 150, AfinidadElemental.FUEGO);

        assertEquals(AfinidadElemental.FUEGO, ancestral.getAfinidad());
    }
    
    
    
    
    
    //MAESTRO ELEMENTAL
    @Test
    public void elMaestroDebePoderAgregarCriaturasCorrectamente() {
        MaestroElemental maestro = new MaestroElemental("Diego", 20, AfinidadElemental.FUEGO);
        Criatura criatura = new CriaturaDomesticada("Enzo", 100, AfinidadElemental.AGUA);

        maestro.agregarCriatura(criatura);

        assertEquals(1, maestro.getCriaturas().size());
        assertTrue(maestro.getCriaturas().containsKey("Enzo"));
    }


  

    @Test
    public void elMaestroConMaestriaSuficienteDebeEntrenarALaCriatura() throws Exception {
        MaestroElemental maestro = new MaestroElemental("Diego", 30, AfinidadElemental.FUEGO);
        Criatura criatura = new CriaturaDomesticada("Enzo", 100, AfinidadElemental.AIRE);

        maestro.agregarCriatura(criatura);

        maestro.entrenarCriatura("Enzo"); 

        assertEquals(110, criatura.getEnergia());
    }



    @Test(expected = MaestriaInsuficienteException.class)
    public void debeTirarExcepcionSiLaMaestriaEsInsuficiente() throws Exception {
        MaestroElemental maestro = new MaestroElemental("Harry Potter", 5, AfinidadElemental.TIERRA);
        Criatura criatura = new CriaturaDomesticada("Willy", 100, AfinidadElemental.FUEGO);

        
        maestro.agregarCriatura(criatura);

        maestro.entrenarCriatura("Willy");
    }

 

    @Test(expected = IllegalArgumentException.class)
    public void debeTirarExcepcionSiLaCriaturaNoExiste() throws Exception {
        MaestroElemental maestro = new MaestroElemental("Melchor", 15, AfinidadElemental.AGUA);

        maestro.entrenarCriatura("NoExiste");
    }



    @Test
    public void debePacificarUnaCriaturaInestable() {
        MaestroElemental maestro = new MaestroElemental("Gaspar", 25, AfinidadElemental.AIRE);
        Criatura criatura = new CriaturaSalvaje("Loco", 150, AfinidadElemental.FUEGO);
      
        criatura.setEstado(EstadoEmocional.INESTABLE);

        maestro.agregarCriatura(criatura);

        maestro.pacificarCriatura("Loco");

        assertEquals(EstadoEmocional.TRANQUILA, criatura.getEstado());
    }

  
    @Test
    public void pacificarCriaturaInexistenteNoDebeGenerarError() {
        MaestroElemental maestro = new MaestroElemental("Baltazar", 25, AfinidadElemental.AIRE);
    
        maestro.pacificarCriatura("Luna");

        assertTrue(true);
    }

    

    @Test(expected = IllegalArgumentException.class)
    public void noDebePermitirNivelDeMaestriaFueraDeRango() {
        new MaestroElemental("Merlin", 0, AfinidadElemental.TIERRA); 
    }

    @Test(expected = IllegalArgumentException.class)
    public void noDebePermitirNivelDeMaestriaMayorA50() {
        new MaestroElemental("Merlin", 51, AfinidadElemental.TIERRA); 
    }

    

    @Test
    public void elHashMapDebeContenerALaCriaturaExacta() {
        MaestroElemental maestro = new MaestroElemental("Draco Malfoy", 20, AfinidadElemental.AGUA);
        Criatura criatura = new CriaturaDomesticada("Popi", 130, AfinidadElemental.FUEGO);

        maestro.agregarCriatura(criatura);

        assertSame(criatura, maestro.getCriaturas().get("Popi"));
    }
    
    
    
    
    
    //BENDICION DEL RIO
    @Test
    public void energiaSeDuplicaCorrectamente() {
        Criatura base = new CriaturaDomesticada("Snoopy", 50, AfinidadElemental.AGUA);
        Transformacion bendicion = new BendicionDelRio(base);

        assertEquals(100, bendicion.getEnergia());
    }

    @Test
    public void energiaNoDebeSuperar180() {
        Criatura base = new CriaturaDomesticada("Oreo", 120, AfinidadElemental.AGUA);
        Transformacion bendicion = new BendicionDelRio(base);

        assertEquals(180, bendicion.getEnergia());
    }

    @Test
    public void entrenarDebeDelegarAlaCriaturaBaseBendicionDelRio() {
        Criatura base = new CriaturaDomesticada("Bicho", 100, AfinidadElemental.AGUA);
        Transformacion bendicion = new BendicionDelRio(base);

        bendicion.entrenar(); 

        assertEquals(110, base.getEnergia());
    }
	    
    

	
    
    //LLAMA INTERNA
    @Test
    public void energiaAumenta30SiLaAfinidadEsFuego() {
        Criatura base = new CriaturaDomesticada("Scooby", 100, AfinidadElemental.FUEGO);
        Transformacion llama = new LlamaInterna(base);

        assertEquals(130, llama.getEnergia());
    }

    @Test
    public void energiaNoDebeSuperar200() {
        Criatura base = new CriaturaDomesticada("Pluto", 190, AfinidadElemental.FUEGO);
        Transformacion llama = new LlamaInterna(base);

        assertEquals(200, llama.getEnergia());
    }

    @Test
    public void siNoEsDeFuegoDebeMantenerEnergiaBase() {
        Criatura base = new CriaturaDomesticada("Tequila", 100, AfinidadElemental.AGUA);
        Transformacion llama = new LlamaInterna(base);

        assertEquals(100, llama.getEnergia());
    }

     @Test
    public void siNoEsDeFuegoDebeVolverseInestable() {
        Criatura base = new CriaturaDomesticada("Frutilla", 100, AfinidadElemental.AIRE);
        Transformacion llama = new LlamaInterna(base);

        assertEquals(EstadoEmocional.INESTABLE, llama.getEstado());
    }

    @Test
    public void siEsDeFuegoMantieneSuEstadoOriginal() {
        Criatura base = new CriaturaDomesticada("Pera", 100, AfinidadElemental.FUEGO);
        Transformacion llama = new LlamaInterna(base);

        assertEquals(base.getEstado(), llama.getEstado());
    }

    @Test
    public void entrenarDebeDelegarAlaCriaturaBaseLlamaInterna() {
        Criatura base = new CriaturaDomesticada("Uva", 100, AfinidadElemental.FUEGO);
        Transformacion llama = new LlamaInterna(base);

        llama.entrenar(); 

        assertEquals(110, base.getEnergia());
    }        
    
    
    
    
    
    //VINCULO TERRESTRE
    @Test
    public void energiaDebeElevarseA50SiEsMenor() {
        Criatura base = new CriaturaDomesticada("Bruno", 30, AfinidadElemental.TIERRA);
        Transformacion vinculo = new VinculoTerrestre(base);

        assertEquals(50, vinculo.getEnergia());
    }

    @Test
    public void energiaSeMantieneSiEsMayorA50() {
        Criatura base = new CriaturaDomesticada("Lucas", 80, AfinidadElemental.TIERRA);
        Transformacion vinculo = new VinculoTerrestre(base);

        assertEquals(80, vinculo.getEnergia());
    }

    @Test
    public void entrenarDebeDelegarAlaCriaturaBaseVinculoTerrestre() {
        Criatura base = new CriaturaDomesticada("Benito", 100, AfinidadElemental.TIERRA);
        Transformacion vinculo = new VinculoTerrestre(base);

        vinculo.entrenar(); 

        assertEquals(110, base.getEnergia());
    }
    
    
    
    
    
    //ASCENSO DEL VIENTO
    @Test
    public void afinidadSiempreDebeSerAireSiElObjetivoNoEsAire() {
        Criatura base = new CriaturaDomesticada("Fido,", 100, AfinidadElemental.TIERRA);
        Transformacion viento = new AscensoDelViento(base);

        assertEquals(AfinidadElemental.AIRE, viento.getAfinidad());
    }

    @Test
    public void siLaCriaturaEsDeAireSeMantieneSuAfinidad() {
        Criatura base = new CriaturaDomesticada("Lolo", 100, AfinidadElemental.AIRE);
        Transformacion viento = new AscensoDelViento(base);

        assertEquals(AfinidadElemental.AIRE, viento.getAfinidad());
    }

    @Test
    public void entrenarDebeDelegarAlaCriaturaBaseAscensoDelViento() {
        Criatura base = new CriaturaDomesticada("Bella", 100, AfinidadElemental.AIRE);
        Transformacion viento = new AscensoDelViento(base);

        viento.entrenar(); 

        assertEquals(110, base.getEnergia());
    }    
    
    
    
    
    
    //INTERACCION ENTRE CRIATURAS
    @Test
    public void mismasAfinidadesAmbasGananDiezDeEnergia() {
        Criatura uno = new CriaturaDomesticada("Uno", 100, AfinidadElemental.AGUA);
        Criatura dos = new CriaturaSalvaje("Dos", 120, AfinidadElemental.AGUA);

        InteraccionEntreCriaturas.interactuar(uno, dos);

        assertEquals(110, uno.getEnergia());
        assertEquals(130, dos.getEnergia());
    }


    @Test
    public void afinidadesOpuestasVuelvenInestablesALasCriaturas() {
        Criatura uno = new CriaturaDomesticada("Uno", 100, AfinidadElemental.AGUA);
        Criatura dos = new CriaturaDomesticada("Dos", 120, AfinidadElemental.FUEGO);

        InteraccionEntreCriaturas.interactuar(uno, dos);

        assertEquals(EstadoEmocional.INESTABLE, uno.getEstado());
        assertEquals(EstadoEmocional.INESTABLE, dos.getEstado());
    }

	
    @Test
    public void ancestralDominaLaInteraccion() {
        Criatura ancestral = new CriaturaAncestral("Anci", 150, AfinidadElemental.TIERRA);
        Criatura otra = new CriaturaDomesticada("Peque", 100, AfinidadElemental.AGUA);

        InteraccionEntreCriaturas.interactuar(ancestral, otra);

        assertEquals(170, ancestral.getEnergia());
        assertEquals(85, otra.getEnergia());
    }

 
    @Test
    public void energiaNoDebeCaerDebajoDeCero() {
        Criatura ancestral = new CriaturaAncestral("Anci", 150, AfinidadElemental.AIRE);
        Criatura otra = new CriaturaDomesticada("Mini", 10, AfinidadElemental.AGUA);

        InteraccionEntreCriaturas.interactuar(ancestral, otra);

        assertEquals(170, ancestral.getEnergia());
        assertEquals(0, otra.getEnergia());
    }
    
    
    
    
    
    //REPORTES DEL CONSEJO 
    @Test
    public void listarTodasLasCriaturasDevuelveTodasLasCriaturas() {

        MaestroElemental maestro1 = new MaestroElemental("Diego", 30, AfinidadElemental.FUEGO);
        MaestroElemental maestro2 = new MaestroElemental("Zinedine", 30, AfinidadElemental.AGUA);

        maestro1.agregarCriatura(new CriaturaDomesticada("Lionel", 100, AfinidadElemental.AGUA));
        maestro2.agregarCriatura(new CriaturaSalvaje("Pele", 150, AfinidadElemental.FUEGO));
        maestro2.agregarCriatura(new CriaturaAncestral("Cristiano", 120, AfinidadElemental.TIERRA));

        List<MaestroElemental> maestros = Arrays.asList(maestro1, maestro2);
        ConsejoDeElandria consejo = new ConsejoDeElandria(maestros);

        List<Criatura> todas = consejo.listarTodasLasCriaturas();

        assertEquals(3, todas.size());
    }

	
    @Test
    public void obtenerCriaturaConMasEnergiaSeleccionaLaCorrecta() {

        MaestroElemental maestro = new MaestroElemental("Diego", 30, AfinidadElemental.AGUA);

        Criatura criatura1 = new CriaturaDomesticada("Zinedine", 100, AfinidadElemental.AGUA);
        Criatura criatura2 = new CriaturaSalvaje("Lionel", 150, AfinidadElemental.FUEGO);
        Criatura criatura3 = new CriaturaAncestral("Pele", 180, AfinidadElemental.TIERRA);

        maestro.agregarCriatura(criatura1);
        maestro.agregarCriatura(criatura2);
        maestro.agregarCriatura(criatura3);

        ConsejoDeElandria consejo = new ConsejoDeElandria(Arrays.asList(maestro));

        assertEquals(criatura3, consejo.obtenerCriaturaConMasEnergia());
    }

	
    @Test
    public void maestroConMasCriaturasTransformadasDevuelveElMayor() {

    	MaestroElemental maestro1 = new MaestroElemental("Pablo", 30, AfinidadElemental.FUEGO);
    	maestro1.agregarCriatura(
         new LlamaInterna(
             new CriaturaDomesticada("A1", 100, AfinidadElemental.FUEGO)
         )
     );

		
     MaestroElemental maestro2 = new MaestroElemental("Rodrigo", 30, AfinidadElemental.AGUA);
     maestro2.agregarCriatura(
         new BendicionDelRio(
             new CriaturaSalvaje("B1", 150, AfinidadElemental.FUEGO)
         )
     );
     maestro2.agregarCriatura(
         new AscensoDelViento(
             new CriaturaDomesticada("B2", 80, AfinidadElemental.TIERRA)
         )
     );

     ConsejoDeElandria consejo = new ConsejoDeElandria(Arrays.asList(maestro1, maestro2));

     assertEquals(maestro2, consejo.maestroConMasCriaturasTransformadas());
    }

    

    @Test
    public void cantidadPorAfinidadCuentaCorrectamenteLasCriaturas() {

        MaestroElemental maestro1 = new MaestroElemental("Pablo", 30, AfinidadElemental.TIERRA);
        MaestroElemental maestro2 = new MaestroElemental("Rodrigo", 30, AfinidadElemental.AGUA);

        maestro1.agregarCriatura(new CriaturaDomesticada("A1", 100, AfinidadElemental.AGUA));
        maestro2.agregarCriatura(new CriaturaSalvaje("B1", 120, AfinidadElemental.FUEGO));
        maestro2.agregarCriatura(new CriaturaAncestral("B2", 150, AfinidadElemental.TIERRA));

        ConsejoDeElandria consejo = new ConsejoDeElandria(Arrays.asList(maestro1, maestro2));

        Map<AfinidadElemental, Integer> mapa = consejo.cantidadPorAfinidad();

        assertEquals(Integer.valueOf(1), mapa.get(AfinidadElemental.AGUA));
        assertEquals(Integer.valueOf(1), mapa.get(AfinidadElemental.FUEGO));
        assertEquals(Integer.valueOf(1), mapa.get(AfinidadElemental.TIERRA));
    }
}
    
    
