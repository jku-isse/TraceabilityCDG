/*
  LoadGameAction - A action to load a game.

  Copyright (C) 2003 The Java-Chess team <info@java-chess.de>

  This program is free software; you can redistribute it and/or
  modify it under the terms of the GNU General Public License
  as published by the Free Software Foundation; either version 2
  of the License, or (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software
  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*/

package de.java_chess.javaChess.action;

import antlr.*;
import de.java_chess.javaChess.pgn.*;
import de.java_chess.javaChess.notation.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;


/**
 * This class implements a action to load a game from a PGN file.
 */
public class LoadGameAction extends JavaChessAction {

    // Instance variables


    // Constructors

    /**
     * Create a new action instance.
     */
    public LoadGameAction() {
	super( "Load game");
    }
    

    // Methods

    /**
     * The actual action.
     *
     * @param event The event, that caused this action.
     */
    public void actionPerformed( ActionEvent event) {

	// Missing: save current project?

	JFileChooser chooser = new JFileChooser();

	chooser.setDialogTitle( "Load pgn file");
	chooser.setFileFilter( SaveGameAsAction.getPGNFileFilter());

	int retval = chooser.showOpenDialog( null);
	if( retval == JFileChooser.APPROVE_OPTION) {

	    File file = chooser.getSelectedFile();
	    try {
		PGNFile pgnFile = new PGNFile( new BufferedReader( new FileReader( file)));
		GameNotation notation = pgnFile.readGame();
	    } catch( FileNotFoundException fe) {
		JOptionPane.showMessageDialog( null, "File " + file.getName() + " not found!", "File not found", JOptionPane.ERROR_MESSAGE);
	    } catch( RecognitionException re) {
	    } catch( TokenStreamException te) {}
	}
    }
}
