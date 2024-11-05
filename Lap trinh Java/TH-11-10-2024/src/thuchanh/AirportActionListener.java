package thuchanh;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AirportActionListener implements ActionListener {
    private AirportFrame airportFrame;

    public AirportActionListener(AirportFrame frame) {
        this.airportFrame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == airportFrame.getTypeComboBox()) {
            airportFrame.updateFields();
        } else if (source == airportFrame.getAddButton()) {
            airportFrame.addAirport();
        } else if (source == airportFrame.getDeleteButton()) {
        	airportFrame.deleteAirport();
        } else if (source == airportFrame.getSearchButton()) {
        	airportFrame.searchAirport();
        } else if (source == airportFrame.getLoadFromTextFileMenuItem()) {
        	airportFrame.loadFromTextFile();
        } else if (source == airportFrame.getSaveToTextFileMenuItem()) {
        	airportFrame.saveToTextFile();
        } else if (source == airportFrame.getLoadFromBinaryFileMenuItem()) {
        	airportFrame.loadFromBinaryFile();
        } else if (source == airportFrame.getSaveToBinaryFileMenuItem()) {
        	airportFrame.saveToBinaryFile();
        } else if (source == airportFrame.getEditButton()) {
        	airportFrame.editAirport();
        }
    }
}
