package BattagliaNavaleProject.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import BattagliaNavaleProject.formGui.MenuPrincipaleView;

public class MenuPrincipaleControl implements ActionListener{
	private MenuPrincipaleView menu;
	
	public MenuPrincipaleControl(MenuPrincipaleView menu) {
		this.menu=menu;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			menu.open();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
