package com.github.hezavehir;

import java.io.File;

/*
 * author: @lichard49
 * repository: lichard49/MFCCTest.java
 * url: https://gist.github.com/lichard49/69ab2a2ddd3c11394974c404ca102a55
 *
 * I'm sorry; I don't know if there is a better way to show
 * the owner of the code when I am writing this repository.
*/

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;

public class MFCCTest {
    public static void main(String[] args) throws Exception {
        final AudioDispatcher dispatcher = AudioDispatcherFactory.fromFile(
                new File("./aud/Persian/numbers/1(yek).wav"),
                (int)(0.2*48000),
                0);

        dispatcher.addAudioProcessor(new AudioProcessor() {

            @Override
            public void processingFinished() {
            }

            @Override
            public boolean process(AudioEvent audioEvent) {
                System.out.println("### Time = " + audioEvent.getTimeStamp());
                MyMFCC mfcc = new MyMFCC();
                float[] mfccs = mfcc.getMFCC(audioEvent.getFloatBuffer(),
                        audioEvent.getSampleRate(),
                        13);
                printArray("mfccs", mfccs);

                return true;
            }
        });
        dispatcher.run();
    }

    public static void printArray(String name, float[] array) {
        System.out.println(name + " [" + array.length + "]");
        for (float f : array) {
            System.out.print(f + " ");
        }
        System.out.println('\n');
    }
}
