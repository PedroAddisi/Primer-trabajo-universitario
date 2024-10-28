package juego;


import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Tortuga {
	private double x;
	private double y= 10;
	private int largo = 37;
	private int alto = 12;
	private boolean tocaConIsla = false;
	private boolean tocaConHeroe = false;
	private boolean tocaConGnomo = false;
	private boolean adelanteNoHayIsla = false;
	private boolean tocaConDisparo = false; 
	private Isla islaToca;
	private int direccion=-1;
	private double velocidad = 0.3;
	private Image img = Herramientas.cargarImagen("OrcoFinal.gif");
	private int islasTortugas[]={0,4,9,11};
	private Random random= new Random();
	private boolean IzqODer;
	private int azar;
	
	public Tortuga(Isla islas[], Tortuga tortugas[]) {
		azar=(int)(Math.random()*4);
		IzqODer = random.nextBoolean();
		this.elegirAzar(tortugas);	
		if (IzqODer == false) {
			this.x=islas[islasTortugas[azar]].getX()-islas[islasTortugas[azar]].getAlto()/4;
		}
		if (IzqODer == true) {
			this.x=islas[islasTortugas[azar]].getX()+islas[islasTortugas[azar]].getAlto()/4;
		}
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
		if(colision(h.getX(), h.getY() , h.getLargo(), h.getAlto() , this.x, this.y, this.largo, this.alto)) {
			this.tocaConHeroe = true;
		}
		else{
			this.tocaConHeroe = false;
		}
	}
	
	public void colisionConGnomo(Heroe h) {
		if(colision(h.getX(), h.getY() , h.getLargo(), h.getAlto() , this.x, this.y, this.largo, this.alto)) {
			this.tocaConGnomo = true;
		}
		else{
			this.tocaConGnomo = false;
		}
	}	
	public void colisionConIsla(Isla islas[]) {
		for (Isla i : islas) {
			if(colision(i.getX(), i.getY() ,i.getLargo(), i.getAlto() , this.x, this.y, this.largo, this.alto)) {
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
	public void colisionAdelante() {
		if(islaToca !=null) {
			if (!colision(this.islaToca.getX(), this.islaToca.getY(),this.islaToca.getLargo(), this.islaToca.getAlto(), this.x+1*this.direccion, this.y, this.largo,this.alto)) {
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
		if(colision(t.getX(), t.getY(), t.getLargo(), t.getAlto(), this.x, this.y, this.largo, this.alto)) {
			this.tocaConDisparo=true;
		}
		else {
			this.tocaConDisparo=false;
		}
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		if(this.x > 800 || this.x < 0) {
			 System.out.println("Orco fuera de mapa");
			 return;	
			}
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		if(this.y > 610 || this.y < 0) {
			 System.out.println("orco fuera de mapa");
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
	public boolean isTocaConGnomo() {
		return tocaConGnomo;
	}
	public void setTocaConGnomo(boolean tocaConGnomo) {
		this.tocaConGnomo = tocaConGnomo;
	}
	public boolean isAdelanteNoHayIsla() {
		return adelanteNoHayIsla;
	}
	public void setAdelanteNoHayIsla(boolean adelanteNoHayIsla) {
		this.adelanteNoHayIsla = adelanteNoHayIsla;
	}
	public boolean isTocaConDisparo() {
		return tocaConDisparo;
	}
	public void setTocaConDisparo(boolean tocaConDisparo) {
		this.tocaConDisparo = tocaConDisparo;
	}
	public Isla getIslaToca() {
		return islaToca;
	}
	public void setIslaToca(Isla islaToca) {
		this.islaToca = islaToca;
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
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	public int[] getIslasTortugas() {
		return islasTortugas;
	}
	public void setIslasTortugas(int[] islasTortugas) {
		this.islasTortugas = islasTortugas;
	}
	public Random getRandom() {
		return random;
	}
	public void setRandom(Random random) {
		this.random = random;
	}
	public boolean isIzqODer() {
		return IzqODer;
	}
	public void setIzqODer(boolean izqODer) {
		IzqODer = izqODer;
	}
	public int getAzar() {
		return azar;
	}
	public void setAzar(int azar) {
		this.azar = azar;
	}
}

