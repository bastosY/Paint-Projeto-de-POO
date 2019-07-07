
package paint;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.JPanel;



public abstract class Form implements Serializable{

        // Classe mãe de Rectangle, Circle, Triangle
        
	protected int x, y;
	protected double area;
        protected Color cor;
        protected Color corBorda = Color.black;
        protected boolean fill;
        
        // Construtor de form
	public Form(Color cor ,boolean fill) {
            this.cor = cor;
            //this.corBorda = corBorda;
            this.fill = fill;
	}

         // Métodos Getters e Setters
        public boolean isFill() {
            return fill;
        }

        public void setFill(boolean fill) {
            this.fill = fill;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

	public double getArea() {
            return area;
	}

	public void setArea(Double area) {
            this.area = area;
	}
        
        public void setCor(Color cor) {
            this.cor = cor;
        }

        public Color getCor() {
            return cor;
        }
        
        public Color getCorBorda() {
            return corBorda;
        }      

        public void setCorBorda(Color corBorda) {
            this.corBorda = corBorda;
        }
        
        // Métodos abstrator de desenho
        public abstract void desenhar(Graphics graficos);
}

