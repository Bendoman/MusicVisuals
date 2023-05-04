package c21430484;

public class AudioBandsVisual
{
    TeamVisual mv;

    public AudioBandsVisual(TeamVisual mv)
    {
        this.mv = mv; 
    }

    public void render()
    {
        float gap = mv.height / (float) mv.getBands().length;
                    
        mv.noStroke();
        for(int i = 0 ; i < mv.getBands().length ; i ++)
        {
            mv.fill(208, 152, 3, 40 + 100 - (i * 10));
    
            mv.rect(0, i * gap, +mv.getSmoothedBands()[i] * 0.4f, gap);
            mv.rect(mv.width, i * gap, -mv.getSmoothedBands()[i] * 0.4f, gap);
        }
    }
}