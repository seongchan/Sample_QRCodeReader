package com.clipandbooks.sample.qrcodereader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends Activity implements View.OnClickListener{

    private Button mReadBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReadBtn = (Button)findViewById(R.id.capture);

        mReadBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
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
                    IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                    Log.d("TAG", "OK");
                    Log.d("TAG", "RESULT"+result.getContents());
                    Log.d("TAG", "----------");
                } else {
                    Log.d("TAG", "NOT OK");
                }
                break;
            default :
                Log.d("TAG", "NOT RESULT CODE");

        }
    }
}
