import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class S_Snake extends PApplet {


// 1. Follow the recipe instructions inside the Segment class.

// This class will be used to represent each part of the moving snake.

class Segment {

  //2. Create x and y member variables to hold the location of each segment.
  int x;
  int y;
  // 3. Add a constructor with parameters to initialize each variable.
  Segment(int x, int y){
    this.x = x;
    this.y = y;
  }

  // 4. Add getter and setter methods for both the x and y member variables.
  public int getx(){
   return x; 
  }
  public int gety(){
    return y;
  }
}


// 5. Create (but do not initialize) a Segment variable to hold the head of the Snake
Segment head;


// 6. Create and initialize String to hold the direction of your snake e.g. "up"
String dir = "";


// 7. Create and initialize a variable to hold how many pieces of food the snake has eaten.
int food = 0;


// 8. Create and initialize foodX and foodY variables to hold the location of the food.
int foodx = ((int)random(50)*10);
int foody = ((int)random(50)*10);
// (Hint: use the random method to set both the x and y to random locations within the screen size (500 by 500).)

//int foodX = ((int)random(50)*10);



public void setup() {

  // 9. Set the size of your sketch (500 by 500).

  


  // 10. initialize your head to a new segment.
  head = new Segment(50,50);

  // 11. Use the frameRate(int rate) method to set the rate to 20.
  frameRate(20);
}


public void draw() {

  background(0);


  //12. Call the drawFood, drawSnake, move, and collision methods.
  drawFood();
  drawSnake();
  move();
  collision();
  manageTail();
}


// 13. Complete the drawFood method below. (Hint: each piece of food should be a 10 by 10 rectangle).

public void drawFood() {
  fill(0, 255, 0);
  rect(foodx, foody, 10, 10);
}


//14. Draw the snake head

public void drawSnake() {
  fill(0, 0, 255);
  rect(head.x, head.y, 10, 10); 

  //test your code
}


// 15. Complete the move method below.

public void move() {

  // 16. Create a switch statement using your direction variable. Depending on the direction, add a new segment to your snake.
  //This is an incomplete switch statement:
  switch(dir) {
  case "up":
    head.y -= 10;
    break;
  case "down":
    head.y += 10;
    break;
  case "left":
   head.x -= 10; 
    break;
  case "right":
    head.x += 10; 
    break;
  }


  // 17. Call the checkBoundaries method to make sure the snake doesn't go off the screen.
  checkBoundaries();
}


// 18. Complete the keyPressed method below. Use if statements to set your direction variable depending on what key is pressed.

public void keyPressed() {
  switch(keyCode){
   case UP:
   if(!dir.equals("down")){
    dir = "up";
   }
    break;
  case DOWN:
    if(!dir.equals("up")){
    dir = "down";
   }
    break;
  case LEFT:
   if(!dir.equals("right")){
    dir = "left";
   }
    break;
  case RIGHT:
    if(!dir.equals("left")){
    dir = "right";
   }
    break;
  }
}



// 19. check if your head is out of bounds (teleport your snake to the other side).

public void checkBoundaries() {
  if(head.x>500){
    head.x = 0;
  }
  if(head.x<0){
   head.x = 500; 
  }
  if(head.y>500){
   head.y = 0; 
  }
  if(head.y<0){
   head.y = 500; 
  }
}



//20. Make sure that the key for your current direction\u2019s opposite doesn\u2019t work(i.e. If you\u2019re going up, down key shouldn\u2019t work)



// 21. Complete the missing parts of the collision method below.

public void collision() {
  if(head.x == foodx && head.y == foody){
    food++;
    foodx = ((int)random(50)*10);
    foody = ((int)random(50)*10);
  }
}



/**
 
 ** Part 2: making the tail
 
 **/

//  1. Create and initialize an ArrayList of Segments. (This will be your snake tail!)
ArrayList <Segment> tail = new ArrayList <Segment>();

// 2. Complete the missing parts of the manageTail method below.

public void manageTail() {

  //Call the drawTail and checkTailCollision methods.
  drawTail();
  checkTailCollision();
  // Add a new Segment to your ArrayList that has the same X and Y as the head of your snake.
  tail.add(new Segment(head.x, head.y));
  // While the snake size is greater than your food, remove the first Segment in your snake.
  while(tail.size() > food){
    tail.remove(0);
  }
}

public void drawTail() {
    for(Segment i:tail){
     rect(i.x, i.y, 10, 10); 
    }
}


// 3. Complete the missing parts of the bodyCollision method below.

public void checkTailCollision() {

  // If your head has the same location as one of your segments...
  for(Segment i:tail){
     if(i.x == head.x && i.y == head.y){
       food = 1;
     }
    }
  // Set food back to 1.

  //Call this method at the begining of your manageTail method.
}
  public void settings() {  size(500, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "S_Snake" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
