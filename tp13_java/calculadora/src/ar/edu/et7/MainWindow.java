package ar.edu.et7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JTextField campoMonto;
    private JTextField campoTNA;
    private JTextField campoCuotas;
    private JButton botonCalcular;
    private JTextArea resultado;
    private Calculos calc;

    public MainWindow() {
        // Configuración de la ventana principal
        setTitle("Calculadora de Cuotas Alemanas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Crear los componentes
        campoMonto = new JTextField(10);
        campoTNA = new JTextField(10);
        campoCuotas = new JTextField(10);
        botonCalcular = new JButton("Calcular");
        resultado = new JTextArea(10, 30);
        resultado.setEditable(false);

        this.calc = new Calculos();
        
        // Panel para los inputs
        JPanel panelInputs = new JPanel();
        panelInputs.setLayout(new GridLayout(4, 2));
        panelInputs.add(new JLabel("Monto:"));
        panelInputs.add(campoMonto);
        panelInputs.add(new JLabel("TNA (%):"));
        panelInputs.add(campoTNA);
        panelInputs.add(new JLabel("Cuotas:"));
        panelInputs.add(campoCuotas);
        panelInputs.add(new JLabel(""));  // Placeholder
        panelInputs.add(botonCalcular);

        // Panel para el resultado
        JPanel panelResultado = new JPanel();
        panelResultado.setLayout(new BorderLayout());
        panelResultado.add(new JLabel("Resultado:"), BorderLayout.NORTH);
        panelResultado.add(new JScrollPane(resultado), BorderLayout.CENTER);

        // Agregar los paneles a la ventana
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelInputs, BorderLayout.NORTH);
        getContentPane().add(panelResultado, BorderLayout.CENTER);

        // Configurar la acción del botón
        botonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });
    }

    private void calcular() {
        try {
            // Obtener los valores de los campos de texto
            float monto = Float.parseFloat(campoMonto.getText());
            float tna = Float.parseFloat(campoTNA.getText());
            int cuotas = Integer.parseInt(campoCuotas.getText());

            // Calcular las cuotas
            float[] cuotasMensuales = calc.calcularCuotasAleman(monto, tna, cuotas);

            // Mostrar el resultado en el área de texto
            StringBuilder resultadoTexto = new StringBuilder("Cuotas:\n");
            for (int i = 0; i < cuotasMensuales.length; i++) {
                resultadoTexto.append(String.format("Cuota %d: %.2f%n", i + 1, cuotasMensuales[i]));
            }
            resultado.setText(resultadoTexto.toString());
        } catch (NumberFormatException ex) {
            // Manejar el caso en que los inputs no sean números válidos
            resultado.setText("Por favor, ingrese valores válidos.");
        }
    }

    public static void main(String[] args) {
        // Crear y mostrar la ventana
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
}

