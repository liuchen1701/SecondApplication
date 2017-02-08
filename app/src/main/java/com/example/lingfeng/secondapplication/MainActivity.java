package com.example.lingfeng.secondapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText operand1;
    private EditText operand2;
    private Button btnAddition;
    private Button btnSubtraction;
    private Button btnDivision;
    private Button btnMultiplication;
    private TextView txtResult;
    private Button btnClear;
    public DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operand1 = (EditText) findViewById(R.id.input1);
        operand2 = (EditText) findViewById(R.id.input2);
        btnAddition = (Button) findViewById(R.id.plus);
        btnSubtraction = (Button) findViewById(R.id.minus);
        btnDivision = (Button) findViewById(R.id.divide);
        btnMultiplication = (Button) findViewById(R.id.multiply);
        txtResult = (TextView) findViewById(R.id.result);
        btnClear = (Button) findViewById(R.id.clear);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        btnAddition.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if ((operand1.getText().length() > 0) && (operand2.getText().length() > 0 )){
                    double oper1 = Double.parseDouble(operand1.getText().toString());
                    double oper2 = Double.parseDouble(operand2.getText().toString());
                    double theResult = oper1 + oper2;
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    txtResult.setText(Double.toString(theResult));
                    Map<String,Object> map = new HashMap<String, Object>();
                    map.put("result", theResult);
                    map.put("timestamp",com.google.firebase.database.ServerValue.TIMESTAMP);
                    map.put("operation","plus");
                    mDatabase.push().setValue(map);
                }
                else{
                    Toast.makeText(MainActivity.this, "Please enter number in both operand fields", Toast.LENGTH_LONG).show();
                }
            }
        });
        Button btn = (Button)findViewById(R.id.open_activity_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        btnSubtraction.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if ((operand1.getText().length() > 0) && (operand2.getText().length() > 0 )){
                    double oper1 = Double.parseDouble(operand1.getText().toString());
                    double oper2 = Double.parseDouble(operand2.getText().toString());
                    double theResult = oper1 - oper2;
                    txtResult.setText(Double.toString(theResult));
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    Map<String,Object> map = new HashMap<String, Object>();
                    map.put("result", theResult);
                    map.put("timestamp",com.google.firebase.database.ServerValue.TIMESTAMP);
                    map.put("operation","minus");
                    List listA = new ArrayList();

                    listA.add("element 1");
                    listA.add("element 2");
                    listA.add("element 3");
                    map.put("rand", listA);
                    mDatabase.setValue(map);
                }
                else{
                    Toast.makeText(MainActivity.this, "Please enter number in both operand fields", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnMultiplication.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if ((operand1.getText().length() > 0) && (operand2.getText().length() > 0 )){
                    double oper1 = Double.parseDouble(operand1.getText().toString());
                    double oper2 = Double.parseDouble(operand2.getText().toString());
                    double theResult = oper1 * oper2;
                    txtResult.setText(Double.toString(theResult));
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    Map<String,Object> map = new HashMap<String, Object>();
                    map.put("result", theResult);
                    map.put("timestamp",com.google.firebase.database.ServerValue.TIMESTAMP);
                    map.put("operation","multiply");
                    mDatabase.push().setValue(map);
                }
                else{
                    Toast.makeText(MainActivity.this, "Please enter number in both operand fields", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnDivision.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if ((operand1.getText().length() > 0) && (operand2.getText().length() > 0 )){
                    double oper1 = Double.parseDouble(operand1.getText().toString());
                    double oper2 = Double.parseDouble(operand2.getText().toString());
                    double theResult = oper1 / oper2;
                    txtResult.setText(Double.toString(theResult));
                    Map<String,Object> map = new HashMap<String, Object>();
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    map.put("result", theResult);
                    map.put("timestamp",com.google.firebase.database.ServerValue.TIMESTAMP);
                    map.put("operation","divide");
                    mDatabase.push().setValue(map);
                }
                else{
                    Toast.makeText(MainActivity.this, "Please enter number in both operand fields", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                operand1.setText("");
                operand2.setText("");
                txtResult.setText("0.00");
                operand1.requestFocus();
            }
        });
    }


}
