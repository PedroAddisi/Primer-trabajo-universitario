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
	Isla[] islas=new Isla[15];
	Heroe heroe;

	//
	void gravedad() {
		heroe.y+=0.5;
	}
	boolean colision(double x1, double y1,double x2, double y2, double dist) {
		return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)< dist*dist;
		}
	boolean colisionprueba(double x1, double y1,double A1,double L1,double x2, double y2,double A2,double L2) {
		if(x1 + L1 >= x2 && x1 <= x2 + L2 && y1 + A1 >= y2 && y1 <= y2 + A2) {
			return true;
		}
		return false;
	}
	
	Juego()
	{
		
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Navecitas-Grupo Nr.", 800, 600);
		// Inicializar lo que haga falta para el juego
		// ..
	islas[0]=new Isla(96,512,112,36,Color.red);
	islas[1]=new Isla(248,512,112,36,Color.red);
	islas[2]=new Isla(400,512,112,36,Color.red);
	islas[3]=new Isla(552,512,112,36,Color.red);
	islas[4]=new Isla(704,512,112,36,Color.red);
	islas[5]=new Isla(172,406,112,36,Color.red);
	islas[6]=new Isla(324,406,112,36,Color.red);
	islas[7]=new Isla(476,406,112,36,Color.red);
	islas[8]=new Isla(628,406,112,36,Color.red);
	islas[9]=new Isla(248,300,112,36,Color.red);
	islas[10]=new Isla(400,300,112,36,Color.red);
	islas[11]=new Isla(552,300,112,36,Color.red);
	islas[12]=new Isla(324,194,112,36,Color.red);
	islas[13]=new Isla(476,194,112,36,Color.red);
	islas[14]=new Isla(400,88,112,36,Color.red);
	heroe=new Heroe(400,475,37,12,Color.CYAN);
		//imfondo=Herramientas.cargarImagen("elfondo.jpg");
		
	
		//img= Herramientas.cargarImagen("elfondo.jpg");

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		//Generacion de Texto
		entorno.cambiarFont("Arial", 32, Color.yellow);
		entorno.escribirTexto("Tiempo : " , 0, 25);
		entorno.escribirTexto("Salvados : ", 300, 25);
		entorno.escribirTexto("Muertos : ", 600, 25);
		// Procesamiento de un instante de tiempo
		// GENERACION DE ISLA
		for(Isla islas:islas) {
			islas.dibujarisla(entorno);
		}
		//
		//GENERACION Y MOVIMIENTO DEL HEROE
		heroe.dibujarheroe(entorno);
		if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			heroe.moverAdelante();
		}
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			heroe.moverAtras();
		}
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
			heroe.Salto();
		}
		for(Isla islas:islas) {//Preguntar 
			if(colisionprueba(heroe.x,heroe.y,heroe.alto,heroe.largo,islas.x,islas.y,islas.alto,islas.largo)){
			System.out.println("Colision!!!!");
		}
			}
		//gravedad();
	}
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}