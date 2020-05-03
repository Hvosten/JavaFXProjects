package SimpleCalculator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Controller {
    @FXML private GridPane gridBox;
    @FXML private VBox vBox;
    @FXML private TextField middleBar;

    @FXML private TextField displayField;

    @FXML private Button zeroButton;
    @FXML private Button oneButton;
    @FXML private Button twoButton;
    @FXML private Button threeButton;
    @FXML private Button fourButton;
    @FXML private Button fiveButton;
    @FXML private Button sixButton;
    @FXML private Button sevenButton;
    @FXML private Button eightButton;
    @FXML private Button nineButton;
    @FXML private Button doubleZeroButton;

    @FXML private Button dotButton;
    @FXML private Button clearButton;
    @FXML private Button deleteButton;
    @FXML private Button revertButton;
    @FXML private Button leftBracketButton;
    @FXML private Button rightBracketButton;

    @FXML private Button additionButton;
    @FXML private Button subtractionButton;
    @FXML private Button divisionButton;
    @FXML private Button multiplicationButton;
    @FXML private Button equalButton;


    private String currDisplay = "0";
    private String previousOperation = "n/a";

    MathEvaluator evaluator = new MathEvaluator();

    private static final int MAX_LENGTH = 40;
    private static final Background blueBackground = new Background(new BackgroundFill(Color.rgb(48, 167, 227), null, null));
    private static final Background yellowBackground = new Background(new BackgroundFill(Color.rgb(207, 176, 6), null, null));
    private static final Background blackBackground = new Background(new BackgroundFill(Color.rgb(56, 56, 56), null, null));
    private static final Background redBackground = new Background(new BackgroundFill(Color.rgb(224, 91, 67), null, null));
    private static final Background greenBackground = new Background(new BackgroundFill(Color.rgb(135, 245, 164), null, null));
    private static final Background greyBackground = new Background(new BackgroundFill(Color.rgb(184, 173, 173), null, null));

    public void initialize(){
        colorizeComponent();
        setNewDisplay();
        addListeners();
    }

    private void addListeners() {
        zeroButton.setOnAction(onClickAddToDisplay("0"));
        oneButton.setOnAction(onClickAddToDisplay("1"));
        twoButton.setOnAction(onClickAddToDisplay("2"));
        threeButton.setOnAction(onClickAddToDisplay("3"));
        fourButton.setOnAction(onClickAddToDisplay("4"));
        fiveButton.setOnAction(onClickAddToDisplay("5"));
        sixButton.setOnAction(onClickAddToDisplay("6"));
        sevenButton.setOnAction(onClickAddToDisplay("7"));
        eightButton.setOnAction(onClickAddToDisplay("8"));
        nineButton.setOnAction(onClickAddToDisplay("9"));
        doubleZeroButton.setOnAction(onClickAddToDisplay("00"));
        leftBracketButton.setOnAction(onClickAddToDisplay("("));
        rightBracketButton.setOnAction(onClickAddToDisplay(")"));

        additionButton.setOnAction(onClickAddOperation("+"));
        subtractionButton.setOnAction(onClickAddOperation("-"));
        divisionButton.setOnAction(onClickAddOperation("/"));
        multiplicationButton.setOnAction(onClickAddOperation("*"));
        dotButton.setOnAction(onClickAddOperation("."));

        clearButton.setOnAction(event -> {
            currDisplay = "0";
            setNewDisplay();
        });

        deleteButton.setOnAction(event -> {
            currDisplay = currDisplay.length()==1 ? "0" : currDisplay.substring(0,currDisplay.length()-1);
            setNewDisplay();
        });

        equalButton.setOnAction(event -> {
            previousOperation = currDisplay;
            try {
                currDisplay = evaluator.calculate(currDisplay).stripTrailingZeros().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            setNewDisplay();
        });

        revertButton.setOnAction(event -> {
            if(!previousOperation.equals("n/a")){
                currDisplay = previousOperation;
                setNewDisplay();
            }
        });
    }

    private void setNewDisplay() {
        displayField.textProperty().setValue(currDisplay);
    }

    private void colorizeComponent() {
        gridBox.setBackground(greyBackground);

        vBox.setBackground(greenBackground);
        middleBar.setBackground(greenBackground);

        zeroButton.setBackground(blackBackground);
        oneButton.setBackground(blackBackground);
        twoButton.setBackground(blackBackground);
        threeButton.setBackground(blackBackground);
        fourButton.setBackground(blackBackground);
        fiveButton.setBackground(blackBackground);
        sixButton.setBackground(blackBackground);
        sevenButton.setBackground(blackBackground);
        eightButton.setBackground(blackBackground);
        nineButton.setBackground(blackBackground);
        doubleZeroButton.setBackground(blackBackground);
        dotButton.setBackground(blackBackground);

        clearButton.setBackground(redBackground);
        deleteButton.setBackground(redBackground);

        revertButton.setBackground(blueBackground);
        leftBracketButton.setBackground(blueBackground);
        rightBracketButton.setBackground(blueBackground);
        equalButton.setBackground(blueBackground);

        additionButton.setBackground(yellowBackground);
        subtractionButton.setBackground(yellowBackground);
        multiplicationButton.setBackground(yellowBackground);
        divisionButton.setBackground(yellowBackground);
    }

    private EventHandler<ActionEvent> onClickAddToDisplay(String addend){
        return event -> {
            if(currDisplay.length()+addend.length() <= MAX_LENGTH){
                currDisplay = (currDisplay.equals("0")) ?  addend : currDisplay + addend;
                setNewDisplay();
            }
        };
    }

    private EventHandler<ActionEvent> onClickAddOperation(String operand){
        return event -> {
            if(currDisplay.length() < MAX_LENGTH){
                currDisplay += operand;
                setNewDisplay();
            }
        };
    }
}
