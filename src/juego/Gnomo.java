package juego;

import entorno.Board;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;
import java.util.random.*;

import entorno.Herramientas;
import entorno.Entorno;
import entorno.InterfaceJuego;
public class Gnomo {
	//Defino variable de irrepresentacion
	double x;
	double y;
	int largo;
	int alto;
	Color c;
	boolean tocaConIsla=false;
	boolean tocaConHeroe=false;
	int direccion;
	double velocidad =1.1;

	public Gnomo(int x,int y, int largo, int alto, Color c) {
		this.x=x;
		this.y=y;
		this.largo=largo;
		this.alto=alto;	
		this.c=c;
	}
	public void dibujargnomo(Entorno entorno) {
		entorno.dibujarRectangulo(x,y, alto, largo, 0, c);
	}
	public void elegirDireccion() {
		Random derechaOIzquierda = new Random();
		boolean direc = derechaOIzquierda.nextBoolean(); //Genero un booleano random
		if (direc) { //Si es verdadero va para la derecha
			this.direccion=1;
		}
		else {
			this.direccion=-1;
		}
		//La velocidad se multiplicara por este numero para q camine hacia ese lado
		this.velocidad = this.velocidad*this.direccion;
	}
	public void colisionConHeroe(Heroe h) {
		if (Math.sqrt((this.x-h.x)*(this.x-h.x)+(this.y-h.y)*(this.y-h.y))<=12) {
			this.tocaConHeroe=true;
		}
		else {
			this.tocaConHeroe=false;
		}
	}
	public void colisionConIsla(Isla islas[]) {
		for (Isla i: islas) {
			if ((this.y==i.y) && ((i.x-i.largo/2)<=this.x) && (this.x<=(i.x+i.largo/2))){
				this.tocaConIsla=true;
			}
		}
	}
	public void avanzarGnomo() {
		this.x+=this.velocidad;
	}
	public void gravedadGnomos(Isla[] islas) {
		this.colisionConIsla(islas);
		if (!this.tocaConIsla) {
			this.y += 2;
		}
	}
	public void movimiento(Isla[] islas) {
		if(!this.tocaConIsla) {
			this.gravedadGnomos(islas);
			if(this.tocaConIsla) {
				this.elegirDireccion();
			}
		}
		this.avanzarGnomo();
	}
}
