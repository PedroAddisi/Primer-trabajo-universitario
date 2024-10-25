package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
public class DisparoHeroe {
double x;
double y;
double alto = 10;
double largo = 10;
Color c = Color.BLACK;
boolean disparo = false;
double velocidad =1.5;
Image img = Herramientas.cargarImagen("Bola de fuego.gif");
public DisparoHeroe(Heroe h) {
	this.x = h.x;
	this.y = h.y;
	}
public void DIbujarDisparo(Entorno entorno) {
	//entorno.dibujarRectangulo(x, y, alto, largo , 0, c );
	entorno.dibujarImagen(img, x, y, 0, 0.5);
}
boolean colision(double x1, double y1, double a1, double l1, double x2, double y2, double a2, double l2) {
	
	return x1 - l1 / 2 <= x2 + l2 / 2  && x1 + l1 / 2 >= x2 - l2 / 2 && y1 - a1 / 2 <= y2 + a2 / 2 && y1 + a1 / 2 >= y2 - a2 / 2;
	}
public void direcDer() {
		this.velocidad = 1.5;
}
public void direcIzq() {
		this.velocidad =- 1.5;
		}
public void mover() {
	this.x += this.velocidad;
}
public void desaparece(Heroe heroe) {
	if(this.x < 0 || this.x >800) {
		this.disparo = false;
		}
	}
}
