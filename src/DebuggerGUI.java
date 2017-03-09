import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.TitledBorder;



public class DebuggerGUI extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	//private DebuggerGUI frame;

    public static int txtBookNum = 1;
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
    JTextArea textArea;
    JScrollPane scrollEditor;

    //register memory viewer
    JTextArea registerViewer;
    JTextArea stackViewer;
    JPanel rightViewer;

    //Memory Viewer
    JTextArea memoryViewer;
    JPanel bottomViewer;

    // buttons
    JPanel inputButtons;

    //borders





    public DebuggerGUI(){
        frame = new JFrame();
        frame.setTitle("6502 Emulator & Debugger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setLocation(new Point(300,200));
        frame.setResizable(false);

        initComponent();
        initEvent();
        initButtons();
        initRegisterViewer();
        initMemoryViewer();

        JPanel mainPanel = new JPanel(new BorderLayout());

        assemblyEditor.setPreferredSize(new Dimension(600, 300));
        mainPanel.add(assemblyEditor, BorderLayout.WEST);

        rightViewer.setPreferredSize(new Dimension(200, 300));
        mainPanel.add(rightViewer, BorderLayout.EAST);

        bottomViewer.setPreferredSize(new Dimension(500, 200));
        mainPanel.add(bottomViewer, BorderLayout.SOUTH);

        //mainPanel.add(inputButtons, BorderLayout.SOUTH);


        frame.add(mainPanel);
        frame.setJMenuBar(menuBar);
        frame.pack();
        frame.setVisible(true);



    }

    private void initComponent(){


        //create menu bar
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        initMenu();
        menuBar.add(menu);
        this.setJMenuBar(menuBar);


        //create text area
        initTextArea();
        assemblyEditor = new JPanel(new BorderLayout());
//        assemblyEditor.setSize(300,300);
//        assemblyEditor.setLocation(new Point(0,0));
        assemblyEditor.add(new JLabel("Assembly:", SwingConstants.LEFT), BorderLayout.PAGE_START);
        assemblyEditor.setPreferredSize(new Dimension(30, 100));
        assemblyEditor.add(textArea);
        assemblyEditor.add(scrollEditor);



    }

    private void initMemoryViewer(){
        JPanel tmp = new JPanel(new BorderLayout());
        memoryViewer = new JTextArea();
        TitledBorder registerTitle = new TitledBorder("Memory:");
        memoryViewer.setBorder(registerTitle);
        memoryViewer.setPreferredSize(new Dimension(500, 200));
        memoryViewer.setEditable(false);

        bottomViewer = new JPanel(new BorderLayout());
        bottomViewer.add(memoryViewer, BorderLayout.WEST);
        bottomViewer.add(inputButtons, BorderLayout.EAST);


    }

    private void initRegisterViewer(){

        JPanel tmp = new JPanel(new BorderLayout());
        registerViewer = new JTextArea();
        TitledBorder registerTitle = new TitledBorder("Registers:");
        registerViewer.setBorder(registerTitle);
        registerViewer.setPreferredSize(new Dimension(200, 100));
        registerViewer.setEditable(false);


        stackViewer = new JTextArea();
        TitledBorder stackTitle = new TitledBorder("Stack:");
        stackViewer.setBorder(stackTitle);
        stackViewer.setPreferredSize(new Dimension(200, 400));
        stackViewer.setEditable(false);

        tmp.add(registerViewer, BorderLayout.NORTH);
        tmp.add(stackViewer, BorderLayout.SOUTH);

        rightViewer = new JPanel(new BorderLayout());
        rightViewer.add(tmp, BorderLayout.CENTER);

    }

    private void initButtons(){
        //create user input buttons
//        btnStep.setBounds(300,130, buttonWidth,buttonHeight);
//        btnStepOver.setBounds(300,100, buttonWidth,buttonHeight);
//        btnExecute.setBounds(300,130, buttonWidth,buttonHeight);
//        btnAssemble.setBounds(300,100, buttonWidth,buttonHeight);
        btnStep.setMinimumSize(new Dimension(buttonWidth, buttonHeight)); //300,130, buttonWidth,buttonHeight);
        btnStepOver.setMinimumSize(new Dimension(buttonWidth, buttonHeight));
        btnExecute.setMinimumSize(new Dimension(buttonWidth, buttonHeight));
        btnAssemble.setMinimumSize(new Dimension(buttonWidth, buttonHeight));

//        btnStep.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
//        btnStepOver.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
//        btnExecute.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
//        btnAssemble.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

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
        //btnStepOver.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightButtons.add(btnStepOver);

        inputButtons.add(leftButtons, BorderLayout.WEST);
        inputButtons.add(rightButtons, BorderLayout.EAST);




//        inputButtons.add(btnStep);
//        inputButtons.add(btnStepOver);
//        inputButtons.add(btnExecute);
//        inputButtons.add(btnAssemble);

    }

    private void initTextArea(){
        textArea = new JTextArea(8, 40);
        textArea.setFont(new Font("Serif", Font.PLAIN, 16));
        textArea.setEditable(true);
//        textArea.setLineWrap(true);
//        textArea.setWrapStyleWord(true);
        //textArea.setPreferredSize(new Dimension(400, 100));


        //scroll pane
        scrollEditor = new JScrollPane(textArea);
        scrollEditor.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


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
            }
        });

        btnStepOver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnExecute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CPU.Execute();
            }
        });

        btnAssemble.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


	private void initialize(){
        //Memory
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(6, 20, 336, 284);
        contentPane.add(scrollPane);

        //entry field
        JTextPane textPane = new JTextPane();
        scrollPane.setViewportView(textPane);

        JButton btnExecute = new JButton("Execute");
        btnExecute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnExecute.setBounds(354, 102, 90, 29);
        contentPane.add(btnExecute);

        JButton btnStep = new JButton("Step");
        btnStep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CPU.CPURun();
            }
        });
        btnStep.setBounds(354, 20, 90, 29);
        contentPane.add(btnStep);

        JButton btnStepOver = new JButton("Step Over");
        btnStepOver.setBounds(354, 61, 90, 29);
        contentPane.add(btnStepOver);

        //frame
        JPanel panel = new JPanel();
       // panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBounds(252, 316, 192, 146);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("A=$00");
        lblNewLabel.setBounds(83, 40, 43, 16);
        panel.add(lblNewLabel);

        JLabel lblS = new JLabel("X=$00");
        lblS.setBounds(83, 68, 42, 16);
        panel.add(lblS);

        JLabel lblY = new JLabel("Y=$00");
        lblY.setBounds(83, 96, 42, 16);
        panel.add(lblY);

        JLabel lblRegisters = new JLabel("Registers:");
        lblRegisters.setBounds(9, 6, 85, 16);
        panel.add(lblRegisters);

        JPanel panel_1 = new JPanel();
        //panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_1.setBounds(6, 316, 237, 146);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblMemory = new JLabel("Memory:");
        lblMemory.setBounds(6, 6, 54, 16);
        panel_1.add(lblMemory);
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

    }

}


