
package paint;

import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;
import telas.Launcher;

// Classe respónsavel por criar os objetos dos desenhos

public class Desenho extends Funcoes{
       
    /*
        Controle = 0, nenhuma figura selecionada
        Controle = 1, retângulo selecionado
        Controle = 2, círculo selecionado
        Controle = 3, triângulo selecionado
    
    */
    private int largura, altura, diametro;
    private int controle = 0;
    private Color cor = null;
    private Color corBorda = Color.black;
    private boolean fill = false;
  
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
    public int getDiametro() {
        return diametro;
    }

    public void setDiametro(int diametro) {
        this.diametro = diametro;
    }

    public int getControle() {
        return controle;
    }

    public void setControle(int controle) {
        this.controle = controle;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }
    
    
    // Método que cria os objetos e os adiciona no ArrayList
    public Form criarFigura(MouseEvent mouse, JList list){
        Form figura = null;
        boolean desenhou;
        
        // Se for igual a 1, íra criar um retângulo
        if(getControle() == 1){
            if(this.getAltura() != 0 && this.getLargura() != 0){
                figura = new Rectangle(this.getLargura(), this.getAltura(), mouse.getX(), mouse.getY(), this.cor,this.fill);        
            }
        }
            
        // Se for igual a 2, íra criar um círculo
        else if(getControle() == 2){
            if(this.getDiametro() != 0){
                figura = new Circle(this.getDiametro(), mouse.getX(), mouse.getY(), this.cor, this.fill);
            }  
        }
        
        // Se for igual a 3, íra criar um círculo
        else if(getControle() == 3){
            if(this.getAltura() != 0 && this.getLargura() != 0){
                figura = new Circle(this.getDiametro(), mouse.getX(), mouse.getY(), this.cor, this.fill);  
                int[] pontosX = new int[3];
                int[] pontosY = new int[3];

                pontosX[0] = mouse.getX();
                pontosY[0] = mouse.getY() - this.getAltura()*2/3;
                pontosX[1] = pontosX[0] + this.getLargura()/2;
                pontosY[1] = pontosY[0] + this.getAltura();
                pontosX[2] = pontosX[0] - this.getLargura()/2;
                pontosY[2] = pontosY[1];
                figura = new Triangle(pontosX, pontosY, pontosX[0], pontosY[0] + this.getAltura()*2/3 ,this.cor, this.fill);
            }  
        }
        this.addFormas(figura, list);
        return figura;      
    }
}
