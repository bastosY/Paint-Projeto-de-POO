
package paint;

import java.awt.*;
import javax.swing.JPanel;

// Classe que possui os atributos de desenho de círculos

public class Circle extends Form{ 
    private int diametro;

    // Construtor de circle
    public Circle(int diametro, int x, int y, Color cor, boolean fill) {
        super(cor, fill);
        this.setX(x);
        this.setY(y);
    	this.diametro = diametro;
    }
    
    // Métodos Getters e Setters
    public int getDiametro() {
    	return diametro;
    }

    public void setDiametro(int diametro) {
    	this.diametro = diametro;
    }
  
    // Método de desenhar os círculos
    @Override
    public void desenhar(Graphics graficos) throws NullPointerException {
        if(this.fill == true){
            graficos.setColor(this.cor);
            graficos.fillOval(getX() - getDiametro()/2, getY() - getDiametro()/2, getDiametro(), getDiametro());
        }
        
        graficos.setColor(this.corBorda);
        graficos.drawOval(getX() - getDiametro()/2, getY() - getDiametro()/2, getDiametro(), getDiametro());
        
    }
    
}
