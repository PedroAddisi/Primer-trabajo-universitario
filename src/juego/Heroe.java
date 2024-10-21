package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;

import entorno.Entorno;
import entorno.Herramientas;

public class Heroe {
	double x = 400;
	double y = 475;
	int largo = 37;
	int alto = 12;
	Color c = Color.CYAN;
	boolean enisla = false;
	double velocidad = 1.5;
	double gravedad = 1;
	Boolean saltando = false;
	Image img=Herramientas.cargarImagen("Elheroe.gif");

	public void dibujarheroe(Entorno entorno) {
		//entorno.dibujarRectangulo(x, y, alto, largo, 0, c);
		entorno.dibujarImagen(img, this.x, this.y, 0,0.15);
}
	boolean colision(double x1, double y1, double a1, double l1, double x2, double y2, double a2, double l2) {
		
		return x1 - l1 / 2 <= x2 + l2 / 2  && x1 + l1 / 2 >= x2 - l2 / 2 && y1 - a1 / 2 <= y2 + a2 / 2 && y1 + a1 / 2 >= y2 - a2 / 2;
	}
	public boolean tocaPorArriba(Isla []islas) {
		for(Isla i:islas) {
			if (this.x + 9 - this.largo / 2 <= i.x + i.alto/ 2 && this.x - 9 + this.largo / 2 >= i.x - i.alto / 2 && this.y - 18.5 - this.alto / 2 <= i.y + i.largo / 2 && this.y + this.alto / 2 >= i.y - i.largo / 2) {
				return true;
			}
		}
	return false;
	}
	public void moverAdelante() {
		if(!this.saltando) {
		this.x += 1 * velocidad;
	}
	}
	public void moverAtras() {
		if(!this.saltando) {
			this.x += 1 * (-velocidad);
	}
	}
	public void SaltoIzq(Isla [] islas) {
		if(!this.tocaPorArriba(islas))
			this.y -= 2;
		this.x -= 0.2;
	}
	public void SaltoDer(Isla [] islas) {
		if(!this.tocaPorArriba(islas))
			this.y -= 2;
		this.x += 0.2;
	}
	boolean estaTocandoHeroe=false;
	public void colisionConIsla(Isla islas[]) {
		for (Isla i : islas) {
			if(colision(i.x, i.y ,i.largo, i.alto , this.x, this.y, this.largo, this.alto)) {
				this.estaTocandoHeroe = true;
				return;
			}
		}
		this.estaTocandoHeroe = false;
	}
	public void gravedadHeroe(Isla[] islas) {
		this.colisionConIsla(islas);
		if (!this.estaTocandoHeroe && !this.saltando || this.tocaPorArriba(islas)  ) {
			this.saltando = true;
			this.y += this.gravedad;
		}
	}
	
}
