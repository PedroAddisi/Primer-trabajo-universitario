package juego;

import entorno.Board;
import java.awt.Color;
import java.awt.Image;

import entorno.Herramientas;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Isla {
	double x;
	double alto;
	double largo;
	double y;
	Image img1;
	Color c;
	public Isla(double x, double y, int alto, int largo, Color c) {
		this.x=x;
		this.y=y;
		this.alto=alto;
		this.largo=largo;
		this.c=c;
		img1=Herramientas.cargarImagen("isla.png");
		}
	public void dibujarisla(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, alto, largo, 0,Color.red);;
		//entorno.dibujarImagen(img1, x, y- 12, 0,0.4);
		}
	}
		
