package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Nave {
	private double x = 400;
	private double y = 590;
	private double largo = 10;
	private double alto = 52;
	private boolean tocaConGnomo;
	private boolean tocaConHeroe;
	private double velocidad =1.5;
	private Image img=Herramientas.cargarImagen("Nave.png");
	public void dibujarnave(Entorno entorno) {//Dibuja el objeto en pantalla
		entorno.dibujarImagen(img, x, y, 0, 0.05);
	
	}
	boolean colision(double x1, double y1, double a1, double l1, double x2, double y2, double a2, double l2) {//booleano que pregunta si colisiona con algun objeto
		
		return x1 - l1 / 2 <= x2 + l2 / 2  && x1 + l1 / 2 >= x2 - l2 / 2 && y1 - a1 / 2 <= y2 + a2 / 2 && y1 + a1 / 2 >= y2 - a2 / 2;
	}
	public void colisionConGnomo(Gnomo g) {//funcion que pregunta si el objeto colisiono con gnomo
	if(colision(g.getX(), g.getY() , g.getLargo(), g.getAlto() , this.x, this.y, this.largo, this.alto)) {
		this.tocaConGnomo = true;
	}
	else{
		this.tocaConGnomo = false;
	}
	
}
	public void colisionConHeroe(Heroe h) {//funcion que pregunta si el objeto colisiono con heroe
	if(colision(h.getX(), h.getY() , h.getLargo(), h.getAlto() , this.x, this.y, this.largo, this.alto)) {
		this.tocaConHeroe = true;
	}
	else{
		this.tocaConHeroe = false;
	}
}
	public void moverAdelante(int x) {//movimiento de el objeto por derecha
	this.x += 1 * velocidad;
	}
	public void moverAtras() {//movimiento de el objeto por izquierda
		this.x += 1 * (-velocidad);
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
	public double getLargo() {
		return largo;
	}
	public void setLargo(double largo) {
		this.largo = largo;
	}
	public double getAlto() {
		return alto;
	}
	public void setAlto(double alto) {
		this.alto = alto;
	}
	public boolean isTocaConGnomo() {
		return tocaConGnomo;
	}
	public void setTocaConGnomo(boolean tocaConGnomo) {
		this.tocaConGnomo = tocaConGnomo;
	}
	public boolean isTocaConHeroe() {
		return tocaConHeroe;
	}
	public void setTocaConHeroe(boolean tocaConHeroe) {
		this.tocaConHeroe = tocaConHeroe;
	}
	public double getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
}
