import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import java.awt.BorderLayout;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class guiScreen extends JPanel
{
    static int h;
    static int w;
    static String s;
    static String enteredString ;
    static int table[][];
    static Map<String, Integer> medMap = new Hashtable<>();
    public guiScreen()
    {
        super();
        setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g)
    {
        Font  f = new Font("Times New Roman", Font.PLAIN, 20);
        g.setFont(f);
        g.setColor(Color.black);

        super.paintComponent(g);

        int i = 0;
        boolean atLeast = true;
        int len =-1;
        g.drawString("Entered String = ", 10, 20);
        g.drawString(enteredString,200,20);
        g.drawString("word", 10, 50);
        g.drawString("minimum edit distance", 250, 50);
        do {
            if(i<5){
                len++;
            }
            for (Map.Entry<String, Integer> entry : medMap.entrySet()) {

                if (entry.getValue() == (len)) {
                    g.drawString(entry.getKey(), 10, 50 + (i+1) * 30);
                    g.drawString(entry.getValue().toString(), 300, 50 + (i+1) * 30);
                    //System.out.println(entry.getKey());
                    i++;
                }
                if (i == 5) {
                    atLeast=false;
                    break;
                }
            }
        }while(atLeast);
    }

    public static void main(String[] args){
        JButton open = new JButton();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("C:"));
        fileChooser.setDialogTitle("Please choose list of words");

        if (fileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
            System.out.println("okay");
        }
        File selectedFile = fileChooser.getSelectedFile();
        while(true) {
            enteredString = JOptionPane.showInputDialog("Enter String:");
            if(enteredString==null)
                break;

            long startTime = System.nanoTime();

            findMED(selectedFile);

            long endTime = System.nanoTime();
            long totalTime = TimeUnit.NANOSECONDS.toSeconds(endTime - startTime);
            long nanotime = endTime-startTime;
            System.out.println(totalTime + " seconds find med " + enteredString);
            System.out.println(nanotime +" nanoseconds find med" + enteredString);

            guiScreen panel = new guiScreen();
            JFrame application = new JFrame();

            application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            application.add(panel);


            application.setSize((w * 100) + 500, (w * 100) + 400);
            application.setVisible(true);
        }

    }

    static public void findMED(File selectedFile){

        try {


            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile), "ISO-8859-9"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line != "") {
                    getDistance dist = new getDistance();
                    dist.getInput(line, enteredString);
                    dist.populateTable();
                    w = enteredString.length();
                    h = line.length();
                    table = dist.table;
                    medMap.put(line, table[w][h]);

                }
            }
            reader.close();


        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", selectedFile);
            e.printStackTrace();

        }
    }

}