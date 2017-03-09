import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.EventListener.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultCaret;


public class DebuggerGUI extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	//private DebuggerGUI frame;

    int frameXpos = 200;
    int frameYpos = 200;

	/**
	 * Launch the application.
	 */
//	public static void debuggerINIT() {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DebuggerGUI frame = new DebuggerGUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
    private JButton btnStep  = new JButton    ("Step");
    private JButton btnStepOver = new JButton ("Step Over");
    private JButton btnExecute  = new JButton ("Execute");
    private JButton btnAssemble  = new JButton("Assemble");


    private int buttonWidth = 150;
    private int buttonHeight = 25;


    //menu bar inits
    JMenuBar menuBar = new JMenuBar();
    JMenu menu;
    JMenuItem menuItem;

    //text area init
    JPanel assemblyEditor;
    public JTextArea textArea;
    JScrollPane scrollEditor, stackScroll, memoryScroll;

    //register memory viewer
    JTextArea registerViewer;
    JTextArea stackViewer;
    JPanel rightViewer;
    JPanel registerFlagViewer;

    JCheckBox flagS;
    JCheckBox flagV;
    JCheckBox flagNull;
    JCheckBox flagB;
    JCheckBox flagD;
    JCheckBox flagI;
    JCheckBox flagZ;
    JCheckBox flagC;

    //Memory Viewer
    JTextArea memoryViewer;
    JPanel bottomViewer;

    // buttons
    JPanel inputButtons;

    //borders

    //file chooser
    JFileChooser fileChooser = new JFileChooser();

    //Flags
    boolean isAssembled = false; //flag for making sure user has assembled code.



    public DebuggerGUI(){
        frame = new JFrame();
        frame.setTitle("6502 Emulator & Debugger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        JPanel mainPanel = new JPanel(new BorderLayout());

        assemblyEditor.setPreferredSize(new Dimension(600, 300));
        assemblyEditor.setBorder(new TitledBorder(new EtchedBorder(), "Assembly Editor:"));
        assemblyEditor.setBorder(BorderFactory.createEmptyBorder(10,10,0,7));
        mainPanel.add(assemblyEditor, BorderLayout.WEST);

        rightViewer.setPreferredSize(new Dimension(240, 300));
        rightViewer.setBorder(BorderFactory.createEmptyBorder(15,10,0,10));
        mainPanel.add(rightViewer, BorderLayout.EAST);

        bottomViewer.setPreferredSize(new Dimension(500, 200));
        bottomViewer.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.add(bottomViewer, BorderLayout.SOUTH);

        //mainPanel.add(inputButtons, BorderLayout.SOUTH);


        frame.add(mainPanel);
        frame.setJMenuBar(menuBar);
        frame.pack();
        disableButtons();
        frame.setVisible(true);



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
        //bottomViewer.add(inputButtons, BorderLayout.EAST);


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

//        rightViewer = new JPanel(new BorderLayout());
//        rightViewer.add(tmp, BorderLayout.CENTER);

    }

    private void initButtons(){

        inputButtons = new JPanel();

        JPanel leftButtons = new JPanel();
        JPanel rightButtons = new JPanel();
        leftButtons.setLayout(new BoxLayout(leftButtons, BoxLayout.Y_AXIS));
        rightButtons.setLayout(new BoxLayout(rightButtons, BoxLayout.Y_AXIS));

        inputButtons.setLayout(new BorderLayout());
        //inputButtons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        //inputButtons.add(Box.createVerticalGlue());
        //inputButtons.setLayout(new BoxLayout());

        //btnAssemble.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftButtons.add(btnAssemble);
        //btnExecute.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftButtons.add(btnExecute);

        //btnStep.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightButtons.add(btnStep);
        rightButtons.add(btnStepOver);

        //need these to set all buttons at same size.
        btnStep.setMinimumSize(new Dimension(buttonWidth, buttonHeight)); //300,130, buttonWidth,buttonHeight);
        btnStepOver.setMinimumSize(new Dimension(buttonWidth, buttonHeight));
        btnExecute.setMinimumSize(new Dimension(buttonWidth, buttonHeight));
        btnAssemble.setMinimumSize(new Dimension(buttonWidth, buttonHeight));

        btnStep.setMaximumSize(new Dimension(buttonWidth, buttonHeight)); //300,130, buttonWidth,buttonHeight);
        btnStepOver.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        btnExecute.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        btnAssemble.setMaximumSize(new Dimension(buttonWidth, buttonHeight));

        //border around buttons
        leftButtons.setBorder(new EmptyBorder(5, 5, 5, 5));
        rightButtons.setBorder(new EmptyBorder(5, 5, 5, 5));

        //add buttons to main panel
        inputButtons.add(leftButtons, BorderLayout.WEST);
        inputButtons.add(rightButtons, BorderLayout.EAST);




//        inputButtons.add(btnStep);
//        inputButtons.add(btnStepOver);
//        inputButtons.add(btnExecute);
//        inputButtons.add(btnAssemble);

    }

    private void initTextArea(){
        assemblyEditor = new JPanel();

        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(563, 325));
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        //textArea.setPreferredSize(new Dimension(400, 1000));


        //scroll pane;
        scrollEditor = new JScrollPane(textArea);
        scrollEditor.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        assemblyEditor.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED), "Assembly:"));
        DefaultCaret caret = (DefaultCaret)textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

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


//        assemblyEditor.setSize(300,300);
//        assemblyEditor.setLocation(new Point(0,0));
        //assemblyEditor.setBorder(new TitledBorder(new EtchedBorder(), "Assembly Editor:"));
        //assemblyEditor.setPreferredSize(new Dimension(30, 100));
        //textArea.setMargin(new Insets(5, 5, 5, 5));
        //assemblyEditor.add(textArea);
        assemblyEditor.add(scrollEditor);





    }

    private void initEvent(){

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(1);
            }
        });

        btnStep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CPU.CPURun();
                updateGUI();
            }
        });

        btnStepOver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnExecute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CPU.Execute();
                updateGUI();
            }
        });

        btnAssemble.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                assemble();
            }
        });
    }
    public void enableButtons(){
        btnExecute.setEnabled(true);
        btnStep.setEnabled(true);
        btnStepOver.setEnabled(true);
    }

    public void disableButtons(){
        btnExecute.setEnabled(false);
        btnStep.setEnabled(false);
        btnStepOver.setEnabled(false);
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

            }
        });
        menu.add(newMenuItem);

        // File->Open, N - Mnemonic
        JMenuItem openMenuItem = new JMenuItem("Open", null);
        openMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        menu.add(openMenuItem);

        // File->Save, N - Mnemonic
        JMenuItem saveMenuItem = new JMenuItem("Save", null);
        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(saveMenuItem);

        // File->Save As, N - Mnemonic
        JMenuItem saveasMenuItem = new JMenuItem("Save As", null);
        saveasMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(saveasMenuItem);

        // File->Close, N - Mnemonic
        JMenuItem closeMenuItem = new JMenuItem("Close", null);
        closeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        menu.add(closeMenuItem);

        // File->Quit, N - Mnemonic
        JMenuItem quitMenuItem = new JMenuItem("Quit", null);
        quitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(quitMenuItem);

        menuBar.add( Box.createHorizontalStrut( 200 ) );

        //do not color the buttons
        btnAssemble.setContentAreaFilled(false);
        btnExecute.setContentAreaFilled(false);
        btnStep.setContentAreaFilled(false);
        btnStepOver.setContentAreaFilled(false);

        menuBar.add(btnAssemble);
        menuBar.add(btnExecute);
        menuBar.add(btnStep);
        menuBar.add(btnStepOver);


    }


    private boolean openFile(){
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            textArea.setText("");
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            try {
                BufferedReader in = new BufferedReader(new FileReader(selectedFile));
                String line = in.readLine();
                while (line != null) {
                    textArea.append(line + "\n");
                    line = in.readLine();
                }
                return true;
            } catch(IOException ex){
                return false;
            }

        }
        return false;
    }

    public void updateGUI(){
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


    }

    public String instructions;
    private boolean assemble(){

        Assembly asm = Import.importInstructions(textArea.getText());
        Memory.clean();
        Memory.setMemory(asm.assemble());
        Registers.init_Memory();
        Memory.instrToString();
        System.out.println();
        enableButtons();
        updateGUI();


        return true;
    }
}



