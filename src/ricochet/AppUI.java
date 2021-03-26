/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ricochet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.*;
import java.awt.GridBagLayout;
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
    private RicochetField pnlField = new RicochetField();
    private JPanel pnlControl = new JPanel();
    
    private GridBagLayout controlGrid = new GridBagLayout();
    private Clockhandlerclass clockhandler;
    
    private JTextField inputRefreshRateTextField = new JTextField("100");
    private JTextField inputSpeedTextField = new JTextField("800");
    private JTextField inputDirectionTextField = new JTextField("120");
    private JTextField inputLocationX = new JTextField("400");
    private JTextField inputLocationY = new JTextField("200");
    
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
        pnlControl.setLayout(controlGrid);
        

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 1;
        c.weightx = .01;
        c.weighty = .2;
        c.gridx = 0;
        c.gridy = 0;
        
        pnlControl.add(clearButton, c);
        clearButton.setBackground(Color.WHITE);
        clearButton.addActionListener(this);
        
        
//        startButton.setPreferredSize(new Dimension(50, 50));

        c.gridx = 1;
        pnlControl.add(startButton, c);
        startButton.setBackground(Color.WHITE);
        startButton.addActionListener(this);
        
        
        c.gridx = 2;
        pnlControl.add(quitButton, c);
        quitButton.setBackground(Color.WHITE);
        quitButton.addActionListener(this);
        
        
        c.gridx = 3;
        JPanel nestedLocation = new JPanel();
        nestedLocation.setBackground(Color.PINK);
        nestedLocation.setLayout(new GridLayout(3, 2, 5, 20));
        nestedLocation.add(new JLabel("Ball Location:"));
        nestedLocation.add(new JLabel(" "));
        nestedLocation.add(new JLabel("X:"));
        nestedLocation.add(inputLocationX);
        nestedLocation.add(new JLabel("Y:"));
        nestedLocation.add(inputLocationY);
        pnlControl.add(nestedLocation, c);

        
        c.gridx = 0;
        c.gridy = 1;
        JPanel nestedRefreshRate = new JPanel();
        nestedRefreshRate.setBackground(Color.PINK);
        nestedRefreshRate.setLayout(new GridLayout(2, 1));
        nestedRefreshRate.add(new JLabel("Refresh Rate (Hz)"));
        nestedRefreshRate.add(inputRefreshRateTextField);
        pnlControl.add(nestedRefreshRate, c);

        c.gridx = 1;
        JPanel nestedSpeed = new JPanel();
        nestedSpeed.setBackground(Color.PINK);
        nestedSpeed.setLayout(new GridLayout(2, 1));
        nestedSpeed.add(new JLabel("Speed (pix/sec)"));
        nestedSpeed.add(inputSpeedTextField);
        pnlControl.add(nestedSpeed, c);
        
        c.gridx = 2;
        JPanel nestedDirection = new JPanel();
        nestedDirection.setBackground(Color.PINK);
        nestedDirection.setLayout(new GridLayout(2, 1));
        nestedDirection.add(new JLabel("Direction (Degrees)"));
        nestedDirection.add(inputDirectionTextField);
        pnlControl.add(nestedDirection, c);
        
        
        /*
        Clock Handler Setup
        */
        clockhandler = new Clockhandlerclass();
        timer = new Timer(10, clockhandler);
        
        
        this.setSize(1920,1040);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ricochet Ball");
        this.setVisible(true); 
    }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startButton) {
                if (startButton.getText() == "Start") { //When click start, initialize the ball then change the text to pause
                    System.out.println("Start Button called");
                    try {
                        double speed = Double.parseDouble(inputSpeedTextField.getText());
                        if (Double.isNaN(speed) || speed <= 0) throw new Exception(); // If the inputSpeed isnt a double or speed is negative or zero, throw an error;
                        double refreshRate = Double.parseDouble(inputRefreshRateTextField.getText());
                        if (Double.isNaN(refreshRate) || refreshRate <= 0) throw new Exception();
                        double direction = Double.parseDouble(inputDirectionTextField.getText());
                        if (Double.isNaN(direction) || direction < 0 || direction > 360) throw new Exception();
                        double locationX = Double.parseDouble(inputLocationX.getText());
                        if (Double.isNaN(locationX) || locationX < 0) throw new Exception();
                        double locationY = Double.parseDouble(inputLocationY.getText());
                        if (Double.isNaN(locationY) || locationY < 0) throw new Exception();
                        
                        
                        pnlField.initializeBall(speed, locationX, locationY, refreshRate, direction);
                        

                        //delay= 1000ms/hz. Converts refresh rate into delay between ticks.
                        System.out.println("Delay: " + (int)(1000/refreshRate));
                        timer.setDelay((int)(1000/refreshRate));
                        
                        timer.start();
                        startButton.setText("Pause");
                        
                    } catch (Exception err) {
                        System.out.println("Error");
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
                timer.stop();
                inputSpeedTextField.setText("");
                inputRefreshRateTextField.setText("");
                inputDirectionTextField.setText("");
                inputLocationX.setText("");
                inputLocationY.setText("");
                startButton.setText("Start");
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
