public class Player extends Element {

    Player(int x, int y){
        this.position = new Position(x,y);
        this.ch = '⚇';
        this.color = "#66b3ff";
    }

    public Position moveUp(){
         return new Position(position.getX(),position.getY()-1);
    }

    public Position moveDown(){
         return new Position(position.getX(),position.getY()+1);
    }

    public Position moveLeft(){
         return new Position(position.getX()-1,position.getY());
    }

    public Position moveRight(){
        return new Position(position.getX()+1,position.getY());
    }

}
