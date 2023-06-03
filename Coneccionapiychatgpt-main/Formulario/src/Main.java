import Presentancion.FormularioPrincipal;

import javax.swing.*;

public class Main extends FormularioPrincipal {

    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FormularioPrincipal form=new FormularioPrincipal();
        frame.setContentPane(form.getpanelprincipal());
        frame.setSize(500,400);
        frame.setVisible(true);

    }

}