package juego;

import entorno.Board;
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
	Gnomo gnomo;
	int tickCount = 0;// Pruebas borrar cuando termine
	int largo = 112;
	int alto = 36;
	char visionHeroe;
	//
	//
	//Colision
	//
	//Generacion de islas
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
	Image imagenFondo;
	Image casa;

	Juego() {

		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Navecitas-Grupo Nr.", 800, 600);
		//
		// islas
		islas=this.generadorIslas();
		//
		// Heroe
		heroe = new Heroe(400, 475, 37, 12, Color.CYAN);
		//
		//Gnomo
		gnomo = new Gnomo(350, 40, 37, 12, Color.green);
		//
		imagenFondo = Herramientas.cargarImagen("elfondo.jpg");
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
		entorno.dibujarImagen(imagenFondo, 400, 300,0,1.8);
		entorno.dibujarImagen(casa, 400,52,0,0.15);
		tickCount += 1;// Pruebas borrar en final
		// Generacion de Texto
		entorno.cambiarFont("Arial", 32, Color.yellow);
		entorno.escribirTexto("Tiempo : ", 0, 25);
		entorno.escribirTexto("Salvados : ", 300, 25);
		entorno.escribirTexto("Muertos : ", 600, 25);
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
	}
				heroe.saltando=false;
				heroe.colisionConIsla(islas);
				heroe.gravedadHeroe(islas);
				//System.out.println("Colision. Tick: " + tickCount + "!");// ESTA EN PRUEBA
		//
		//Generaracion y movimiento de gnomo 
		gnomo.dibujargnomo(entorno);
		gnomo.movimiento(islas);
		gnomo.gravedadGnomos(islas);
		gnomo.colisionConHeroe(heroe);
		//
		System.out.println(heroe.tocaPorArriba(islas));
		if (heroe.tocaPorArriba(islas)) {
			System.out.println("TOCAAAAAAAAAAAA");
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}