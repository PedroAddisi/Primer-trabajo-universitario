package juego;

import java.awt.Color;
import java.awt.Point;
import entorno.Entorno;
public class DisparoHeroe {
double x;
double y;
double alto;
double largo;
Color c;
boolean disparo =true;
public DisparoHeroe(double x, double y, double alto,double largo, Color c) {
	this.x=x;
	this.y=y;
	this.alto=alto;
	this.largo=largo;
	this.c=c;
	}
boolean colision(double x1, double y1, double l1, double a1, double x2, double y2, double l2, double a2) {
	
	return x1 - a1 / 2 <= x2 + a2 / 2  && x1 + a1 / 2 >= x2 - a2 / 2 && y1 - l1 / 2 <= y2 + l2 / 2 && y1 + l1 / 2 >= y2 - l2 / 2;
	}
public void GenerarDisparo(Entorno entorno, Heroe heroe) {
	entorno.dibujarRectangulo(heroe.x, heroe.y, 10, 10 , 0, Color.BLACK );
}
public void fuegoDer() {
	if(this.disparo== true) {
		this.x+=1;
	}
	if(this.x >= 600) {
		
	}
}
public void fuegoIzq() {
	if(this.disparo== true) {
		this.x-=1;
	}
	if(this.x <= 600) {
		
	}
}
		}
