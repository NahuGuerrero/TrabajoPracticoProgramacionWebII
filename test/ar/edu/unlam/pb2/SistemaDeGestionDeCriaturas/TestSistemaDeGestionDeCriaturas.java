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
    
    
