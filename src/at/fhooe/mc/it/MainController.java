package at.fhooe.mc.it;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainController implements PropertyChangeListener {
    private MainModel mMainModel;
    @FXML
    private TextField mTextFieldInput;

    @FXML
    private void onReadFileButton() {
        if(mMainModel != null)
            mMainModel.readFile("data/smsCorpus_en_2015.03.09_all.xml");
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

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert mTextFieldInput != null : "fx:id=\"mTextFieldInput\" was not injected: check your FXML file 'ui.fxml'.";

        mTextFieldInput.textProperty().addListener((observable, oldValue, newValue) -> {
            
        });
    }
}
