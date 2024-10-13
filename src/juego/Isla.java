package juego;

import entorno.Board;
import java.awt.Color;
import java.awt.Image;

import entorno.Herramientas;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Isla {
	double x;
	double alto;
	double largo;
	double y;
	Image img1;
	Color c;
	public Isla(double x, double y, int alto, int largo, Color c) {
		this.x=x;
		this.y=y;
		this.alto=alto;
		this.largo=largo;
		this.c=c;
		//img1=Herramientas.cargarImagen("elfondo.jpg");
	}
	public void dibujarisla(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, alto, largo, 0, c);
		//entorno.dibujarImagen(img1, x, y, angulo);
	}
	}
		
//*for(int i=0;i<islas.length;i++){
//if(i==0) {
//	islas[i]=new Isla(96,512,112,36,Color.red);
//}
//if(i>=1 && i <=4) {
//	islas[i]=new Isla(islas[i-1].x+106,512,112,36,Color.RED);
//}
//if (i==5) {
//	islas[i]=new Isla(islas[1].x+112,islas[1].y-106,112,36,Color.RED);
//}
//if (i>=6 && i<=8) {
//	islas[i]=new Isla(islas[i-1].x+168,islas[1].y-106,112,36,Color.RED);
//}
//if(i==9) {
//	islas[i]=new Isla(islas[6].x+112,islas[1].y-212,112,36,Color.RED);
//}
//	if (i>=10 && i<=11) {
//		islas[i]=new Isla(islas[i-1].x+168,islas[1].y-212,112,36,Color.RED);
//	}
//	if(i==12) {
//		islas[i]=new Isla(islas[10].x+112,islas[1].y-318,112,36,Color.RED);
//}
//	if(i==13) {
//		islas[i]=new Isla(islas[i-1].x+168,islas[1].y-318,112,36,Color.RED);
//}
//	if(i==14) {
//		islas[i]=new Isla(islas[13].x+112,islas[1].y-424,112,36,Color.RED);
//}
//i++;

