package c21430484;

import processing.core.PApplet;
import processing.core.PShape;

public class CrossVisual 
{
    TeamVisual mv;

    PShape cross; 
    PShape innerCross; 

    float objScale; 
    float scaler;
    float abScale;

    long currentTime;

    public CrossVisual(TeamVisual mv)
    {
        this.mv = mv; 
        innerCross = mv.loadShape("innerCross.obj");
        cross = mv.loadShape("cross.obj");

        objScale = 0.8f; 

        cross.rotateX(+260);
        cross.scale(objScale);

        innerCross.rotateX(+260);
        innerCross.scale(objScale);
    }

    
    public void changeScale()
    {
        float scaler = 0.8f;  

        abScale = (mv.getSmoothedBands()[0] * 0.8f); 
        abScale = PApplet.map(abScale, 0f, 40f, 0.8f, 1.1f);
        scaler = PApplet.lerp(scaler, abScale, 0.85f);

        if(objScale > scaler)
        {   
            while(objScale > scaler)
            {
                objScale = objScale * 0.999f;
                cross.scale(0.999f);
                innerCross.scale(0.999f);
            }
        }
        else 
        {
            while(objScale < scaler)
            {
                objScale = objScale * 1.001f;
                cross.scale(1.001f);
                innerCross.scale(1.001f);
            }
        }
    }


    public void render(int phase, int direction)
    {
        cross.setFill(mv.color(208, 152, 3));
        mv.camera(0f, 0f, mv.height * .86602f, 0f, 0f, 0f, 0f, 1f, 0f);
        mv.lights();
        mv.shape(cross);
        
        innerCross.setFill(mv.color(0));
        mv.shape(innerCross);

        if(phase == 1)
            renderPhase1(direction);
        else if(phase == 2)
            renderPhase2(direction); 
    }

    public void renderPhase1(int direction)
    {
        if(mv.timeElapsed() > 5000)
        {
            cross.rotateY(0.005f * direction);
            cross.rotateZ(0.005f * direction);    

            innerCross.rotateY(0.005f * direction);
            innerCross.rotateZ(0.005f * direction);    
        }

        if(mv.timeElapsed() > 38700)
        {
            changeScale();

            cross.rotateX(-0.0005f * direction);   
            innerCross.rotateX(-0.0005f * direction);
        }    

        if(mv.timeElapsed() > 125000 && mv.timeElapsed() < 137500)
        {
            if(mv.timeElapsed() < 131250)
            {
                cross.rotateY(0.02f * direction);
                cross.rotateZ(0.02f * direction);    
    
                innerCross.rotateY(0.02f * direction);
                innerCross.rotateZ(0.02f * direction);  
    
                cross.rotateX(-0.02f * direction);   
                innerCross.rotateX(-0.02f * direction);
            }
            else 
            {
                cross.rotateY(-0.02f * direction);
                cross.rotateZ(-0.02f * direction);    
    
                innerCross.rotateY(-0.02f * direction);
                innerCross.rotateZ(-0.02f * direction);  
    
                cross.rotateX(0.07f * direction);   
                innerCross.rotateX(0.07f * direction);
            }
        }

        if(mv.timeElapsed() > 186500)
        {
            cross.rotateY(0.01f * direction);
            innerCross.rotateY(0.01f * direction);
        
        }            
    }

    public void renderPhase2(int direction)
    {
        cross.rotateZ(0.01f * direction);    
        innerCross.rotateZ(0.01f * direction);  

        if(mv.timeElapsed() > 170000 && mv.timeElapsed() < 187000)
        {
            cross.rotateY(0.035f * direction);
            innerCross.rotateY(0.035f * direction);
            
            cross.rotateZ(0.045f * direction);     
            innerCross.rotateZ(0.045f * direction);  

            cross.rotateX(-0.02f * direction);   
            innerCross.rotateX(-0.02f * direction);  
        }
    }
}   

