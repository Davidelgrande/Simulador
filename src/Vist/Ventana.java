/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vist;

import Modelo.Procesos; 
import Modelo.Recursos; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Color; 
import java.awt.Font; 
import javax.swing.JOptionPane;

public class Ventana extends javax.swing.JFrame {
    
  

     private static List<Procesos> procesosL= new ArrayList(); 
     private static List<Procesos> nuevo= new ArrayList(); 
     private static List<Procesos> terminado = new ArrayList(); 
     private static List<Recursos> recursos= new ArrayList(); 
     private static List<Procesos> bloqueado= new ArrayList(); 
     private static List<Procesos> ejecucion = new ArrayList(); 
     private static List<Procesos> listo = new ArrayList(); 
     List<Procesos> procesos = new ArrayList();
     Recursos ob1 = new Recursos("R1", null, true); 
     Recursos ob2 = new Recursos("R2", null, true); 
     Recursos ob3 = new Recursos("R3", null, true); 
     Recursos ob4 = new Recursos("R4", null, true); 
     Recursos ob5 = new Recursos ("R5", null, true); 
     Recursos ob6 = new Recursos("R6", null, true);
     
   
   public List getProcesos(){
       return procesosL; 
   }
   public void setProcesos(Procesos proceso1){
       procesosL.add(proceso1); 
   }
    public  List<Procesos> getProcesosL() {
        return procesosL;
    }
    
    public void setProcesosL(List<Procesos> procesosL) {
        Ventana.procesosL = procesosL;
    }
     
     
     public void agregar_recursos(){
         recursos.add(ob1); 
         recursos.add(ob2); 
         recursos.add(ob3); 
         recursos.add(ob4);
         recursos.add(ob5); 
         recursos.add(ob6); 
         
     }
     public String validar_recursos(){
         String recursos = new String(); 
         if(Recurso1.isSelected()){
           recursos=recursos+" "+Recurso1.getText(); 
         }
          if(Recurso2.isSelected()){
           recursos=recursos+" "+Recurso2.getText(); 
         }
           if(Recurso3.isSelected()){
           recursos=recursos+" "+Recurso3.getText(); 
         }
            if(Recurso4.isSelected()){
           recursos=recursos+" "+Recurso4.getText(); 
         }
             if(Recurso5.isSelected()){
           recursos=recursos+" "+Recurso5.getText(); 
         }
              if(Recurso6.isSelected()){
           recursos=recursos+" "+Recurso6.getText(); 
         }
          
              return recursos; 
       
         
     }
  public void limpiar_proceso(){
      this.JTextTamaño.setText("");
      this.TextID.setText("");
      this.jTextNombre.setText(""); 
      this.Recurso1.setSelected(false);
      this.Recurso2.setSelected(false);
      this.Recurso3.setSelected(false);
      this.Recurso4.setSelected(false);
      this.Recurso5.setSelected(false);
      this.Recurso6.setSelected(false);
      
   
  }
  public void agregar_proceso(){
      Procesos proceso =new Procesos(); 
      
      int id = Integer.parseInt(this.TextID.getText()); 
      String recursos; 
      recursos=validar_recursos(); 
         String nombre=this.jTextNombre.getText(); 
      
      System.out.println(recursos);
      int tamano= Integer.parseInt(this.JTextTamaño.getText()); 
      System.out.println("recursos");
      if(id <=0){
          JOptionPane.showMessageDialog(this, "tipo de id no valido ", "Peligro ", JOptionPane.ERROR_MESSAGE);
          this.jTabbedPane1.setSelectedIndex(0);
          this.TextID.setBackground(Color.yellow);
      }else if(tamano<0 || tamano>2000){
          JOptionPane.showMessageDialog(this, "El tamaño no es valido o es muy grande", "Error", JOptionPane.ERROR_MESSAGE);
          this.JTextTamaño.setBackground(Color.yellow);

  
      }else{
        proceso= new Procesos(id,nombre,"nuevo",tamano,1, recursos); 
       System.out.println(proceso);
      setProcesos(proceso); 
      }
      
   
      
  }
  
   
    
    public void llenarTabla_Nuevo(){
 
   
      DefaultTableModel modelo= (DefaultTableModel)this.jTableNuevo.getModel(); 
      modelo.getDataVector().clear();
      if(procesosL!=null){
          for(Procesos pro : procesosL){
             
              if(pro.getEstado().equals("nuevo")){
                 int i = procesosL.indexOf(pro); 
                 if(i%2==0){
                  this.jTableNuevo.setBackground(new Color(38,177,247));
                  modelo.addRow(new Object[]{pro.getNombre(),pro.getTamano()});
                     
                 }else{
                  this.jTableNuevo.setBackground(new Color(146,168,213));
                  modelo.addRow(new Object[]{pro.getNombre(),pro.getTamano()});
                 }
              }
          }
      }
    }
    private void llenarTabla_Datos(){
     DefaultTableModel modelo= (DefaultTableModel)this.JTable_Proc.getModel(); 
      modelo.getDataVector().clear(); 
      if(procesosL!=null){
          for(Procesos pro: procesosL){
              if(pro.getEstado().equals("nuevo")){
                   this.JTable_Proc.setBackground(new Color(38,177,247));
                  modelo.addRow(new Object[]{pro.getId(), pro.getNombre(), pro.getTamano(), pro.getNumero_hilos(),pro.getRecursos(), pro.getEstado()});
              }
          }
      }
    }
    private void llenarTabla_Listo(){
       DefaultTableModel modelo = (DefaultTableModel)this.jTableListo.getModel(); 
       int c=0; 
        for (int i = 0; i < modelo.getRowCount(); i++) {
           modelo.removeRow(0);
            
        }
        for (Procesos pro : procesosL){
            if(pro.getEstado().equals("listo")){
                c++;
                modelo.addRow(new Object[]{pro.getNombre(),pro.getTamano()});
            }
        }
    }
    
    private void llenar_Ejecución(){
        DefaultTableModel modelo = (DefaultTableModel)this.jTableEjecucion.getModel(); 
    
        for(Procesos pro: procesosL){
            if(pro.getEstado().equals("ejecucion")){
                modelo.addRow(new Object[]{pro.getNombre(),pro.getTamano()}); 
            
                
            }
        }
    }
    
    private void llenar_Bloqueado(){
        DefaultTableModel modelo = (DefaultTableModel)this.jTableBloqueado.getModel(); 
        for (Procesos pro: procesosL){
            if(pro.getEstado().equals("bloqueado")){
               modelo.addRow(new Object[]{pro.getNombre(), pro.getTamano()});
            }
        }
    }
    
    private void llenar_recursos(){
         DefaultTableModel modelo = (DefaultTableModel) this.jTableRecu.getModel();
         modelo.getDataVector().clear();
      
         if(recursos != null){
             for(Recursos re : recursos){
                 System.out.println(re.getProceso());
                 if(re.getProceso() == null){
                    modelo.addRow(new Object[]{re.getNombre(), re.isEstado(), "vacio"});
                 }else{
                   modelo.addRow(new Object[]{re.getNombre(), re.isEstado(), re.getProceso().getNombre()});
                 }
             }
         }
    }
    private void llenar_terminado(){
       DefaultTableModel modelo = (DefaultTableModel) this.jTableTerminado.getModel();
      for(Procesos pro : procesosL){
          if(pro.getEstado().equals("terminado")){
             modelo.addRow(new Object[]{pro.getNombre(), pro.getTamano()});
          }
      }
    }
      private void limpiarlisto() {
        DefaultTableModel modelo = (DefaultTableModel) this.jTableListo.getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
        }
    }
     private void limpiarbloqueado() {
        DefaultTableModel modelo = (DefaultTableModel) this.jTableBloqueado.getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
        }
    }
    
      
    private void limpiar_ejecucion() {
        DefaultTableModel modelo = (DefaultTableModel) this.jTableEjecucion.getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
        }
    }
      private void limpiarnuevo() {
        DefaultTableModel modelo = (DefaultTableModel) this.jTableNuevo.getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
        }
    }
      private void limpiarterminado(){
        DefaultTableModel modelo = (DefaultTableModel) this.jTableTerminado.getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
        }
      }
        private void limpiarREcursos() {
        DefaultTableModel modelo = (DefaultTableModel) this.jTableRecu.getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
        }
    }
      
   
  
    public Ventana() {
        initComponents();
        this.jTabbedPane1.setEnabledAt(1, false);
        this.jTabbedPane1.setEnabledAt(2, false);
    }
    public Ventana(List<Procesos> lista){
        initComponents();
        procesosL = lista;
            }
    public void control_panel(){
         this.jTabbedPane1.setEnabledAt(1, true);
        this.jTabbedPane1.setSelectedIndex(1);
        this.jTabbedPane1.setEnabledAt(2, true);
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jFormProcesos = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextNombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TextID = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        JTextTamaño = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        Recurso1 = new javax.swing.JCheckBox();
        Recurso4 = new javax.swing.JCheckBox();
        Recurso2 = new javax.swing.JCheckBox();
        Recurso5 = new javax.swing.JCheckBox();
        Recurso3 = new javax.swing.JCheckBox();
        Recurso6 = new javax.swing.JCheckBox();
        jList_Pro = new javax.swing.JScrollPane();
        JTable_Proc = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableRecu = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableBloqueado = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableNuevo = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableListo = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableTerminado = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableEjecucion = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(203, 252, 252));

        jPanel1.setBackground(new java.awt.Color(203, 252, 252));

        jTabbedPane1.setBackground(new java.awt.Color(198, 199, 240));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tabla de Control ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Trebuchet MS", 1, 12))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(105, 180, 196));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Trebuchet MS", 1, 12))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ID_Proceso:");

        jTextNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextNombreMouseClicked(evt);
            }
        });
        jTextNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNombreActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Nombre_Proceso:");

        TextID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextIDMouseClicked(evt);
            }
        });
        TextID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextIDActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Tamaño_Proceso:");

        JTextTamaño.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTextTamañoMouseClicked(evt);
            }
        });
        JTextTamaño.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTextTamañoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextID, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JTextTamaño, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextID, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTextTamaño, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(181, 168, 234));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cantidad_Recursos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Trebuchet MS", 1, 12))); // NOI18N

        Recurso1.setText("R1");

        Recurso4.setText("R4");

        Recurso2.setText("R2");

        Recurso5.setText("R5");

        Recurso3.setText("R3");

        Recurso6.setText("R6");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Recurso1)
                    .addComponent(Recurso4))
                .addGap(60, 60, 60)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Recurso5)
                    .addComponent(Recurso2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Recurso3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Recurso6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(33, 33, 33))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Recurso1)
                    .addComponent(Recurso2)
                    .addComponent(Recurso3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Recurso4)
                    .addComponent(Recurso5)
                    .addComponent(Recurso6))
                .addContainerGap())
        );

        javax.swing.GroupLayout jFormProcesosLayout = new javax.swing.GroupLayout(jFormProcesos);
        jFormProcesos.setLayout(jFormProcesosLayout);
        jFormProcesosLayout.setHorizontalGroup(
            jFormProcesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFormProcesosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jFormProcesosLayout.setVerticalGroup(
            jFormProcesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFormProcesosLayout.createSequentialGroup()
                .addGroup(jFormProcesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Agregar Proceso", jFormProcesos);

        JTable_Proc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre_Proceso", "Tamaño_Proceso", "Numero_Proceso", "Cantidad_Recursos", "Estado_Proceso"
            }
        ));
        jList_Pro.setViewportView(JTable_Proc);

        jTabbedPane1.addTab("Lista de Procesos", jList_Pro);

        jScrollPane7.setForeground(new java.awt.Color(215, 27, 43));

        jTableRecu.setBackground(new java.awt.Color(204, 255, 204));
        jTableRecu.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jTableRecu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                " Recurso", "Estado", " Procesos"
            }
        ));
        jScrollPane7.setViewportView(jTableRecu);

        jTabbedPane1.addTab("Lista de Recursos", jScrollPane7);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SIMULADOR BLOQUE CONTROL DE PROCESOS  (BCP) ");

        jButton1.setBackground(new java.awt.Color(36, 200, 212));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vist/clear1.jpg"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(10, 240, 29));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vist/Ejecutar1.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 221, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Escenario de Ejecución", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Trebuchet MS", 1, 12))); // NOI18N

        jScrollPane5.setBackground(new java.awt.Color(255, 50, 51));

        jTableBloqueado.setBackground(new java.awt.Color(255, 82, 68));
        jTableBloqueado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Proceso ", "Peso( KB)"
            }
        ));
        jTableBloqueado.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTableBloqueadoMouseMoved(evt);
            }
        });
        jScrollPane5.setViewportView(jTableBloqueado);

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Bloqueado");

        jScrollPane1.setBackground(new java.awt.Color(146, 168, 213));

        jTableNuevo.setBackground(new java.awt.Color(206, 255, 28));
        jTableNuevo.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jTableNuevo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Proceso ", "Peso(KB)"
            }
        ));
        jTableNuevo.setGridColor(new java.awt.Color(0, 0, 0));
        jTableNuevo.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(jTableNuevo);

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel2.setText("Estado Nuevo");

        jScrollPane3.setBackground(new java.awt.Color(94, 84, 240));

        jTableListo.setBackground(new java.awt.Color(204, 204, 255));
        jTableListo.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jTableListo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Proceso", "Peso(KB)"
            }
        ));
        jTableListo.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane3.setViewportView(jTableListo);

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Estado Listo ");

        jScrollPane4.setBackground(new java.awt.Color(94, 212, 97));

        jTableTerminado.setBackground(new java.awt.Color(104, 199, 33));
        jTableTerminado.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jTableTerminado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Proceso", "Peso(KB)"
            }
        ));
        jScrollPane4.setViewportView(jTableTerminado);

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("En Ejecución");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Terminado ");

        jScrollPane2.setBackground(new java.awt.Color(94, 84, 240));

        jTableEjecucion.setBackground(new java.awt.Color(255, 240, 186));
        jTableEjecucion.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jTableEjecucion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                " Proceso", "  Peso(KB)"
            }
        ));
        jScrollPane2.setViewportView(jTableEjecucion);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vist/Arrow.png"))); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vist/Arrow.png"))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vist/Arrow.png"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vist/Arrow(Abajo).png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vist/Arrow(DiagonaL).png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(226, 226, 226)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel14))
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(71, 71, 71)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(17, 17, 17)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel11))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel13))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(44, 44, 44))
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(732, 732, 732))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(272, 272, 272)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1187, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(this.TextID.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Ingrese ID", "Error", JOptionPane.WARNING_MESSAGE);
            this.TextID.setBackground(Color.red);
        }else if(this.JTextTamaño.getText().equals("")){
             JOptionPane.showMessageDialog(this,"Ingrese Tamaño","Error", JOptionPane.WARNING_MESSAGE);
            this.JTextTamaño.setBackground(Color.red);
        }else if(this.jTextNombre.getText().equals("")){
             JOptionPane.showMessageDialog(this,"Ingrese Nombre", "Error", JOptionPane.WARNING_MESSAGE);
            this.JTextTamaño.setBackground(Color.red);
        }else{
        agregar_proceso(); 
        
        llenarTabla_Nuevo();
        limpiar_proceso(); 
        control_panel(); 
        llenarTabla_Datos(); 
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        agregar_recursos(); 
        Procesos pro = new Procesos(); 
        limpiarnuevo(); 
        for(Procesos proc : procesosL){
            proc.setEstado("listo"); 
            llenarTabla_Listo();    
        }
        limpiarnuevo(); 
        llenarTabla_Datos(); 
        this.jTabbedPane1.setSelectedIndex(2);
        Correr();
        for(Procesos pros: procesosL)
        {
            pros.toString(); 
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNombreActionPerformed

    private void JTextTamañoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextTamañoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTextTamañoActionPerformed

    private void TextIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextIDActionPerformed
     
    }//GEN-LAST:event_TextIDActionPerformed

    private void TextIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextIDMouseClicked
        
            this.TextID.setBackground(Color.white);
      
    }//GEN-LAST:event_TextIDMouseClicked

    private void jTextNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextNombreMouseClicked
        this.jTextNombre.setBackground(Color.white);
    }//GEN-LAST:event_jTextNombreMouseClicked

    private void JTextTamañoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTextTamañoMouseClicked
        this.jTextNombre.setBackground(Color.white); 
    }//GEN-LAST:event_JTextTamañoMouseClicked

    private void jTableBloqueadoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBloqueadoMouseMoved
    
    }//GEN-LAST:event_jTableBloqueadoMouseMoved
 public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }
  public void Thread(){
      try{
          Thread.sleep(1000);
      }catch(InterruptedException ex){
          Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  public void Correr(){
      new Thread(){
         @Override
         public void run()
         {
            while(jTableListo.getModel().getRowCount() !=0 )
            {
                for(Procesos pro: procesosL)
                {
                   if(validarRecursos(pro) && pro.getTamano()>0)
                   {
                      pro.setEstado("listo");
                   }
                   if(pro.getEstado().equals("listo")&& pro.getTamano()>0)
                   {
                       if(validarEjecucion(pro))
                       {
                          if(pro.getTamano()!=0)
                          {
                             pro.setEstado("ejecucion");
                             limpiarREcursos(); 
                             llenar_recursos(); 
                             llenarTabla_Datos(); 
                             limpiarlisto(); 
                             llenarTabla_Listo(); 
                             llenar_Ejecución(); 
                             Thread(); 
                             pro.setTamano(pro.getTamano()-1);
                            
                             if(Operar()== true)
                             {
                              limpiarREcursos(); 
                              llenar_recursos(); 
                              liberar_recu(); 
                              limpiarbloqueado(); 
                              devolver_listo(); 
                              llenarTabla_Listo(); 
      
                             }
                             if(pro.getTamano() >0)
                             {
                               pro.setEstado("listo");
                               limpiar_ejecucion(); 
                               llenarTabla_Listo();     
                             }else{
                               pro.setEstado("terminado"); 
                               liberar_recu();
                               limpiarterminado(); 
                               llenar_terminado(); 
                              
                               
                               limpiar_ejecucion(); 
                               limpiarbloqueado();
                               llenar_recursos(); 
                               
                             }
                             llenarTabla_Datos(); 
                             
                          }else{
                              break; 
                          } 
                       }else{
                           pro.setEstado("bloqueado");
                           llenar_Bloqueado(); 
                           llenarTabla_Datos(); 
                       }
                   }else{
                       // 
                       
                   }
                }
            }
             
         }
      }.start(); 
  }
  private void liberar_recu(){
    for (Recursos re: recursos)
    {
        re.setEstado(true);
        re.setProceso(null); 
    }
    
   
}
   public boolean Operar(){
       int numero = (int) (Math.random()* 2 +1); 
       
       if(numero == 3){
         
           System.out.println("Limpiando recursos");
           return true; 
       }else{
           return false; 
       }
        
       
   }
  private void devolver_listo(){
      for (Procesos pro : procesosL){
          if(pro.getEstado().equals("bloqueado"))
          {
              pro.setEstado("listo");
          }
          limpiarlisto(); 
          llenarTabla_Listo(); 
      }
  }
   public boolean validarRecursos(Procesos pro) {
        String[] recursosPro = pro.getRecursos().split(" ");

        int estado = 0;
        ///si no hay un recurso disponible
        for (int i = 0; i < recursos.size(); i++) {
            for (String recPro : recursosPro) {
                if (recPro.equals(recursos.get(i).getNombre())) {
                    if (recursos.get(i).isEstado() != true && recursos.get(i).getProceso().getId() != pro.getId()) {
                        this.jTableRecu.setForeground(new Color(215,27,43));
                        estado++;
                    }
                    if (recursos.get(i).isEstado() != true && recursos.get(i).getProceso().getId() == pro.getId()) {
                        return true;
                        
                    }
                }
            }

        }
        if (estado == 0) {
            return true;
                    } else {
            return false;
        }
    }

  

  public boolean validarEjecucion(Procesos pro){
      String[] recursos_Pro = pro.getRecursos().split(" "); 
      
      int estado= 0; 
      for (int i = 0; i < recursos.size(); i++) {
         for(String recur : recursos_Pro)
         {
             if(recur.equals(recursos.get(i).getNombre()))
             {
                 if(recursos.get(i).isEstado()!= true && recursos.get(i).getProceso().getId() != pro.getId())
                 {
                     estado++; 
                     
                 }
                    if(recursos.get(i).isEstado()!= true && recursos.get(i).getProceso().getId() == pro.getId())
                    {
                   return true;    
                     
                    }
             }
          
             }
      
      }
      Recursos rec = new Recursos(); 
      if(estado==0)
      {
          for (int i = 0; i < recursos.size(); i++)
          {
             for(String recur : recursos_Pro)
             {
                 if(recur.equals(recursos.get(i).getNombre()))
                 {
                     recursos.get(i).setProceso(pro);
                     recursos.get(i).setEstado(false);
                 }
             }
              
          }
          return true; 
      }else{
          return false; 
      }
  }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTable_Proc;
    private javax.swing.JTextField JTextTamaño;
    private javax.swing.JCheckBox Recurso1;
    private javax.swing.JCheckBox Recurso2;
    private javax.swing.JCheckBox Recurso3;
    private javax.swing.JCheckBox Recurso4;
    private javax.swing.JCheckBox Recurso5;
    private javax.swing.JCheckBox Recurso6;
    private javax.swing.JTextField TextID;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jFormProcesos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jList_Pro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableBloqueado;
    private javax.swing.JTable jTableEjecucion;
    private javax.swing.JTable jTableListo;
    private javax.swing.JTable jTableNuevo;
    private javax.swing.JTable jTableRecu;
    private javax.swing.JTable jTableTerminado;
    private javax.swing.JTextField jTextNombre;
    // End of variables declaration//GEN-END:variables
}
