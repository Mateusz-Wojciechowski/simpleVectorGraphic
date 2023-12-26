package com.example.vectorgraphicproject;

import javaClasses.*;
import javaClasses.Shapes.*;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelloController {
    @FXML
    private Button translateButton;
    @FXML
    private Canvas canvas;
    @FXML
    private Pane canvasPane;

    private List<Item> itemList = new ArrayList<>();

    @FXML
    public void initialize() {
        drawShapes();
    }

    private void drawShapes() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Circle circle = new Circle(50, new Point(1920 / 2 - 50, 1080 / 2 - 50), false);
        Triangle triangle = new Triangle(new Point(1920 / 4, 1080 / 4), new Point(1920 / 4 + 100, 1080 / 4 - 80), new Point(1920 / 4 + 50, 1080 / 4 + 20), true);
        Rect rect = new Rect(100, 120, new Point(1920 / 4 + 300, 1080 / 4 + 100), false);
        Segment segment = new Segment(new Point(1920 / 2 + 100, 1080 / 2 + 200), new Point(1920 / 2 + 130, 1080 / 2 + 110));
        Font font = new Font("Arial", 20);
        TextItem textItem = new TextItem("Hello world", new Point(1920 / 2 + 435, 1080 / 4 - 100), font);
        Star star = new Star(new Point(1920 / 2 + 432, 1080 / 2 + 125), 5, 100, 50);
        ComplexItem snowman = createSnowman();

        addItem(circle, gc);
        addItem(triangle, gc);
        addItem(rect, gc);
        addItem(segment, gc);
        addItem(textItem, gc);
        addItem(star, gc);
        addItem(snowman, gc);
    }

    private void addItem(Item item, GraphicsContext gc) {
        itemList.add(item);
        item.draw(gc);
    }

    @FXML
    protected void translateButtonAction() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        clearCanvas(gc);

        Random random = new Random();
        for (Item item : itemList) {
            item.translate(new Point(random.nextInt(-100, 100), random.nextInt(-100, 100)), gc);

            item.draw(gc);
        }
    }

    private void clearCanvas(GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    public ComplexItem createSnowman(){
        int bottomRadius = 100;
        int middleRadius = 70;
        int topRadius = 50;

        int bottomCenterX = 400;
        int bottomCenterY = 500;
        int middleCenterX = 400;
        int middleCenterY = bottomCenterY - bottomRadius - middleRadius;
        int topCenterX = 400;
        int topCenterY = middleCenterY - middleRadius - topRadius;

        Circle bottomBall = new Circle(bottomRadius, new Point(bottomCenterX - bottomRadius, bottomCenterY - bottomRadius), false);
        Circle middleBall = new Circle(middleRadius, new Point(middleCenterX - middleRadius, middleCenterY - middleRadius), false);
        Circle topBall = new Circle(topRadius, new Point(topCenterX - topRadius, topCenterY - topRadius), false);

        Circle leftEye = new Circle(5, new Point(topCenterX - 15 - 5, topCenterY - 10 - 5), true);
        Circle rightEye = new Circle(5, new Point(topCenterX + 15 - 5, topCenterY - 10 - 5), true);
        Triangle nose = new Triangle(new Point(topCenterX, topCenterY),
                new Point(topCenterX, topCenterY + 10),
                new Point(topCenterX + 20, topCenterY), true);

        ComplexItem snowman = new ComplexItem();
        snowman.addChild(bottomBall);
        snowman.addChild(middleBall);
        snowman.addChild(topBall);
        snowman.addChild(leftEye);
        snowman.addChild(rightEye);
        snowman.addChild(nose);
        return snowman;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}