package ar.edu.unrn.radio.aop.ui3;


import ar.edu.unrn.radio.aop.model3.Concurso;
import ar.edu.unrn.radio.aop.model3.Participante;
import ar.edu.unrn.radio.aop.model3.RepositorioConcursos;
import ar.edu.unrn.radio.aop.model3.ServicioDeInscripciones;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RadioCompetition extends JFrame {

    private final JTextField txtName = new JTextField(20);
    private final JTextField txtLastName = new JTextField(20);
    private final JTextField txtDni = new JTextField(20);
    private final JTextField txtPhone = new JTextField(20);
    private final JTextField txtEmail = new JTextField(20);
    private final JComboBox<String> comboBox = new JComboBox<>();
    private final JButton btnOk = new JButton("Inscribirse");

    private final RepositorioConcursos repo;
    private final ServicioDeInscripciones inscripciones;
    private final Map<String, Concurso> concursosMap = new HashMap<>();

    public RadioCompetition(RepositorioConcursos repo, ServicioDeInscripciones inscripciones) {
        this.repo = repo;
        this.inscripciones = inscripciones;
        setupUI();
        cargarConcursos();
    }

    private void setupUI() {
        setTitle("Inscripción a Concurso");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Nombre:"));
        panel.add(txtName);
        panel.add(new JLabel("Apellido:"));
        panel.add(txtLastName);
        panel.add(new JLabel("DNI:"));
        panel.add(txtDni);
        panel.add(new JLabel("Teléfono (NNNN-NNNNNN):"));
        panel.add(txtPhone);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Concurso:"));
        panel.add(comboBox);
        panel.add(btnOk);

        btnOk.addActionListener(this::guardarInscripcion);

        setContentPane(panel);
        setVisible(true);
    }

    private void cargarConcursos() {
        comboBox.addItem("Seleccione un concurso...");
        List<Concurso> concursos = repo.concursosVigentes();
        for (Concurso c : concursos) {
            String key = c.toString();
            concursosMap.put(key, c);
            comboBox.addItem(key);
        }
    }

    private void guardarInscripcion(ActionEvent e) {
        if (!validaciones()) return;

        Participante participante = new Participante(
                txtName.getText(),
                txtLastName.getText(),
                txtPhone.getText(),
                txtEmail.getText(),
                txtDni.getText()
        );

        String key = (String) comboBox.getSelectedItem();
        Concurso concurso = concursosMap.get(key);

        inscripciones.inscribir(participante, concurso);
        JOptionPane.showMessageDialog(this, "Inscripción registrada correctamente.");
        limpiarFormulario();
    }

    private void limpiarFormulario() {
        txtName.setText("");
        txtLastName.setText("");
        txtDni.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        comboBox.setSelectedIndex(0);
    }

    private boolean validaciones() {
        if (txtName.getText().isBlank()) {
            showError("El nombre no puede estar vacío.");
            return false;
        }
        if (txtLastName.getText().isBlank()) {
            showError("El apellido no puede estar vacío.");
            return false;
        }
        if (txtDni.getText().isBlank()) {
            showError("El DNI no puede estar vacío.");
            return false;
        }
        if (!txtEmail.getText().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            showError("El email no tiene un formato válido.");
            return false;
        }
        if (!txtPhone.getText().matches("\\d{4}-\\d{6}")) {
            showError("El teléfono debe ingresarse como NNNN-NNNNNN.");
            return false;
        }
        if (comboBox.getSelectedIndex() == 0) {
            showError("Debe seleccionar un concurso.");
            return false;
        }
        return true;
    }

    private void showError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
