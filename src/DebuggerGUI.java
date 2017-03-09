import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;


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
    JScrollPane scrollEditor, stackScroll, memoryScroll;

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

        rightViewer.setPreferredSize(new Dimension(200, 300));
        rightViewer.setBorder(BorderFactory.createEmptyBorder(15,10,0,10));
        mainPanel.add(rightViewer, BorderLayout.EAST);

        bottomViewer.setPreferredSize(new Dimension(500, 200));
        bottomViewer.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
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
        //menuBar.add(menu);
        this.setJMenuBar(menuBar);

    }

    private void initMemoryViewer(){
        JPanel tmp = new JPanel(new BorderLayout());
        memoryViewer = new JTextArea();


        //setting margins
        //memoryViewer.setMargin(new Insets(20, 20, 20, 20));
        memoryViewer.setBorder(new TitledBorder(new EtchedBorder(), "Memory:"));
        memoryViewer.setPreferredSize(new Dimension(564, 150));
        memoryViewer.setEditable(false);

        bottomViewer = new JPanel(new BorderLayout());
        memoryViewer.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        memoryScroll = new JScrollPane(memoryViewer);
        memoryScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        bottomViewer.add(memoryScroll, BorderLayout.WEST);
        bottomViewer.add(registerViewer, BorderLayout.EAST);
        //bottomViewer.add(inputButtons, BorderLayout.EAST);


    }

    private void initStackViewer(){
        stackViewer = new JTextArea();
        stackViewer.setPreferredSize(new Dimension(150, 180));
        stackViewer.setEditable(true);

        rightViewer = new JPanel(new BorderLayout());


        //scroll pane;
        stackScroll = new JScrollPane(stackViewer);
        stackScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        stackScroll.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        rightViewer.add(stackScroll, BorderLayout.CENTER);


    }

    private void initRegisterViewer(){

        JPanel tmp = new JPanel(new BorderLayout());
        registerViewer = new JTextArea();

        registerViewer.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        registerViewer.setPreferredSize(new Dimension(180, 100));
        registerViewer.setEditable(false);
        registerViewer.setLineWrap(true);
        registerViewer.setWrapStyleWord(true);





//        tmp.add(registerViewer, BorderLayout.NORTH);
//        tmp.add(stackViewer, BorderLayout.SOUTH);

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
        textArea.setFont(new Font("Serif", Font.PLAIN, 16));
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        //textArea.setPreferredSize(new Dimension(400, 1000));


        //scroll pane;
        scrollEditor = new JScrollPane(textArea);
        scrollEditor.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        assemblyEditor.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED), "Assembly:"));


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

//
//	private void initialize(){
//        //Memory
//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.setBounds(6, 20, 336, 284);
//        contentPane.add(scrollPane);
//
//        //entry field
//        JTextPane textPane = new JTextPane();
//        scrollPane.setViewportView(textPane);
//
//        JButton btnExecute = new JButton("Execute");
//        btnExecute.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//            }
//        });
//        btnExecute.setBounds(354, 102, 90, 29);
//        contentPane.add(btnExecute);
//
//        JButton btnStep = new JButton("Step");
//        btnStep.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                CPU.CPURun();
//            }
//        });
//        btnStep.setBounds(354, 20, 90, 29);
//        contentPane.add(btnStep);
//
//        JButton btnStepOver = new JButton("Step Over");
//        btnStepOver.setBounds(354, 61, 90, 29);
//        contentPane.add(btnStepOver);
//
//        JButton btnAssemble = new JButton("Assemble");
//        btnStepOver.setBounds(354, 61, 90, 29);
//        contentPane.add(btnStepOver);
//
//        //frame
//        JPanel panel = new JPanel();
//       // panel.setBorder(new LineBorder(new Color(0, 0, 0)));
//        panel.setBounds(252, 316, 192, 146);
//        contentPane.add(panel);
//        panel.setLayout(null);
//
//        JLabel lblNewLabel = new JLabel("A=$00");
//        lblNewLabel.setBounds(83, 40, 43, 16);
//        panel.add(lblNewLabel);
//
//        JLabel lblS = new JLabel("X=$00");
//        lblS.setBounds(83, 68, 42, 16);
//        panel.add(lblS);
//
//        JLabel lblY = new JLabel("Y=$00");
//        lblY.setBounds(83, 96, 42, 16);
//        panel.add(lblY);
//
//        JLabel lblRegisters = new JLabel("Registers:");
//        lblRegisters.setBounds(9, 6, 85, 16);
//        panel.add(lblRegisters);
//
//        JPanel panel_1 = new JPanel();
//        //panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
//        panel_1.setBounds(6, 316, 237, 146);
//        contentPane.add(panel_1);
//        panel_1.setLayout(null);
//
//        JLabel lblMemory = new JLabel("Memory:");
//        lblMemory.setBounds(6, 6, 54, 16);
//        panel_1.add(lblMemory);
//    }



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

}


