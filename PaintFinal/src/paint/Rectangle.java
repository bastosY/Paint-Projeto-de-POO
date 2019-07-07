
package paint;

import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;

// Classe que possui os atributos de desenho do retângulo

public class Rectangle extends Form {
    private Integer largura, altura;
   
    // Construtor da classe retângulo
    public Rectangle(int largura, int altura, int x, int y, Color cor ,boolean fill) {
        super(cor , fill);
        this.setX(x);
        this.setY(y);
        this.largura = largura;
        this.altura = altura;
    }
    
    // Métodos Getters e Setters
    public int getLargura() {
    	return largura;
    }

    public void setLargura(int largura) {
    	this.largura = largura;
    }

    public int getAltura() {
    	return altura;
    }

    public void setAltura(int altura) {
    	this.altura = altura;
    }

    // Método que desenhar retângulos
    @Override
    public void desenhar(Graphics graficos) throws NullPointerException{ 
        
        graficos.setColor(this.cor);
        if(this.fill == true){
            graficos.setColor(this.cor);
            graficos.fillRect(this.getX()-this.getLargura()/2, this.getY()-this.getAltura()/2, this.getLargura(), this.getAltura());
        }
        graficos.setColor(this.corBorda);
        graficos.drawRect(this.getX()-this.getLargura()/2, this.getY()-this.getAltura()/2, this.getLargura(), this.getAltura());
       
    }

}
