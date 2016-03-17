package ihm;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Classe permettant de gérer les cellules de la table
 * @author jgoodwin
 */
class CelluleRenderer extends JLabel implements TableCellRenderer 
{
    /**
     * Constructeur par défaut
     */
    public CelluleRenderer() 
    {
        super();
        setOpaque(true);
        setHorizontalAlignment(JLabel.CENTER);
        setBackground(constantes.Constantes.BLEU_CIEL_COLOR);
        setHorizontalAlignment(LEFT);
    }

    /**
     * Altère une cellule en lui donnant les propriétés souhaitées
     * @param table table
     * @param value Objet à altérer
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return a cellule altérée
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
    {
        setText((String)value);
        return this;    
    }
}
