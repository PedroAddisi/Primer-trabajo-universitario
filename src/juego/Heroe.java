package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Heroe {
	private double x = 400;
	private double y = 475;
	private int largo = 37;
	private int alto = 12;
	private boolean enisla = false;
	private double velocidad = 1.5;
	private double gravedad = 1;
	private boolean saltando = false;
	private Image img = Herramientas.cargarImagen("Elheroe.gif");

	public void dibujarheroe(Entorno entorno) {
		entorno.dibujarImagen(img, this.x, this.y,0,0.15);
		}
	boolean colision(double x1, double y1, double a1, double l1, double x2, double y2, double a2, double l2) {
		
		return x1 - l1 / 2 <= x2 + l2 / 2  && x1 + l1 / 2 >= x2 - l2 / 2 && y1 - a1 / 2 <= y2 + a2 / 2 && y1 + a1 / 2 >= y2 - a2 / 2;
	}
	public boolean tocaPorArriba(Isla []islas) {
		for(Isla i:islas) {
			if (this.x + 9 - this.largo / 2 <= i.getX() + i.getAlto()/ 2 && this.x - 9 + this.largo / 2 >= i.getX() - i.getAlto() / 2 && this.y - 18.5 - this.alto / 2 <= i.getY() + i.getLargo() / 2 && this.y + this.alto / 2 >= i.getY() - i.getLargo() / 2) {
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
		if(!this.tocaPorArriba(islas)) {
			this.y -= 2;
			this.x -= 0.2;
		}
	}
	public void SaltoDer(Isla [] islas) {
		if(!this.tocaPorArriba(islas)) {
			this.y -= 2;
			this.x += 0.2;	
		}
	}
	boolean estaTocandoHeroe=false;
	public void colisionConIsla(Isla islas[]) {
		for (Isla i : islas) {
			if(colision(i.getX(), i.getY() ,i.getLargo(), i.getAlto() , this.x, this.y, this.largo, this.alto)) {
				this.estaTocandoHeroe = true;
				return;
			}
		}
		this.estaTocandoHeroe = false;
	}
	public void gravedadHeroe(Isla[] islas) {
		this.colisionConIsla(islas);
		if (!this.estaTocandoHeroe && !this.saltando || this.tocaPorArriba(islas)  ) {
			this.saltando = false;
			this.y += this.gravedad;
		}
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public int getLargo() {
		return largo;
	}
	public void setLargo(int largo) {
		this.largo = largo;
	}
	public int getAlto() {
		return alto;
	}
	public void setAlto(int alto) {
		this.alto = alto;
	}
	public boolean isEnisla() {
		return enisla;
	}
	public void setEnisla(boolean enisla) {
		this.enisla = enisla;
	}
	public double getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}
	public double getGravedad() {
		return gravedad;
	}
	public void setGravedad(double gravedad) {
		this.gravedad = gravedad;
	}
	public boolean isSaltando() {
		return saltando;
	}
	public void setSaltando(boolean saltando) {
		this.saltando = saltando;
	}
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	public boolean isEstaTocandoHeroe() {
		return estaTocandoHeroe;
	}
	public void setEstaTocandoHeroe(boolean estaTocandoHeroe) {
		this.estaTocandoHeroe = estaTocandoHeroe;
	}
	
}
