package at.fhooe.mc.it;

import at.fhooe.mc.it.ui.Toast;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class MainController implements PropertyChangeListener {
  private MainModel mMainModel;
  @FXML
  private TextField mTextFieldInput;

  @FXML
  private Label mWord1;

  @FXML
  private Label mWord2;

  @FXML
  private Label mWord3;

  @FXML
  private Label mToastPlaceHolder;

  @FXML
  private void onReadFileButton() {
    if (mMainModel != null) {
      //TODO make it work
      Runnable runnable = new Runnable() {
        @Override
        public void run() {
          boolean parsed = mMainModel.readFile("data/smsCorpus_en_2015.03.09_all.xml");
          String msg = parsed ? "SmsCorpus Loaded" : "Loading failed!";
          Toast toast = new Toast();
          toast.setContent(new Label(msg));
          toast.setDuration(Duration.INDEFINITE);
          toast.show(mToastPlaceHolder.getScene().getWindow());
        }
      };
      Thread thread = new Thread(runnable);
      thread.run();
    } else {
      System.err.println("MainModel was null when reading");
    }
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
    initWordLabels();
  }

  private void initWordLabels() {
    initOneWordLabel(mWord1);
    initOneWordLabel(mWord2);
    initOneWordLabel(mWord3);
  }

  private void initOneWordLabel(final Label label) {
    label.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        String recommendedWord = label.getText();
        String writtenText = mTextFieldInput.getText();
        boolean writeFullWord = writtenText.endsWith(" ");
        if (!writeFullWord) {
          //TODO don't add whole word
          int lastWordStart = writtenText.lastIndexOf(" ");
          writtenText = writtenText.substring(0, lastWordStart + 1);
        }
        mLastWord = recommendedWord;
        mTextFieldInput.setText(writtenText + recommendedWord + " ");
        mTextFieldInput.positionCaret(mTextFieldInput.getLength());

      }
    });
  }

  private void updateWordLabels(List<String> words) {
    mWord1.setText(words.size() >= 1 ? words.get(0) : "");
    mWord2.setText(words.size() >= 2 ? words.get(1) : "");
    mWord3.setText(words.size() >= 3 ? words.get(2) : "");
  }

  private final List<String> mEnteredWords = new ArrayList<String>();
  private String mLastWord = "";

  @FXML
  // This method is called by the FXMLLoader when initialization is complete
  public void initialize() {
    assert mTextFieldInput != null : "fx:id=\"mTextFieldInput\" was not injected: check your FXML file 'ui.fxml'.";

    mTextFieldInput.textProperty().addListener((observable, oldValue, newValue) -> {
      boolean needNextWord = newValue.endsWith(" ");
      boolean firstChar = oldValue.length() == 0;
      boolean newWordStarted = oldValue.endsWith(" ") || firstChar;
      //Suggest next word
      if (needNextWord) {
        boolean enteredOneChar = oldValue.length() + 1 == newValue.length();
        if (enteredOneChar) {
          //when -1, it gets 0 and word starts at beginning
          int wordStart = oldValue.lastIndexOf(" ") + 1;
          mLastWord = oldValue.substring(wordStart);
        }
        List<String> bestWords = mMainModel.getSuggestedWords(mLastWord.toLowerCase(), 3);
        if (!bestWords.isEmpty()) {
          updateWordLabels(bestWords);
        }
      } else if (newWordStarted) {
        //Suggest to finish the word
        Character startChar = newValue.charAt(newValue.length() - 1);
        boolean isUpperCase = Character.isUpperCase(startChar);
        List<String> bestWords = mMainModel.getSuggestedWords(Character.toLowerCase(startChar), 3);
        if (!bestWords.isEmpty()) {
          updateWordLabels(isUpperCase ? capitalizeWords(bestWords) : bestWords);
        }
      }
    });
  }

  public List<String> capitalizeWords(List<String> words) {
    List<String> res = new ArrayList<>();
    for (String curWord : words) {
      String capitalizedWord = Character.toUpperCase(curWord.charAt(0)) + curWord.substring(1);
      res.add(capitalizedWord);
    }
    return res;
  }


}
