package ar.edu.et7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JTextField montoField;
    private JTextField tnaField;
    private JTextField cuotasField;
    private JButton botonCalcular;
    private JTextArea resultado;
    private Calculos calc;

    public MainWindow() {
        // Configuración de la ventana principal
        setTitle("Calculadora de Cuotas Alemanas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Crear los componentes
        montoField = new JTextField(10);
        tnaField = new JTextField(10);
        cuotasField = new JTextField(10);
        botonCalcular = new JButton("Calcular");
        resultado = new JTextArea(10, 30);
        resultado.setEditable(false);

        this.calc = new Calculos();
        
        // Panel para los inputs
        JPanel panelInputs = new JPanel();
        panelInputs.setLayout(new GridLayout(4, 2));
        panelInputs.add(new JLabel("Monto:"));
        panelInputs.add(montoField);
        panelInputs.add(new JLabel("TNA (%):"));
        panelInputs.add(tnaField);
        panelInputs.add(new JLabel("Cuotas:"));
        panelInputs.add(cuotasField);
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
            float monto = Float.parseFloat(montoField.getText());
            float tna = Float.parseFloat(tnaField.getText());
            int cuotas = Integer.parseInt(cuotasField.getText());

            // Calcular las cuotas alemanas
            float[] cuotasAlemanas = calc.calcularCuotasAlemanas(monto, tna, cuotas);

            // Mostrar el resultado en el área de texto
            StringBuilder resultadoText = new StringBuilder();
            for (int i = 0; i < cuotasAlemanas.length; i++) {
                resultadoText.append("Cuota ").append(i + 1).append(": ").append(String.format("%.2f", cuotasAlemanas[i])).append("\n");
            }
            resultado.setText(resultadoText.toString());
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
