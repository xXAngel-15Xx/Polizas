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
            if (mv.txt1.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(mv, "Ingresa el nombre de la marca.");
                return;
            }
            Marca marca = new Marca(0, mv.txt1.getText());
            int resultado = mm.create(marca);
            String mensaje = resultado == -1
                    ? "Ya existe una marca con ese nombre."
                    : resultado > 0 ? "Marca agregada correctamente." : "No se pudo agregar la marca.";
            JOptionPane.showMessageDialog(mv, mensaje);
            mv.actualizarVista(mm.read());
        });

        mv.btnActualizar.addActionListener(e -> {
            Integer id = obtenerId();
            if (id == null) return;
            if (mv.txt1.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(mv,
                        "El nombre está en blanco. Se conserva el registro original sin cambios.");
                return;
            }
            int resultado = mm.update(new Marca(id, mv.txt1.getText().trim()));
            String mensaje = resultado == -1
                    ? "Ya existe otra marca con ese nombre."
                    : resultado > 0 ? "Marca actualizada correctamente." : "No se encontró una marca con ese ID.";
            JOptionPane.showMessageDialog(mv, mensaje);
            mv.actualizarVista(mm.read());
        });

        mv.btnEliminar.addActionListener(e -> {
            Integer id = obtenerId();
            if (id == null) return;
            int confirmacion = JOptionPane.showConfirmDialog(mv, "¿Eliminar la marca con ID " + id + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion != JOptionPane.YES_OPTION) return;
            int resultado = mm.delete(new Marca(id, ""));
            JOptionPane.showMessageDialog(mv, resultado > 0 ? "Marca eliminada correctamente." : "No se encontró una marca con ese ID.");
            mv.actualizarVista(mm.read());
            mv.txtId.setText("");
            mv.txt1.setText("");
        });

        mv.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = mv.tabla.getSelectedRow();
                if (fila != -1) {
                    mv.txtId.setText(String.valueOf(mv.marcasModelo.getValueAt(fila, 0)));
                    mv.txt1.setText((String) mv.marcasModelo.getValueAt(fila, 1));
                }
            }
        });

        mv.cmbMarcas.addActionListener(e -> {
            if (mv.cmbMarcas.getSelectedItem() instanceof Marca) {
                Marca selected = (Marca) mv.cmbMarcas.getSelectedItem();
                if (selected != null) {
                    mv.txtId.setText(String.valueOf(selected.getId()));
                    mv.txt1.setText(selected.getNombre());
                }
            }
        });
    }

    private Integer obtenerId() {
        try {
            int id = Integer.parseInt(mv.txtId.getText().trim());
            if (id <= 0) throw new NumberFormatException();
            return id;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mv, "Ingresa un ID numérico válido.");
            return null;
        }
    }
}
