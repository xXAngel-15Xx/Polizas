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
    public JButton btn1, btn_a, btnActualizar, btnEliminar;
    public JTextField txt1, txtId;
    public JLabel lbl1, lbl2, lblId;
    public JTable tabla;
    public MarcasTM marcasModelo;
    protected String[] columnas = {"Id", "Nombre"};
    public JComboBox<Marca> cmbMarcas;

    public MarcasVista() {
        setLayout(null);
        
        lbl1 = new JLabel("Welcome to Marcas Admin.");
        lbl1.setBounds(350, 5, 180, 30);

        lblId = new JLabel("ID:");
        lblId.setBounds(50, 40, 35, 30);
        txtId = new JTextField();
        txtId.setBounds(85, 40, 70, 30);

        lbl2 = new JLabel("Nombre:");
        lbl2.setBounds(170, 40, 65, 30);

        txt1 = new JTextField();
        txt1.setBounds(235, 40, 250, 30);

        cmbMarcas = new JComboBox<>();
        cmbMarcas.setBounds(510, 40, 250, 30);
        add(cmbMarcas);

        btn1 = new JButton("Consultar");
        btn1.setBounds(100, 90, 100, 30);

        btn_a = new JButton("Agregar");
        btn_a.setBounds(205, 90, 100, 30);
        this.add(btn_a);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(310, 90, 110, 30);
        this.add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(425, 90, 100, 30);
        this.add(btnEliminar);

        marcasModelo = new MarcasTM();
        tabla = new JTable(marcasModelo);
        
        JScrollPane sc = new JScrollPane(tabla);
        sc.setBounds(200, 150, 800, 300);
        
        this.add(sc);
        this.add(lbl1);
        this.add(lbl2);
        this.add(lblId);
        this.add(txtId);
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
