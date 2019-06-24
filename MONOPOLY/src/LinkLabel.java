import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the class for hyperlink.
 * 
 * @author momo, enting, catherine, sophia
 * @version 1.0
 * @since 2019-05-31
 */
public class LinkLabel extends JLabel {
    private String text, url;
    private boolean isSupported;

    /**
     * construtor of LinkLabel
     * 
     * @param text The text of hyperlink
     * @param url The url of hyperlink
     */
    public LinkLabel(String text, String url) {
        this.text = text;
        this.url = url;
        try {
            this.isSupported = Desktop.isDesktopSupported()
                    && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
        } catch (Exception e) {
            this.isSupported = false;
        }
        setText(false);
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setText(isSupported);
                if (isSupported)
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                setText(false);
            }

            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(
                            new java.net.URI(LinkLabel.this.url));
                } catch (Exception ex) {}
            }
        });
    }

    /**
     * set the text of hyperlink
     * 
     * @param b True if set the color to red, else set the color to black
     */
    private void setText(boolean b) {
        if (!b)
            setText("<html><font color=black><u>" + text);
        else
            setText("<html><font color=red><u>" + text);
    }
}

