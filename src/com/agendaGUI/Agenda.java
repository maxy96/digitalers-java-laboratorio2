package com.agendaGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Agenda extends JFrame{
    private JPanel panelTitulo;
    private JPanel panelFormulario;
    private JPanel panelAcciones;
    private JPanel panelFomularionIzquierdo;
    private JPanel panelFormularioDerecho;
    private JTextField textNumeroField;
    private JTextField textNombreField;
    private JTextField textApellidoField;
    private JTextField textDireccionField;
    private JTextField textTelefonoField;
    private JTextField textEdadField;
    private JLabel tituloPrincipal;
    private JPanel panelIndices;
    private JButton prevButton;
    private JButton submitButton;
    private JButton nextButton;
    private JPanel mainPanel;
    private JTextField textIndice;
    private JButton longitudButton;
    private JLabel indiceLabel;
    private JLabel numeroLabel;
    private JLabel nombreLabel;
    private JLabel apellidoLabel;
    private JLabel direccionLabel;
    private JLabel telefonoLabel;
    private JLabel edadLabel;
    // Atributos
    private int indice = 0;
    private int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private int[] edades = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private String[] nombres = {"", "", "", "", "", "", "", "", "", ""};
    private String[] apellidos = {"", "", "", "", "", "", "", "", "", ""};
    private String[] direcciones = {"", "", "", "", "", "", "", "", "", ""};
    private String[] telefonos = {"", "", "", "", "", "", "", "", "", ""};

    public Agenda(){
        super("Agenda Electronica");
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.mostrarDatosDeAgenda();
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                siguiente();
            }
        });
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior();
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grabar();
            }
        });
        longitudButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mensajeDeAlerta("Total de Nombres agregados: "+ longitudArrayString(nombres)
                                +"\nTotal de Apellidos agregados: "+ longitudArrayString(apellidos)
                                +"\nTotal de Direcciones agregadas: "+ longitudArrayString(direcciones)
                                +"\nTotal de Telefonos agregados: "+ longitudArrayString(telefonos)
                                +"\nTotal de Edades agregadas: "+ longitudArrayInteger(edades)
                                );
            }
        });
    }

    private int longitudArrayInteger(int[] arr){
        int count = 0;
        for (int i = 0; i < arr.length; i++){
            count += (arr[i] > 0) ? 1 : 0;
        }
        // Forma simplificada con lambda
        // (int)Arr.stream(arr).filter(x -> x > 0 ).count();
        // retorna un valor de tipo de dato long, por eso esta parseado
        return count;
    }

    private int longitudArrayString(String[] arr){
        int count = 0;
        for (int i = 0; i < arr.length; i++){
            count += (!arr[i].matches("")) ? 1 : 0;
        }
        // Forma simplificada con lambda
        // (int)Arr.stream(arr).filter(x -> !x.matches("") ).count();
        // retorna un valor de tipo de dato long, por eso esta parseado
        return count;
    }

    private String validarCampoTelefono(String telefono){
        if(telefono.matches("^(\\+{1})?(\\s)?(([0-9]+\\.?){10,15})?")){
            return telefono;
        }else{
            this.mensajeDeAlerta("En el campo telefono solo se debe ingresar datos numericos o + al " +
                                "comienzo del numero con una longitud de 10 o 15 caracteres" +
                                "\nEjemplo: +541111223355 o 541111223355");
            return "";
        }
    }

    private void grabar(){
        this.nombres[this.indice] = this.validarCampoDeTexto(this.textNombreField.getText(), "nombre");
        this.apellidos[this.indice] = this.validarCampoDeTexto(this.textApellidoField.getText(), "apellido");
        this.direcciones[this.indice] = this.textDireccionField.getText();
        this.telefonos[this.indice] = this.validarCampoTelefono(this.textTelefonoField.getText());
        this.edades[this.indice] = this.validarCampoNumericoPositivo(Integer.parseInt(this.textEdadField.getText()), "edad");
    }

    private void siguiente(){
        this.indice++;
        this.mostrarDatosDeAgenda();
        if(this.indice > 8) {
            this.nextButton.setEnabled(false);
        }
        this.prevButton.setEnabled(true);
    }

    private int validarCampoNumericoPositivo(int nro, String campo){
        if(nro >= 0){
            return nro;
        }else{
            this.mensajeDeAlerta("En el campo "+ campo +" se debe ingresar un numero mayor que cero");
            return 0;
        }
    }

    private String validarCampoDeTexto(String texto, String campo){
        if(texto.matches("([a-zA-Z]+\\.?)") || texto.matches("")){
            return texto;
        }else{
            this.mensajeDeAlerta("En el campo " + campo + " solo se debe ingresar datos alfabeticos");
            return "";
        }
    }

    private void mensajeDeAlerta(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private void anterior(){
        this.indice--;
        mostrarDatosDeAgenda();
        if(this.indice < 1) {
            this.prevButton.setEnabled(false);
        }
        this.nextButton.setEnabled(true);
    }

    private void mostrarDatosDeAgenda(){
        textIndice.setText(Integer.toString(this.indice));
        textNumeroField.setText(Integer.toString(this.numeros[this.indice]));
        textNombreField.setText(this.nombres[this.indice]);
        textApellidoField.setText(this.apellidos[this.indice]);
        textDireccionField.setText(this.direcciones[this.indice]);
        textTelefonoField.setText(this.telefonos[this.indice]);
        textEdadField.setText(Integer.toString(this.edades[this.indice]));
    }

    public static void main(String[] args){
        JFrame agenda = new Agenda();
        agenda.setVisible(true);
    }

}
