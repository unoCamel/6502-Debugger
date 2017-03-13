import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.EventListener.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Highlighter;
import javax.swing.text.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;


public class DebuggerGUI extends JFrame {

	private JPanel contentPane;
	public static JFrame frame;
	//private DebuggerGUI frame;

    int frameXpos = 200;
    int frameYpos = 200;

	/**
	 * Create the frame.
	 */
    private static JButton btnStep  = new JButton    ("Step");
    private static JButton btnStepTo = new JButton ("Step To");
    private static JButton btnExecute  = new JButton ("Execute");
    private static JButton btnAssemble  = new JButton("Assemble");
    private static JButton btnMemoryDump = new JButton(("Memory Dump"));


    private int buttonWidth = 150;
    private int buttonHeight = 25;


    //menu bar inits
    JMenuBar menuBar = new JMenuBar();
    JMenu menu;
    JMenuItem menuItem;

    //text area init
    public static JPanel assemblyEditor;
    public static JTextArea textArea;
    public static JScrollPane scrollEditor, stackScroll, memoryScroll, popupScroller;

    //register memory viewer
    public static JTextArea registerViewer;
    public static JTextArea stackViewer;
    public static JPanel rightViewer;
    public static JPanel registerFlagViewer;

    public static JCheckBox flagS;
    public static JCheckBox flagV;
    public static JCheckBox flagNull;
    public static JCheckBox flagB;
    public static JCheckBox flagD;
    public static JCheckBox flagI;
    public static JCheckBox flagZ;
    public static JCheckBox flagC;

    //Memory Viewer
    public static JTextArea memoryViewer;
    public static JTextArea popupViewer;
    public static JPanel popupPanel;
    public static JPanel bottomViewer;
    public static JPanel mainPanel;

    // buttons
    public static JPanel inputButtons;

    //borders

    //File saving
    public static File selectedFile = null;

    //file chooser
    public static JFileChooser fileChooser = new JFileChooser();

    //initial string to determine if different and needs saving
    public static String initialString = null;

    //Flags
    boolean isAssembled = false; //flag for making sure user has assembled code.

    //assembly initiate
    public static String[] currentInstructions;

    //highlighting lines
    private static Highlighter.HighlightPainter currentLine = new DefaultHighlighter.DefaultHighlightPainter(Color.green);

    //step to
    public static String jumpToIndex;
    public static Assembly asm = null;

    //current instruction
    public static CharSequence instructionString = "";
    public static CharSequence prevInstructionString = "";



    public DebuggerGUI(){
        frame = new JFrame();
        frame.setTitle("6502 Emulator & Debugger");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setPreferredSize(new Dimension(840, 600));
        frame.setLocation(new Point(300,200));
        frame.setResizable(false);

        //initButtons();
        initTextArea();
        initComponent();
        initEvent();
        initRegisterViewer();
        initMemoryViewer();
        initStackViewer();

        mainPanel = new JPanel(new BorderLayout());

        assemblyEditor.setPreferredSize(new Dimension(600, 300));
        assemblyEditor.setBorder(new TitledBorder(new EtchedBorder(), "Assembly Editor:"));
        assemblyEditor.setBorder(BorderFactory.createEmptyBorder(10,8,0,7));
        mainPanel.add(assemblyEditor, BorderLayout.WEST);

        rightViewer.setPreferredSize(new Dimension(238, 300));
        rightViewer.setBorder(BorderFactory.createEmptyBorder(15,10,0,10));
        mainPanel.add(rightViewer, BorderLayout.EAST);

        bottomViewer.setPreferredSize(new Dimension(500, 200));
        bottomViewer.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.add(bottomViewer, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setJMenuBar(menuBar);
        frame.pack();
        disableButtons();
        frame.setVisible(true);
        Debugger.assemble(); //set memory blank
        disableButtons();

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if(Debugger.isDifferent() ||  textArea.getText().equals("")) {
                    Debugger.saveConfirmation("Save changes before exiting?");
                }
                frame.dispose();
                System.exit(0);

            }
        });
    }

    private void initComponent(){
        //create menu bar
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        initMenu();
        //menuBar.add(menu);
        this.setJMenuBar(menuBar);

    }

    private void initMemoryViewer(){
        JPanel tmp = new JPanel(new BorderLayout());
        memoryViewer = new JTextArea();


        //setting margins
        //memoryViewer.setMargin(new Insets(20, 20, 20, 20));
        memoryViewer.setBorder(new TitledBorder(new EtchedBorder(), "Memory:"));
        memoryViewer.setPreferredSize(new Dimension(564, 17410));
        memoryViewer.setEditable(false);
        memoryViewer.setFont(new Font("Monospaced", Font.PLAIN, 12));

        bottomViewer = new JPanel(new BorderLayout());
        memoryViewer.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        memoryScroll = new JScrollPane(memoryViewer);
        memoryScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        DefaultCaret caret3 = (DefaultCaret)memoryViewer.getCaret();
        caret3.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

        bottomViewer.add(memoryScroll, BorderLayout.WEST);
        bottomViewer.add(registerFlagViewer, BorderLayout.EAST);


        popupPanel =  new JPanel(new BorderLayout());
        popupViewer = new JTextArea();
        popupViewer.setPreferredSize(new Dimension(780, 24595));
        popupViewer.setEditable(false);
        popupViewer.setFont(new Font("Monospaced", Font.PLAIN, 17));
        popupScroller = new JScrollPane(popupViewer);
        popupScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        popupPanel.add(popupScroller, BorderLayout.CENTER);
        popupPanel.setPreferredSize(new Dimension(800, 600));


    }

    private void initStackViewer(){
        stackViewer = new JTextArea();
        stackViewer.setPreferredSize(new Dimension(150, 8725));
        stackViewer.setEditable(false);
        stackViewer.setFont(new Font("Monospaced", Font.PLAIN, 12));
        DefaultCaret caret2 = (DefaultCaret)stackViewer.getCaret();
        caret2.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

        rightViewer = new JPanel(new BorderLayout());

        //scroll pane;
        stackScroll = new JScrollPane(stackViewer);
        stackScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        stackScroll.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        rightViewer.add(stackScroll, BorderLayout.CENTER);
    }

    private void initRegisterViewer(){

        registerFlagViewer = new JPanel(new BorderLayout());
        registerViewer = new JTextArea();

        JPanel flagViewer = new JPanel();
        flagViewer.setLayout(new GridLayout(8, 0));
        Font flagFont = new Font("Monospaced", Font.BOLD,12);

        flagS = new JCheckBox("S");
        flagV = new JCheckBox("V");
        flagNull = new JCheckBox(" ");
        flagB = new JCheckBox("B");
        flagD = new JCheckBox("D");
        flagI = new JCheckBox("I");
        flagZ = new JCheckBox("Z");
        flagC = new JCheckBox("C");

        flagS.setEnabled(false);
        flagS.setFont(flagFont);
        flagS.setForeground(Color.black);

        flagV.setEnabled(false);
        flagV.setFont(flagFont);

        flagNull.setEnabled(false);
        flagNull.setFont(flagFont);

        flagB.setEnabled(false);
        flagB.setFont(flagFont);

        flagD.setEnabled(false);
        flagD.setFont(flagFont);

        flagI.setEnabled(false);
        flagI.setFont(flagFont);

        flagZ.setEnabled(false);
        flagZ.setFont(flagFont);

        flagC.setEnabled(false);
        flagC.setFont(flagFont);

        flagViewer.add(flagS);
        flagViewer.add(flagV);
        flagViewer.add(flagNull);
        flagViewer.add(flagB);
        flagViewer.add(flagD);
        flagViewer.add(flagI);
        flagViewer.add(flagZ);
        flagViewer.add(flagC);

        registerViewer.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        registerViewer.setPreferredSize(new Dimension(186, 100));
        registerViewer.setEditable(false);
        registerViewer.setLineWrap(true);
        registerViewer.setWrapStyleWord(true);
        registerViewer.setFont(new Font("Monospaced", Font.PLAIN, 12));


        registerFlagViewer.add(registerViewer, BorderLayout.WEST);
        registerFlagViewer.add(flagViewer, BorderLayout.EAST);

    }

    private void initButtons(){

        inputButtons = new JPanel();

        JPanel leftButtons = new JPanel();
        JPanel rightButtons = new JPanel();
        leftButtons.setLayout(new BoxLayout(leftButtons, BoxLayout.Y_AXIS));
        rightButtons.setLayout(new BoxLayout(rightButtons, BoxLayout.Y_AXIS));

        inputButtons.setLayout(new BorderLayout());
        leftButtons.add(btnAssemble);
        leftButtons.add(btnExecute);
        rightButtons.add(btnStep);
        rightButtons.add(btnStepTo);

        //need these to set all buttons at same size.
        btnStep.setMinimumSize(new Dimension(buttonWidth, buttonHeight)); //300,130, buttonWidth,buttonHeight);
        btnStepTo.setMinimumSize(new Dimension(buttonWidth, buttonHeight));
        btnExecute.setMinimumSize(new Dimension(buttonWidth, buttonHeight));
        btnAssemble.setMinimumSize(new Dimension(buttonWidth, buttonHeight));

        btnStep.setMaximumSize(new Dimension(buttonWidth, buttonHeight)); //300,130, buttonWidth,buttonHeight);
        btnStepTo.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        btnExecute.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        btnAssemble.setMaximumSize(new Dimension(buttonWidth, buttonHeight));

        //border around buttons
        leftButtons.setBorder(new EmptyBorder(5, 5, 5, 5));
        rightButtons.setBorder(new EmptyBorder(5, 5, 5, 5));

        //add buttons to main panel
        inputButtons.add(leftButtons, BorderLayout.WEST);
        inputButtons.add(rightButtons, BorderLayout.EAST);


    }

    private void initTextArea(){

        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(563, 1500));
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        assemblyEditor = new JPanel(new BorderLayout());
        //scroll pane;
        scrollEditor = new JScrollPane(textArea);
        scrollEditor.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        assemblyEditor.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        textArea.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                disableButtons();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                disableButtons();
            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                disableButtons();
            }
        });


        assemblyEditor.add(scrollEditor, BorderLayout.CENTER);
    }

    private void initEvent(){

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(1);
            }
        });

        btnStep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Registers.readPC() > (CPU.totalBytes + 0x200)){
                    disableButtons();
                }else{
                    CPU.CPURun();
                    updateGUI();
                }

            }
        });

        btnStepTo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] labelArray = asm.getAllLabels().keySet().toArray(new String[0]);
                if(labelArray.length > 0){
                    String firstKey = labelArray[0];
                    String key = (String)JOptionPane.showInputDialog(null,"Enter a label to execute up to:", "Step To", JOptionPane.QUESTION_MESSAGE, null, labelArray, firstKey);
                    int index = asm.getLineLookup(asm.getLabelIndex(key));
                    CPU.CPURunTo(index);
                    updateGUI();
                    //finished program check
                    if((CPU.totalBytes + 0x210) <= Registers.readPC()){
                        disableButtons();
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Requires labels in assembled program to this functionality");
                }

            }
        });

        btnExecute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CPU.Execute();
                updateGUI();
                disableButtons();
            }
        });

        btnAssemble.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Debugger.assemble();
            }
        });

        btnMemoryDump.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                popupViewer.setText(memoryViewer.getText());
                JOptionPane.showMessageDialog(frame, popupPanel,
                        "Memory",
                        JOptionPane.PLAIN_MESSAGE);
                System.out.println("hello");
            }
        });
    }

    public static void enableButtons(){
        btnExecute.setEnabled(true);
        btnStep.setEnabled(true);
        btnStepTo.setEnabled(true);
    }

    public static void disableButtons(){
        btnExecute.setEnabled(false);
        btnStep.setEnabled(false);
        btnStepTo.setEnabled(false);
    }

    /**
     * Sets up the menu bar by adding file in the menu which include:
     * 		Open, Save, Save As, Close, Quit and their functionality
     */
    private void initMenu() {
        // File Menu, F - Mnemonic
        menu = new JMenu("File");


        menuBar.add(menu);


        // File->New, N - Mnemonic
        JMenuItem newMenuItem = new JMenuItem("New", null);
        newMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(Debugger.isDifferent() || textArea.getText().equals("")){
                    Debugger.saveConfirmation("Save changes?");
                }
                textArea.setText("");
                Debugger.assemble();
                disableButtons();
                selectedFile = null;
            }
        });
        menu.add(newMenuItem);

        // File->Open, N - Mnemonic
        JMenuItem openMenuItem = new JMenuItem("Open", null);
        openMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Debugger.openFile();
            }
        });
        menu.add(openMenuItem);

        // File->Save, N - Mnemonic
        JMenuItem saveMenuItem = new JMenuItem("Save", null);
        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Debugger.saveFile();
            }
        });
        menu.add(saveMenuItem);

        // File->Save As, N - Mnemonic
        JMenuItem saveasMenuItem = new JMenuItem("Save As", null);
        saveasMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Debugger.saveAs();
            }
        });
        menu.add(saveasMenuItem);

        // File->Quit, N - Mnemonic
        JMenuItem quitMenuItem = new JMenuItem("Quit", null);
        quitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!Debugger.isDifferent() || !textArea.getText().equals("")){
                    Debugger.saveConfirmation("Save changes before quitting?");
                }
                System.exit(0);
            }
        });
        menu.add(quitMenuItem);

        menuBar.add( Box.createHorizontalStrut( 160 ) );

        //do not color the buttons
        btnAssemble.setContentAreaFilled(false);
        btnExecute.setContentAreaFilled(false);
        btnStep.setContentAreaFilled(false);
        btnStepTo.setContentAreaFilled(false);
        btnMemoryDump.setContentAreaFilled(false);

        menuBar.add(btnAssemble);
        menuBar.add(Box.createRigidArea(new Dimension(25,0)));
        menuBar.add(btnExecute);
        menuBar.add(btnStep);
        menuBar.add(btnStepTo);
        menuBar.add(Box.createRigidArea(new Dimension(25,0)));
        menuBar.add(btnMemoryDump);


    }

    public static void updateGUI(){
        //set memory viewer
        memoryViewer.setText(Memory.memoryToString());
        stackViewer.setText(Memory.stackToString());


        flagS.setSelected(Registers.isNegative());
        flagV.setSelected(Registers.isOverflow());
        flagNull.setSelected(true);
        flagB.setSelected(Registers.isBreak());
        flagD.setSelected(Registers.isDecimal());
        flagI.setSelected(Registers.isIRQDisabled());
        flagZ.setSelected(Registers.isZero());
        flagC.setSelected(Registers.isCarry());

        //highlighting
        try{
            int curLine = Debugger.checkLine(stackViewer);
            int startStack = stackViewer.getLineStartOffset(curLine);
            int endStack = stackViewer.getLineEndOffset(curLine);
            //finding current line
            prevInstructionString = instructionString;
            instructionString = stackViewer.getText().subSequence(startStack, endStack);
            stackViewer.getHighlighter().addHighlight(startStack, endStack, currentLine);
            System.out.println("Line is " + curLine);
            textArea.getHighlighter().removeAllHighlights();
            int startAssembly = textArea.getLineStartOffset(curLine - 0x100);
            int endAssembly = textArea.getLineEndOffset(curLine -0x100);
            textArea.getHighlighter().addHighlight(startAssembly, endAssembly, currentLine);


        } catch (BadLocationException ex){

        }
        registerViewer.setText(Registers.registersToString());
    }


}



