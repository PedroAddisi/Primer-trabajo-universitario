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
	double x = 350;
	double y = 40;
	int largo = 37;
	int alto = 12;
	Color c = Color.green;
	boolean tocaConIsla = false;
	boolean tocaConHeroe = false;
	int direccion;
	double velocidad = 0.5;
	boolean caer = false;
	Image img=Herramientas.cargarImagen("Enano final.gif");

	public void dibujargnomo(Entorno entorno) {
		//entorno.dibujarRectangulo(x, y, alto, largo, 0, c);
		entorno.dibujarImagen(img, this.x, this.y, 0,0.2);
	}
	public void elegirDireccion() {
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
	public void colisionConHeroe(Heroe h) {
			if(colisionPrueba(h.x, h.y ,h.largo, h.alto , this.x, this.y, this.largo, this.alto)) {
				this.tocaConHeroe = true;
			}
			else{
				this.tocaConHeroe = false;
			}
	}
	public void colisionConIsla(Isla islas[]) {
		for (Isla i : islas) {
			if(colisionPrueba(i.x, i.y ,i.largo, i.alto , this.x, this.y, this.largo, this.alto)) {
				this.tocaConIsla = true;
				return;
			}
		}
		this.tocaConIsla = false;
	}
	public void avanzarGnomo() {
		if(!caer) {
			this.x += this.velocidad;
		}
	}
	public void caerGnomos(Isla[] islas) {
		this.colisionConIsla(islas);
		if (!this.tocaConIsla) {
			this.y += 1;
			caer = true;
		}
		else {
			caer = false;
		}
	}
	public void movimiento (Isla[] islas) {
		if (!this.tocaConIsla) {
			this.caerGnomos(islas);
			this.colisionConIsla(islas);
			if(this.tocaConIsla) {
				this.elegirDireccion();
			}
		}
		this.avanzarGnomo();
	}
	boolean colisionPrueba(double x1, double y1, double a1, double l1, double x2, double y2, double a2, double l2) {
		
		return x1 - l1 / 2 <= x2 + l2 / 2  && x1 + l1 / 2 >= x2 - l2 / 2 && y1 - a1 / 2 <= y2 + a2 / 2 && y1 + a1 / 2 >= y2 - a2 / 2;
	}
	
}
