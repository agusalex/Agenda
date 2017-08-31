package com.mycompany.app.presentacion.vista;




import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaDBConfig  extends JFrame{

    private static final long serialVersionUID = 1L;
    private  JButton btnGuardarConfig;
    private JPanel contentPane;
    private JTextField txtUrl;
    private  JButton btnCerrar;
    private static VentanaDBConfig ventanaDBConfig;
    public JButton getBtnCerrar() {
        return btnCerrar;
    }

    public static VentanaDBConfig getVentanaDBConfig() {


        if(ventanaDBConfig!=null)
            ventanaDBConfig.dispose();

        ventanaDBConfig=new VentanaDBConfig();
        return ventanaDBConfig;
    }

    public JButton getBtnGuardarConfig() {
        return btnGuardarConfig;
    }

    public JTextField getTxtUrl() {
        return txtUrl;
    }





    private VentanaDBConfig() {
        super();

        setBounds(100, 100, 343, 183);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 307, 123);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblURL = new JLabel("URL");
        lblURL.setBounds(10, 11, 113, 14);
        panel.add(lblURL);


        txtUrl = new JTextField();
        txtUrl.setBounds(133, 8, 164, 20);
        panel.add(txtUrl);
        txtUrl.setColumns(10);


        btnGuardarConfig = new JButton("Guardar");
        btnGuardarConfig.addActionListener( actionListener());
        btnGuardarConfig.setBounds(100, 92, 89, 23);
        panel.add(btnGuardarConfig);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener( actionListener());
        btnCerrar.setBounds(208, 92, 89, 23);
        panel.add(btnCerrar);

        this.setVisible(true);
        try{ImageIcon img = new ImageIcon("dbicon.png");
            this.setIconImage(img.getImage());}
        catch (Exception e){
            System.out.println("Error al cargar el icono:"+e.toString());
        }

        this.setDefaultCloseOperation(0);
    }


    public ActionListener actionListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(e.getSource()==btnGuardarConfig){
                    VentanaDBConfig.getVentanaDBConfig().dispose();

            }
            else if(e.getSource()==btnCerrar){
                    VentanaDBConfig.getVentanaDBConfig().dispose();

                }
        }

    };

    }


    private void showErrorMessage(){
        this.txtUrl.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
    }

    private void restoreFieldsColor(){
        this.txtUrl.setBorder(BorderFactory.createLineBorder(Color.decode("#000000")));
    }

    public boolean checkNameField(){
        this.restoreFieldsColor();
        if(this.txtUrl.equals("")) {
            this.showErrorMessage();
            return false;
        }
        else{
            if(txtUrl.getText().length()>14) {
                this.showErrorMessage();
                return false;
            }
        }
        return true;
    }

    public void showError(String msj){
        JOptionPane.showMessageDialog(this,msj);
    }




}
