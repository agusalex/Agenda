package com.mycompany.app.presentacion.vista;




import com.mycompany.app.negocio.Main;
import com.mycompany.app.persistencia.Propiedades;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Properties;

public class VentanaDBConfig  extends JFrame {

    private static final long serialVersionUID = 1L;
    private JButton conect;
    private JPanel contentPane;
    private JTextField txtUrl;
    private JTextField txtDriver;
    private JTextField txtUser;
    private JTextField txtPass;
    private Checkbox defaults;


    private JButton close;

    private static VentanaDBConfig ventanaDBConfig;

    public JButton getClose() {
        return close;
    }


    public static VentanaDBConfig getVentanaDBConfig() {


        if(ventanaDBConfig!=null)
            ventanaDBConfig.dispose();

        ventanaDBConfig=new VentanaDBConfig();
        return ventanaDBConfig;
    }

    public JButton getConect() {
        return conect;
    }

    public JTextField getTxtUrl() {
        return txtUrl;
    }


    private VentanaDBConfig() {
        super();


        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 307, 250);
        setBounds(100, 100, 343, 250);
        contentPane.add(panel);
        panel.setLayout(null);
        int xBaseline = 10;
        int y = 40;

        defaults = new Checkbox("Usar valores por defecto");
        defaults.setBounds(xBaseline, 11, 200, 14);
        defaults.addItemListener(itemListener());
        panel.add(defaults);
        /////////////////////////////////////////
        JLabel lblDriver = new JLabel("Driver:");
        lblDriver.setBounds(xBaseline, y, 50, 14);
        panel.add(lblDriver);

        txtDriver = new JTextField();
        txtDriver.setBounds(xBaseline + 75, y - 2, 164, 20);
        panel.add(txtDriver);
        txtDriver.setColumns(10);
        /////////////////////////////////////////
        y += 14 + 10;

        JLabel lblURL = new JLabel("URL:");
        lblURL.setBounds(xBaseline, y, 113, 14);
        panel.add(lblURL);

        txtUrl = new JTextField();
        txtUrl.setBounds(xBaseline + 75, y - 2, 164, 20);
        panel.add(txtUrl);
        txtUrl.setColumns(10);
        /////////////////////////////////////////
        y += 14 + 10;

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(xBaseline, y, 113, 14);
        panel.add(lblUsuario);

        txtUser = new JTextField();
        txtUser.setBounds(xBaseline + 75, y - 2, 164, 20);
        panel.add(txtUser);
        txtUser.setColumns(10);

        /////////////////////////////////////////
        y += 14 + 10;

        JLabel lblContrasenia = new JLabel("Contraseña:");
        lblContrasenia.setBounds(xBaseline, y, 113, 14);
        panel.add(lblContrasenia);

        txtPass = new JTextField();
        txtPass.setBounds(xBaseline + 75, y - 2, 164, 20);
        panel.add(txtPass);
        txtPass.setColumns(10);
        /////////////////////////////////////////
        y += 14 + 10;

        y += 25;
        conect = new JButton("Conectarse");
        conect.addActionListener(actionListener());
        conect.setBounds(100, y, 100, 23);
        panel.add(conect);

        close = new JButton("Cerrar");
        close.addActionListener(actionListener());
        close.setBounds(208, y, 89, 23);
        panel.add(close);

        this.setVisible(true);
        try {
            ImageIcon img = new ImageIcon("img"+ File.separator+"dbicon.png");
            this.setIconImage(img.getImage());
        } catch (Exception e) {
            System.out.println("Error al cargar el icono:" + e.toString());
        }

        this.setDefaultCloseOperation(0);


        if (Propiedades.exists(Propiedades.getDEFAULTPROP())) {
            setDefaultMode();
        } else if (Propiedades.exists(Propiedades.getCUSTOMPROP())) {
            setCustomMode();

        } else {
            //FIRSTRUN
            setDefaultMode();
        }


    }

    public void setCustomMode() {
        defaults.setState(false);
        Properties props = Propiedades.getProperties(Propiedades.getCUSTOMPROP());
        if (props != null) {
            txtDriver.setText(props.getProperty("jdbc.driver"));

            txtUrl.setText(props.getProperty("jdbc.url"));

            txtUser.setText(props.getProperty("jdbc.username"));

            txtPass.setText(props.getProperty("jdbc.password"));

        }
        txtDriver.setEnabled(true);
        txtUrl.setEnabled(true);
        txtUser.setEnabled(true);
        txtPass.setEnabled(true);

    }

    public void setDefaultMode() {
        defaults.setState(true);

        Properties props = Propiedades.getProperties(Propiedades.getDEFAULTPROP());
        if (props == null) {
            props = Propiedades.getHardcoded();
        }
        txtDriver.setText(props.getProperty("jdbc.driver"));
        txtDriver.setEnabled(false);
        txtUrl.setText(props.getProperty("jdbc.url"));
        txtUrl.setEnabled(false);
        txtUser.setText(props.getProperty("jdbc.username"));
        txtUser.setEnabled(false);
        txtPass.setText(props.getProperty("jdbc.password"));
        txtPass.setEnabled(false);


    }



/*
    public boolean loadProperties(){
        Properties properties= Propiedades.g

    }*/


    public ItemListener itemListener() {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == defaults) {
                    if (defaults.getState()) {
                        setDefaultMode();

                    } else {
                        setCustomMode();
                    }

                }
            }
        };
    }


    public ActionListener actionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == conect) {


                    if (defaults.getState()) {
                        System.out.println("Conectando con defaults...");
                        Propiedades.setHarcodedDefaults();

                        if (!Main.isFirstRun()) {
                            System.out.println("Cerrando Ventanas...");
                            Main.getMain().cerrarVentanas();

                        }

                        VentanaDBConfig.getVentanaDBConfig().dispose();
                        Main.getMain().inicializar();



                    } else {
                        System.out.println("Guardando properties Custom...");
                        System.out.println("Conectando con Custom...");
                        Propiedades.setProperties(Propiedades.getCUSTOMPROP(), txtDriver.getText(), txtUrl.getText(), txtUser.getText(), txtPass.getText());
                        Propiedades.removeDefaults();

                        if (!Main.isFirstRun()) {
                            System.out.println("Cerrando Ventanas...");
                            Main.getMain().cerrarVentanas();

                        }
                        VentanaDBConfig.getVentanaDBConfig().dispose();
                        Main.getMain().inicializar();



                    }


                } else if (e.getSource() == close) {

                    VentanaDBConfig.getVentanaDBConfig().dispose();

                }


            }

        };

    }


    private void showErrorMessage() {
        this.txtUrl.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
    }

    private void restoreFieldsColor() {
        this.txtUrl.setBorder(BorderFactory.createLineBorder(Color.decode("#000000")));
    }

    public boolean checkNameField() {
        this.restoreFieldsColor();
        if (this.txtUrl.equals("")) {
            this.showErrorMessage();
            return false;
        } else {
            if (txtUrl.getText().length() > 14) {
                this.showErrorMessage();
                return false;
            }
        }
        return true;
    }

    public void showMsg(String msj) {
        JOptionPane.showMessageDialog(this, msj);
    }

}