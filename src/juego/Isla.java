package juego;

import entorno.Board;
import java.awt.Color;
import java.awt.Image;

import entorno.Herramientas;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Isla {
	double x;
	double alto=36;
	double largo=112; //Estan al reves
	double y;
	Image img1;
	Color c;
	public Isla(double x, double y, Color c) {
		this.x=x;
		this.y=y;
		this.c=c;
		img1=Herramientas.cargarImagen("isla.png");
		}
	public void dibujarisla(Entorno entorno) {
<<<<<<< HEAD
		entorno.dibujarRectangulo(x, y, alto, largo, 0,Color.red);;
		//entorno.dibujarImagen(img1, x, y- 12, 0,0.4);
=======
		entorno.dibujarRectangulo(x, y, largo, alto, 0, c);
		//entorno.dibujarImagen(img1, x, y, angulo);
>>>>>>> 28c2aba956fdd89460fcd00406fdf4dbe5ac84a8
		}
	}
		
