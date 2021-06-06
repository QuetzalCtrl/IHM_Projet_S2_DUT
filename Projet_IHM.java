package org.openjfx.IHM;

import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class Projet_IHM extends Application {
	HBox colorSelectionContainer;
	ArrayList<Rectangle> colorSelectionPanel = new ArrayList<Rectangle>();
	ArrayList<Circle> coloredCircles = new ArrayList<Circle>();
    ArrayList<Circle> greyCircles = new ArrayList<Circle>();
    ArrayList<ColorPicker> colorPickers = new ArrayList<ColorPicker>();
    ArrayList<HBox> trashBoxes = new ArrayList<HBox>();
    ArrayList<Label> greyValues = new ArrayList<Label>();
    ArrayList<Button> colorButtons = new ArrayList<Button>();
    ArrayList<Button> greyButtons = new ArrayList<Button>();
    VBox coloredCircleBox;
    VBox colorPickersBox;
    VBox greyCircleBox;
    VBox trashBoxesBox;
    VBox colorButtonsBox;
    VBox greyButtonsBox;
    Image plusColor = new Image(getClass().getResourceAsStream("plus.png"));
    ImageView plusIV;
    VBox addColor;
    Label addColorLabel;
    Color selectedColor;
    HBox selectorItself;
    Label copied;
    Image selector = new Image(getClass().getResourceAsStream("upload.png"));
    ImageView selectorImageView;
    int nbColors=0;
    Image image = new Image(getClass().getResourceAsStream("trashbox.png"));
    final Clipboard clipboard = Clipboard.getSystemClipboard();
    final ClipboardContent content = new ClipboardContent();

    @SuppressWarnings({ "unchecked", "unchecked", "unchecked", "rawtypes" })
	@Override
    public void start(Stage stage) {
    	HBox title = new HBox();
    	Label selection = new Label("Pré-sélection de la couleur à ajouter :");
    	selection.setFont(new Font(30));
    	title.getChildren().add(selection);
    	title.setPrefSize(1200, 60);
    	title.setAlignment(Pos.BOTTOM_CENTER);
    	colorSelectionContainer = new HBox();
    	colorSelectionContainer.setPrefSize(1200, 60);
    	colorSelectionContainer.setSpacing(15);
    	colorSelectionContainer.setAlignment(Pos.BOTTOM_CENTER);
		colorSelectionPanel.add(initRectangle(Color.CYAN));
		colorSelectionPanel.add(initRectangle(Color.TEAL));
		colorSelectionPanel.add(initRectangle(Color.BLUE));
		colorSelectionPanel.add(initRectangle(Color.NAVY));
		colorSelectionPanel.add(initRectangle(Color.MAGENTA));
		colorSelectionPanel.add(initRectangle(Color.PURPLE));
		colorSelectionPanel.add(initRectangle(Color.RED));
		colorSelectionPanel.add(initRectangle(Color.MAROON));
		colorSelectionPanel.add(initRectangle(Color.YELLOW));
		colorSelectionPanel.add(initRectangle(Color.OLIVE));
		colorSelectionPanel.add(initRectangle(Color.GREEN));
		colorSelectionPanel.add(initRectangle(Color.LIME));
    	
    	for(int i = 0; i<12; i++) {
    		colorSelectionContainer.getChildren().add(colorSelectionPanel.get(i));
    	}
    	selectorImageView = new ImageView(selector);
    	selectorImageView.setFitHeight(50);
    	selectorImageView.setFitWidth(50);
    	selectorItself = new HBox();
    	selectorItself.getChildren().add(selectorImageView);
    	selectorItself.setAlignment(Pos.TOP_RIGHT);
    	
    	selectorItself.setPrefSize(263, 40);
    	
		HBox selectorContainer = new HBox();
		selectorContainer.setPadding(new Insets(-25,0,0,0));
		selectorContainer.setPrefSize(1200, 0);
		selectorContainer.getChildren().add(selectorItself);
    	
    	//couleur sélectionnée par défaut
    	selectedColor = (Color) colorSelectionPanel.get(0).getFill();
    	colorSelectionPanel.get(0).setStrokeWidth(5);
    	colorSelectionPanel.get(0).setStroke(Color.BLACK);
    	
        
    	for(int i=0; i<12; i++) {
    		modifySelection(i);
    	}
    	
    	
        coloredCircleBox = new VBox();
        coloredCircleBox.setAlignment(Pos.CENTER_RIGHT);
        coloredCircleBox.setSpacing(25);
        
        colorPickersBox = new VBox();
        colorPickersBox.setAlignment(Pos.CENTER);
        colorPickersBox.setPrefWidth(200);
        colorPickersBox.setSpacing(58);
        
        greyCircleBox = new VBox();
        greyCircleBox.setSpacing(25);
        greyCircleBox.setAlignment(Pos.CENTER);
        
        trashBoxesBox = new VBox();
        trashBoxesBox.setSpacing(45);
        trashBoxesBox.setAlignment(Pos.CENTER_RIGHT);
        
        colorButtonsBox = new VBox();
        colorButtonsBox.setSpacing(58);
        colorButtonsBox.setPadding(new Insets(0,20,0,100));
        colorButtonsBox.setAlignment(Pos.CENTER);
        
        greyButtonsBox = new VBox();
        greyButtonsBox.setSpacing(58);
        greyButtonsBox.setPadding(new Insets(0,100,0,10));
        greyButtonsBox.setAlignment(Pos.CENTER);
        
        
        HBox colorContainer = new HBox();
        colorContainer.setAlignment(Pos.CENTER);
		colorContainer.setPadding(new Insets(10));
		colorContainer.setSpacing(10);
		
		plusIV = new ImageView(plusColor);
		plusIV.setFitHeight(64);
		plusIV.setFitWidth(64);
		addColorLabel = new Label("Ajouter une couleur");
		addColorLabel.setFont(new Font(20));
		addColor = new VBox();
		addColor.setMaxWidth(230);
		addColor.getChildren().addAll(plusIV, addColorLabel);
		addColor.setAlignment(Pos.CENTER);

		addColor.setOnMouseClicked(new EventHandler() {

			@Override
			public void handle(Event event) {
				if(nbColors<7) {
					colorPickers.add(new ColorPicker(selectedColor));
		    		
					Circle c1 = new Circle(30);
		        	Circle c2 = new Circle(30);
		        	c1.setFill(selectedColor);
		        	c1.setStrokeWidth(1);
		        	c1.setStroke(Color.BLACK);
		        	c2.setFill(selectedColor.grayscale());
		        	c2.setStrokeWidth(1);
		        	c2.setStroke(Color.BLACK);
		        	coloredCircles.add(c1);
		        	greyCircles.add(c2);
		        	greyValues.add(new Label("#" + (selectedColor.grayscale() + "").substring(2,(selectedColor.grayscale() + "").length() - 2)));
		        	Button b1 = new Button("Copier la valeur de la couleur");
		        	Button b2 = new Button("Copier la valeur du gris");
		        	colorButtons.add(b1);
		        	greyButtons.add(b2);
		        	
		        	ImageView imageView = new ImageView(image);
					imageView.setFitHeight(39);
			    	imageView.setFitWidth(39);
			    	HBox img = new HBox();
			    	img.getChildren().add(imageView);
			    	
			    	trashBoxes.add(img);
		        	
		        	nbColors++;
		        	affichage();
		        	if(nbColors==7) {
		        		addColor.getChildren().clear();
		        	}
				}
												
			}
			
		});		
		colorContainer.getChildren().addAll(colorButtonsBox, coloredCircleBox,colorPickersBox, greyCircleBox, greyButtonsBox, trashBoxesBox);
		
		copied = new Label();
		copied.setFont(new Font(20));
		copied.setTextFill(Color.GREEN);
		HBox copiedBox = new HBox();
		copiedBox.getChildren().add(copied);
		copiedBox.setPrefSize(1200, 0);
		copiedBox.setAlignment(Pos.CENTER);
		
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(20);
        root.getChildren().addAll(title, colorSelectionContainer,selectorContainer, copiedBox, colorContainer, addColor);
 
        Scene scene = new Scene(root, 1200, 900);
        //stage.getIcons().add(new Image("file:/home/infoetu/vanrobah/eclipse-workspace/ihm/src/main/java/IHM/ihm/trashbox.png"));
        stage.setTitle("colorPicker test");
 
        stage.setScene(scene);
        stage.show();
    }
    
    @SuppressWarnings("unchecked")
	private void setEvents(int i) {
    	trashBoxes.get(i).setOnMouseClicked(new EventHandler(){

			@Override
			public void handle(Event event) {
				coloredCircles.remove(i);
				colorPickers.remove(i);
				greyCircles.remove(i);
				trashBoxes.remove(i);
				greyValues.remove(i);
				if(nbColors==7) {
					addColor.getChildren().addAll(plusIV, addColorLabel);
				}
				nbColors--;
				affichage();
			}
			
		});
    	colorPickers.get(i).setOnAction(new EventHandler() {
			public void handle(Event t) {
				coloredCircles.get(i).setFill(colorPickers.get(i).getValue());
				greyCircles.get(i).setFill(colorPickers.get(i).getValue().grayscale());
				greyValues.get(i).setText("#" + (colorPickers.get(i).getValue().grayscale() + "").substring(2,(colorPickers.get(i).getValue().grayscale() + "").length() - 2));
			}
		});
    	
    	colorButtons.get(i).setOnAction(new EventHandler() {

			@Override
			public void handle(Event event) {
				//copy grey color value of the selected color
				Color tmp = (Color) (coloredCircles.get(i).getFill());
		        content.putString("#" + (tmp + "").substring(2,(tmp + "").length() - 2));
		        clipboard.setContent(content);
		        copied.setText("Valeur "+ "#" + (tmp + "").substring(2,(tmp + "").length() - 2) + " copiée !");
		        
			}
    		
    	});
    	
    	greyButtons.get(i).setOnAction(new EventHandler() {

			@Override
			public void handle(Event event) {
				//copy grey color value of the selected color
				Color tmp = (Color) (greyCircles.get(i).getFill());
		        content.putString("#" + (tmp + "").substring(2,(tmp + "").length() - 2));
		        clipboard.setContent(content);
		        copied.setText("Valeur "+ "#" + (tmp + "").substring(2,(tmp + "").length() - 2) + " copiée !");
		        
			}
    		
    	});
    }
    
    private void affichage() {
    	coloredCircleBox.getChildren().clear();
		colorPickersBox.getChildren().clear();
		greyCircleBox.getChildren().clear();
		trashBoxesBox.getChildren().clear();
		colorButtonsBox.getChildren().clear();
		greyButtonsBox.getChildren().clear();
    	for(int i = 0; i<nbColors; i++) {
    		trashBoxesBox.getChildren().add(trashBoxes.get(i));
	    	HBox greyContainer = new HBox();
        	greyContainer.setAlignment(Pos.CENTER);
        	greyContainer.setSpacing(10);
        	coloredCircleBox.getChildren().add(coloredCircles.get(i));
        	colorPickersBox.getChildren().add(colorPickers.get(i));
        	greyContainer.getChildren().addAll(greyCircles.get(i), greyValues.get(i));
        	greyCircleBox.getChildren().add(greyContainer);
        	colorButtonsBox.getChildren().add(colorButtons.get(i));
        	greyButtonsBox.getChildren().add(greyButtons.get(i));
        	setEvents(i);
    	}
    }
    
    public static void main(String[] args) {
        launch(args);
    }
 
    private Rectangle initRectangle(Color c) {
    	Rectangle r = new Rectangle(50, 50);
    	r.setFill(c);
    	r.setStrokeWidth(1);
    	r.setStroke(Color.BLACK);
    	return r;
    }
    
    @SuppressWarnings("unchecked")
	private void modifySelection(int i) {
    	colorSelectionPanel.get(i).setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event event) {
				for(int j = 0; j<12; j++) {
					colorSelectionPanel.get(j).setStrokeWidth(1);
					colorSelectionPanel.get(j).setStroke(Color.BLACK);
				}
				colorSelectionPanel.get(i).setStrokeWidth(5);
				colorSelectionPanel.get(i).setStroke(Color.BLACK);
				selectedColor = (Color) colorSelectionPanel.get(i).getFill();
				selectorItself.setPrefSize(263+i*66, 40);
			}
		});
    }
}
