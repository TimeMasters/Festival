package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;


/**
 * interface permettant de consulter les différents festivals du programme triés par date
 * @author jgoodwin
 */
public class PanelConsultationFestival extends JPanel
{
    JTable tableFestivals;
    
    /**
     * Constructeur par défaut
     */
    public PanelConsultationFestival()
    {
        this.setLayout(new BorderLayout());
        tableFestivals = new JTable();
        tableFestivals.setModel(new ModeleTable());
        tableFestivals.getTableHeader().setBackground(constantes.Constantes.BLEU_VIOLET_COLOR);
        tableFestivals.getTableHeader().setResizingAllowed(false);
        tableFestivals.getTableHeader().setReorderingAllowed(false);
        tableFestivals.getTableHeader().setFont(new Font("Arial",Font.BOLD,15));
        tableFestivals.setFocusable(false);
        tableFestivals.setRowSelectionAllowed(false);
        tableFestivals.setRowHeight(80);
        tableFestivals.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scroll = new JScrollPane(tableFestivals,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(800,500));
        for (int i = 0; i<constantes.Constantes.GENRE_ITEMS.length; i++)
        {
            tableFestivals.getColumnModel().getColumn(i).setPreferredWidth(constantes.Constantes.DIMENSION_FENETRE.width/constantes.Constantes.GENRE_ITEMS.length*2);
        }
        tableFestivals.setDefaultRenderer(String.class, new CelluleRenderer());
        this.add(scroll,BorderLayout.CENTER);
        
        updateUI();
    }
}
