package juego;

import java.awt.Color;
import java.util.Random;

import entorno.Entorno;

public class Tortuga {
	double x = 350;
	double y = 40;
	int largo=37;
	int alto=12;
	Color c;
	boolean tocaConIsla=false;
	boolean tocaConHeroe=false;
	boolean tocaConGnomo=false;
	boolean adelanteNoHayIsla=true;
	int direccion;
	double velocidad =0.3;
	
	public Tortuga(int x,int y, int largo, int alto, Color c) {
		this.x=x;
		this.y=y;
		this.largo=largo;
		this.alto=alto;	
		this.c=c;
	}
	public void dibujarTortuga(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, alto, largo, 0, c);
	}
	boolean colision(double x1, double y1, double l1, double a1, double x2, double y2, double l2, double a2) {
		
		return x1 - a1 / 2 <= x2 + a2 / 2  && x1 + a1 / 2 >= x2 - a2 / 2 && y1 - l1 / 2 <= y2 + l2 / 2 && y1 + l1 / 2 >= y2 - l2 / 2;
	}
	
	public void colisionConHeroe(Heroe h) {
		if(colision(h.x, h.y ,h.largo, h.alto , this.x, this.y, this.largo, this.alto)) {
			this.tocaConHeroe=true;
		}
		else{
			this.tocaConHeroe=false;
		}
	}
	
	public void colisionConGnomo(Heroe h) {
		if(colision(h.x, h.y ,h.largo, h.alto , this.x, this.y, this.largo, this.alto)) {
			this.tocaConGnomo=true;
		}
		else{
			this.tocaConGnomo=false;
		}
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
	
	public void colisionConIsla(Isla islas[]) {
		for (Isla i : islas) {
			if(colision(i.x, i.y ,i.largo, i.alto , this.x, this.y, this.largo, this.alto)) {
				this.tocaConIsla=true;
				return;
			}
		}
		this.tocaConIsla=false;
	}
	
	public void avanzar() {
		this.x+=velocidad;
	}
	
	public void colisionAdelante(Isla islas[]) {
		for (Isla i : islas) {
			if(colision(i.x, i.y ,i.largo, i.alto , (this.x + this.velocidad) , this.y, this.largo, this.alto)) {
				this.adelanteNoHayIsla=false;
				return;
			}
			else {
				this.adelanteNoHayIsla=true;
			}
		}
	}
	
	public void movimiento(Isla islas[]) {
		this.colisionConIsla(islas);
		this.colisionAdelante(islas);
		if(!tocaConIsla) {
			y+=1;
		}
		else if(adelanteNoHayIsla) {
			this.elegirDireccion();
			this.avanzar();
		}
		else {
			this.avanzar();
		}
	}
}

