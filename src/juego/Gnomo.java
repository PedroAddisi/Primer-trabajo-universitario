package juego;

import java.awt.Image;
import java.util.Random;

import entorno.Herramientas;
import entorno.Entorno;
public class Gnomo {
	private double x = 350;
	private double y = 40;
	private int largo = 37;
	private int alto = 12;
	private boolean tocaConIsla = false;
	private boolean tocaConHeroe = false;
	private boolean tocaConTortuga = false;
	private int direccion;
	private double velocidad = 0.5;
	private boolean caer = false;
	private Image img = Herramientas.cargarImagen("Enano final.gif");

	public void dibujargnomo(Entorno entorno) {
		entorno.dibujarImagen(img, this.x, this.y, 0, 0.2);
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
			if(colisionPrueba(h.getX(), h.getY() , h.getLargo(), h.getAlto() , this.x, this.y, this.largo, this.alto)) {
				this.tocaConHeroe = true;
			}
			else{
				this.tocaConHeroe = false;
			}
	}
	public void colisionConTortuga(Tortuga t) {
		if(colisionPrueba(t.getX(), t.getY(), t.getLargo(), t.getAlto(), this.x, this.y, this.largo, this.alto)) {
			this.tocaConTortuga = true;
		}
		else {
			this.tocaConTortuga = false;
		}
	}
	public void colisionConIsla(Isla islas[]) {
		for (Isla i : islas) {
			if(colisionPrueba(i.getX(), i.getY() ,i.getLargo(), i.getAlto() , this.x, this.y, this.largo, this.alto)) {
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
	public double getX() {
		return x;
	}
	public void setX(double x) {
		if(this.x > 800 || this.x < 0) {
			 System.out.println("Gnomo fuera de mapa");
			 return;
			}
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		if(this.y > 610 || this.y < 0) {
			 System.out.println("gnomo fuera de mapa");
			 return;
		}
		this.y = y;
	}
	public int getLargo() {
		return largo;
	}
	public void setLargo(int largo) {
		this.largo = largo;
	}
	public int getAlto() {
		return alto;
	}
	public void setAlto(int alto) {
		this.alto = alto;
	}
	public boolean isTocaConIsla() {
		return tocaConIsla;
	}
	public void setTocaConIsla(boolean tocaConIsla) {
		this.tocaConIsla = tocaConIsla;
	}
	public boolean isTocaConHeroe() {
		return tocaConHeroe;
	}
	public void setTocaConHeroe(boolean tocaConHeroe) {
		this.tocaConHeroe = tocaConHeroe;
	}
	public boolean isTocaConTortuga() {
		return tocaConTortuga;
	}
	public void setTocaConTortuga(boolean tocaConTortuga) {
		this.tocaConTortuga = tocaConTortuga;
	}
	public int getDireccion() {
		return direccion;
	}
	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}
	public double getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}
	public boolean isCaer() {
		return caer;
	}
	public void setCaer(boolean caer) {
		this.caer = caer;
	}
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	
}
