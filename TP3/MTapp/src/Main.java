
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import widget.*;


class Menu extends JMenuBar implements ItemListener {
	
	JCheckBoxMenuItem miDebug;
	JCheckBoxMenuItem fullScreen;
	JMenu mConfiguration;
	MTSurface s; 
	JFrame mainFrame;
	
	public Menu(MTSurface s, JFrame mainFrame) {
		this.s=s;
		this.mainFrame = mainFrame;
		mConfiguration=new JMenu("Options");	
		miDebug=new JCheckBoxMenuItem("Debug Mode");
		miDebug.addItemListener(this);
		fullScreen=new JCheckBoxMenuItem("Full screen");
		fullScreen.addItemListener(this);
		
		mConfiguration.add(miDebug);
		mConfiguration.add(fullScreen);
		this.add(mConfiguration);
	}
	
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource()==miDebug) {
			if (e.getStateChange()==ItemEvent.DESELECTED) 
				s.toggleDebugMode();
			else
				s.toggleDebugMode();
		}
		if (e.getSource()==fullScreen) {
			if (e.getStateChange()==ItemEvent.DESELECTED) {
				s.setPreferredSize(new Dimension(1500,1500/2));
				mainFrame.pack();
			}
			else {
			    Toolkit toolkit =  Toolkit.getDefaultToolkit ();
		        Dimension dim = toolkit.getScreenSize();
				s.setPreferredSize(new Dimension(dim.width,dim.height));
				mainFrame.pack();
			}
		}		
	}
}

public class Main {
	static MTSurface mtSurface;
	static JFrame mainFrame;
	
	
	public static void main(String args[]) {		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});		
	}
	
	public static void createAndShowGUI() {
		mtSurface = new MTSurface();
		mainFrame = new JFrame("Multitouch picture viewer");

		MyGestureAnalyzer myGA = new MyGestureAnalyzer(mtSurface.getComponentMap());

		mtSurface.getComponentMap().setGestureAnalyzer(myGA);

		mainFrame.addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		//mt.stop();
        		System.exit(1);
        	}
        });

		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
        Dimension dim = toolkit.getScreenSize();
        //mtSurface.setPreferredSize(new Dimension(dim.width,dim.height));
		mtSurface.setPreferredSize(new Dimension(800,600));
		mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER));

		
		mainFrame.setJMenuBar(new Menu(mtSurface, mainFrame));

		
		/*final MTPicture p3=new MTPicture("PlitviceNationalPark.jpg",new MyOBB());
		p3.setOrigin(200, 300);
		p3.setAngleinDegrees(-30);
		p3.setScale(0.5);			
		mtSurface.add(p3);*/

		final MTPicture p0 = new MTPicture("SunFlower.jpg",new MyOBB());
		p0.setOrigin(50, 50);
		p0.setAngleinDegrees(-10);
		mtSurface.add(p0);

		/*final MTPicture p1 = new MTPicture("Yellow_Garden_Flowers.jpg",new MyOBB());
		p1.setOrigin(800, 100);
		p1.setAngleinDegrees(30);
		p1.setScale(0.5);	
		mtSurface.add(p1);*/
		
		mainFrame.getContentPane().add(mtSurface);
		
		mainFrame.pack();
		mainFrame.setVisible(true);
	}	

}
