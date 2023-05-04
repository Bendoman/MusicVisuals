package c21430484;

public class WaveForm
{
    BensVisual mv;
    float cy = 0;

    public WaveForm(BensVisual mv)
    {
        this.mv = mv;
        cy = (this.mv.height / 2) + 530;
    }

    public void render()
    {
        for(int i = 0 ; i < mv.getAudioBuffer().size() * 2 ; i ++)
        {
            mv.stroke(208, 152, 3);
            mv.line(i, cy + 10, i, cy  + mv.getAudioBuffer().get(i/2) * 100);
        }
    }
}

