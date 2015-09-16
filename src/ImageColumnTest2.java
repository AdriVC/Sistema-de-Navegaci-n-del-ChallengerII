//        
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//
//public class firstGUI extends JFrame
//     implements ActionListener {
//
//     private boolean showText = false;
//     private boolean showRect = true;
//     private boolean showOval = false;
//     private JButton text;
//     private JButton oval;
//     private JButton rectangle;
//     private JPanel buttonPanel;
//     private DrawStuff drawPanel = new DrawStuff();
//
//     public firstGUI() {
//         super("First GUI");
//         setSize(512, 512);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//         buttonPanel = new JPanel();
//         buttonPanel.setLayout(new GridLayout(1, 3));
//
//         text = new JButton("Text");
//         text.addActionListener(this);
//         buttonPanel.add(text);
//
//         oval = new JButton("Oval");
//         oval.addActionListener(this);
//         buttonPanel.add(oval);
//
//         rectangle = new JButton("Rectangle");
//         rectangle.addActionListener(this);
//         buttonPanel.add(rectangle);
//
//
//         Container contentPane = this.getContentPane();
//         contentPane.add(buttonPanel, BorderLayout.NORTH);
//         add(drawPanel);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent event) {
//        Object source = event.getSource();
//
//        if (source == text) {
//            showText = true;
//            repaint();
//        } else if (source == oval) {
//            showOval = true;
//            repaint();
//        } else if (source == rectangle) {
//            showRect = true;
//            repaint();
//        }
//    }
//
//    public static void main(String[] args) {
//        firstGUI myTest = new firstGUI();
//        myTest.setVisible(true);
//    }
//
//    class DrawStuff extends JPanel {
//
//        @Override
//        public void paintComponent(Graphics g) {
//            super.paintComponent(g);
//
//            if (showText) {
//                g.drawString("Hello", getHeight() / 2, getWidth() / 2);
//                showText = false;
//            } else if (showOval) {
//                g.drawOval(getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2);
//                showOval = false;
//            } else if (showRect) {
//                g.drawRect(getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2);
//                showRect = false;
//            }
//        }
//    }
//} 

//import java.awt.*;
//import java.awt.geom.*;
//import javax.swing.*;
//  
//public class ArrowHead
//{
//    public ArrowHead()
//    {
//        JFrame f = new JFrame();
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.getContentPane().add(new ArrowPanel());
//        f.setSize(400,400);
//        f.setLocation(200,200);
//        f.setVisible(true);
//    }
//  
//    public static void main(String[] args)
//    {
//        new ArrowHead();
//    }
//}
//  
//class ArrowPanel extends JPanel
//{
//    double phi;
//    int barb;
//  
//    public ArrowPanel()
//    {
//        phi = Math.toRadians(40);
//        barb = 20;
//    }
//  
//    @Override
//    protected void paintComponent(Graphics g)
//    {
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D)g;
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                            RenderingHints.VALUE_ANTIALIAS_ON);
//        int w = getWidth();
//        int h = getHeight();
//        Point sw = new Point(w/8, h*7/8);
//        Point ne = new Point(w*7/8, h/8);
//        g2.draw(new Line2D.Double(sw, ne));
//        drawArrowHead(g2, sw, ne, Color.red);
//        drawArrowHead(g2, ne, sw, Color.blue);
//    }
//  
//    private void drawArrowHead(Graphics2D g2, Point tip, Point tail, Color color)
//    {
//        g2.setPaint(color);
//        double dy = tip.y - tail.y;
//        double dx = tip.x - tail.x;
//        double theta = Math.atan2(dy, dx);
//        //System.out.println("theta = " + Math.toDegrees(theta));
//        double x, y, rho = theta + phi;
//        for(int j = 0; j < 2; j++)
//        {
//            x = tip.x - barb * Math.cos(rho);
//            y = tip.y - barb * Math.sin(rho);
//            g2.draw(new Line2D.Double(tip.x, tip.y, x, y));
//            rho = theta - phi;
//        }
//    }
//}

import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ImageColumnTest2 {
   //public static final String IMAGE_SHEET_PATH = "./Resources/Sprites/";
   public static final String[] COUNTRIES = {
      "Denmark", "China", "Chile", "Canada", "Belgium", "Austria",
      "Argentina", "France", "Malaysina", "Lebanon", "Korea", "Japan",
      "Italy", "Ireland", "India", "Hong Kong", "Greece", "Germany"
   };
   public static final int COLS = 6;
   public static final int ROWS = 3;
   private static final String[] COL_NAMES = {"Country", "Flag"};

   private JTable table = new JTable();
   private JScrollPane mainPane = new JScrollPane(table);

    public ImageColumnTest2() throws IOException {
        DefaultTableModel model = new DefaultTableModel(COL_NAMES, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (getRowCount() > 0) {
                    Object value = getValueAt(0, column);
                    if (value != null) {
                        return getValueAt(0, column).getClass();
                    }
                }

                return super.getColumnClass(column);
            }
        };

        for (int col = 0; col < COUNTRIES.length; col++) {
            ImageIcon icon = new ImageIcon("./Resources/Sprites/p" + (col + 1) + ".png");
            String country = COUNTRIES[col];
            Object[] rowData = {country, icon};
            model.addRow(rowData);
        }

        table.setModel(model);
        table.setRowHeight(((ImageIcon) model.getValueAt(0, 1)).getIconHeight());
    }

   public JComponent getMainComponent() {
      return mainPane;
   }

   private static void createAndShowGui() {
      ImageColumnTest2 imgColumnTest = null;
      try {
         imgColumnTest = new ImageColumnTest2();
      } catch (MalformedURLException e) {
         e.printStackTrace();
         System.exit(-1);
      } catch (IOException e) {
         e.printStackTrace();
         System.exit(-1);
      }

      JFrame frame = new JFrame("ImageColumnTest");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(imgColumnTest.getMainComponent());
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}