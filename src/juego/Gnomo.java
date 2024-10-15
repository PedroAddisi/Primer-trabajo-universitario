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
	String[] porDondeToca = new String[5];
	boolean tocaConHeroe=false;
	int direccion;
	double velocidad =1.1;
	
	public Gnomo(int x,int y, int largo, int alto, Color c) {
		this.x=x;
		this.y=y;
		this.largo=largo;
		this.alto=alto;	
		this.c=c;
		porDondeToca[0]= "Toca parte inferior de la isla";
		porDondeToca[1]= "Toca parte superior de la isla";
		porDondeToca[2]= "Toca parte izquierda de la isla";
		porDondeToca[3]= "Toca parte derecha de la isla";
		porDondeToca[4]= "NO TOCA";
	}
	public void dibujargnomo(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, alto, largo, 0, c);
	}
	public void elegirDireccion() {
		Random derechaOIzquierda = new Random();
		boolean direc = derechaOIzquierda.nextBoolean(); //Genero un booleano random
		if (direc) { //Si es verdadero va para la derecha
			this.direccion= 1;
		}
		else {
			this.direccion= -1;
		}
		//La velocidad se multiplicara por este numero para q camine hacia ese lado
		this.velocidad = this.velocidad*this.direccion;
	}
	public void colisionConHeroe(Heroe h) {
			if(colisionPrueba(h.x, h.y ,h.largo, h.alto , this.x, this.y, this.largo, this.alto)) {
				this.tocaConHeroe=true;
			}
			else{
				this.tocaConHeroe=false;
			}
	}
	public void colisionConIsla(Isla islas[]) {
		for (Isla i : islas) {
			if(colisionPrueba(i.x, i.y ,i.alto, i.largo , this.x, this.y, this.largo, this.alto)) {
				this.tocaConIsla=true;
				return;
			}
		}
		this.tocaConIsla=false;
	}
	public void avanzarGnomo() {
		this.x+=this.velocidad;
	}
	public void gravedadGnomos(Isla[] islas) {
		this.colisionConIsla(islas);
		if (!this.tocaConIsla) {
			this.y += 1;
		}
	}
	public void movimiento (Isla[] islas) {
		if (!this.tocaConIsla) {
			this.gravedadGnomos(islas);
			this.colisionConIsla(islas);
			if(this.tocaConIsla) {
				this.elegirDireccion();
			}
		}
		this.avanzarGnomo();
	}
	boolean colisionPrueba(double x1, double y1, double a1, double l1, double x2, double y2, double l2, double a2) {
		
		return x1 - l1/2 <= x2 + a2 / 2  && x1 + l1 / 2 >= x2 - a2 / 2 && y1 - a1 / 2 <= y2 + l2 / 2 && y1 + a1 / 2 >= y2 - l2 / 2;
	}
//	public int porDondeChoca (double x, double y, double alto, double largo, Isla [] islas) {
//		for(Isla i:islas) {
//			if ((i.x-i.largo/2<=x) && (x<=i.x+i.largo/2) && ((y+alto/2)==i.y-alto/2)){
//				return 0;
//			}
//			if ((i.x-i.largo/2<=x) && (x<=i.x+i.largo/2) && (y-alto/2==i.y+alto/2)){
//				return 1;
//			}
//			if ((i.y-i.alto/2<=y) && (y<=i.y+i.alto/2) && (x+largo/2==i.x-i.largo/2)){
//				return 2;
//			}
//			if ((i.y-i.alto/2<=y) && (y<=i.y+i.alto/2) && (x-largo/2==i.x+i.largo/2)){
//				return 3;
//			}
//		}
//		return 4;
//	}
}
//			if((i.y+i.alto/2<=y) || (y<=i.y-i.alto/2) || (x+ancho/2==i.x-i.largo/2)) {
//				return 1;