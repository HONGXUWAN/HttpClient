package cn.edu.jssvc.whx.httpclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class MainActivity extends Activity {

    private static final int SHOW_RESPONSE = 0;
    private Button sendrequest;
    private TextView requesttext;
    private String response;
//    private Handler mhandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what){
//                case SHOW_RESPONSE:
//                    requesttext.setText((String)msg.obj);
//            }
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendrequest = (Button) findViewById(R.id.send_request);
        requesttext = (TextView) findViewById(R.id.request_text);
        //fjslfjejoirfrjewfjewojf
        sendrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendHttpClient();
            }
        });
    }

    private void sendHttpClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpclient = new DefaultHttpClient();
//                    HttpGet httpget = new HttpGet("https://www.baidu.com");
                    HttpPost httppost = new HttpPost("https://www.baidu.com");
//                    List<NameValuePair> params = new ArrayList<NameValuePair>();
//                    params.add(new BasicNameValuePair("username","admin"));
//                    params.add(new BasicNameValuePair("password","123456"));
//                    UrlEncodedFormEntity entitypost = new UrlEncodedFormEntity(params,"utf-8");
//                    httppost.setEntity(entitypost);
                    HttpResponse httpresponse =httpclient.execute(httppost);
                    if (httpresponse.getStatusLine().getStatusCode()==200){
                        HttpEntity entity = httpresponse.getEntity();
                         response = EntityUtils.toString(entity,"utf-8");
//                        Message message = new Message();
//                        message.what = SHOW_RESPONSE;
//                        message.obj = response.toString();
//                        mhandler.sendMessage(message);
                        showresponsetext();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        }).start();
    }

    private void showresponsetext() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                requesttext.setText(response);
            }
        });
    }
}
