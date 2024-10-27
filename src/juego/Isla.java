package juego;

import java.awt.Image;

import entorno.Herramientas;
import entorno.Entorno;

public class Isla {
	private double x;
	private double alto;
	private double largo;
	private double y;
	private Image img1;
	public Isla(double x, double y, int alto, int largo) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.largo = largo;
		img1 = Herramientas.cargarImagen("isla.png");
		}
	public void dibujarisla(Entorno entorno) {
		entorno.dibujarImagen(img1, x, y- 12, 0,0.4);
		}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getAlto() {
		return alto;
	}
	public void setAlto(double alto) {
		this.alto = alto;
	}
	public double getLargo() {
		return largo;
	}
	public void setLargo(double largo) {
		this.largo = largo;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public Image getImg1() {
		return img1;
	}
	public void setImg1(Image img1) {
		this.img1 = img1;
	}
	
}
		
