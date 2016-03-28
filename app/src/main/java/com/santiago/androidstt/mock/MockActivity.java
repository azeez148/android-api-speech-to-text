package com.santiago.androidstt.mock;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.santiago.androidstt.R;
import com.santiago.androidstt.STT.SimpleSpeechToText;

import java.util.List;

/**
 * Created by santiago on 27/03/16.
 */
public class MockActivity extends Activity implements SimpleSpeechToText.SimpleSpeechToTextListener {

    private TextView button;
    private TextView text;

    private boolean recording = false;

    private SimpleSpeechToText simpleSTT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mock);

        button = (TextView) findViewById(R.id.activity_mock_record_button);
        text = (TextView) findViewById(R.id.activity_mock_text);

        simpleSTT = new SimpleSpeechToText(this);
        simpleSTT.setListener(this);
        //simpleSTT.setLanguage(SimpleLang.ENGLISH);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecordClick();
            }
        });
    }

    private void onRecordClick() {
        if(recording)
            simpleSTT.stopRecording();
        else simpleSTT.startRecording();

        recording = !recording;

        refreshUI();
    }

    private void refreshUI() {
        if (recording)
            button.setText(getResources().getString(R.string.stop_record));
        else button.setText(getResources().getString(R.string.record));
    }

    @Override
    public void onError(int error) {
        recording = false;
        refreshUI();
    }

    @Override
    public void onResults(@Nullable String bestResult, List<String> results) {
        text.setText(bestResult);

        recording = false;
        refreshUI();
    }

    @Override
    public void onPartialResults(String partialResult) {
        text.setText(partialResult);
    }
}
