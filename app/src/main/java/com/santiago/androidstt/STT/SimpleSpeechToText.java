package com.santiago.androidstt.STT;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santiago on 27/03/16.
 */
public class SimpleSpeechToText {

    private @NonNull SimpleLanguage language = SimpleLanguage.AUTO;

    private @Nullable SimpleSpeechToTextListener listener = null;

    private SpeechRecognizer speechRecognizer = null;

    public SimpleSpeechToText(@NonNull Context context) {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        speechRecognizer.setRecognitionListener(speechRecognizerListener);
    }

    public void setListener(@Nullable SimpleSpeechToTextListener listener) {
        this.listener = listener;
    }

    public void stopRecording() {
        speechRecognizer.stopListening();
    }

    public void setLanguage(@NonNull SimpleLanguage language) {
        this.language = language;
    }

    public void startRecording() {
        Intent intent = new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra( RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            intent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, true);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language.getCode());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizer.startListening(intent);
    }

    private RecognitionListener speechRecognizerListener = new RecognitionListener() {

        private boolean isReady = false;

        @Override
        public void onReadyForSpeech(Bundle params) {
            isReady = true;
        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {
        }

        @Override
        public void onError(int error) {
            if(isReady && listener != null) //&& isready
                listener.onError(error);

            isReady = false;
        }

        @Override
        public void onResults(Bundle results) {
            isReady = false;

            ArrayList<String> resultList = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            if(resultList != null && listener != null)
                listener.onResults(resultList.get(0), resultList);
        }

        @Override
        public void onPartialResults(Bundle partialResults) {
            /**
             * Only retrieve always the first value, since its the most likely candidate
             *
             * Documentation:
             * Key used to retrieve an ArrayList from the Bundle passed to the onResults(Bundle) and onPartialResults(Bundle) methods. These strings are the possible recognition results, where the first element is the most likely candidate.
             */
            //
            ArrayList<String> list = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            if (list != null && listener != null)
                listener.onPartialResults(list.get(0));
        }

        @Override
        public void onEvent(int eventType, Bundle params) { }
    };

    public interface SimpleSpeechToTextListener {
        void onError(int error);
        void onResults(@Nullable String bestResult, @NonNull List<String> results);
        void onPartialResults(@NonNull String partialResult);
    }


}
