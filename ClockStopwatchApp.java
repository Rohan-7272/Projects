package basics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockStopwatchApp extends JFrame implements ActionListener {
    JLabel clockLabel, stopwatchLabel;
    JButton startBtn, stopBtn, resetBtn;
    Timer clockTimer;
    Timer stopwatchTimer;
    int elapsedSeconds = 0;
    boolean stopwatchRunning = false;

    public ClockStopwatchApp() {
        setTitle("Clock and Stopwatch");
        setSize(500, 300);
        setLayout(new GridLayout(4, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        clockLabel = new JLabel();
        clockLabel.setFont(new Font("Arial", Font.BOLD, 30));
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        add(clockLabel);

        stopwatchLabel = new JLabel("00:00:00");
        stopwatchLabel.setFont(new Font("Arial", Font.BOLD, 40));
        stopwatchLabel.setHorizontalAlignment(JLabel.CENTER);
        add(stopwatchLabel);

        JPanel buttonPanel = new JPanel();
        startBtn = new JButton("Start");
        stopBtn = new JButton("Stop");
        resetBtn = new JButton("Reset");

        startBtn.addActionListener(this);
        stopBtn.addActionListener(this);
        resetBtn.addActionListener(this);

        buttonPanel.add(startBtn);
        buttonPanel.add(stopBtn);
        buttonPanel.add(resetBtn);
        add(buttonPanel);

        clockTimer = new Timer(1000, e -> updateClock());
        clockTimer.start();
        stopwatchTimer = new Timer(1000, e -> updateStopwatch());
        setVisible(true);
    }
    private void updateClock() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String time = timeFormat.format(new Date());
        clockLabel.setText("Current Time: " + time);
    }
    private void updateStopwatch() {
        elapsedSeconds++;
        int hours = elapsedSeconds / 3600;
        int minutes = (elapsedSeconds % 3600) / 60;
        int seconds = elapsedSeconds % 60;
        stopwatchLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startBtn) {
            if (!stopwatchRunning) {
                stopwatchTimer.start();
                stopwatchRunning = true;
            }
        } else if (e.getSource() == stopBtn) {
            stopwatchTimer.stop();
            stopwatchRunning = false;
        } else if (e.getSource() == resetBtn) {
            stopwatchTimer.stop();
            stopwatchRunning = false;
            elapsedSeconds = 0;
            stopwatchLabel.setText("00:00:00");
        }
    }
    public static void main(String[] args) {
        new ClockStopwatchApp();
    }
}
