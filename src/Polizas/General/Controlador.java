package Polizas.General;

import Polizas.Marcas.MarcasControl;
import Polizas.Marcas.MarcasVista;
import javax.swing.JLabel;
import Polizas.General.Bienvenida;

public class Controlador {
    protected Bienvenida vista;
    protected MarcasVista marcasVista;
    protected MarcasControl marcasControl;

    public Controlador(Bienvenida vista) {
        this.vista = vista;
        this.eventos();
    }

    public void eventos() {
        vista.mensaje.addActionListener(e -> 
            vista.cl.show(vista.pc, "mensaje")
        );

        vista.home.addActionListener(e -> {
            vista.pc.add(new JLabel("Panel Home"), "home");
            vista.cl.show(vista.pc, "home");
        });

        vista.marcas.addActionListener(e -> {
            marcasVista = new MarcasVista();
            marcasControl = new MarcasControl(marcasVista);
            vista.pc.add(marcasVista, "mk");
            vista.cl.show(vista.pc, "mk");
        });
    }
}