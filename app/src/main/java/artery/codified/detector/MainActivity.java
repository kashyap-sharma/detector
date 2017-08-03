package artery.codified.detector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText text;
    private Button get;
    private TextView textview;
    String textString;
    final String url = "https://www.googleapis.com/language/translate/v2?key=AIzaSyAIKDHIzU5rIuuz0w6zROlUDDr3tHE59W8&target=en&q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        text = (EditText) findViewById(R.id.text);
        get = (Button) findViewById(R.id.get);
        textview = (TextView) findViewById(R.id.textview);

        get.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
         textString = text.getText().toString().trim();
        if (TextUtils.isEmpty(textString)) {
            Toast.makeText(this, "please provide some text", Toast.LENGTH_SHORT).show();
            return;
        }

        getResponse();


    }

    private void getResponse() {

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url+textString+"&format=text", null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data=response.getJSONObject("data");
                            JSONArray translations=data.getJSONArray("translations");
                            for(int i=0;i<translations.length();i++){
                                JSONObject js=translations.getJSONObject(i);
                                textview.setText(js.getString("detectedSourceLanguage"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(getRequest);

    }
}
