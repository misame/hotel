package com.alura.hotel.views;

import com.alura.hotel.controlador.ControladorHuesped;
import com.alura.hotel.controlador.ControladorReservas;
import com.alura.hotel.modelo.Huesped;
import com.alura.hotel.modelo.Reserva;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private DefaultTableModel modelo;
    private DefaultTableModel modeloHuesped;
    private JLabel labelAtras;
    private JLabel labelExit;
    int xMouse, yMouse;
    List<Object[]> resultado;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Busqueda frame = new Busqueda();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Busqueda() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BUSQUEDA");
        lblNewLabel_4.setForeground(new Color(12, 138, 199));
        lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 22));
        lblNewLabel_4.setBounds(331, 62, 280, 42);
        contentPane.add(lblNewLabel_4);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);

        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 14));
        modelo = (DefaultTableModel) tbReservas.getModel();
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Fecha Check In");
        modelo.addColumn("Fecha Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");
        modelo.addColumn("Numero de Huesped");
        JScrollPane scroll_table = new JScrollPane(tbReservas);
        panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
                null);
        scroll_table.setVisible(true);

        tbHuespedes = new JTable();
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 14));
        modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
        modeloHuesped.addColumn("N�mero de Huesped");
        modeloHuesped.addColumn("Nombre");
        modeloHuesped.addColumn("Apellido");
        modeloHuesped.addColumn("Fecha de Nacimiento");
        modeloHuesped.addColumn("Nacionalidad");
        modeloHuesped.addColumn("Telefono");
        modeloHuesped.addColumn("N�mero de Reserva");
        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
        panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
                scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);

            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el boton este cambiara� de color
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el boton este volvera� al estado
                // original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                String valor = txtBuscar.getText();
                ControladorReservas cr = new ControladorReservas();
                ControladorHuesped ch = new ControladorHuesped();
                //Verifica si se puede convertir a Long
                Long huespedId = null;
                try {
                    huespedId = Long.parseLong(valor);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }

                if (huespedId != null) {
                    resultado = cr.consultaHuespedYReservaId(huespedId);
                    actualizarTablaHuespedes(resultado);
                    actualizarTablaReservas(resultado);
                } else {
                    resultado = ch.consultaHuespedesYReservaApellido(valor);
                    actualizarTablaHuespedes(resultado);
                    actualizarTablaReservas(resultado);
                }
            }
        });
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel btnEditar = new JPanel();
        btnEditar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                ControladorReservas cr = new ControladorReservas();
                cr.ActualizarReservasHuespedes(tbHuespedes, tbReservas);

            }
        });
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        JPanel btnEliminar = new JPanel();
        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultTableModel tbH = (DefaultTableModel) tbHuespedes.getModel();
                int filaSeleccionadaH = tbHuespedes.getSelectedRow();
                Long idSeleccionadoH = Long.parseLong(tbH.getValueAt(filaSeleccionadaH, 0).toString());

                DefaultTableModel tbR = (DefaultTableModel) tbReservas.getModel();
                int filaSeleccinadoR = tbReservas.getSelectedRow();
                Long idSeleccionadoR = Long.parseLong(tbR.getValueAt(filaSeleccinadoR, 0).toString());

                if (idSeleccionadoH != null && panel.getSelectedIndex() == 1) {
                    int opcion = JOptionPane.showConfirmDialog(null, "�Seguro que desea borrar al huesped?\nTambien se borrar�n todas sus reservas", "BORRAR HUESPED", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) {
                        ControladorHuesped ch = new ControladorHuesped();
                        ch.eliminarHuesped(idSeleccionadoH);
                    }
                }
                if (idSeleccionadoR != null && panel.getSelectedIndex() == 0) {
                    int opcion = JOptionPane.showConfirmDialog(null, "�Seguro que desea borrar la reserva?", "BORRAR RESERVA", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) {
                        ControladorReservas cr = new ControladorReservas();
                        cr.eliminarReserva(idSeleccionadoR);
                    }
                }
            }
        });

        btnEliminar.setLayout(null);
        btnEliminar.setBackground(new Color(12, 138, 199));
        btnEliminar.setBounds(767, 508, 122, 35);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);

        JLabel lblEliminar = new JLabel("ELIMINAR");
        lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEliminar.setForeground(Color.WHITE);
        lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEliminar.setBounds(0, 0, 122, 35);
        btnEliminar.add(lblEliminar);
        setResizable(false);

    }

    private void actualizarTablaHuespedes(List<Object[]> resultados) {
        DefaultTableModel modeloHuespedes = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return !(column == 0 || column == 5);
            }

        };
        modeloHuespedes.addColumn("N�mero de Huesped");
        modeloHuespedes.addColumn("Nombre");
        modeloHuespedes.addColumn("Apellido");
        modeloHuespedes.addColumn("Fecha de Nacimiento");
        modeloHuespedes.addColumn("Nacionalidad");
        modeloHuespedes.addColumn("Telefono");
        modeloHuespedes.addColumn("N�mero de Reserva");

        for (Object[] resultado : resultados) {
            Huesped huesped = (Huesped) resultado[0];
            Reserva reserva = (Reserva) resultado[1];

            Object[] fila = new Object[7];
            fila[0] = huesped.getId();
            fila[1] = huesped.getNombre();
            fila[2] = huesped.getApellido();
            fila[3] = huesped.getFechaNacimiento();
            fila[4] = huesped.getNacionalidad();
            fila[5] = huesped.getTelefono();
            fila[6] = reserva.getId();

            modeloHuespedes.addRow(fila);
        }

        tbHuespedes.setModel(modeloHuespedes);
    }

    private void actualizarTablaReservas(List<Object[]> resultados) {
        DefaultTableModel modeloReservas = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {

                return !(column == 0 || column == 5);
            }
        };
        modeloReservas.addColumn("Numero de Reserva");
        modeloReservas.addColumn("Fecha Check In");
        modeloReservas.addColumn("Fecha Check Out");
        modeloReservas.addColumn("Valor");
        modeloReservas.addColumn("Forma de Pago");
        modeloReservas.addColumn("Numero de huesped");

        for (Object[] resultado : resultados) {
            Huesped huesped = (Huesped) resultado[0];
            Reserva reserva = (Reserva) resultado[1];

            Object[] fila = new Object[6];
            fila[0] = reserva.getId();
            fila[1] = reserva.getFechaEntrada();
            fila[2] = reserva.getFechaSalida();
            fila[3] = reserva.getValor();
            fila[4] = reserva.getFormaPago();
            fila[5] = huesped.getId();

            modeloReservas.addRow(fila);
        }

        tbReservas.setModel(modeloReservas);
    }

//C�digo que permite mover la ventana por la pantalla seg�n la posici�n de "x" y "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}
