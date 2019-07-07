
package paint;

import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.*;


/*
    Classe para realizar a captura dos dados dos Input
*/

public class FuncoesInput extends JPanel{
    
    private String valorString;
    private Integer valorInt = 0;
    
    // Captura os valores digitado nas dimensões
    public int inputValor(JFrame frame, JDialog dialog, JTextField inputValor){
        
        try{
            this.valorString = inputValor.getText();
            this.valorInt = Integer.parseInt(this.valorString);
            if(this.valorInt < 0){
                throw new NumberFormatException();
            }
        }
        // Abertura da janela de aviso
        catch (NumberFormatException error){
            if(dialog.isActive()){
                 JOptionPane.showMessageDialog(dialog, "Valor Inválido!", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
            else if(!dialog.isActive()){
                JOptionPane.showMessageDialog(frame, "Valor Inválido!", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        }
        return this.valorInt;
    }
    
    // Captura o valor digitado nas posições 
    public int inputPos(JDialog dialog, JTextField inputPos){
        try{
            this.valorString = inputPos.getText();
            this.valorInt = Integer.parseInt(this.valorString);
        }
        // Abertura da janela de aviso
        catch (NumberFormatException error){
            JOptionPane.showMessageDialog(dialog, "Valor Inválido", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
        return this.valorInt;
    }
}
