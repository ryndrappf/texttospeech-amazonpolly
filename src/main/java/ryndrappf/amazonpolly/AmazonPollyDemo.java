package ryndrappf.amazonpolly;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.model.*;

import java.io.InputStream;
import java.io.OutputStream;

public class AmazonPollyDemo {

    private final AmazonPollyClient amazonPollyClient;

    private final Voice voice;

    public AmazonPollyDemo(Region region) {
        BasicAWSCredentials credentials = new BasicAWSCredentials("YOUR_ACCESS_KEY", "YOUR_SECRET_KEY");

        amazonPollyClient = new AmazonPollyClient(credentials, new ClientConfiguration());

        amazonPollyClient.setRegion(region);

        DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();

        DescribeVoicesResult describeVoicesResult = amazonPollyClient.describeVoices(describeVoicesRequest);

        voice = describeVoicesResult.getVoices().get(0);
    }

    public InputStream synthesize (String text, OutputFormat outputFormat){
        SynthesizeSpeechRequest speechRequest = new SynthesizeSpeechRequest().withText(text).withOutputFormat(outputFormat).withVoiceId(VoiceId.Astrid);

        SynthesizeSpeechResult speechResult = amazonPollyClient.synthesizeSpeech(speechRequest);

        return speechResult.getAudioStream();
    }
}
