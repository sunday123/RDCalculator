package com.lyx.rdcalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.math.BigInteger;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("联系我:695200158@qq.com");
        builder.show();
    }
}
