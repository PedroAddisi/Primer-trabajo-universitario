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
	//
	Image imfondo;
	Isla isla;
	Isla[] islas = new Isla[15];
	Heroe heroe;
	Gnomo gnomo;
	int tickCount = 0;// Pruebas borrar cuando termine
	int largo = 112;
	int alto = 36;
	char visionHeroe;

	// gravedad para que caiga
	void activarGravedad() {
		// Just a comment
		heroe.y += 0.5;
	}
	//
	boolean colisionPrueba(double x1, double y1, double l1, double a1, double x2, double y2, double l2, double a2) {// no
																													// funca
		return x1 - a1 / 2 <= x2 + a2 / 2  && x1 + a1 / 2 >= x2 - a2 / 2 && y1 - l1 / 2 <= y2 + l2 / 2 && y1 + l1 / 2 >= y2 - l2 / 2;
	}

	Juego() {

		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Navecitas-Grupo Nr.", 800, 600);
		//
		// islas
		islas[0] = new Isla(96, 512, largo, alto, Color.red);
		islas[1] = new Isla(248, 512, largo, alto, Color.red);
		islas[2] = new Isla(400, 512, largo, alto, Color.red);
		islas[3] = new Isla(552, 512, largo, alto, Color.red);
		islas[4] = new Isla(704, 512, largo, alto, Color.red);
		islas[5] = new Isla(172, 406, largo, alto, Color.red);
		islas[6] = new Isla(324, 406, largo, alto, Color.red);
		islas[7] = new Isla(476, 406, largo, alto, Color.red);
		islas[8] = new Isla(628, 406, largo, alto, Color.red);
		islas[9] = new Isla(248, 300, largo, alto, Color.red);
		islas[10] = new Isla(400, 300, largo, alto, Color.red);
		islas[11] = new Isla(552, 300, largo, alto, Color.red);
		islas[12] = new Isla(324, 194, largo, alto, Color.red);
		islas[13] = new Isla(476, 194, largo, alto, Color.red);
		islas[14] = new Isla(400, 88, largo, alto, Color.red);
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
		// aca va ir el if para el salto//
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
			heroe.Salto();

		}
		//Generar gnomo
		gnomo.dibujargnomo(entorno);
		//
		//Camino alazar Gnomo
		//Gravedad Gnomo
		gnomo.colisionConIsla(islas);
		gnomo.y+=1;
		 if (gnomo.tocaConIsla) {
			 System.out.println("COLISION DE GNOMO");
		 }
		//
		boolean estaTocandoHeroe=false;
		for (Isla islas : islas) {// Preguntar el problema no es de la funcion en si creo.
			if (colisionPrueba(islas.x, islas.y, islas.largo, islas.alto, heroe.x, heroe.y, heroe.largo, heroe.alto)) {
				estaTocandoHeroe=true;
				System.out.println("Colision. Tick: " + tickCount + "!");// ESTA EN PRUEBA
			} 
		}
		if(!estaTocandoHeroe) {
			activarGravedad();
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}