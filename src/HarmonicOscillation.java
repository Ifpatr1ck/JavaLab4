import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HarmonicOscillation extends JFrame {
    private JTextField lengthField, constantField, timerField;
    private JButton startButton, stopButton;
    private JPanel animationPanel;

    private double length, constant;
    private Timer timer;
    private double time = 0;

    public HarmonicOscillation() {
        setTitle("Гармонические колебания");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400); //Размер менюшки
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(); //Создание панели ввода
        inputPanel.setLayout(new FlowLayout());

        //Поле ввода длины отрезка
        JLabel lengthLabel = new JLabel("Длина отрезка (q):");
        lengthField = new JTextField(10); //Размер поля
        inputPanel.add(lengthLabel);
        inputPanel.add(lengthField);

        //Поле ввода константы
        JLabel constantLabel = new JLabel("Константа (w):");
        constantField = new JTextField(10);
        inputPanel.add(constantLabel);
        inputPanel.add(constantField);

        //Поле ввода времени
        JLabel timerLabel = new JLabel("Время (t):");
        timerField = new JTextField(10);
        inputPanel.add(timerLabel);
        inputPanel.add(timerField);


        //Кнопки "Старт" и "Стоп"
        startButton = new JButton("Старт");
        stopButton = new JButton("Стоп");
        inputPanel.add(startButton);
        inputPanel.add(stopButton);

        animationPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                double distance = length * (1 + Math.cos(constant * time)) / 2;
                int x = (int) (distance / length * getWidth());
                g.fillOval(x, getHeight() / 2 - 5, 10, 10);
            }
        };

        add(inputPanel, BorderLayout.NORTH);
        add(animationPanel, BorderLayout.CENTER);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAnimation();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopAnimation();
            }
        });
    }

    private void startAnimation() {
        try {
            length = Double.parseDouble(lengthField.getText());
            constant = Double.parseDouble(constantField.getText());
            time = Double.parseDouble(timerField.getText());

            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    time += 0.01;
                    animationPanel.repaint();
                }
            });

            timer.start();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Некорректный ввод!");
        }
    }

    private void stopAnimation() {
        if (timer != null) {
            timer.stop();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HarmonicOscillation frame = new HarmonicOscillation();
                frame.setVisible(true);
            }
        });
    }
}