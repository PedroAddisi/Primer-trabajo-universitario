package juego;


import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
public class DisparoHeroe {
private double x;
private double y;
private double alto = 10;
private double largo = 10;
private boolean disparo = false;
private double velocidad =3.5;
Image img = Herramientas.cargarImagen("Bola de fuego.gif");
public DisparoHeroe(Heroe h) {
	this.x = h.getX();
	this.y = h.getY();
	}
public void DIbujarDisparo(Entorno entorno) {//funcion encargada de dibujar el objeto
	entorno.dibujarImagen(img, x, y, 0, 0.5);
}
boolean colision(double x1, double y1, double a1, double l1, double x2, double y2, double a2, double l2) {//booleano que pregunta si colisiona con algun objeto
	
	return x1 - l1 / 2 <= x2 + l2 / 2  && x1 + l1 / 2 >= x2 - l2 / 2 && y1 - a1 / 2 <= y2 + a2 / 2 && y1 + a1 / 2 >= y2 - a2 / 2;
	}
public void direcDer() {//valor para que el disparo vaya a la derecha
		this.velocidad = 1.5;
}
public void direcIzq() {//valor para que el disparo vaya a la izquierda
		this.velocidad =- 1.5;
		}
public void mover() {//le otorga movimiento al disparo
	this.x += this.velocidad;
}
public void desaparece(Heroe heroe) {//si el disparo se va de la pantalla desaparece
	if(this.x < 0 || this.x >800) {
		this.disparo = false;
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
public boolean isDisparo() {
	return disparo;
}
public void setDisparo(boolean disparo) {
	this.disparo = disparo;
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
