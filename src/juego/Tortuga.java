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
	boolean adelanteNoHayIsla = true;
	boolean tocaConDisparo = false; 
	int direccion;
	double velocidad = 0.3;
	Image img = Herramientas.cargarImagen("OrcoFinal.gif");
	
	public Tortuga() {
		Random random = new Random();
		boolean azar = random.nextBoolean();
		if(azar) {
			this.x = Math.random()*(305)+40;
		}
		else {
			this.x = Math.random()*(305)+456;
		}
		
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
	
	public void elegirDireccionAlazar() {
		Random derechaOIzquierda = new Random();
		boolean direc = derechaOIzquierda.nextBoolean(); //Genero un booleano random
		if (direc) { //Si es verdadero va para la derecha
			this.direccion = 1;
		}
		else {
			this.direccion = -1;
		}
		
		//La velocidad se multiplicara por este numero para q camine hacia ese lado
		this.velocidad = this.velocidad*this.direccion;
	}
	
	public void colisionConIsla(Isla islas[]) {
		for (Isla i : islas) {
			if(colision(i.x, i.y ,i.largo, i.alto , this.x, this.y, this.largo, this.alto)) {
				this.tocaConIsla = true;
				return;
			}
		}
		this.tocaConIsla  = false;
	}
	
	public void avanzar() {
		this.x += this.velocidad;
	}
	
	public void colisionAdelante(Isla islas[]) {
		for (Isla i : islas) {
			if(colision(i.x, i.y ,i.largo, i.alto , (this.x + this.velocidad) , this.y, this.largo, this.alto)) {
				this.adelanteNoHayIsla = false;
				return;
			}
			else {
				this.adelanteNoHayIsla = true;
			}
		}
	}
	public void cambiarDireccion() {
		this.direccion = this.direccion*-1;
		this.velocidad = this.velocidad*this.direccion;
	}
	public void movimiento(Isla islas[]) {
		this.colisionConIsla(islas);
		this.colisionAdelante(islas);
		if(!tocaConIsla) {
			y += 1;
			this.elegirDireccionAlazar();
		}
		else if(adelanteNoHayIsla) {
			this.cambiarDireccion();
			this.avanzar();
		}
		else {
			this.avanzar();
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
			this.x = 400;//aca en vez de 400 hacerla null y joya
		}
	}
}

