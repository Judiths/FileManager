package helper;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class RightClickMenu extends JComponent implements MouseListener, MouseMotionListener {

	protected JPopupMenu popup;
	protected Container contentPane;
	
	public RightClickMenu(JPopupMenu popup, Container contentPane) {
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
		this.popup = popup;
		this.contentPane = contentPane;
	}
	
	public void mouseMoved(MouseEvent e) {
		redispatchMouseEvent(e,false);
	}

	public void mouseDragged(MouseEvent e) {
		redispatchMouseEvent(e,false);
	}
	
	public void mouseClicked(MouseEvent e) {
		redispatchMouseEvent(e,false);
	}
	
	public void mouseEntered(MouseEvent e) {
		redispatchMouseEvent(e,false);
	}
	
	public void mouseExited(MouseEvent e) {
		redispatchMouseEvent(e,false);
	}
	
	public void mousePressed(MouseEvent e) {
		redispatchMouseEvent(e,false);
	}
	
	public void mouseReleased(MouseEvent e) {
		redispatchMouseEvent(e,false);
	}
	
	protected void redispatchMouseEvent(MouseEvent e, boolean b) {
		// TODO Auto-generated method stub
		if(e.isPopupTrigger()) {
			showPopup(e.getComponent(), e.getX(), e.getY());
		} else {
			doDispatch(e);
		}
	}

	protected void doDispatch(MouseEvent e) {
		// TODO Auto-generated method stub
		Component component = getRealComponent(e.getPoint());
		if(component == null) {
			return ;
		}
		Point componentPoint = SwingUtilities.convertPoint(this, 
				e.getPoint(), component);
		component.dispatchEvent(new MouseEvent(component, e.getID(), e.getWhen(),
				e.getModifiers(), componentPoint.x, componentPoint.y, 
				e.getClickCount(), e.isPopupTrigger()));
	}
	
	protected Component getRealComponent(Point point) {
		// TODO Auto-generated method stub
		Point containerPoint = SwingUtilities.convertPoint(this, point, contentPane);
		Component component = SwingUtilities.getDeepestComponentAt(contentPane,
				containerPoint.x, containerPoint.y);
		
		return component;
	}

	public void showPopup(Component component, int x, int y) {
		// TODO Auto-generated method stub
		popup.show(component, x, y);
	}

    public static void p(String str) {
        System.out.println(str);
    }
}
