/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wmjxb2_project;

/**
 *
 * @author wjohnke
 */
public class Obstacle {
    public Edge coord;
    public int speed=0;
    public int directionX=0;
    public int directionY=0;
    
    public Obstacle(int currentX, int currentY, int speed, int directionX, int directionY){
        this.coord=new Edge();
        this.coord.vertX=currentX;
        this.coord.vertY=currentY;
        this.speed=speed;
        this.directionX=directionX;
        this.directionY=directionY;
    }
    
    public void move(Obstacle node, int limit){
        
        
        for(int i=0; i<node.speed; i++){
            node.coord.vertX+=node.directionX;
            node.coord.vertY+=node.directionY;
            
            
            /*Handle circumstances where it hits a wall*/
            if((node.coord.vertX>limit)&&( (node.coord.vertY>limit)^(node.coord.vertY)<1 )){
                
                //Obstacle has hit a corner on right side
                node.directionX=(-1)*node.directionX;
                node.directionY=(-1)*node.directionY;

                node.coord.vertX=limit;
                //Either reset vertY coordinate to top-right or bottom-right corner
                node.coord.vertY=(node.coord.vertY<=1)? (1) : limit;
            }
            else if ((node.coord.vertX<1)&&( (node.coord.vertY>limit)^(node.coord.vertY)<1 )){
                //Obstacle has hit a corner on left side
                node.directionX=(-1)*node.directionX;
                node.directionY=(-1)*node.directionY;

                node.coord.vertX=1;
                //Either reset vertY coordinate to top-left or bottom-left corner
                node.coord.vertY=(node.coord.vertY<=1)? (1) : limit;
            }
            else if((node.coord.vertX>limit)^(node.coord.vertX<1) ){
                //Obstacle has bounced into east wall or west wall
                node.directionX=(-1)*node.directionX;

                node.coord.vertX=(node.coord.vertX<=1) ? (1) : limit;
            }
            else if(((node.coord.vertY)>limit)^(node.coord.vertY<1) ){
                //Obstacle has bounced into north or south wall
                node.directionY=(-1)*node.directionY;

                node.coord.vertY=(node.coord.vertY<=1) ? (1) : limit;
            }
            else{
            }
            
        }
        
    }
    
    public void move(Obstacle robot, Obstacle ob1, Obstacle ob2, Edge goal){
        
        /*
        Due to nature of the problem, the most optimal solution/shortest path (if the distance is >1,
        will be to move to edges diagonally connected as much as possible. The path from one edge to 
        another is essentially the hypotenuse of a triangle, so the quickest path will be the one that maintains
        the slope of that hypotenuse the most.
        
        For these reasons I just based the decision on where to move off of the current coordinate of the robot
        and whether or not it can move diagonally (as long as it's making progress towards the goal. If it cannot,
        due to an obstacle blocking the way, it will wait, hoping that the next time instant does not put an
        obstacle onto the same edge as itself.
        */
        
        Edge first=ob1.coord;
        Edge second=ob2.coord;
        int distance;
        
        if(robot.coord.vertX==goal.vertX){
            //Same column
            if(robot.coord.vertY==goal.vertY){
                System.out.println("You have won the game!");
            }
            else{
                distance=goal.vertY-robot.coord.vertY;
                if(distance>0){
                    if( !((robot.coord.vertY+1==first.vertY) || (robot.coord.vertY+1==second.vertY)) ){
                        robot.coord.vertY++;
                    }
                }
                else{
                   if( !((robot.coord.vertY-1==first.vertY) || (robot.coord.vertY-1==second.vertY)) ){
                        robot.coord.vertY--;
                    } 
                }
            }
        }
        
        else if(robot.coord.vertY==goal.vertY){
            //Same row
            distance=goal.vertX-robot.coord.vertX;
            if(distance>0){
                if( !((robot.coord.vertX+1==first.vertX) || (robot.coord.vertX+1==second.vertX)) ){
                    robot.coord.vertX++;
                }
            }
            else{
                if( !((robot.coord.vertX-1==first.vertX) || (robot.coord.vertX-1==second.vertX)) ){
                    robot.coord.vertX--;
                }
            }
        }
        
        else{
            int distanceX, distanceY;
            distanceX=goal.vertX-robot.coord.vertX;
            distanceY=goal.vertY-robot.coord.vertY;
            
            
            
            if(distanceX>0){
                if(distanceY>0){
                    if(!(((robot.coord.vertX+1==first.vertX) && (robot.coord.vertY+1==first.vertY)) || ((robot.coord.vertX+1==second.vertX) && (robot.coord.vertY+1==second.vertY)) )){
                        robot.coord.vertX++;
                        robot.coord.vertY++;
                    }
                }
                else{
                    if(!(((robot.coord.vertX+1==first.vertX) && (robot.coord.vertY-1==first.vertY)) || ((robot.coord.vertX+1==second.vertX) && (robot.coord.vertY-1==second.vertY)) )){
                        robot.coord.vertX++;
                        robot.coord.vertY--;
                    }
                }
            }else{
                if(distanceY>0){
                        robot.coord.vertX--;
                        robot.coord.vertY++;
                    }                    if(!(((robot.coord.vertX-1==first.vertX) && (robot.coord.vertY+1==first.vertY)) || ((robot.coord.vertX-1==second.vertX) && (robot.coord.vertY+1==second.vertY)) )){

                }
                else{
                    if(!(((robot.coord.vertX-1==first.vertX) && (robot.coord.vertY-1==first.vertY)) || ((robot.coord.vertX-1==second.vertX) && (robot.coord.vertY-1==second.vertY)) )){
                        robot.coord.vertX--;
                        robot.coord.vertY--;
                    }
                }
            }
            
        }
        
        
        
    }
    

}
