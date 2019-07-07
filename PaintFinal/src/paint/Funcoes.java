
package paint;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class Funcoes {
    
    private int numbRec = 1;
    private int numbCir = 1;
    private int numbTri = 1;
    private DefaultListModel model = new DefaultListModel();
    private ArrayList<Form> figuras = new ArrayList<>();
    private boolean carregar = true;
    
    // Métodos Getters e Setters
    public ArrayList<Form> getFiguras() {
        return figuras;
    }

    public void setFiguras(ArrayList<Form> figuras) {
        this.figuras = figuras;
    }

    public int getNumbRec() {
        return numbRec;
    }

    public void setNumbRec(int numbRec) {
        this.numbRec = numbRec;
    }

    public int getNumbCir() {
        return numbCir;
    }

    public void setNumbCir(int numbCir) {
        this.numbCir = numbCir;
    }

    public int getNumbTri() {
        return numbTri;
    }

    public void setNumbTri(int numbTri) {
        this.numbTri = numbTri;
    }

    public DefaultListModel getModel() {
        return model;
    }

    public void setModel(DefaultListModel model) {
        this.model = model;
    }

    public boolean isCarregar() {
        return carregar;
    }

    public void setCarregar(boolean carregar) {
        this.carregar = carregar;
    }
    
    
    
    // Métodos que adicionam e removem as figuras
    
    // Adiciona a figura ao ArrayList e ao JList
    public void addFormas(Form desenho, JList listaFormas){
       
        if(desenho instanceof Rectangle){
            this.model.addElement("Retângulo "+this.numbRec);
            listaFormas.setModel(this.model);
            this.numbRec++;
        }
        else if(desenho instanceof Circle){
            this.model.addElement("Círculo "+this.numbCir);
            listaFormas.setModel(this.model);
            this.numbCir++;
        }
        
        else if(desenho instanceof Triangle){
            this.model.addElement("Triângulo "+this.numbTri);
            listaFormas.setModel(this.model);
            this.numbTri++;
        }   
        this.figuras.add(desenho);  
    }
    
    // Apaga uma desenho
    public void rmForma(JPanel panel, Graphics graficos, JList listaFormas, int index){
        this.model.remove(index);
        listaFormas.setModel(this.model);
        this.figuras.remove(index);
        this.carregarLista(panel, listaFormas, graficos);
       
    }
   
    // Reinicia a tela
    public void rmTela(JPanel panel, JList listaFormas){
        this.carregar = true;
        this.model.removeAllElements();
        listaFormas.setModel(this.model);
        this.figuras.removeAll(this.figuras);
        this.numbRec = 1;
        this.numbCir = 1;
        this.numbTri = 1;
        panel.repaint();
        
    }
   
    // Métodos de salvar e carregar os desenhos
    
    // Salva as figuras em um arquivo ".dat"
    public void salvarArquivo(JPanel panel){
        this.carregar = true;
        try {
            if(this.figuras.size() == 0){
                throw new Exception();
            }
            FileOutputStream arquivo = new FileOutputStream("banco.dat");
            ObjectOutputStream salvar = new ObjectOutputStream(arquivo);
            
            salvar.writeObject(this.figuras);
            JOptionPane.showMessageDialog(panel, "Arquivo salvo!", "Aviso", JOptionPane.WARNING_MESSAGE);
            arquivo.close();
            salvar.close();  
        } 
        catch (FileNotFoundException error) {
            JOptionPane.showMessageDialog(panel, "Arquivo não encontrado!", "ERROR", JOptionPane.WARNING_MESSAGE);
        } 
        catch (IOException error) {
            JOptionPane.showMessageDialog(panel, "Arquivo corrompido!", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
        catch (Exception error){
            JOptionPane.showMessageDialog(panel, "Não possui figuras!", "ERROR", JOptionPane.WARNING_MESSAGE);
        }          
    } 
    
    // Preenche o JList e carrega os desenhos
    public void carregarLista(JPanel panel, JList listaFormas, Graphics graficos){
        
        this.model.removeAllElements();
        listaFormas.setModel(this.model);
        this.numbRec = 1;
        this.numbCir = 1;
        this.numbTri = 1;
        
        for(Form desenho : this.figuras){
            
            if(desenho instanceof Rectangle){
                this.model.addElement("Retângulo "+this.numbRec);
                listaFormas.setModel(this.model);
                this.numbRec++;
            }
            else if(desenho instanceof Circle){
                this.model.addElement("Círculo "+this.numbCir);
                listaFormas.setModel(this.model);
                this.numbCir++;
            }

            else if(desenho instanceof Triangle){
                this.model.addElement("Triângulo "+this.numbTri);
                listaFormas.setModel(this.model);
                this.numbTri++;
            }
        }
        panel.repaint();
    }
    
    // Carrega o arquivo 
    public void carregarArquivo(JPanel panel, Graphics graficos ,JList listaFormas){
        if(isCarregar()){
            try {

                FileInputStream arquivo = new FileInputStream("banco.dat");
                ObjectInputStream carregar = new ObjectInputStream(arquivo);
                ArrayList<Form> backup = (ArrayList<Form>) carregar.readObject();

                for(Form desenhoBackup : backup){
                        this.figuras.add(desenhoBackup);
                    }
                
                JOptionPane.showMessageDialog(panel, "Arquivo carregado!", "Aviso", JOptionPane.WARNING_MESSAGE);
                System.out.println(figuras.size());
                this.carregarLista(panel, listaFormas, graficos);
                arquivo.close();
                carregar.close();
            }
            catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(panel, "Arquivo não encontrado!", "ERROR", JOptionPane.WARNING_MESSAGE);
            }    
            catch (IOException ex) {
                JOptionPane.showMessageDialog(panel, "Arquivo corrompido!", "ERROR", JOptionPane.WARNING_MESSAGE);
            } 
            catch (ClassNotFoundException ex) {
                Logger.getLogger(Funcoes.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }  
        else if(!isCarregar()){
            JOptionPane.showMessageDialog(panel, "Arquivo atual!", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
        this.carregar = false;
    }
    
    
    
    
    // Métodos de edição da figuras
    
    // Modifica o tamanho da figura
    public void redimensionarFigura(JPanel panel, Graphics grafico, int index, int largura, int altura, int diametro){
        if(this.figuras.get(index) instanceof Rectangle){
            Rectangle retangulo = (Rectangle)figuras.get(index); 
            retangulo.setAltura(altura);
            retangulo.setLargura(largura);
            retangulo.desenhar(grafico);
            this.figuras.set(index, retangulo);
        }
        else if(this.figuras.get(index) instanceof Triangle){
            Triangle triangulo = (Triangle) figuras.get(index);
            int[] pontosX = new int[3];
            int[] pontosY = new int[3];
            
            pontosX[0] = triangulo.getX();
            pontosY[0] = triangulo.getY() - altura*2/3;
            pontosX[1] = pontosX[0] + largura/2;
            pontosY[1] = pontosY[0] + altura;
            pontosX[2] = pontosX[0] - largura/2;
            pontosY[2] = pontosY[1];
            
            
          ;
            triangulo.setPontosX(pontosX);
            triangulo.setPontosY(pontosY);
            triangulo.desenhar(grafico);
            this.figuras.set(index, triangulo);
        }
            
        else if(this.figuras.get(index) instanceof Circle){
            Circle circulo = (Circle) figuras.get(index);
            
            circulo.setDiametro(diametro);
            circulo.desenhar(grafico);
            this.figuras.set(index, circulo);
        }
        panel.repaint();
    }

    // Modifica a cor da figura
    public void alterarCor(JPanel panel, Graphics grafico, int index, Color cor){
        this.figuras.get(index).setFill(true);
            
        if(this.figuras.get(index) instanceof Rectangle){
            Rectangle retangulo = (Rectangle)figuras.get(index); 
            retangulo.setCor(cor);
            retangulo.desenhar(grafico);
            this.figuras.set(index, retangulo);
        }
        else if(this.figuras.get(index) instanceof Circle){
            Circle circulo = (Circle) figuras.get(index);
            
            circulo.setCor(cor);
            circulo.desenhar(grafico);
            this.figuras.set(index, circulo);
        }
        else if(this.figuras.get(index) instanceof Triangle){
            Triangle triangulo = (Triangle) figuras.get(index);
            triangulo.setCor(cor);
            triangulo.desenhar(grafico);
            this.figuras.set(index, triangulo);
        }
        panel.repaint();
    }
    
    // Modifica a cor da borda da figura
    public void alterarCorBorda(JPanel panel, Graphics grafico, int index, Color cor){
        if(this.figuras.get(index) instanceof Rectangle){
            Rectangle retangulo = (Rectangle)figuras.get(index); 
            retangulo.setCorBorda(cor);
            retangulo.desenhar(grafico);
            this.figuras.set(index, retangulo);
        }
        else if(this.figuras.get(index) instanceof Circle){
            Circle circulo = (Circle) figuras.get(index);
            circulo.setCorBorda(cor);
            circulo.desenhar(grafico);
            this.figuras.set(index, circulo);
        }
        else if(this.figuras.get(index) instanceof Triangle){
            Triangle triangulo = (Triangle) figuras.get(index);
            triangulo.setCorBorda(cor);
            triangulo.desenhar(grafico);
            this.figuras.set(index, triangulo);
        }
        panel.repaint();
    }
    
    // Movimenta a figura
    public void movimentarFigura(JPanel panel, Graphics grafico, int index, int posicaoX, int posicaoY){
        if(this.figuras.get(index) instanceof Rectangle){
            Rectangle retangulo = (Rectangle)figuras.get(index);     
            retangulo.setX(posicaoX);
            retangulo.setY(posicaoY);
            retangulo.desenhar(grafico);
            this.figuras.set(index, retangulo);
        }
        else if(this.figuras.get(index) instanceof Circle){
            Circle circulo = (Circle) figuras.get(index);
            
            circulo.setX(posicaoX);
            circulo.setY(posicaoY);
            circulo.desenhar(grafico);
            this.figuras.set(index, circulo);
        }
        else if(this.figuras.get(index) instanceof Triangle){
            Triangle triangulo = (Triangle) figuras.get(index);
            triangulo.setX(posicaoX);
            triangulo.setY(posicaoY);
            triangulo.desenhar(grafico);
            this.figuras.set(index, triangulo);
        }
        panel.repaint();
    }
    
   
   
}
