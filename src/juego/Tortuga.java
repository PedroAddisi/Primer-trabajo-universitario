package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Tortuga {
	double x;
	double y= 10;
	int largo = 37;
	int alto = 12;
	boolean tocaConIsla = false;
	boolean tocaConHeroe = false;
	boolean tocaConGnomo = false;
	boolean adelanteNoHayIsla = false;
	boolean tocaConDisparo = false; 
	Isla islaToca;
	int direccion=-1;
	double velocidad = 0.3;
	Image img = Herramientas.cargarImagen("OrcoFinal.gif");
	int islasTortugas[]={0,4,9,11};
	Random random= new Random();
	boolean IzqODer;
	int xTortuga;
	int azar;
	
	public Tortuga(Isla islas[], Tortuga tortugas[]) {
		azar=(int)(Math.random()*4);
		IzqODer = random.nextBoolean();
		this.elegirAzar(tortugas);	
		if (IzqODer == false) {
			this.x=Math.random()*(islas[islasTortugas[azar]].alto/2+1)+(islas[islasTortugas[azar]].x);
		}
		if (IzqODer == true) {
			this.x=Math.random()*(islas[islasTortugas[azar]].alto/2+1)+(islas[islasTortugas[azar]].x-islas[islasTortugas[azar]].alto/2);
		}
		System.out.println(azar);
		System.out.println(IzqODer);
	}
	public void elegirAzar(Tortuga tortugas[]) {
		for (Tortuga t: tortugas) {
			if(t !=null) {
			if ((t.azar == this.azar && this.IzqODer == t.IzqODer)) {
				this.azar= (int) (Math.random()*4);
				this.IzqODer= random.nextBoolean();
				this.elegirAzar(tortugas);
				}
			}
		}
		return;
	}
	public void dibujarTortuga(Entorno entorno) {
		entorno.dibujarImagen(img, this.x, this.y, 0, 0.18);
	}
	boolean colision(double x1, double y1, double a1, double l1, double x2, double y2, double a2, double l2) {
		
		return x1 - l1 / 2 <= x2 + l2 / 2  && x1 + l1 / 2 >= x2 - l2 / 2 && y1 - a1 / 2 <= y2 + a2 / 2 && y1 + a1 / 2 >= y2 - a2 / 2;
	}
	
	public void colisionConHeroe(Heroe h) {
		if(colision(h.x, h.y , h.largo, h.alto , this.x, this.y, this.largo, this.alto)) {
			this.tocaConHeroe = true;
		}
		else{
			this.tocaConHeroe = false;
		}
	}
	
	public void colisionConGnomo(Heroe h) {
		if(colision(h.x, h.y , h.largo, h.alto , this.x, this.y, this.largo, this.alto)) {
			this.tocaConGnomo = true;
		}
		else{
			this.tocaConGnomo = false;
		}
	}	
	public void colisionConIsla(Isla islas[]) {
		for (Isla i : islas) {
			if(colision(i.x, i.y ,i.largo, i.alto , this.x, this.y, this.largo, this.alto)) {
				this.tocaConIsla = true;
				islaToca=i;
				return;
			}
		}
		this.tocaConIsla  = false;
	}
	
	public void avanzar() {
		this.x += this.velocidad*this.direccion;
	}
	
//	public void colisionAdelante(Isla islas[]) {
//		for (Isla i : islas) {
//			if(colision(i.x, i.y ,i.largo, i.alto , (this.x + this.velocidad) , this.y, this.largo, this.alto)) {
//				this.adelanteNoHayIsla = true;
//				return;
//			}
//			else {
//				this.adelanteNoHayIsla = false;
//			}
//		}
	public void colisionAdelante() {
		if(islaToca !=null) {
			if (!colision(this.islaToca.x, this.islaToca.y,this.islaToca.largo, this.islaToca.alto, this.x+1*this.direccion, this.y, this.largo,this.alto)) {
				this.adelanteNoHayIsla = true;
				return;
			}
			else {
				this.adelanteNoHayIsla = false;
			}
		}
	}
	public void cambiarDireccion() {
		this.direccion = this.direccion*-1;
	}
	public void movimiento(Isla islas[]) {
		this.colisionConIsla(islas);
		if(!tocaConIsla) {
			y += 1;
		}else {
			this.avanzar();
		}
		if(adelanteNoHayIsla) {
			this.cambiarDireccion();
		}
	}
	public void colisionConDisparo(DisparoHeroe t) {
		if(colision(t.x, t.y, t.largo, t.alto, this.x, this.y, this.largo, this.alto)) {
			this.tocaConDisparo=true;
		}
		else {
			this.tocaConDisparo=false;
		}
		if(this.tocaConDisparo == true) {
			t.disparo=false;
		}
	}
}

