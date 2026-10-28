package Polizas.Marcas;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MarcasVista extends JPanel {
    public JButton btn1, btn_a;
    public JTextField txt1;
    public JLabel lbl1, lbl2;
    public JTable tabla;
    public MarcasTM marcasModelo;
    protected String[] columnas = {"Id", "Nombre"};
    public JComboBox<Marca> cmbMarcas;

    public MarcasVista() {
        setLayout(null);
        
        lbl1 = new JLabel("Welcome to Marcas Admin.");
        lbl1.setBounds(350, 5, 180, 30);

        lbl2 = new JLabel("Ingresa tu nombre: ");
        lbl2.setBounds(50, 40, 150, 30);

        txt1 = new JTextField();
        txt1.setBounds(170, 40, 250, 30);

        cmbMarcas = new JComboBox<>();
        cmbMarcas.setBounds(500, 40, 250, 30);
        add(cmbMarcas);

        btn1 = new JButton("Consultar");
        btn1.setBounds(150, 90, 100, 30);

        btn_a = new JButton("Agregar");
        btn_a.setBounds(250, 90, 100, 30);
        this.add(btn_a);

        marcasModelo = new MarcasTM();
        tabla = new JTable(marcasModelo);
        
        JScrollPane sc = new JScrollPane(tabla);
        sc.setBounds(200, 150, 800, 300);
        
        this.add(sc);
        this.add(lbl1);
        this.add(lbl2);
        this.add(txt1);
        this.add(btn1);
    }

    public void actualizarVista(List<Marca> lista) {
        marcasModelo.vaciarlista();
        marcasModelo.llenar(lista);

        cmbMarcas.removeAllItems();
        for (Marca m : lista) {
            cmbMarcas.addItem(m);
        }
    }
}