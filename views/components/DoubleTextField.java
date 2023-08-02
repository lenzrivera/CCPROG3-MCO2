package views.components;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

/**
 * This class is a text field that only allows positive double values to
 * be inputted.
 */
public class DoubleTextField extends JFormattedTextField {
    /**
     * Creates a DoubleTextField with the specified number of columns to be
     * displayed.
     * @param columnCount The number of columns to be displayed in the text 
     * field.
     */
    public DoubleTextField(int columnCount) {
        super(getNumberFormatter());
        setColumns(columnCount);
    }

    /**
     * Creates and returns a NumberFormatter with the specified format for 
     * double values. The format will display numbers with a minimum single 
     * decimal place and only allows positive values.
     *
     * @return the created NumberFormatter with the specified format for double 
     * values.
     */
    private static NumberFormatter getNumberFormatter() {
        NumberFormat format = DecimalFormat.getInstance();
        format.setMinimumFractionDigits(1);
        format.setMaximumFractionDigits(Integer.MAX_VALUE);
        format.setGroupingUsed(false);

        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setMinimum(0.0);
        formatter.setCommitsOnValidEdit(true);

        return formatter;
    }
}
