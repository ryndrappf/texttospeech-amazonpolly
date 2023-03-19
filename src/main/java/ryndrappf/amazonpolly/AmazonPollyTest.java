package ryndrappf.amazonpolly;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.model.OutputFormat;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.InputStream;

public class AmazonPollyTest {

    public static void main(String[] args) throws JavaLayerException {

        String text = "Hallo From Amazon Polly, And Hallo From Youtube Ryndra Putra Pratama Firdaus";
        AmazonPollyDemo amazonPollyDemo = new AmazonPollyDemo(Region.getRegion(Regions.US_EAST_1));

        InputStream voice = amazonPollyDemo.synthesize(text, OutputFormat.Mp3);

        //create an MP3 player
        AdvancedPlayer player = new AdvancedPlayer(voice,
                javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());

        player.setPlayBackListener(new PlaybackListener() {
            @Override
            public void playbackStarted(PlaybackEvent evt) {
                System.out.println("Playback started");
                System.out.println(text);
            }

            @Override
            public void playbackFinished(PlaybackEvent evt) {
                System.out.println("Playback finished");
            }
        });
        // play it!
        player.play();
    }
}
