package Presentancion;

import Logica.ConsultaChatGPT;
import Logica.PaisesDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Formulariosegundario {
    private JPanel PanelSegundario;
    private JButton botongenerarArchivo;
    private JButton botonGenerarPIB;
    private JButton botonFaltante;
    private JLabel ConsultaChatgpt;
    private JLabel lblGini;
    private JLabel nombrePais;
    private JLabel nombreCapital;
    private JLabel nombreMoneda;
    private JLabel nombreIdioma;
    private JTextField Recolectapregunta;
    private JLabel Imagebandera;
    private JLabel MensajeGuardado;

    public Formulariosegundario (String paiss, String capitalfinal, String monedaFinal, String idiomafinal, double ginis, ImageIcon flags){
        nombrePais.setText("El país es: "+paiss);
        nombreCapital.setText("La capital es: "+capitalfinal);
        nombreMoneda.setText(String.valueOf("La moneda es: "+monedaFinal));
        nombreIdioma.setText(String.valueOf("El idioma es: "+idiomafinal));
        lblGini.setText("El Gini del pais "+paiss+" es de "+ginis);
        Imagebandera.setIcon(flags);


        botonFaltante.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String promt=Recolectapregunta.getText();
                ConsultaChatGPT RespuestaChat= new ConsultaChatGPT();
                String respuestaGPT=null;
                respuestaGPT=RespuestaChat.getConsultaPreguntaGPT(promt);
                ConsultaChatgpt.setText(respuestaGPT);
            }
        });
        botongenerarArchivo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                PaisesDTO paisesDTO = new PaisesDTO();
                MensajeGuardado.setText("Base de datos almacenada");
                paisesDTO.guardar(paiss, capitalfinal,monedaFinal, idiomafinal, ginis);

            }
        });
        botonGenerarPIB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Plano Cartesiano");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                PlanoCartesiano plano = new PlanoCartesiano();
                frame.add(plano);
                frame.setSize(600, 600);
                frame.setVisible(true);
            }
        });
    }

    public Formulariosegundario() {
    }

    public JPanel getpanels(){
        return PanelSegundario;
    }

}
