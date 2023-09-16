import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * This class illustrates the paint app that enable user to draw the
 * circle and square on the canvas. It is also able to draw all shapes
 * at once and remove the all drawing. it implements mouse click event to
 * run the app.
 *
 * @author Nisarg Nayak
 */
public class PaintApp extends Application {

    // TODO: Instance Variables for View Components and Model

    private Button circleButton;
    private Button rectangleButton;
    private TextField locationX;
    private TextField locationY;
    private TextField size;
    private TextField colorField1;
    private TextField colorField2;
    private TextField colorField3;
    private Button drawButton;
    private Button undrawButton;
    private Label errorLabel;
    private Circle c1;
    private Square s1;
    private GraphicsContext gc;
    private double radius = 50;
    private double squareSize=50;
    private double green = 0.0;
    private double red = 0.1;
    private Label shapeCount;
    private Label actualCount;
    private int count =0;
    boolean isDrawButtonPressed = false;
    boolean isCircleButtonPressed = false;
    boolean isSquareButtonPressed = false;
    private Label instruction;
    private ArrayList<Square> squareArrayList;
    private ArrayList<Circle> circleArrayList;
    private Canvas canvas;
    private boolean hasAError;
    private Label startInstruction;

    // TODO: Private Event Handlers and Helper Methods

    /**
     * Draws all the shape stored in ArrayList
     * @param e the event to trigger on
     */
    public void drawShape(ActionEvent e){

        if(!isCircleButtonPressed && !isSquareButtonPressed){
            errorLabel.setText("No shape is drawn yet: ");
        }
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0,0,700,450);

        for(Circle c : circleArrayList){
            c.draw(gc);
        }
        for(Square r:squareArrayList){
            s1.draw(gc);
        }

        isDrawButtonPressed = true;
        isCircleButtonPressed = true;
        isSquareButtonPressed = true;
        startInstruction.setText("");
    }

    /**
     * Undraw the shapes when clicked on the button
     * @param me the mouse event
     */
    public void undrawShape(MouseEvent me){

        if (!circleArrayList.isEmpty()) {

            for (int i = 0; i < circleArrayList.size(); i++) {
                circleArrayList.remove(circleArrayList.size() - 1);
                circleArrayList.remove(c1);
                gc.setFill(Color.LIGHTBLUE);
                gc.fillRect(0,0,700,450);
            }
        }
        if(!squareArrayList.isEmpty()) {
            for (int j = 0; j < squareArrayList.size(); j++) {
                squareArrayList.remove(squareArrayList.size() - 1);
                squareArrayList.remove(s1);
                gc.setFill(Color.LIGHTBLUE);
                gc.fillRect(0,0,700,450);
            }
        }
        startInstruction.setText("");
    }

    /**
     * Draw a circle on the canvas
     * @param circle the circle
     */
    public void drawCircle(Circle circle){
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0,0,700,450);
        circle.draw(gc);
        startInstruction.setText("");
    }

    /**
     * Draw a square on the canvas
     * @param square the square
     */
    public void drawSquare(Square square){
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0,0,700,450);
        square.draw(gc);
        startInstruction.setText("");
    }

    /**
     * Make a circle when circle button is clicked
     * @param me the mouse event
     */
    public void makeCircle(MouseEvent me){

        double circleSize = Double.parseDouble(size.getText());
        c1 = new Circle(400,200,radius,Color.color(0.3,green,0.3));
        circleArrayList.add(c1);
        green=green+0.05;
        radius = radius +5;
        if(green>1.0){
            green = 1;
        }
        count++;
        String countString = String.valueOf(count);
        actualCount.setText(countString);
        drawCircle(c1);
        isSquareButtonPressed = false;
        isCircleButtonPressed = true;
        startInstruction.setText("");
    }

    /**
     * Make a square when square button is clicked
     * @param me the mouse event
     */
    public void makeSquare(MouseEvent me){
        isSquareButtonPressed =  true;
        isCircleButtonPressed = false;
        s1 = new Square(200,200,squareSize,Color.color(0.3,green,0.3));
        squareArrayList.add(s1);
        green=green+0.05;
        squareSize = squareSize +20;
        if(green>1.0){
            green = 1;
        }
        count++;
        String countString = String.valueOf(count);
        actualCount.setText(countString);
        drawSquare(s1);
        startInstruction.setText("");
    }

    /**
     * draw a square by clicking on the canvas
     * @param me the mouse event
     */
    public void squareByMouse(MouseEvent me) {

        if (!isDrawButtonPressed) {

            if (isSquareButtonPressed) {

                s1 = new Square(me.getX(), me.getY(), squareSize, Color.color(red, green, 0.3));
                squareArrayList.add(s1);
                green = green + 0.04;
                red = red + 0.06;
                if (red > 1.0 || green > 1.0) {
                    red = 1;
                    green = 1;
                }
                drawSquare(s1);
                isCircleButtonPressed = false;
                startInstruction.setText("");

            }
        } else if (isDrawButtonPressed && isSquareButtonPressed) {

            try{

                instruction.setText("Instruction: Draw button is pressed!! Now, you will use all three text-fields \nto draw the shape by the mouse event");
                double shapeSize = Double.parseDouble(size.getText());
                double x = Double.parseDouble(locationX.getText());
                double y = Double.parseDouble(locationY.getText());
                double userRed = Double.parseDouble(colorField1.getText());
                double userGreen = Double.parseDouble(colorField2.getText());
                double userBlue = Double.parseDouble(colorField3.getText());

                // try and Catch the exceptions for the square
                try {
                    s1 = new Square(x, y, shapeSize, Color.color(userRed, userGreen, userBlue));
                    squareArrayList.add(s1);
                    drawSquare(s1);
                    count++;
                    String countString = String.valueOf(count);
                    actualCount.setText(countString);
                    hasAError = false;
                    updateError();

                } catch (IllegalArgumentException e) {
                    errorLabel.setText(e.getMessage());
                    errorLabel.setStyle("fx-text-fill: white");
                    errorLabel.setBackground(Background.fill(Color.RED));
                    hasAError = true;
                    updateError();

                } catch (ArithmeticException e) {
                    errorLabel.setText(e.getMessage());
                    errorLabel.setStyle("fx-text-fill: white");
                    errorLabel.setBackground(Background.fill(Color.RED));
                    hasAError = true;
                    updateError();

                } catch (InputMismatchException e) {
                    errorLabel.setText("color parameters must be number and it falls between 0 -1 ");
                    errorLabel.setStyle("fx-text-fill: white");
                    errorLabel.setBackground(Background.fill(Color.RED));
                    hasAError = true;
                    updateError();
                }
            }catch (NumberFormatException e){
                errorLabel.setText("error");
            }
            startInstruction.setText("");
        }
    }

    /**
     *
     * raw a circle by clicking on the canvas
     * @param me the mouse event
     */
    public void circleByMouse(MouseEvent me) {

        if (!isDrawButtonPressed) {

            if (isCircleButtonPressed) {

                c1 = new Circle(me.getX(), me.getY(), radius, Color.color(red, green, 0.3));
                circleArrayList.add(c1);
                green = green + 0.04;
                red = red + 0.06;
                if (red > 1.0 || green > 1.0) {
                    red = 1;
                    green = 1;
                }
                drawCircle(c1);
                isSquareButtonPressed = false;
                count++;
                String countString = String.valueOf(count);
                actualCount.setText(countString);
                startInstruction.setText("");
            }

        } else if (isDrawButtonPressed && isCircleButtonPressed) {

            // try and Catch the exceptions for the circle
            try {
                instruction.setText("Instruction: Draw button is pressed!! Now, you will use all three text-fields \nto draw the shape by the mouse event");
                double circleSize = Double.parseDouble(size.getText());
                double circleX = Double.parseDouble(locationX.getText());
                double circleY = Double.parseDouble(locationY.getText());
                double userRed = Double.parseDouble(colorField1.getText());
                double userGreen = Double.parseDouble(colorField2.getText());
                double userBlue = Double.parseDouble(colorField3.getText());

                c1 = new Circle(circleX, circleY, circleSize, Color.color(userRed, userGreen, userBlue));
                circleArrayList.add(c1);

                drawCircle(c1);
                radius = circleSize;
                count++;
                String countString = String.valueOf(count);
                actualCount.setText(countString);
                hasAError = false;
                updateError();
            } catch (InputMismatchException e) {
                errorLabel.setText("Colors parameter must be numbers and it must fall in between 0 to 1");
                errorLabel.setStyle("fx-text-fill: white");
                errorLabel.setBackground(Background.fill(Color.RED));
                hasAError = true;
                updateError();
            } catch (IllegalArgumentException e) {
                errorLabel.setText(e.getMessage());
                errorLabel.setStyle("fx-text-fill: white");
                errorLabel.setBackground(Background.fill(Color.RED));
                hasAError = true;
                updateError();
            } catch (ArithmeticException e) {
                errorLabel.setText(e.getMessage());
                errorLabel.setStyle("fx-text-fill: white");
                errorLabel.setBackground(Background.fill(Color.RED));
            }
            startInstruction.setText("");
        }
    }

    /**
     * update the error label
     */
    public void updateError(){
        if(!hasAError){
            errorLabel.setText("No Error");
            errorLabel.setBackground(Background.fill(Color.YELLOW));
            startInstruction.setText("");
        }
    }


    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 700, 700); // set the size here
        stage.setTitle("Assignment 8_001231775"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here

        // 1. Create the model
        circleArrayList = new ArrayList<>();
        squareArrayList = new ArrayList<>();


        // 2. Create the GUI components
        circleButton = new Button("Circle");
        rectangleButton = new Button("Rectangle");
        Label locationLabel = new Label("location: ");
        locationX = new TextField();
        locationY = new TextField();
        Label sizelabel = new Label("size");
        size = new TextField("50");
        Label colorLabel = new Label("color");
        colorField1 = new TextField();
        colorField2 = new TextField();
        colorField3 = new TextField();
        drawButton = new Button("Draw");
        undrawButton = new Button("Undraw");
        errorLabel = new Label("No Error");
        shapeCount = new Label("Shapes Count: ");
        actualCount = new Label("");
        instruction = new Label("Instruction: \"Text fields do not work \nuntil you press the draw button");
        canvas= new Canvas(700,450);
        startInstruction = new Label("Select the shape to start the painting");


        // 3. Add components to the root

        root.getChildren().addAll(circleButton,rectangleButton,locationX,locationY,locationLabel,size,sizelabel,
                colorLabel,colorField3,canvas,colorField2,colorField1,drawButton,undrawButton,errorLabel,
                actualCount,shapeCount,instruction,startInstruction);


        // 4. Configure the components (colors, fonts, size, location)

        circleButton.relocate(0,530);
        rectangleButton.relocate(45,530);
        locationLabel.relocate(130,530);
        locationX.relocate(180,530);
        locationX.setPrefWidth(45);
        locationY.setPrefWidth(45);
        locationY.relocate(225,530);
        sizelabel.relocate(280,530);
        size.relocate(305,530);
        size.setPrefWidth(45);
        colorLabel.relocate(365,530);
        colorField1.relocate(400,530);
        colorField1.setPrefWidth(40);
        colorField2.relocate(440,530);
        colorField2.setPrefWidth(40);
        colorField3.relocate(480,530);
        colorField3.setPrefWidth(40);
        drawButton.relocate(550,530);
        undrawButton.relocate(600,530);
        errorLabel.relocate(0,630);
        errorLabel.setBackground(Background.fill(Color.YELLOW));
        errorLabel.setPrefWidth(700);
        errorLabel.setAlignment(Pos.CENTER);
        canvas.relocate(0,0);
        shapeCount.relocate(10,490);
        actualCount.relocate(95,490);
        shapeCount.setStyle("-fx-text-fill: red");
        actualCount.setStyle("-fx-text-fill: red");
        instruction.setStyle("-fx-text-fill: red");
        instruction.relocate(10,450);
        startInstruction.relocate(200,50);
        startInstruction.setStyle("-fx-text-fill: red");
        startInstruction.setBackground(Background.fill(Color.LIGHTBLUE));
        startInstruction.setFont(new Font(15));




        // 5. Add Event Handlers and do final setup

        drawButton.setOnAction(this::drawShape);
        gc = canvas.getGraphicsContext2D();
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, this::circleByMouse);
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,this::squareByMouse);
        circleButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this:: makeCircle);
        rectangleButton.addEventHandler(MouseEvent.MOUSE_CLICKED,this::makeSquare);
        undrawButton.addEventHandler(MouseEvent.MOUSE_CLICKED,this::undrawShape);


        // 6. Show the stage
        stage.show();
    }
    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
