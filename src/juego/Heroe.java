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
	String[] porDondeToca = new String[5];
	boolean enisla=false;
	double velocidad =1.5;
	double gravedad=0.5;
	public Heroe(double x, double y, int largo, int alto, Color c) {
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
	public void dibujarheroe(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, alto, largo, 0, c);
}
	boolean colisionPrueba(double x1, double y1, double a1, double l1, double x2, double y2, double l2, double a2) {
		
		return x1 - l1 / 2 <= x2 + a2 / 2  && x1 + l1 / 2 >= x2 - a2 / 2 && y1 - a1 / 2 <= y2 + l2 / 2 && y1 + a1 / 2 >= y2 - l2 / 2;
	}
	public void moverAdelante() {
		
		this.x += Math.cos(this.angulo) * velocidad;
		this.y += Math.sin(this.angulo) * velocidad;
	}
	public void moverAtras() {
		this.x += Math.cos(this.angulo) * (-velocidad);
		this.y += Math.sin(this.angulo) * (-velocidad);
	}
	public void Salto() {
		if(this.estaTocandoHeroe ==true) {
			
		this.y-=120;
		}
	}
	boolean estaTocandoHeroe=false;
	public void colisionConIsla(Isla islas[]) {
		for (Isla i : islas) {
			if(colisionPrueba(i.x, i.y ,i.alto, i.largo , this.x, this.y, this.largo, this.alto)) {
				this.estaTocandoHeroe=true;
				return;
			}
		}
		this.estaTocandoHeroe=false;
	}
	public void gravedadHeroe(Isla[] islas) {
		this.colisionConIsla(islas);
		if (!this.estaTocandoHeroe) {
			this.y += this.gravedad;
		}
	}
//	public int porDondeChoca (double x, double y, double alto, double largo, Isla [] islas) {
//		for(Isla i:islas) {
//			if ((i.x-i.largo/2<=x) && (x<=i.x+i.largo/2) && (y+alto/2==i.y-alto/2)){
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
		
