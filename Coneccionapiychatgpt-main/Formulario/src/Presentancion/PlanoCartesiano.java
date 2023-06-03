package Presentancion;

import javax.swing.*;
import java.awt.*;

public class PlanoCartesiano extends JPanel {

    private int[] puntosX = {1, 2, 3, 4};   // Coordenadas X de los puntos
    private int[] puntosY = {2, 2, 2, 2};   // Coordenadas Y de los puntos

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int ancho = getWidth();
        int alto = getHeight();
        int origenX = ancho / 2;
        int origenY = alto / 2;

        // Dibujar el plano cartesiano
        g.drawLine(0, origenY, ancho, origenY);   // Eje X
        g.drawLine(origenX, 0, origenX, alto);   // Eje Y

        // Dibujar los números en el eje X
        for (int i = -10; i <= 10; i++) {
            int x = origenX + i * 30;   // Espaciado de 30 píxeles entre los números en el eje X
            int y = origenY + 12;   // Distancia vertical del número con respecto al eje X

            g.drawString(Integer.toString(i), x, y);
        }

        // Dibujar los números en el eje Y
        for (int i = -10; i <= 10; i++) {
            int x = origenX - 20;   // Distancia horizontal del número con respecto al eje Y
            int y = origenY - i * 30;   // Espaciado de 30 píxeles entre los números en el eje Y

            g.drawString(Integer.toString(i), x, y);
        }

        // Dibujar los puntos y las líneas que los conectan
        g.setColor(Color.RED);   // Color rojo para los puntos y las líneas

        for (int i = 0; i < puntosX.length; i++) {
            int x = origenX + puntosX[i] * 30;   // Escalar la coordenada X al tamaño del panel
            int y = origenY - puntosY[i] * 30;   // Escalar la coordenada Y al tamaño del panel

            g.fillOval(x - 3, y - 3, 6, 6);   // Dibujar punto como un óvalo relleno

            if (i < puntosX.length - 1) {
                int x2 = origenX + puntosX[i + 1] * 30;   // Escalar la coordenada X del siguiente punto
                int y2 = origenY - puntosY[i + 1] * 30;   // Escalar la coordenada Y del siguiente punto

                g.drawLine(x, y, x2, y2);   // Dibujar línea que conecta los puntos
            }
        }
    }
}