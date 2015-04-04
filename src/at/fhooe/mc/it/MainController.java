package at.fhooe.mc.it;

import javafx.fxml.FXML;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainController implements PropertyChangeListener {
    private MainModel mMainModel;
    @FXML
    private void onReadFileButton() {

    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {

    }

    public void setMainModel(MainModel _mainModel) {
        if (mMainModel != null) {
            mMainModel.removePropertyChangeListener(this);
        }
        mMainModel = _mainModel;
        mMainModel.addPropertyChangeListener(this);
    }
}
