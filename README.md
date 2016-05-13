# android-api-speech-to-text
Simple speech to text


Usage:

Create an instance of the SimpleSpeechToText
```Java
  simpleSTT = new SimpleSpeechToText(aContext);
  simpleSTT.setListener(this);
  simpleSTT.setLanguage(SimpleLang.ENGLISH);
  //I only added ENGLISH SPANISH AND EVERYTHING because im lazy, but you can add everyone if you want :)
```

Start it or stop it
```Java
  if(recording)
      simpleSTT.stopRecording();
  else simpleSTT.startRecording();
```

Implement from the listener interface and do stuff
```Java
  @Override
  public void onError(int error) {
    //Do stuff...
  }

  @Override
  public void onResults(@Nullable String bestResult, List<String> results) {
    //Do stuff...
  }

  @Override
  public void onPartialResults(String partialResult) {
    //Do stuff...
  }
```
