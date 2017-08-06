package artery.codified.detector;

/**
 * Created by kashy on 08/06/17.
 */

public class TranslateData {
    private String data;
    private String translatedText;
    private String detections;


    public String getData() {
        return data;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public String getDetections() {
        return detections;
    }


    public void setData(String data) {
        this.data = data;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public void setDetections(String detections) {
        this.detections = detections;
    }

}
