package juego;

import java.awt.Color;
import java.awt.Point;

import entorno.Entorno;

public class Heroe {
	double x;
	double y;
	int largo;
	int alto;
	Color c;
	double angulo;
	boolean enisla=false;
	double velocidad =1.5;
	double gravedad=0.5;
	public Heroe(double x, double y, int largo, int alto, Color c) {
		this.x=x;
		this.y=y;
		this.largo=largo;
		this.alto=alto;	
		this.c=c;
	}
	public void dibujarheroe(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, alto, largo, 0, c);
}
	boolean colisionPrueba(double x1, double y1, double l1, double a1, double x2, double y2, double l2, double a2) {
		
		return x1 - a1 / 2 <= x2 + a2 / 2  && x1 + a1 / 2 >= x2 - a2 / 2 && y1 - l1 / 2 <= y2 + l2 / 2 && y1 + l1 / 2 >= y2 - l2 / 2;
	}
	public void moverAdelante() {
		
		this.x += Math.cos(this.angulo) * velocidad;
		this.y += Math.sin(this.angulo) * velocidad;
	}
	public void moverAtras() {
		this.x += Math.cos(this.angulo) * (-velocidad);
		this.y += Math.sin(this.angulo) * (-velocidad);
	}
	public void Salto() {
		if(this.estaTocandoHeroe ==true) {
			
		this.y-=120;
		}
	}
	boolean estaTocandoHeroe=false;
	public void colisionConIsla(Isla islas[]) {
		for (Isla i : islas) {
			if(colisionPrueba(i.x, i.y ,i.largo, i.alto , this.x, this.y, this.largo, this.alto)) {
				this.estaTocandoHeroe=true;
				return;
			}
		}
		this.estaTocandoHeroe=false;
	}
	public void gravedadHeroe(Isla[] islas) {
		this.colisionConIsla(islas);
		if (!this.estaTocandoHeroe) {
			this.y += this.gravedad;
		}
	}
}
		
