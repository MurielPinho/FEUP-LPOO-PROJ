package com.g38.model;

public class Wall extends Element {
    public Wall(int x, int y){
        this.position = new Position(x,y);
        this.ch = '█';
        this.color = "#D9D9D9";
    }
}
