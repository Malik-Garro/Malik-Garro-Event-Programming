import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Activity 6: Complete Interactive Application
 * Integrates ActionListeners, ItemListeners, DocumentListeners, and Key/Mouse events
 * into a unified interactive Swing application showcase.
 */
public class InteractiveShowcaseApp extends JFrame {

    public InteractiveShowcaseApp() {
        setTitle("Week 4: Complete Interactive Swing Showcase");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 550);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Tab 1: Button & Actions
        tabbedPane.addTab("Button Actions", createButtonPanel());

        // Tab 2: Item & Selection
        tabbedPane.addTab("Item Listeners", createItemPanel());

        // Tab 3: Text & Document
        tabbedPane.addTab("Document Listeners", createDocumentPanel());

        // Tab 4: Interactive Canvas (Mouse)
        tabbedPane.addTab("Mouse Drawing", createDrawingPanel());

        add(tabbedPane);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel header = new JLabel("ActionListener Demo (Lambda, Method Ref, Inner Class)", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(header, BorderLayout.NORTH);

        JLabel status = new JLabel("Click any button below to fire an event", SwingConstants.CENTER);
        status.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(status, BorderLayout.CENTER);

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton btnLambda = new JButton("Lambda Button");
        JButton btnMethodRef = new JButton("Method Reference");
        JButton btnAnonymous = new JButton("Anonymous Inner");

        btnLambda.addActionListener(e -> status.setText("Lambda listener fired: " + e.getActionCommand()));
        btnMethodRef.addActionListener(this::handleMethodRef);
        btnAnonymous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                status.setText("Anonymous inner class listener fired!");
            }
        });

        buttonRow.add(btnLambda);
        buttonRow.add(btnMethodRef);
        buttonRow.add(btnAnonymous);
        panel.add(buttonRow, BorderLayout.SOUTH);

        return panel;
    }

    private void handleMethodRef(ActionEvent e) {
        JOptionPane.showMessageDialog(this,
                "Method reference listener triggered successfully!",
                "Method Reference Event",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private JPanel createItemPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Select Options (ItemListener in Real-Time):", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(title);

        JPanel options = new JPanel(new FlowLayout());
        JCheckBox darkTheme = new JCheckBox("Dark Mode Preview");
        JRadioButton opt1 = new JRadioButton("Option A", true);
        JRadioButton opt2 = new JRadioButton("Option B");
        ButtonGroup bg = new ButtonGroup();
        bg.add(opt1);
        bg.add(opt2);

        options.add(darkTheme);
        options.add(opt1);
        options.add(opt2);
        panel.add(options);

        JLabel result = new JLabel("Option A selected | Normal Mode", SwingConstants.CENTER);
        result.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(result);

        ItemListener updater = e -> {
            String theme = darkTheme.isSelected() ? "Dark Mode" : "Normal Mode";
            String sel = opt1.isSelected() ? "Option A" : "Option B";
            result.setText(sel + " selected | " + theme);
        };

        darkTheme.addItemListener(updater);
        opt1.addItemListener(updater);
        opt2.addItemListener(updater);

        return panel;
    }

    private JPanel createDocumentPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel prompt = new JLabel("Live Search / Text Filtering (DocumentListener):");
        panel.add(prompt, BorderLayout.NORTH);

        JTextField field = new JTextField();
        JLabel echo = new JLabel("Character count: 0 | Live preview: (empty)");

        field.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void update() {
                String text = field.getText();
                echo.setText("Character count: " + text.length() + " | Live preview: " + text);
            }
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) { update(); }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) { update(); }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) { }
        });

        panel.add(field, BorderLayout.CENTER);
        panel.add(echo, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createDrawingPanel() {
        JPanel wrapper = new JPanel(new BorderLayout());
        JLabel hint = new JLabel("Drag mouse on canvas to draw points (MouseMotionListener)", SwingConstants.CENTER);
        wrapper.add(hint, BorderLayout.NORTH);

        java.util.List<Point> points = new java.util.ArrayList<>();
        JPanel canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(30, 100, 200));
                for (Point p : points) {
                    g.fillOval(p.x - 4, p.y - 4, 8, 8);
                }
            }
        };
        canvas.setBackground(Color.WHITE);

        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                points.clear();
                points.add(e.getPoint());
                canvas.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                points.add(e.getPoint());
                canvas.repaint();
            }
        };

        canvas.addMouseListener(ma);
        canvas.addMouseMotionListener(ma);
        wrapper.add(canvas, BorderLayout.CENTER);
        return wrapper;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InteractiveShowcaseApp().setVisible(true));
    }
}
