import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;
import java.util.EventObject;

public class TextEditor extends JFrame implements ActionListener
{
    JFrame mainframe;
     JMenuBar menubar;
     JMenu File, Edit, Format, View, Exit;
     JMenuItem New_Window,Open,Save,SaveAs,PageSetup,Print;
     JMenuItem Undo,Cut,Redo,Copy,Paste,Delete,Find,Replace,Goto,SelectAll;
     JMenuItem WordWrap;
     JMenuItem Zoom,ZoomIn,ZoomOut;
     JTextArea textArea;
    TextEditor()
    {
      mainframe = new JFrame();//INITIALISING MAIN FRAME

        //INITIALISING MENU-BAR, REFER LINE NO.
        menubar = new JMenuBar();
        File = new JMenu("File");
        Edit = new JMenu("Edit");
        Format = new JMenu("Format");
        View = new JMenu("View");
        Exit = new JMenu("Close");

        //INITIALISING ACTION LISENER TO FILEMENU
        Exit.addActionListener(this);

        //INITIALISING TEXT AREA
        textArea = new JTextArea();

        //INITIALISE AND ADDING FILE MENU ITEMS
        New_Window = new JMenuItem("New Window");
        Open = new JMenuItem("Open File");
        Save = new JMenuItem("Save File");
        SaveAs = new JMenuItem("SaveAs");
        PageSetup = new JMenuItem("PageSetup");
        Print = new JMenuItem("Print");

        //INITIALISING ACTION LISENER TO FILEMENU
        New_Window.addActionListener(this);
        Open.addActionListener(this);
        Save.addActionListener(this);
        SaveAs.addActionListener(this);
        PageSetup.addActionListener(this);
        Print.addActionListener(this);

        //ADDING ITEMS MENUS TO FILE MENU
        File.add(New_Window);
        File.add(Open);
        File.add(Save);
        //File.add(SaveAs);
        //File.add(PageSetup);
        File.add(Print);

        //INITIALISE AND ADDING EDIT MENU ITEMS
        Cut = new JMenuItem("Cut");
        Undo = new JMenuItem("Undo");
        Redo = new JMenuItem("Redo");
        Copy = new JMenuItem("Copy");
        Paste = new JMenuItem("Paste");
        Delete = new JMenuItem("Delete");
        Find = new JMenuItem("Find");
        Replace = new JMenuItem("Replace");
        Goto = new JMenuItem("Goto");
        SelectAll = new JMenuItem("SelectAll");

      //INITIALISING ACTION LISENER TO EDITMENU
        Cut.addActionListener(this);
        Undo.addActionListener(this);
        Redo.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener(this);
        Delete.addActionListener(this);
        Find.addActionListener(this);
        Replace.addActionListener(this);
        Goto.addActionListener(this);
        SelectAll.addActionListener(this);

      //ADDING ITEMS MENUS TO FILE MENU

        Edit.add(Cut);
        Edit.add(Copy);
        Edit.add(Paste);
        Edit.add(Delete);
        //Edit.add(Undo);
        //Edit.add(Redo);
        //Edit.add(Find);
        //Edit.add(Replace);
        //Edit.add(Goto);
        Edit.add(SelectAll);

        //INITIALISING
        WordWrap = new JMenuItem("WordWrap");
        //INTIALISING ACTION LISTENER TO FORMATMENU
        WordWrap.addActionListener(this);
        //ADDING FORMAT MENU ITEMS
        Format.add(WordWrap);

        //INITIALISE AND ADDING VIEW MENU ITEMS
        Zoom = new JMenuItem("Zoom");
        ZoomIn = new JMenuItem("ZoomIn");
        ZoomOut = new JMenuItem("ZoomOut");

        //NITIALISING ACTION LISENER TO EDITMENU
        Zoom.addActionListener(this);
        ZoomIn.addActionListener(this);
        ZoomOut.addActionListener(this);

        View.add(Zoom);
        View.add(ZoomIn);
        View.add(ZoomOut);


        //ADDING MENUS TO MENU BAR, REFER LINE NO.16
        menubar.add(File);
        menubar.add(Edit);
        //menubar.add(Format);
        //menubar.add(View);
        menubar.add(Exit);


      mainframe.setJMenuBar(menubar); //SHOWING MENU-BAR ON MAINFRAME

      //CREATING CONTENT PANE
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

      //ADDING TEXTAREA TO THE PANEL
        panel.add(textArea,BorderLayout.CENTER);

      //CREATING SCROLL PANE
        JScrollPane scrollpane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

      //ADDING SCROLLBAR TO PANEL
      panel.add(scrollpane);

      //ADDING PANEL TO MAINFRAME
      mainframe.add(panel);

      mainframe.setBounds(300,150,600,350);
      mainframe.setTitle("Text Editor");
      mainframe.setVisible(true);
      mainframe.setLayout(null);

    }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource()==Cut){
      //perform cut operation
      textArea.cut();
    }
    if(e.getSource()==Copy){
      //perform copy operation
      textArea.copy();
    }
    if(e.getSource()==Paste)
    {
      textArea.paste();
    }
    if(e.getSource()==Delete)
    {
      textArea.replaceSelection("");
    }
    ///////////////////////////////////////////////////////////////
    /////////////////     TO BE ADDED     /////////////////////////
 /* if(e.getSource()==Find)
    {
      textArea.();
    }
    if(e.getSource()==Replace)
    {
      textArea.();
    }
    if(e.getSource()==Goto)
    {
     textArea.();
    }

  */
    ///////////////////////////////////////////////////////////////
    if(e.getSource()==SelectAll)
    {
      //perform selectall operation
      textArea.selectAll();
    }

    if(e.getSource()==Exit)
    {
      System.exit(0);
    }
    if(e.getSource()==Open)
    {
      //open a file chooser
      JFileChooser fileChooser= new JFileChooser("C:/Users/DELL/Desktop");
      int chooseOption = fileChooser.showOpenDialog(null);
      //if we have clicked on Open button
    if(chooseOption==JFileChooser.APPROVE_OPTION)
    {
      // getting the selected file
      java.io.File file = fileChooser.getSelectedFile();
      String filePath = file.getPath();
      try
      {
        //Initialize file reader
        FileReader fileReader = new FileReader(filePath);
        //Initialize Buffered Reader
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String intermediate = "";
        String output= "";
        // Read contents of file line by line
        while ((intermediate=bufferedReader.readLine())!=null)
        {
          output+=intermediate+"\n";

        }
        //set the output string to text area
        textArea.setText(output);
      }
      catch (FileNotFoundException fileNotFoundException)
      {
        fileNotFoundException.printStackTrace();
      }
      catch (IOException ex)
      {
        ex.printStackTrace();
      }

    }
  }
    if(e.getSource()==Save)
    {
      //Initialize file picker
      JFileChooser fileChooser;
      fileChooser = new JFileChooser("C:/Users/DELL/Desktop");
      // get choose options from file chooser
      int chooseOption = fileChooser.showSaveDialog(null);
      // check if we clicked on save button
      if(chooseOption == JFileChooser.APPROVE_OPTION)
      {
        //create a new file with chosen directory path and file name
        File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
        try
        {
          //Initialize file writer
          FileWriter fileWriter = null;
          fileWriter = new FileWriter(file);
          //Initialize buffered writer
          BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
          //write contents of text area to file
          textArea.write(bufferedWriter);
          bufferedWriter.close();
        }
        catch (IOException ex)
        {
          ex.printStackTrace();
        }
      }
    }
    //GET NEW NOTEPAD
    if (e.getSource() == New_Window)
    {
      TextEditor newtextEditor = new TextEditor();
    }
   //PRINTING TEXT FILE ON NOTEPAD
    if(e.getSource()==Print)
    {
      try
      {
        boolean print = textArea.print();
      } 
      catch (PrinterException ex)
      {
        throw new RuntimeException(ex);
      }
    }


  }


    public static void main(String[] args)
    {
        TextEditor obj = new TextEditor();
    }


}
////////////////////////////////////////////////////////////////////////////
/* THINGS TO BE RETURN FOR NEW BUTTON UNLOCK
   Line no. 55(SAVE AS), 56(PAGE SETUP), 89(Undo), 90(Redo), 91(Find)
            92(Replace), 93(Goto), 121(Format), 122(View)

   THINGS TO BE RETURN FOR NEW FUNCTION UNLOCK
   Line no. 171 - 187
   Line no.
 */