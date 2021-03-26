/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ricochet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;


public class AppUI extends JFrame implements ActionListener  {
    
    private JPanel pnlTitle = new JPanel();
    private JPanel pnlField = new JPanel();
    private JPanel pnlControl = new JPanel();
    private Clockhandlerclass clockhandler;
    
    private JTextField inputSpeedTextField = new JTextField();
    
    private JButton startButton = new JButton("Start");
    private JButton quitButton = new JButton("Quit");
    private JButton clearButton = new JButton("Clear");
    
    private Timer timer;
    
    
    public AppUI() {
        this.setLayout(new BorderLayout(3, 1));
       
        pnlTitle.setPreferredSize(new Dimension(50, 50));
        pnlField.setPreferredSize(new Dimension(100, 100));
        pnlControl.setPreferredSize(new Dimension(200, 200));
        
        this.add(pnlTitle, BorderLayout.PAGE_START);
        this.add(pnlField, BorderLayout.CENTER);
        this.add(pnlControl, BorderLayout.SOUTH);

        /*
        Title Panel Setup
        */
        pnlTitle.setBackground(Color.YELLOW);
        pnlTitle.add(new JLabel("Diamond Animation by Quentin May"));

        /*
        Field Panel Setup
        */
        pnlField.setBackground(Color.GREEN);
        pnlField.setLayout(new GridLayout(4, 2, 5, 5));

        /*
        Control Panel Setup
        */
        pnlControl.setBackground(Color.PINK);
        pnlControl.setLayout(new GridLayout(1, 4, 5, 5));


        pnlControl.add(startButton);
        startButton.setBackground(Color.WHITE);
        startButton.addActionListener(this);

        pnlControl.add(new JLabel("Speed:"));
        pnlControl.add(inputSpeedTextField);

        pnlControl.add(quitButton);
        quitButton.setBackground(Color.RED);
        quitButton.addActionListener(this);

        /*
        Clock Handler Setup
        */
        clockhandler = new Clockhandlerclass();
        timer = new Timer(10, clockhandler); //Cant change speed that it updates without changing the distance travelled manually in Quad.

        this.setSize(1920,1040);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("223J Assignment 2");
        this.setVisible(true); 
    }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startButton) {
                if (startButton.getText() == "Start") { //When click start, initialize the ball then change the text to pause
                    try {
                        double speed = Double.parseDouble(inputSpeedTextField.getText());
                        if (Double.isNaN(speed) || speed <= 0) throw new Exception(); // If the inputSpeed isnt a double or speed is negative or zero, throw an error;
//                        pnlField.initializeBall(speed); // needs to be switched to the double parser that the professor prefers.
                        startButton.setText("Pause");
                        System.out.println("Start Button called");
                        timer.start();
                    } catch (Exception err) {
                        inputSpeedTextField.setText("ERROR");
                    }
                } else if (startButton.getText() == "Pause") { //When paused, just stop timer and change button to resume.
                    timer.stop();
                    startButton.setText("Resume");
                } else if (startButton.getText() == "Resume") { //When resume, simply just start timer and change button to pause.
                    timer.start();
                    startButton.setText("Pause");
                }
            } else if (e.getSource() == quitButton) {
                System.exit(0);
            } else if (e.getSource() == clearButton) {
             //clear the boxes   
            }
        }

         private class Clockhandlerclass implements ActionListener {   
             public void actionPerformed(ActionEvent event) {

                 if (event.getSource() == timer) {
                         pnlField.repaint();


                
            }
         }
    }
}
