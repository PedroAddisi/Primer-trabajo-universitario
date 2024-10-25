package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Nave {
	double x = 400;
	double y = 590;
	double largo = 10;
	double alto = 52;
	boolean tocaConGnomo;
	boolean tocaConHeroe;
	double velocidad =1.5;
	Image img=Herramientas.cargarImagen("Nave.png");
	public void dibujarnave(Entorno entorno) {
		entorno.dibujarImagen(img, x, y, 0, 0.05);
	
	}
	boolean colision(double x1, double y1, double a1, double l1, double x2, double y2, double a2, double l2) {
		
		return x1 - l1 / 2 <= x2 + l2 / 2  && x1 + l1 / 2 >= x2 - l2 / 2 && y1 - a1 / 2 <= y2 + a2 / 2 && y1 + a1 / 2 >= y2 - a2 / 2;
	}
	public void colisionConGnomo(Gnomo h) {
	if(colision(h.x, h.y , h.largo, h.alto , this.x, this.y, this.largo, this.alto)) {
		this.tocaConGnomo = true;
	}
	else{
		this.tocaConGnomo = false;
	}
	
}
	public void colisionConHeroe(Heroe h) {
	if(colision(h.x, h.y , h.largo, h.alto , this.x, this.y, this.largo, this.alto)) {
		this.tocaConHeroe = true;
	}
	else{
		this.tocaConHeroe = false;
	}
}
	public void moverAdelante(int x) {
	this.x += 1 * velocidad;
	}
	public void moverAtras() {
		this.x += 1 * (-velocidad);
	}

}
