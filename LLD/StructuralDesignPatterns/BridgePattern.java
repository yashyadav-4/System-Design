package LLD.StructuralDesignPatterns;

//implementor interface
interface VideoQuality{
    void load(String title);
}

class SDQuality implements VideoQuality{
    @Override
    public void load(String title){
        System.out.println("Streaming " + title + " in SD Quality");
    }
}

class HDQuality implements VideoQuality{
    @Override
    public void load(String title){
        System.out.println("Streaming " + title + " in HD Quality");
    }
}

class UltraHDQuality implements VideoQuality {
    public void load(String title){
        System.out.println("Streaming " + title + " in 4K Ultra HD Quality");
    }
}

// Abstraction
abstract class VideoPlayer{
    protected VideoQuality quality;

    public VideoPlayer(VideoQuality quality){
        this.quality = quality;
    }

    public abstract void play(String title);
}

class WebPlayer extends VideoPlayer{
    public WebPlayer(VideoQuality quality){
        super(quality);
    }

    public void play(String title){
        System.out.println("Web Platform:");
        quality.load(title);
    }
}

class MobilePlayer extends VideoPlayer{
    public MobilePlayer(VideoQuality quality){
        super(quality);
    }

    public void play(String title){
        System.out.println("Mobile Platform:");
        quality.load(title);
    }
}

public class BridgePattern {
    public static void main(String[] args) {
        // Playing on Web with HD Quality
        VideoPlayer player1 = new WebPlayer(new HDQuality());
        player1.play("Interstellar");

        // Playing on Mobile with Ultra HD Quality
        VideoPlayer player2 = new MobilePlayer(new UltraHDQuality());
        player2.play("Inception");
    }
}
