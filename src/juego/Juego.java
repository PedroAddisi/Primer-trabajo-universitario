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
	Image imfondo;
	Isla isla;
	Isla[] islas = new Isla[15];
	Heroe heroe;
	Gnomo gnomo;
	int tickCount = 0;// Pruebas borrar cuando termine
	int largo = 112;
	int alto = 36;
	double gravedad= 0.5;
	char visionHeroe;
	//

	// gravedad para que caiga
	void activarGravedadHeroe() {
		heroe.y +=gravedad ;
	}
	//
	//Colision
	boolean colisionPrueba(double x1, double y1, double l1, double a1, double x2, double y2, double l2, double a2) {
																													
		return x1 - a1 / 2 <= x2 + a2 / 2  && x1 + a1 / 2 >= x2 - a2 / 2 && y1 - l1 / 2 <= y2 + l2 / 2 && y1 + l1 / 2 >= y2 - l2 / 2;
	}
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
		entorno.dibujarRectangulo(400, 52 , 40 ,36 ,0 ,Color.BLUE);
		//
		// GENERACION Y MOVIMIENTO DEL HEROE
		heroe.dibujarheroe(entorno);
		if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			heroe.moverAdelante();
			visionHeroe = entorno.TECLA_DERECHA;
			//preguntaria si en la proxima mover derecha voy a colisionar por dececha entonces no te dejo mover derecha 
		}
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			heroe.moverAtras();
			visionHeroe = entorno.TECLA_IZQUIERDA;
			//preguntaria si en la proxima mover izquierda voy a colisionar por izquierda si es asi no te dejo mover izquierda
		}
		boolean estaTocandoHeroe=false;
		for (Isla islas : islas) {
			if (colisionPrueba(islas.x, islas.y, islas.largo, islas.alto, heroe.x, heroe.y, heroe.largo, heroe.alto)) {
				estaTocandoHeroe=true;
				if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
					heroe.Salto();

				}
				System.out.println("Colision. Tick: " + tickCount + "!");// ESTA EN PRUEBA
			} 
		}
		if(!estaTocandoHeroe) {
			activarGravedadHeroe();
		}
		//Generaracion y movimiento de gnomo 
		gnomo.dibujargnomo(entorno);
		gnomo.movimiento(islas);
		gnomo.gravedadGnomos(islas);
		gnomo.colisionConHeroe(heroe);
		//
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}