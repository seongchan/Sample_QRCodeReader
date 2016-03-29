package com.clipandbooks.sample.qrcodereader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends Activity implements View.OnClickListener{

    private Button mReadBtn;
    private TextView mResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReadBtn = (Button)findViewById(R.id.capture);
        mResult = (TextView)findViewById(R.id.result);

        mReadBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mResult.setText("");
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case IntentIntegrator.REQUEST_CODE :
                if (resultCode == Activity.RESULT_OK) {

                    /* 아래코드는 제대로 동작하지 않아서 data를 바로 가져다가 쓸 것 */
                    /*
                    IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                    String tmp = result.getContents();
                    Log.d("TAG", "OK");
                    Log.d("TAG", "RESULT"+tmp);
                    Log.d("TAG", "----------");
                    */
                    String contents = data.getStringExtra(Intents.Scan.RESULT);
                    Log.d("TAG", "OK");
                    Log.d("TAG", "RESULT CONTENT : " + contents);
                    Log.d("TAG", "-----------");
                    mResult.setText(contents);
                } else {
                    Log.d("TAG", "NOT OK");
                }
                break;
            default :
                Log.d("TAG", "NOT RESULT CODE");

        }
    }
}
