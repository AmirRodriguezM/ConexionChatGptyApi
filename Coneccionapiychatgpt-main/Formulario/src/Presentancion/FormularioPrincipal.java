package Presentancion;

import Logica.ConsultaApiPaises;
import Logica.Logica1;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class FormularioPrincipal extends JFrame{

    private JPanel panelprincipal;
    private JTextField RecolectaPais;
    private JButton BotonBuscarPais;
    private JLabel Imagelogo;

    Logica1 logica=new Logica1();

    public FormularioPrincipal(){


        BotonBuscarPais.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {

                String pais = RecolectaPais.getText();

                ConsultaApiPaises api = new ConsultaApiPaises();
                String respuesta = null;
                String capitalfinal;
                String paiss = null;

                String year = null;
                double val = 0;
                JsonNode moneda;
                JsonNode idioma;
                String monedaFinal;
                String idiomafinal = null;

                String ginitext = null;
                double ginis;
                String flags = null;
                try {
                    respuesta = api.getPaisInfo(pais);
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode json = objectMapper.readTree(respuesta.toString());

// Verificación de país
                    JsonNode paisNode = json;

                    try {
                        if (paisNode != null && !paisNode.isNull()) {
                            paiss = json.get(0).get("name").get("common").asText();

                        } else {
                            throw new NullPointerException(); // Lanzar una excepción para activar el bloque catch
                        }
                    } catch (NullPointerException Errorcapital) {
                        paiss = "No indica informacion en el api";
                    }
// Verificación de capital
                    JsonNode capitalNode = json.get(0).get("capital");

                    try {
                        if (capitalNode != null && !capitalNode.isNull()) {
                            capitalfinal = capitalNode.get(0).asText();

                        } else {
                            throw new NullPointerException(); // Lanzar una excepción para activar el bloque catch
                        }
                    } catch (NullPointerException Errorcapital) {
                        capitalfinal = "No indica informacion en el api";
                    }
// Verificación de moneda

                    moneda = json.get(0).get("currencies");

                    try {
                        if (moneda != null && !moneda.isNull()) {
                            String codigo = moneda.fieldNames().next();
                            monedaFinal = moneda.get(codigo).get("name").asText();

                        } else {
                            throw new NullPointerException(); // Lanzar una excepción para activar el bloque catch
                        }
                    } catch (NullPointerException Errormoneda) {
                        monedaFinal = "No indica informacion en el api";

                    }

// Verificación de idioma
                    idioma = json.get(0).get("languages");
                    try {
                        if (idioma != null && !idioma.isNull()) {

                            if (idioma.isObject()) {
                                String code = idioma.fieldNames().next();
                                idiomafinal = idioma.get(code).asText();
                            }
                        } else {
                            throw new NullPointerException(); // Lanzar una excepción para activar el bloque catch
                        }
                    } catch (NullPointerException Errorlanguage) {
                        idiomafinal = "No indica informacion en el api"; // Utilizando Double.NaN para indicar "no tiene"
                    }

                    ginis = 0;
                    String yea = "2019";

// Verificación de gini

                    try {
                        ginis = json.get(0).get("gini").get(yea).asDouble();

                    } catch (NullPointerException ErrorGini) {

                        System.out.println("No indica información");

                    }

                    try {

                        flags = json.get(0).get("flags").get("png").asText();

                    } catch (NullPointerException ErrorGini) {

                        System.out.println("No indica información");

                    }


                } catch (IOException ex) {
                    throw new RuntimeException(ex);

                }

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Formulariosegundario form2 = new Formulariosegundario(paiss, capitalfinal, monedaFinal, idiomafinal, ginis, procesarImagen(flags));
                frame.setContentPane(form2.getpanels());
                frame.setSize(800, 600);
                frame.setVisible(true);

            }
        });
    }

    public ImageIcon procesarImagen(String flags) {
        try {
            URL url=new URL(flags);

            ImageIcon imagen = new ImageIcon(url);
            int ancho = 200;
            int alto = 200;
            Image img = imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            ImageIcon imagenEscalada = new ImageIcon(img);
            return imagenEscalada;


        } catch (NullPointerException ErrorGini) {


        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public JPanel getpanelprincipal() {

        return this.panelprincipal;
    }

}

