package memorygame;

import java.awt.Image;
import java.lang.Math;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.GridLayout;


@SuppressWarnings("serial")
public class MainFrame extends javax.swing.JFrame implements ActionListener {
	private  int img_v=1;
	
	public MainFrame(int img) {
		img_v = img;
		initComponents();
	    initIcons();
	    initGame();
	}
    public MainFrame() {
        initComponents();
        initIcons();
        initGame();
    }
    
///////////////////////////////////////////////////////////////////////////////////////////

    private void initGame() {
        score = 0;
        int x = 0;
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile(icons[x], new ImageIcon(getClass().getResource("/images/logo.png")));
            tiles[i].addActionListener(this);
            gamePanel.add(tiles[i]);
            if ((i + 1) % 2 == 0) {
                x++;
            }
        }
        title.setText("Score: " + score);
        shuffle();

    }

    private void initIcons() {
        Image img;
        for (int i = 0; i < icons.length; i++) {
            img = new ImageIcon(getClass().getResource("/images/img" + i + ".png")).getImage();
            icons[i] = createIcon(img);
        }

    }

    private ImageIcon createIcon(Image img) {
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        bi.createGraphics().drawImage(img, 0, 0, null);
        img = bi.getScaledInstance(70,70, 1);
        return new ImageIcon(img);
    }

   //////////////////////////////////////////////////////////////////////////////////////////7777777
    
    
    
    
    
    
    
    
    
    
    private void showHelp() {
        //if tiles[0] would be null than all the tiles would be null here
        if (tiles[0] != null) {
            for (int i = 0; i < tiles.length; i++) {
                if (!tiles[i].isNoIcon()) {
                    tiles[i].showTile();
                    tiles[i].removeActionListener(this);
                }
            }
            score -= 50;
            title.setText("Score: " + score);
        }
    }

    
    
    
    
    
    
    
    
    private void hideHelp() {

        for (int i = 0; i < tiles.length; i++) {
            if (!tiles[i].isNoIcon()) {
                tiles[i].hideTile();
                tiles[i].addActionListener(this);
            }
        }

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    private void check() {

        if (predict1 != predict2 && predict1.getImage() == predict2.getImage()) {
            new Thread() {
                @Override
                public void run() {
                    Sound sound = null;
                    try {
                        sound
                                = new Sound(getClass().getResource("/sounds/guess.wav"));
                    } catch (Exception e) {
                    }
                    InputStream stream
                            = new ByteArrayInputStream(sound.getSamples());
                    sound.play(stream);
                }
            }.start();//sound 
            new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 3; i++) {
                        try {
                            predict1.hideTile();
                            predict2.hideTile();
                            Thread.sleep(100);
                            predict1.showTile();
                            predict2.showTile();
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            System.out.println(ex);
                        }
                    }
                    predict1.setNoIcon();
                    predict2.setNoIcon();
                    for (int i = 0; i < tiles.length; i++) {
                        if (!tiles[i].isNoIcon()) {
                            won = false;
                            break;
                        } else {
                            won = true;
                        }
                    }
                    if (won) {
                        if (score > 0) {
                            new Thread() {
                                @Override
                                public void run() {
                                    Sound sound = null;
                                    try {
                                        sound
                                                = new Sound(getClass().getResource("/sounds/won.wav"));
                                    } catch (Exception e) {
                                    }
                                    InputStream stream
                                            = new ByteArrayInputStream(sound.getSamples());
                                    sound.play(stream);
                                }
                            }.start();//won sound
                            JOptionPane.showMessageDialog(gamePanel, "You Won! Your Score is " + score);
                        } else {
                            new Thread() {
                                @Override
                                public void run() {
                                    Sound sound = null;
                                    try {
                                        sound
                                                = new Sound(getClass().getResource("/sounds/loose.wav"));
                                    } catch (Exception e) {
                                    }
                                    InputStream stream
                                            = new ByteArrayInputStream(sound.getSamples());
                                    sound.play(stream);
                                }
                            }.start();//loose sound
                            JOptionPane.showMessageDialog(gamePanel, "You Loose! Your Score is " + score);
                        }
                        initGame();
                    }
                }
            }.start();//animation
            predict1.removeActionListener(this);
            predict2.removeActionListener(this);
            score += 10;
            title.setText("Score: " + score);

        } else {
            predict1.hideTile();
            predict2.hideTile();
            score -= 10;
            title.setText("Score: " + score);
        }
    }

    private void shuffle() {
        gamePanel.removeAll();
        ArrayList<Integer> al = new ArrayList<Integer>();
        for (int i = 0; i < Math.pow(img_v,2);) {
            int x = (int) (Math.random() * Math.pow(img_v,2));
            if (!al.contains(x)) {
                al.add(x);
                i++;
            }
        }
        for (int i = 0; i < Math.pow(img_v,2); i++) {
            gamePanel.add(tiles[al.get(i)]);
            tiles[al.get(i)].hideTile();
        }
    }

    
    
    
    
     
    
    
   
    @SuppressWarnings("unchecked")

    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        title = new javax.swing.JTextField();
        title.setBounds(25, 0, 350, 40);
        close = new javax.swing.JLabel();
        close.setBounds(375, 0, 25, 40);
        help = new javax.swing.JLabel();
        help.setBounds(0, 0, 25, 40);
        gamePanel = new javax.swing.JPanel();
        controlPanel = new javax.swing.JPanel();
        play = new javax.swing.JButton();
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Matching Game");
        setBackground(new java.awt.Color(0, 255, 0));
        setIconImage(new ImageIcon(getClass().getResource("/images/logo.png")).getImage());
        setLocationByPlatform(true);
        setName("MainFrame"); // NOI18N
        setUndecorated(true);

        titlePanel.setBackground(new java.awt.Color(196, 196, 196));
        titlePanel.setPreferredSize(new java.awt.Dimension(50, 40));

        title.setEditable(false);
        title.setBackground(new java.awt.Color(169, 169, 169));
        title.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        title.setText("Score: ");
        title.setToolTipText("After Clicking mouse here use arrow keys to move");
        title.setBorder(null);
        title.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        title.setSelectionColor(new java.awt.Color(153, 153, 255));
        title.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                titleMouseDragged(evt);
            }
        });
        title.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                titleKeyPressed(evt);
            }
        });
        titlePanel.setLayout(null);
        titlePanel.add(title);

        close.setBackground(new java.awt.Color(255, 0, 0));
        close.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        close.setForeground(new java.awt.Color(255, 0, 0));
        close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close.setText("X");
        close.setToolTipText("Close");
        close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        close.setPreferredSize(new java.awt.Dimension(25, 25));
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });
        titlePanel.add(close);

        help.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        help.setForeground(new java.awt.Color(0, 0, 255));
        help.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        help.setText("?");
        help.setToolTipText("Right click to hide controls and Left click to see Images");
        help.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        help.setPreferredSize(new java.awt.Dimension(25, 25));
        help.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                helpMouseClicked(evt);
            }
        });
        titlePanel.add(help);

        getContentPane().add(titlePanel, java.awt.BorderLayout.NORTH);

        gamePanel.setBackground(new Color(192, 192, 192));
        gamePanel.setPreferredSize(new java.awt.Dimension(400,400));
        getContentPane().add(gamePanel, java.awt.BorderLayout.CENTER);
        gamePanel.setLayout(new GridLayout(img_v, img_v, 2, 2));

        controlPanel.setBackground(new java.awt.Color(153, 153, 255));
        controlPanel.setPreferredSize(new java.awt.Dimension(300, 40));
        controlPanel.setLayout(new java.awt.GridLayout(1, 2));

        play.setBackground(new Color(192, 192, 192));
        play.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        play.setForeground(new java.awt.Color(0, 0, 0));
        play.setText("PLAY");
        play.setToolTipText("Play new Game");
        play.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playActionPerformed(evt);
            }
        });
        controlPanel.add(play);

      

        getContentPane().add(controlPanel, java.awt.BorderLayout.SOUTH);
        
        btnNewButton = new JButton("main menu");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mainMenu m = new mainMenu();
        		dispose();
        		m.setVisible(true);
        	}
        });
        btnNewButton.setBackground(new Color(192, 192, 192));
        btnNewButton.setIcon(new ImageIcon("C:\\Users\\Pro\\Downloads\\left-arrow.png"));
        controlPanel.add(btnNewButton);

        pack();
    }

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            this.dispose();
        }
    }

    private void helpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (!helping) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            helping = true;
                            showHelp();
                            Thread.sleep(2000);
                            hideHelp();
                            helping = false;
                        } catch (InterruptedException ex) {
                            System.out.println(ex);
                        }
                    }
                }.start();
            }
        }
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (controlPanel.isVisible()) {
                setSize(600, 625);
                controlPanel.setVisible(false);
            } else {
                setSize(600, 665);
                controlPanel.setVisible(true);
            }
        }
    }//GEN-LAST:event_helpMouseClicked

    private void titleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_titleKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            setLocation(getX() - 5, getY());
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            setLocation(getX() + 5, getY());
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            setLocation(getX(), getY() - 5);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            setLocation(getX(), getY() + 5);
        }
    }//GEN-LAST:event_titleKeyPressed


    private void playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playActionPerformed
        initGame();
    }//GEN-LAST:event_playActionPerformed

    private void titleMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleMouseDragged
        setLocation(evt.getXOnScreen() - 300, evt.getYOnScreen());
    }//GEN-LAST:event_titleMouseDragged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
  
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
           
                new MainFrame().setVisible(true);
            }
        });
    }

    Tile[] tiles = new Tile[36];
    ImageIcon[] icons = new ImageIcon[18];
    int status, score;
    Tile predict1, predict2;
    private boolean won, helping;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel close;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JLabel help;
    private javax.swing.JButton play;
    private javax.swing.JTextField title;
    private javax.swing.JPanel titlePanel;
    private JButton btnNewButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        if (status == 0) {
            predict1 = (Tile) e.getSource();
            predict1.showTile();
            status++;
        } else if (status == 1) {
            status++;
            predict2 = (Tile) e.getSource();
            new Thread() {
                @Override
                public void run() {
                    try {
                        predict2.showTile();
                        Thread.sleep(500);
                        check();
                        Thread.sleep(600);
                        status = 0;
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }.start();

        }
    }
}
