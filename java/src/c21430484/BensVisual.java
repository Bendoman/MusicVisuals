package c21430484;

import ie.tudublin.*;
import ddf.minim.AudioBuffer;
import processing.core.PFont;
import processing.core.PImage;

public class BensVisual extends Visual
{    
    WaveForm wf;
    CrossVisual cv; 
    AudioBandsVisual abv;
    TrumpetBandVisual tbv;
    CircularWaveVisual cbv;

    PImage justice; 
    AudioBuffer ab; 

    long startTime;
    long currentTime;

    int spinDirection; 

    PFont font; 
    
    public void settings()
    {
        fullScreen(P3D); 
    }

    public void setup()
    {
        startMinim();

        // Call loadAudio to load an audio file to process 
        loadAudio("Genesis.mp3");   
        
        wf = new WaveForm(this);
        cv = new CrossVisual(this);
        abv = new AudioBandsVisual(this);
        tbv = new TrumpetBandVisual(this);
        cbv = new CircularWaveVisual(this);
        font = createFont("Futura.ttf", 80);

        textFont(font);
        justice = loadImage("justice.png");

        startTime = -1;
        spinDirection = 1; 

        background(0);
    }

    public void keyPressed()
    {
        if (key == ' ')
        {            
            if(timeElapsed() > 1)
            {
                getAudioPlayer().pause();
                setup();
            }
            else 
            {
                getAudioPlayer().cue(0);
                getAudioPlayer().play();
                startTime = System.currentTimeMillis();
            }
        }

        if(key == CODED)
        {
            if(keyCode == LEFT)
                spinDirection = -1;
            else if(keyCode == RIGHT)
                spinDirection = 1;
        }
    }   

    boolean drawBackground = true; 
    
    public void draw()
    {
        currentTime = System.currentTimeMillis();
        if(startTime != -1)
        {
            if((timeElapsed() > 124060 && timeElapsed() < 137500) || (timeElapsed() > 181500 && timeElapsed() < 186500))
                drawBackground = false;
            else 
                drawBackground = true;
        }

        if(drawBackground)
            background(0);

        try
        {
            // Call this if you want to use FFT data
            calculateFFT(); 
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        // Call this is you want to use frequency bands
        calculateFrequencyBands(); 

        // Call this is you want to get the average amplitude
        calculateAverageAmplitude();        
        

        if(startTime != -1)
        {
            if(timeElapsed() > 137500 && timeElapsed() < 186500)
                renderPhase2();
            else 
                renderPhase1();
        }
        else 
            renderPhase1();
    }  

    
    public void renderPhase1()
    {
        translate(-width/2, -height/2, 0);
        
        if(timeElapsed() == 0)
        {
            textSize(80);
            text("Press space to start", 560, height - 200);          
        }
        
        if(timeElapsed() < 202500)
            tbv.render(1);
        else 
            tbv.render(2);

        wf.render();
        
        cv.render(1, spinDirection); 
    }
    
    
    public void renderPhase2()
    {
        translate(-width/2, -height/2, 0);
        cbv.render();
        popMatrix();
        
        abv.render();
        
        
        cv.render(2, spinDirection);
    }

    public long timeElapsed()
    {
        if(startTime == -1)
            return 0; 

        return currentTime - startTime; 
    }
}