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
	public Heroe(double x,double y,int largo,int alto,Color c) {
		this.x=x;
		this.y=y;
		this.largo=largo;
		this.alto=alto;	
		this.c=c;
	}
	public void dibujarheroe(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, alto, largo, 0, c);
}
	public void moverAdelante() {
		
		this.x += Math.cos(this.angulo)*1.5;
		this.y += Math.sin(this.angulo)*1.5;
		if(this.x > 900) {
			this.x=-100;
		}
		if(this.x < -100) {
			this.x=900;
		}
		if(this.y > 650) {
			this.y=-50;
		}
		if(this.y < -50) {
			this.y=650;
		}
	}
	public void moverAtras() {
		this.x += Math.cos(this.angulo)*(-1.5);
		this.y += Math.sin(this.angulo)*(-1.5);
		if(this.x > 900) {
			this.x=-100;
		}
		if(this.x < -100) {
			this.x=900;
		}
		if(this.y > 650) {
			this.y=-50;
		}
		if(this.y < -50) {
			this.y=650;
		}
	}
	public void Salto() {//ver como hacer para que cuando se aprete saltar no se logre hacer de nuevo.
		this.y-=10;
		}
	
	
	}
		
