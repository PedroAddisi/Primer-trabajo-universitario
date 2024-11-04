package juego;
import java.text.DecimalFormat;
import java.util.Random;
import java.awt.Color;
import java.awt.Image;
import entorno.Herramientas;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego

{
	private Entorno entorno;
	//Variables
	Isla isla;
	Isla[] islas = new Isla[15];
	Heroe heroe;
	Nave nave;
	Gnomo gnomo;
	Tortuga tortuga;
	DisparoHeroe Disparo;
	Gnomo[] gnomos = new Gnomo[4];
	Tortuga []tortugas = new Tortuga[8];
	Double tiempo;
    String tiempomos;
    DecimalFormat decimalFormat =new DecimalFormat("#.#");
	char visionHeroe;
	Boolean PantalladeInicio;
	Boolean PantallaGameOver;
	Image pantalladeinicio;
	Image imagenFondo;
	Image casa;
	Image gameover;
	int tick = 0;
	int salvados = 0;
	int muertos = 0;
	int guardoIndiceTortuga=-1;
	int guardoIndiceGnomo=-1;
	static int Nivel =1;
	Random random;
	//Generacion de islas
	public Gnomo[] generadorDeGnomos() {//genera un arreglo de gnomos verificando que no existan nulls
        Gnomo[]gnomos = new Gnomo[4];
        for(int i = 0; i < gnomos.length; i++) {
            if (gnomos[i] == null) {
                gnomos[i] = new Gnomo();
            }
        }
        return gnomos;
    }
	public void generartortuga(Tortuga tortugas[]) {////genera un arreglo de tortugas verificando que no existan nulls
		for(int i = 0; i < tortugas.length; i++) {
			if (tortugas[i] == null) {
				guardoIndiceTortuga=i;
			}
		}//si existe un objeto null guardo el indice para luego generar una tortgua en ese espacio del arreglo 
		if(guardoIndiceTortuga!=-1) {//si existe un null genera una tortuga
			tortugas[guardoIndiceTortuga]= new Tortuga(islas,tortugas);
			guardoIndiceTortuga=-1;	
		}

	}
	public Isla[] generadorIslas () {//dibuja las islas dentro del entrono
	    Isla[] islas = new Isla[15];
	    int largo = 112;//le damos el valor a la primera isla
		int alto = 36;//le damos el valor a la primera isla
	    int ejeX = 96;//le damos el valor a la primera isla
	    int ejeY = 512;//le damos el valor a la primera isla 
	    int espacio = 40 ; //le damos el valor a la primera isla
	    int indice = 0;
	    int cantidadDeEspacios = 1;
	    int numDeIslaEnFila = 1;//cuenta las islas de la fila de izquierda a derecha 
	    int fila = 5;//decide la cantidad de islas que hay que generar en la fila 
	    while(fila > 0){//cuenta las filas por la que va generando desde abajo hacia arriba 
	        while (numDeIslaEnFila <= fila){
	            if (numDeIslaEnFila == 1 && indice != 0){// si es la primera de la fila tiene condicion especial 
	                ejeY -= 106;
	            }
	            else if (indice == 0) {//genera la primera isla de todas con los valores de arriba
	                islas[0] = new Isla (ejeX, ejeY, alto, largo);
	            }
	            else if (indice != 0) {//genera las islas en esa fila 
	                ejeX += largo + espacio ;
	            }
	        islas[indice]= new Isla(ejeX, ejeY, alto, largo);
	        indice++;
	        numDeIslaEnFila++;
	        }
	    ejeX = (largo/2 + espacio) + (largo/2 + espacio/2) * cantidadDeEspacios;
	    cantidadDeEspacios++;
	    numDeIslaEnFila = 1;
	    fila--;
	    }
	    return islas;
	    }
	//
	Juego() {
		//Tiempo
        tiempo = 120.0;
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Al rescate de los Gnomos", 800, 600);
		//
		// islas
		islas = this.generadorIslas();
		//
		// Heroe
		nave = new Nave();
		heroe = new Heroe();
		Disparo= new DisparoHeroe(heroe);
		//
		//Gnomo
		gnomo = new Gnomo();
		//
		tortuga= new Tortuga(islas,tortugas);
		imagenFondo = Herramientas.cargarImagen("elfondo.jpg");
		pantalladeinicio = Herramientas.cargarImagen("inicioo.jpg");
		casa = Herramientas.cargarImagen("casa.png");
		gameover = Herramientas .cargarImagen("gameover.png");
		Herramientas.loop("Sountrack.wav");
		// Inicia el juego!
		this.entorno.iniciar();
		//
		
	}
	public void tick() {
		//GENERACION DE PANTALLA DE INCIO
		tick++;//cuenta los ticks
		entorno.cambiarFont("Arial", 20, Color.WHITE);
		entorno.dibujarImagen(pantalladeinicio, 400 , 300, 0);
		entorno.escribirTexto("Click derecho para empezar", 300, 500);
		entorno.cambiarFont("Arial",40, Color.BLACK);
		entorno.escribirTexto("Al rescate de los Gnomos", 180, 100);
		try {
		if(entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {// quita el menu para iniciar el juego
			this.PantalladeInicio = false;
		}
		//
			if(this.PantalladeInicio == false) {//arranca el programa
		//GENERACION DE TEXTO Y PANTALLA
		tiempomos=decimalFormat.format(tiempo);
		tiempo-= 0.015;//cada tick vale este valor
		entorno.dibujarImagen(imagenFondo, 400, 300,0,1.8);
		entorno.dibujarImagen(casa, 400,52,0,0.15);
		entorno.cambiarFont("Arial", 32, Color.yellow);
		entorno.escribirTexto("Tiempo : " + tiempomos , 0, 25);
		entorno.escribirTexto("Salvados : " + salvados, 300, 25);
		entorno.escribirTexto("Muertos : " + muertos, 600, 25);
		//
		// GENERACION DE ISLA
		for (Isla islas : islas) {//dibuja todas las islas del arreglo
			islas.dibujarisla(entorno);
		}
		//
		//Generacion y nave
		nave.dibujarnave(entorno);
		nave.setX(entorno.mouseX());//se mueve en eje x en base a la posicion del mouse 
		nave.colisionConHeroe(heroe);
		if(nave.isTocaConHeroe() == true) {//salva al heroe
			heroe.setX(400);
			heroe.setY(450);
		}
		//
		// GENERACION Y MOVIMIENTO DEL HEROE
		heroe.dibujarheroe(entorno);
		heroe.setSaltando(false);
		heroe.colisionConIsla(islas);
		heroe.gravedadHeroe(islas);
		if(heroe.getY() == 600 ) {//si cae al vacio grita 
			Herramientas.play("Dead.wav");
		}
		//comandos de movimiento del jugador
		if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			heroe.moverAdelante();
			visionHeroe = entorno.TECLA_DERECHA; 
		}
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			heroe.moverAtras();
			visionHeroe = entorno.TECLA_IZQUIERDA;
			
		}
		if(visionHeroe == entorno.TECLA_IZQUIERDA) {
				if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
					heroe.setSaltando(true);
					heroe.SaltoIzq(islas);
				}
		}
		if(visionHeroe == entorno.TECLA_DERECHA) {
			if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
				heroe.setSaltando(true);
				heroe.SaltoDer(islas);
			}
				}
		if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {
			Herramientas.play("Salto.wav");	
		}
		//DISPARO DEL HEROE
		if(entorno.sePresiono(entorno.TECLA_ESPACIO)) {
			Herramientas.play("Fire.wav");
		}
		Disparo.desaparece(heroe);
		if(entorno.sePresiono(entorno.TECLA_ESPACIO)) {
			Disparo.setDisparo(true);
		}
		if(Disparo.isDisparo() == true) {
			Disparo.DIbujarDisparo(entorno);
			Disparo.mover();
		}
		if (!Disparo.isDisparo()) {
			if(this.visionHeroe == entorno.TECLA_DERECHA) {
				Disparo.direcDer();
			}
			else if(this.visionHeroe == entorno.TECLA_IZQUIERDA) {
				Disparo.direcIzq();
			}
			Disparo.setX(heroe.getX());
			Disparo.setY(heroe.getY());
		}
		//
		//Generaracion y movimiento de gnomo
		for (int i = 0; i < gnomos.length;i++) {
			if(gnomos[i]!=null) {
				for(int t=0; t<tortugas.length;t++) {
					if(tortugas[t]!=null) {
						gnomos[i].colisionConTortuga(tortugas[t]);
						if(gnomos[i].isTocaConTortuga()) {
							gnomos[i] = null;
							muertos++;
							Herramientas.play("Grito.wav");
						}
					}
				}
				gnomos[i].colisionConHeroe(heroe);
				nave.colisionConGnomo(gnomos[i]);
				if(gnomos[i].isTocaConHeroe() && gnomos[i].getY()>200 || nave.isTocaConGnomo() == true) {
					gnomos[i] = null;
					salvados++;
					Herramientas.play("Gracias.wav");
				}
				else if (gnomos[i].getY() >= 605) {
					gnomos[i] = null;
					muertos++;
				}
				else {
					gnomos[i].dibujargnomo(entorno);
					gnomos[i].movimiento(islas);
					gnomos[i].caerGnomos(islas);
				}
			}else {
			gnomos[i] = new Gnomo();
			}
		}
		//
		//Movimiento tortuga
//		for(Tortuga t: tortugas) {
//			t.dibujarTortuga(entorno);
//			t.movimiento(islas);
//		}
		if (tick%350 ==0) {
			this.generartortuga(tortugas);
			}
		for(int i=0; i<tortugas.length;i++) {
			if (tortugas[i]!=null) {
				tortugas[i].colisionAdelante();
				tortugas[i].dibujarTortuga(entorno);
				tortugas[i].movimiento(islas);
				tortugas[i].colisionConHeroe(heroe);
				if(tortugas[i].isTocaConHeroe()) {
					this.PantallaGameOver=true;
					Herramientas.play("Dead.wav");
				}
				tortugas[i].colisionConDisparo(Disparo);
				if (tortugas[i].isTocaConDisparo()==true) {
					tortugas[i]=null;
					}
				}
			}
		}
	}
	catch(Exception ex) {
		//System.out.println("Esperando instruccion pantalla de inicio");
	}
			
			try {
	            if(this.tiempo < 0 || this.muertos >= 5 ||heroe.getY() >= 600) {
	                this.PantallaGameOver = true;
	            }
	            if(this.PantallaGameOver == true) {
	                entorno.dibujarImagen(gameover, 400, 300, 0, 3.5);
	            }
	        }
	        catch(Exception ex){
	        	//System.out.println("Todavia no se perdio, no carga pantalla game over");
	        }
			try {

			 if(this.salvados >= 15) {
	            	this.PantalladeInicio=true;
	            	Nivel+=1;
	            }
			 if(this.PantalladeInicio == true) {
				 entorno.dibujarImagen(pantalladeinicio, 400 , 300, 0); 
				 entorno.escribirTexto("Victoria", 180, 100);
			 }
			}
			catch(Exception ex){
	        	//System.out.println("Todavia no se perdio, no carga pantalla game over");
	        }
}
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
	
}