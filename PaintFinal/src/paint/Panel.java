
package paint;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/*

    Painel em que será pintado na tela

*/


public class Panel extends JPanel{
    
    
    Funcoes figuras;
    
    public Panel() {
      
    }
    public Panel(Funcoes lista){
        figuras = lista;   
    }
    
    
    @Override
    protected void paintComponent(Graphics graficos){
        super.paintComponent(graficos);
        for(Form desenho : figuras.getFiguras()){
               desenho.desenhar(graficos);
        } 
    }  
}
