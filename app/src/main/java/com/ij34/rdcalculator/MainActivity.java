package com.ij34.rdcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.math.BigInteger;
import java.util.prefs.Preferences;


public class MainActivity extends AppCompatActivity {
    private EditText hexInput,decInput,octInput,binInput;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        init();
    }

    private void init() {

        hexInput = findViewById( R.id.hexInput );
        decInput = findViewById( R.id.decInput );
        octInput = findViewById( R.id.octInput );
        binInput = findViewById( R.id.binInput );

        //16
        hexInput.addTextChangedListener(hexTextWatcher );
        //10
        decInput.addTextChangedListener(decTextWatcher);
        //8
        octInput.addTextChangedListener(octTextWatcher);
        //2
        binInput.addTextChangedListener(binTextWatcher);

        checkFirstInstall();

    }


    private void checkFirstInstall() {
        SharedPreferences preferences = getSharedPreferences("com-ij34-rdcalculator", 0);
        System.out.println("版本"+BuildConfig.VERSION_NAME);
        String a = preferences.getString("agree-version","");
        if (a.equals(BuildConfig.VERSION_NAME)){
            return;
        }

        //添加普通按钮
        AlertDialog alertDialog2 = new AlertDialog.Builder(this)
                .setTitle(this.getString(R.string.read_before_use_title))
                .setMessage(this.getString(R.string.read_before_use_content))
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton(this.getString(R.string.agree_btn), new DialogInterface.OnClickListener() {//添加"Yes"按钮
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("agree-version",BuildConfig.VERSION_NAME);
                        editor.commit();
                    }
                })

                .setNegativeButton(this.getString(R.string.disagree_btn), (dialogInterface, i) -> {

                    android.os.Process.killProcess(android.os.Process.myPid());
                })
                .setNeutralButton(this.getString(R.string.privacy_policy), (dialogInterface, i) -> {//Intent.ACTION_VIEW
                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(this.getString(R.string.privacy_policy_url)));
                    startActivity(intent);

                })
                .create();
        alertDialog2.show();







    }


    //16进制监听事件
    TextWatcher hexTextWatcher=new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(s!=null){
                if (s.toString().trim().length() >18) {
                    hexInput.setText(s.toString().substring(0, 18));
                    hexInput.setSelection(18);
                }else { //do
                    //移除其他监听，防止同时相互赋值矛盾
                    decInput.removeTextChangedListener( decTextWatcher );
                    octInput.removeTextChangedListener( octTextWatcher );
                    binInput.removeTextChangedListener( binTextWatcher );

                    String ss=s.toString();
                    if (ss.length()>0){
                        decInput.setText( new BigInteger( ss, 16 ).toString( 10 ) );
                        octInput.setText( new BigInteger( ss, 16 ).toString( 8 ) );
                        binInput.setText( new BigInteger( ss, 16 ).toString( 2 ) );
                    }else{
                        decInput.setText( "");
                        octInput.setText("" );
                        binInput.setText( "");
                    }
                    //重新添加监听
                    decInput.addTextChangedListener( decTextWatcher );
                    octInput.addTextChangedListener( octTextWatcher );
                    binInput.addTextChangedListener( binTextWatcher );
                }


            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    //10进制监听事件
    TextWatcher decTextWatcher =  new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            System.out.println("十进制前："+s);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            System.out.println( "十进制中：" + s );
            if (s != null) {
                if (s.toString().trim().length() > 20) {
                    decInput.setText( s.toString().substring( 0, 20 ) );
                    decInput.setSelection( 20 );
                } else { //do
                    //移除其他监听，防止同时相互赋值矛盾
                    hexInput.removeTextChangedListener( hexTextWatcher );
                    octInput.removeTextChangedListener( octTextWatcher );
                    binInput.removeTextChangedListener( binTextWatcher );

                    String ss=s.toString();
                    if (ss.length()>0){
                        hexInput.setText( new BigInteger( ss, 10 ).toString( 16 ));
                        octInput.setText( new BigInteger( ss, 10 ).toString( 8 ) );
                        binInput.setText( new BigInteger( ss, 10 ).toString( 2 ) );
                    }else{
                        hexInput.setText( "");
                        octInput.setText("" );
                        binInput.setText( "");
                    }
                    //重新添加监听
                    hexInput.addTextChangedListener( hexTextWatcher );
                    octInput.addTextChangedListener( octTextWatcher );
                    binInput.addTextChangedListener( binTextWatcher );


                }
            }
        }
        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    //8进制监听事件
    TextWatcher octTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.toString().trim().length() >22) {
                octInput.setText(s.toString().substring(0, 22));
                octInput.setSelection(22);
            }else{ //do
                //移除其他监听，防止同时相互赋值矛盾
                decInput.removeTextChangedListener( decTextWatcher );
                hexInput.removeTextChangedListener( hexTextWatcher );
                binInput.removeTextChangedListener( binTextWatcher );

                String ss=s.toString();
                if (ss.length()>0){
                    decInput.setText( new BigInteger( ss, 8 ).toString( 10 ) );
                    hexInput.setText( new BigInteger( ss, 8 ).toString( 16 ) );
                    binInput.setText( new BigInteger( ss, 8 ).toString( 2 ) );
                }else{
                    decInput.setText( "");
                    hexInput.setText("" );
                    binInput.setText( "");
                }
                //重新添加监听
                decInput.addTextChangedListener( decTextWatcher );
                hexInput.addTextChangedListener( hexTextWatcher );
                binInput.addTextChangedListener( binTextWatcher );


            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    //2进制监听事件
    TextWatcher binTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.toString().trim().length() >64) {
                binInput.setText(s.toString().substring(0, 64));
                binInput.setSelection(64);
            }else{ //do
                //移除其他监听，防止同时相互赋值矛盾
                decInput.removeTextChangedListener( decTextWatcher );
                octInput.removeTextChangedListener( octTextWatcher );
                hexInput.removeTextChangedListener( hexTextWatcher );

                String ss=s.toString();
                if (ss.length()>0){
                    decInput.setText( new BigInteger( ss, 2 ).toString( 10 ) );
                    octInput.setText( new BigInteger( ss, 2 ).toString( 8 ) );
                    hexInput.setText( new BigInteger( ss, 2 ).toString( 16 ) );
                }else{
                    decInput.setText( "");
                    octInput.setText("" );
                    hexInput.setText( "");
                }
                //重新添加监听
                decInput.addTextChangedListener( decTextWatcher );
                octInput.addTextChangedListener( octTextWatcher );
                hexInput.addTextChangedListener( hexTextWatcher );

            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public void aboutAction(View view) {
        Intent intent=new Intent(this,AboutActivity.class);
        startActivity(intent);

    }
}
