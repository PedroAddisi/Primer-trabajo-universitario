package juego;
import java.text.DecimalFormat;
import entorno.Board;
import java.awt.Color;
import java.awt.Image;

import javax.sound.sampled.Clip;

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
	Gnomo gnomo;
	DisparoHeroe Disparo;
	Gnomo[] gnomos;
	Tortuga tortuga;
	Double tiempo;
    String tiempomos;
    DecimalFormat decimalFormat =new DecimalFormat("#.#");
	int largo = 112;
	int alto = 36;
	char visionHeroe;
	Boolean PantalladeInicio;
	Image pantalladeinicio;
	Image imagenFondo;
	Image casa;
	int tick=0;
	int salvados=0;
	int muertos=0;
	//
	//Generacion de islas
	public Gnomo[] generadorDeGnomos() {
		Gnomo[]gnomos = new Gnomo[4];
		for(int i=0; i<gnomos.length;i++) {
			if (gnomos[i]==null) {
				gnomos[i]=new Gnomo();
			}
		}
		return gnomos;
	}
	public Isla[] generadorIslas () {
	    Isla[] islas = new Isla[15];
	    int ejeX= 96;
	    int ejeY= 512;
	    int indice= 0;
	    int cantidadDeEspacios= 1;
	    int numDeIslaEnFila= 1;
	    int fila=5;
	    while(fila>0){
	        while (numDeIslaEnFila<=fila){
	            if (numDeIslaEnFila == 1 && indice != 0){
	                ejeY-=106;
	            }
	            else if (indice == 0) {
	                islas[0]= new Isla (ejeX, ejeY, largo, alto, Color.red);
	            }
	            else if (indice !=0) {
	                ejeX+= 152;
	            }
	        islas[indice]= new Isla(ejeX, ejeY, largo, alto, Color.red);
	        indice++;
	        numDeIslaEnFila++;
	        }
	    ejeX=96 + 76 * cantidadDeEspacios;
	    cantidadDeEspacios++;
	    numDeIslaEnFila= 1;
	    fila--;
	    }
	    return islas;
	    }
	//
	Juego() {
		//Tiempo
        tiempo=120.0;
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Navecitas-Grupo Nr.", 800, 600);
		//
		// islas
		islas=this.generadorIslas();
		//
		// Heroe
		heroe = new Heroe();
		Disparo= new DisparoHeroe(heroe.x, heroe.y, 10, 10, Color.RED);
		//
		//Gnomo
		gnomo = new Gnomo();
		gnomos = this.generadorDeGnomos();
		//
		//Tortuga
		tortuga = new Tortuga(350, 40, 37, 12, Color.red);
		//
		imagenFondo = Herramientas.cargarImagen("elfondo.jpg");
		pantalladeinicio=Herramientas.cargarImagen("inicioo.jpg");
		casa = Herramientas.cargarImagen("casa.png");
		// Inicia el juego!
		
		this.entorno.iniciar();
		
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
		tick++;
		entorno.cambiarFont("Arial", 20, Color.WHITE);
		entorno.dibujarImagen(pantalladeinicio, 400 , 300, 0);
		entorno.escribirTexto("Click derecho para empezar", 300, 500);
		entorno.cambiarFont("Arial",40, Color.BLACK);
		entorno.escribirTexto("La carniceria de los Orcos", 180, 100);
		if(entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
			this.PantalladeInicio=false;
		}
			if(this.PantalladeInicio== false) {
		//GENERACION DE TEXTO Y PANTALLA
		tiempomos=decimalFormat.format(tiempo);
		tiempo-=0.015;
		entorno.dibujarImagen(imagenFondo, 400, 300,0,1.8);
		entorno.dibujarImagen(casa, 400,52,0,0.15);
		entorno.cambiarFont("Arial", 32, Color.yellow);
		entorno.escribirTexto("Tiempo : " + tiempomos, 0, 25);
		entorno.escribirTexto("Salvados : " + salvados, 300, 25);
		entorno.escribirTexto("Muertos : " + muertos, 600, 25);
		//
		// GENERACION DE ISLA
		for (Isla islas : islas) {
			islas.dibujarisla(entorno);
		}
		//
		// GENERACION Y MOVIMIENTO DEL HEROE
		heroe.dibujarheroe(entorno);
		if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			heroe.moverAdelante();
			visionHeroe = entorno.TECLA_DERECHA; 
		}
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			heroe.moverAtras();
			visionHeroe = entorno.TECLA_IZQUIERDA;
		}
		if(visionHeroe==entorno.TECLA_IZQUIERDA) {
				if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
					heroe.saltando=true;
					heroe.SaltoIzq(islas);
				}
		}
		if(visionHeroe==entorno.TECLA_DERECHA) {
			if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
				heroe.saltando=true;
				heroe.SaltoDer(islas);
			}
			if(entorno.sePresiono(entorno.TECLA_ESPACIO)) {
				Disparo.DIbujarDisparo(entorno, heroe);
			}
	}
				heroe.saltando=false;
				heroe.colisionConIsla(islas);
				heroe.gravedadHeroe(islas);
		//
		//Generaracion y movimiento de gnomo
		for (int i=0; i<gnomos.length;i++) {
			if(gnomos[i]!=null) {
				gnomos[i].colisionConHeroe(heroe);
				if(gnomos[i].tocaConHeroe) {
					gnomos[i]=null;
					salvados++;
				}
				else if (gnomos[i].y >= 605) {
					gnomos[i]=null;
					muertos++;
				}
				else {
					gnomos[i].dibujargnomo(entorno);
					gnomos[i].movimiento(islas);
					gnomos[i].caerGnomos(islas);
				}
			}
			else {
				gnomos[i] = new Gnomo();
			}
		}
		//Movimiento tortuga
		
		tortuga.dibujarTortuga(entorno);
		tortuga.movimiento(islas);
		}
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}