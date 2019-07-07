
package paint;

import java.awt.*;
import javax.swing.JPanel;

// Classe que possui os atributos de desenho do triângulo

public class Triangle extends Form{
    // Pontos que formam o triângulo
    private int[] pontosX;
    private int[] pontosY;
    
    // Construtor da classe
    public Triangle(int[] pontosX, int[] pontosY, int X, int Y, Color cor, boolean fill) {
    	super(cor, fill);
    	this.pontosX = pontosX;
        this.pontosY = pontosY;
        this.setX(X);
        this.setY(Y);
    }
    
    // Métodos Gettes e Setters
    public int[] getPontosX() {
        return pontosX;
    }

    public void setPontosX(int[] pontosX) {
        this.pontosX = pontosX;
    }

    public int[] getPontosY() {
        return pontosY;
    }

    public void setPontosY(int[] pontosY) {
        this.pontosY = pontosY;
    }
  
    public int getLargura(){
        return this.pontosX[1] - this.pontosX[2];
    }
    
    public void setLargura(int largura){
        this.pontosX[1] = largura/2;
        this.pontosX[2] = -largura/2;
    }
   
    public int getAltura(){
        return this.pontosY[1] - this.pontosY[0]; 
    }
    
    public void setAltura(int altura){
        this.pontosY[1] = this.pontosX[0] + altura;
        this.pontosY[2] = this.pontosY[1];
    }
    
    //Método que faz o desenho dos triângulos
    @Override
    public void desenhar(Graphics graficos) throws NullPointerException {
        
        if(this.fill == true){
            graficos.setColor(this.cor);
            graficos.fillPolygon(this.pontosX, this.pontosY, 3);
        }
        graficos.setColor(this.corBorda);
        graficos.drawPolygon(this.pontosX, this.pontosY, 3);
        
    }
}
