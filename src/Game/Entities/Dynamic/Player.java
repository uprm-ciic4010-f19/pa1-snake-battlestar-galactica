package Game.Entities.Dynamic;

import Main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

//----------------- import math for score -------------------------//

import java.math.*;

//----------------------------- end ------------------------------//

/**
 * Created by AlexVR on 7/2/2018.
 */
public class Player {

    public int lenght;
    public int n;
    public boolean justAte;
    private Handler handler;

    public int xCoord;
    public int yCoord;

    public int moveCounter;

    public String direction;//is your first name one?
    
    //----------------------- variable for speed -------------------------//
    
    public int speed = 5;
    public double comida = 0;

    //-------------------------------- end ------------------------------//
    public Player(Handler handler){
        this.handler = handler;
        xCoord = 0;
        yCoord = 0;
        moveCounter = 0;
        direction= "Right";
        justAte = false;
        lenght= 1;

    }

    public void tick(){
        moveCounter++;
        if(moveCounter>=speed) {
            checkCollisionAndMove();
            moveCounter=0;
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)){
        	if(direction != "Down") { //Preventing Backtracking
        		direction="Up";
        	}
        
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)){
        	if (direction != "Up") { //Preventing Backtracking
        		direction="Down";
        	}
            
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)){
        	if (direction != "Right") {//Preventing Backtracking
        		direction="Left";
        	}
        	
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)){
        	if (direction != "Left") {//Preventing Backtracking
        		direction="Right";
        	}
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
        	
        	//handler.getKeyManager().setUi
        }
        
//--------------------------------Implementing "N"---------------------------------//
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)){
        	lenght++;
            Tail tail= null;

            switch (direction){
                case "Left":
                    if( handler.getWorld().body.isEmpty()){
                        if(this.xCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                            tail = new Tail(this.xCoord+1,this.yCoord,handler);
                        }else{
                            if(this.yCoord!=0){
                                tail = new Tail(this.xCoord,this.yCoord-1,handler);
                            }else{
                                tail =new Tail(this.xCoord,this.yCoord+1,handler);
                            }
                        }
                    }else{
                        if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
                            tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler);
                        }else{
                            if(handler.getWorld().body.getLast().y!=0){
                                tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler);
                            }else{
                                tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler);

                            }
                        }

                    }
                    break;
                case "Right":
                    if( handler.getWorld().body.isEmpty()){
                        if(this.xCoord!=0){
                            tail=new Tail(this.xCoord-1,this.yCoord,handler);
                        }else{
                            if(this.yCoord!=0){
                                tail=new Tail(this.xCoord,this.yCoord-1,handler);
                            }else{
                                tail=new Tail(this.xCoord,this.yCoord+1,handler);
                            }
                        }
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                        }else{
                            if(handler.getWorld().body.getLast().y!=0){
                                tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                            }else{
                                tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                            }
                        }

                    }
                    break;
                case "Up":
                    if( handler.getWorld().body.isEmpty()){
                        if(this.yCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                            tail=(new Tail(this.xCoord,this.yCoord+1,handler));
                        }else{
                            if(this.xCoord!=0){
                                tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                            }else{
                                tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                            }
                        }
                    }else{
                        if(handler.getWorld().body.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                        }else{
                            if(handler.getWorld().body.getLast().x!=0){
                                tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                            }else{
                                tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                            }
                        }

                    }
                    break;
                case "Down":
                    if( handler.getWorld().body.isEmpty()){
                        if(this.yCoord!=0){
                            tail=(new Tail(this.xCoord,this.yCoord-1,handler));
                        }else{
                            if(this.xCoord!=0){
                                tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                            }else{
                                tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                            } System.out.println("Tu biscochito");
                        }
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                        }else{
                            if(handler.getWorld().body.getLast().x!=0){
                                tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                            }else{
                                tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                            }
                        }

                    }
                    break;
                    
            }
            handler.getWorld().body.addLast(tail);
            handler.getWorld().playerLocation[tail.x][tail.y] = true;
        }
//---------------------------------------End----------------------------------------//  
        
        
//----------------------------Increasing/Decreasing speed--------------------------//
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_EQUALS)){
        	speed--;
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_MINUS)){
        	speed++;
        }
//----------------------------------------End----------------------------------------//
        
        
    }

    public void checkCollisionAndMove(){
        handler.getWorld().playerLocation[xCoord][yCoord]=false;
        int x = xCoord;
        int y = yCoord;
        switch (direction){
            case "Left":
                if(xCoord==0){
                	//kill();
                	
                	//------------------------- Teleportation in X axis ---------------------//
                    xCoord += (xCoord) + handler.getWorld().GridWidthHeightPixelCount-1;
                    //-------------------------------- end ---------------------------------//
                }else{
                    xCoord--;
                }
                break;
            case "Right":
                if(xCoord==handler.getWorld().GridWidthHeightPixelCount-1){
                    //kill();
                	//------------------------ Teleportation in X axis -------------------//
                    xCoord = 0;
                    //-------------------------------- end -------------------------------//
                }else{
                    xCoord++;
                }
                break;
            case "Up":
                if(yCoord==0){
                    //kill();
                	//------------------------- Teleportation in Y axis -----------------//
                	yCoord += (yCoord) + handler.getWorld().GridWidthHeightPixelCount-1;
                	//-------------------------------- end ------------------------------//
                }else{
                    yCoord--;
                }
                break;
            case "Down":
                if(yCoord==handler.getWorld().GridWidthHeightPixelCount-1){
                    //kill();
                	//------------------------ Teleportation in Y axis -----------------//
                	yCoord = 0;
                	//------------------------------- end ------------------------------//
                }else{
                    yCoord++;
                }
                break;
        }
        handler.getWorld().playerLocation[xCoord][yCoord]=true;


        if(handler.getWorld().appleLocation[xCoord][yCoord]){
            Eat();
        }

        if(!handler.getWorld().body.isEmpty()) {
            handler.getWorld().playerLocation[handler.getWorld().body.getLast().x][handler.getWorld().body.getLast().y] = false;
            handler.getWorld().body.removeLast();
            handler.getWorld().body.addFirst(new Tail(x, y,handler));
        }

    }

    public void render(Graphics g,Boolean[][] playeLocation){
        Random r = new Random();
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {
            	
            	//--------- Changed the color of the "Snake" to GREEN ----------//
            	Color snake_color = new Color (0,204,0);
                g.setColor(snake_color);  
                //----------------------- end ---------------------------------//
                
                if(playeLocation[i][j]||handler.getWorld().appleLocation[i][j]){
                    g.fillRect((i*handler.getWorld().GridPixelsize),
                            (j*handler.getWorld().GridPixelsize),
                            handler.getWorld().GridPixelsize,
                            handler.getWorld().GridPixelsize);
                }

            }
        }


    }
    

    public void Eat(){
    	justAte = true;
        lenght++;
        Tail tail= null;
        handler.getWorld().appleLocation[xCoord][yCoord]=false;
        handler.getWorld().appleOnBoard=false;
        //------------------ experiment adding speed to snake when eats ------------------//
        
        moveCounter++;
        if(moveCounter>=speed) {
        	checkCollisionAndMove();
        	moveCounter=0;
        	speed--;
        	}
        
        //-------------------------------------- end ------------------------------------//

        switch (direction){
            case "Left":
                if( handler.getWorld().body.isEmpty()){
                    if(this.xCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail = new Tail(this.xCoord+1,this.yCoord,handler);
                    }else{
                        if(this.yCoord!=0){
                            tail = new Tail(this.xCoord,this.yCoord-1,handler);
                        }else{
                            tail =new Tail(this.xCoord,this.yCoord+1,handler);
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler);
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler);
                        }else{
                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler);

                        }
                    }

                }
                break;
            case "Right":
                if( handler.getWorld().body.isEmpty()){
                    if(this.xCoord!=0){
                        tail=new Tail(this.xCoord-1,this.yCoord,handler);
                    }else{
                        if(this.yCoord!=0){
                            tail=new Tail(this.xCoord,this.yCoord-1,handler);
                        }else{
                            tail=new Tail(this.xCoord,this.yCoord+1,handler);
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().x!=0){
                        tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                        }
                    }

                }
                break;
            case "Up":
                if( handler.getWorld().body.isEmpty()){
                    if(this.yCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=(new Tail(this.xCoord,this.yCoord+1,handler));
                    }else{
                        if(this.xCoord!=0){
                            tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                        }
                    }

                }
                break;
            case "Down":
                if( handler.getWorld().body.isEmpty()){
                    if(this.yCoord!=0){
                        tail=(new Tail(this.xCoord,this.yCoord-1,handler));
                    }else{
                        if(this.xCoord!=0){
                            tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                        } System.out.println("Tu biscochito");
                    }
                }else{
                    if(handler.getWorld().body.getLast().y!=0){
                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                        }
                    }

                }
                break;
                
                
        }
        
        if(justAte) {
        	
        	//--------------------------- Calculating rounded score ---------------------------//
        	comida += (Math.sqrt(2* comida +1));
        	System.out.println(Math.round(comida));
        	//-------------------------------------- end -------------------------------------//
        	
        }
        
        handler.getWorld().body.addLast(tail);
        handler.getWorld().playerLocation[tail.x][tail.y] = true;
    }

    public void kill(){
        lenght = 0;
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

                handler.getWorld().playerLocation[i][j]=false;

            }
        }
    }
    

    public boolean isJustAte() {
        return justAte;
    }

    public void setJustAte(boolean justAte) {
        this.justAte = justAte;
    }
    
}
