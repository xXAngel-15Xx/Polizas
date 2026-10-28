package Polizas.Marcas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;

public class MarcasControl {
    private MarcasVista mv;
    private MarcasModelo mm;
    private List<Marca> lista;

    public MarcasControl(MarcasVista marcasVista) {
        this.mv = marcasVista;
        this.mm = new MarcasModelo();
        marcasEventos();
    }

    public void marcasEventos() {
        mv.btn1.addActionListener(e -> {
            lista = mm.read();
            mv.actualizarVista(lista);
        });

        mv.btn_a.addActionListener(e -> {
            Marca marca = new Marca(0, mv.txt1.getText());
            JOptionPane.showMessageDialog(mv, mm.create(marca));
            mv.actualizarVista(mm.read());
        });

        mv.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = mv.tabla.getSelectedRow();
                if (fila != -1) {
                    mv.txt1.setText((String) mv.marcasModelo.getValueAt(fila, 1));
                }
            }
        });

        mv.cmbMarcas.addActionListener(e -> {
            if (mv.cmbMarcas.getSelectedItem() instanceof Marca) {
                Marca selected = (Marca) mv.cmbMarcas.getSelectedItem();
                if (selected != null) {
                    mv.txt1.setText(selected.getNombre());
                }
            }
        });
    }
}