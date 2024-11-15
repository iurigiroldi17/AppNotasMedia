import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AplicativodeNotas extends JFrame {
    private JTextField tfNota;
    private JTextArea taNotas;
    private JButton btnAdicionar, btnCalcular;
    private JLabel lblResultado;
    private ArrayList<Double> notas;
    private JPanel AplicativoNotas;

    public AplicativodeNotas() {
        setTitle("Aplicativo de Notas");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        tfNota = new JTextField(10);
        taNotas = new JTextArea(5, 20);
        taNotas.setEditable(false);
        btnAdicionar = new JButton("Adicionar Nota");
        btnCalcular = new JButton("Calcular Média");
        lblResultado = new JLabel("Média: - Status: -");
        notas = new ArrayList<>();

        add(new JLabel("Informe a Nota:"));
        add(tfNota);
        add(btnAdicionar);
        add(new JScrollPane(taNotas));
        add(btnCalcular);
        add(lblResultado);

        btnAdicionar.addActionListener(e -> adicionarNota());
        btnCalcular.addActionListener(e -> calcularMedia());
    }

    private void adicionarNota() {
        try {
            double nota = Double.parseDouble(tfNota.getText());
            if (nota >= 0 && nota <= 10) {
                notas.add(nota);
                taNotas.append("Nota: " + nota + "\n");
                tfNota.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Nota deve ser entre 0 e 10", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }  catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Nota inválida", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calcularMedia() {
        if (notas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma nota inserida", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double media = notas.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        String status = media >= 7.0 ? "Aprovado" : "Reprovado";
        lblResultado.setText(String.format("Média: %.2f Status: %s", media, status));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AplicativodeNotas().setVisible(true));
    }
}


 