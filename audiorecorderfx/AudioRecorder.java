package audiorecorderfx;

/** @author edward weiss
 * 
 * The purpose of this package should be to record whatever audio is coming
 * out of the computer's sound card in a variety of file formats.
 */

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFormat;
import java.util.*;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
//import javax.sound.sampled.Mixer.Info;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class AudioRecorder {

    /**
     * @param args the command line arguments
     */
    
    private static TargetDataLine target;
    private static Mixer mixer;
    private static AudioFormat audioFormat;
    
    public AudioRecorder() {
        
        for (Mixer.Info a : AudioSystem.getMixerInfo()) {                           // Iterate through list of mixers on system
            System.out.println(a);
        }
          
        mixer = AudioSystem.getMixer( AudioSystem.getMixerInfo()[4] );            // Get Primary Sound Driver, first entry in Mixer list
        audioFormat = getAudioFormat();  

        DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);// create the info for a SourceDataLine

        try{ target = (TargetDataLine)mixer.getLine(info); 
             target.open();
             target.start();
             System.out.println("TargetDataLine obtained,opened,and started");
        }                     
        catch(LineUnavailableException e){ 
            e.printStackTrace(); 
        }              // catch if line unavailable
            
      
    }
    
    public AudioFormat getAudioFormat(){    
        return new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,44100.0F,16,1,2,44100.0F,false);
    }
    
    public void record(){
        // This needs to be a new thread, otherwise one thread alone is managing the whole program and it has no way of stopping once the input stream has begun
        AudioInputStream inputStream = new AudioInputStream(target);
        try{
            AudioSystem.write(inputStream, AudioFileFormat.Type.WAVE, new File("Test.wav"));
        }catch(Exception e){
            e.printStackTrace();
        }     
    }
    
    public void stop(){target.stop(); target.close();}
}
