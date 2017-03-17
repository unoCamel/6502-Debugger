import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class Debugger {

    public static boolean openFile(){
        DebuggerGUI.fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        FileNameExtensionFilter txtfilter = new FileNameExtensionFilter(
                "txt files (*.txt)", "txt");
        DebuggerGUI.fileChooser.setFileFilter(txtfilter);
        int result = DebuggerGUI.fileChooser.showOpenDialog(DebuggerGUI.frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            DebuggerGUI.textArea.setText("");
            DebuggerGUI.selectedFile = DebuggerGUI.fileChooser.getSelectedFile();
            //System.out.println("Selected file: " + DebuggerGUI.selectedFile.getAbsolutePath());
            try {
                BufferedReader in = new BufferedReader(new FileReader(DebuggerGUI.selectedFile));
                String line = in.readLine();
                while (line != null) {
                    DebuggerGUI.textArea.append(line + "\n");
                    line = in.readLine();
                }
                DebuggerGUI.initialString = DebuggerGUI.textArea.getText();
                return true;
            } catch(IOException ex){
                return false;
            }

        }
        return false;
    }

    public static boolean saveConfirmation(String message){
        int ret = JOptionPane.showConfirmDialog(DebuggerGUI.frame, message, "Save changes?", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ret == JOptionPane.CANCEL_OPTION){
            return true;
        }
        if (ret == JOptionPane.YES_OPTION){
            saveFile();
            return false;
        }
        return false;
    }



    public static void saveAs(){
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(DebuggerGUI.frame) == JFileChooser.APPROVE_OPTION) {
            File tmp = fileChooser.getSelectedFile();
            try{
                //force txt extension. example.xml.txt is okay.
                if(!FilenameUtils.getExtension(tmp.getName()).equalsIgnoreCase("txt")){
                    tmp = new File(tmp.toString() + ".txt");
                    tmp = new File(tmp.getParentFile(), FilenameUtils.getBaseName(tmp.getName()) + ".txt");
                }
                PrintWriter writer = new PrintWriter(tmp);
                writer.print(DebuggerGUI.textArea.getText());
                writer.flush();
                // save to file
            }catch(FileNotFoundException ex){
                System.out.println("file not found");
            }

        }
    }

    public static void saveFile(){
        try{
            if(DebuggerGUI.selectedFile == null || DebuggerGUI.textArea.getText().equals("")){
                saveAs();
            }else{
                PrintWriter writer = new PrintWriter(DebuggerGUI.selectedFile);
                writer.print(DebuggerGUI.textArea.getText());
                writer.flush();
            }

        }catch(FileNotFoundException ex){
            System.out.println("file not found");
        }
    }

    public static boolean isDifferent(){
        return !DebuggerGUI.textArea.getText().equals(DebuggerGUI.initialString);
    }

    public static int checkLine(JTextArea field){
        int lineCounter = 0;
        for (String line : field.getText().split("\\n")){
            //System.out.println(line.subSequence(0, 4) + " and comparing" + "$" + Integer.toHexString(Registers.readPC()));
            if(line.subSequence(0, 4).equals("$" + Integer.toHexString(Registers.readPC()).toUpperCase())){
                return lineCounter;
            } else{
                lineCounter++;
            }
        }

        return 0;
    }

    public static int checkLine(JTextArea field, int stackIndex){
        int lineCounter = 0;
        int numberNewLines = 0;
        for (String line : field.getText().split("\\n")){
            //System.out.println(line.subSequence(0, 4) + " and comparing" + "$" + Integer.toHexString(Registers.readPC()));
            if(line.trim().isEmpty() || line.subSequence(0, 1).equals(";") || line.trim().charAt(0) == ';'){
                numberNewLines++;
            }else{
                if(lineCounter == stackIndex){
                    return lineCounter++ + numberNewLines;}
                lineCounter++;
            }
        }
        return lineCounter + numberNewLines;
    }

    public static boolean assemble(){

        DebuggerGUI.asm = Import.importInstructions(DebuggerGUI.textArea.getText());
        try{
            DebuggerGUI.currentInstructions = DebuggerGUI.asm.getAllInstructions();
            Memory.clean();
            Registers.init_Memory();
            Memory.setMemory(DebuggerGUI.asm.assemble());
            //Memory.instrToString();
            DebuggerGUI.enableButtons();
            DebuggerGUI.updateGUI();
            DebuggerGUI.updateGUI();
        } catch(NullPointerException ex){
            System.out.println("Error found in assembled code.");
            return false;
        }



        return true;
    }

}
