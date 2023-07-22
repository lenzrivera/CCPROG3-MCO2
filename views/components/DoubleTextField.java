package views.components;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

public class DoubleTextField extends JFormattedTextField {
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

    public DoubleTextField(int columnCount) {
        super(getNumberFormatter());
        setColumns(columnCount);        
    }
}
