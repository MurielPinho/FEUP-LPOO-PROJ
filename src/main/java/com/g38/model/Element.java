package com.g38.model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

abstract public class Element {
    protected Position position;
    char ch;
    String color;

    Element(){
        this.position = new Position(10,10);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setColor(String color)
    {
     this.color = color;
    }

    public String getColor()
    {
     return color;
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString(this.color));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), Character.toString(this.ch), SGR.BOLD);

    }
}
