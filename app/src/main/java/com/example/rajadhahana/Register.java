package com.example.rajadhahana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText txtName,txtContactNo,txtNic,txtEmail,txtPassword,txtCopassword;
    Button singupbtn,a;
    DatabaseReference dbref;
    User us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtName=findViewById(R.id.editText);
        txtContactNo=findViewById(R.id.editText20);
        txtNic=findViewById(R.id.editText22);
        txtEmail=findViewById(R.id.editText23);
        txtPassword=findViewById(R.id.editText29);
        txtCopassword=findViewById(R.id.editText30);

        singupbtn=findViewById(R.id.singup);
        a=findViewById(R.id.textView4);
        us=new User();

        singupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //                Intent intent=new Intent(MainActivity.this,login.class);
                //                startActivity(intent);
                dbref = FirebaseDatabase.getInstance().getReference().child("User");
                String email=txtEmail.getText().toString().trim();
                String pass=txtPassword.getText().toString().trim();
                try {
                    if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "enter name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtContactNo.getText().toString()))
                        Toast.makeText(getApplicationContext(), "enter contact number", Toast.LENGTH_LONG).show();
                    else if (TextUtils.isEmpty(txtNic.getText().toString()))
                        Toast.makeText(getApplicationContext(), "enter nic", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtEmail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "enter email", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtPassword.getText().toString()))
                        Toast.makeText(getApplicationContext(), "enter password", Toast.LENGTH_SHORT).show();
                    else if (!(txtPassword.getText().toString()).equals(txtCopassword.getText().toString()))
                        Toast.makeText(getApplicationContext(), "enter valid password", Toast.LENGTH_SHORT).show();
                    else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

                        txtEmail.setError("Invalid Email");
                        txtEmail.setFocusable(true);
                    }else if(pass.length()<6){
                        txtPassword.setError("Password length 6 charaacters");
                        txtPassword.setFocusable(true);
                    }

                    else {
                        us.setName(txtName.getText().toString().trim());
                        Invitation.setMyName(txtName.getText().toString().trim());
                        us.setContactNo(Integer.parseInt(txtContactNo.getText().toString().trim()));
                        Invitation.setMynumber(txtContactNo.getText().toString().trim());
                        us.setNic(txtNic.getText().toString().trim());
                        us.setEmail(txtEmail.getText().toString().trim());
                        us.setPassword(txtPassword.getText().toString().trim());
                        us.setCopassword(txtCopassword.getText().toString().trim());

                        // dbref.push().setValue(us);
                        dbref.child(us.getName()).setValue(us);
                        Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                        clear();
                        Intent intent1=new Intent(Register.this,Dashboard.class);
                        intent1.putExtra("name",us.getName());
                        startActivity(intent1);
                    }
                }
                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Invalid",Toast.LENGTH_SHORT).show();
                }
            }
        });
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iu=new Intent(Register.this,login.class);
                startActivity(iu);
            }
        });
        //mydb =new  DatabaseHelper(this);

        // singupbtn = findViewById(R.id.singup);

        // singupbtn.setOnClickListener(new View.OnClickListener() {
        // @Override
        //public void onClick(View view) {
        //  Intent intent = new Intent(MainActivity.this,profile.class);
        // startActivity(intent);

        //                editName=(EditText)findViewById(R.id.editText);
        //                editC0_Number=(EditText)findViewById(R.id.editText20);
        //                editNIC=(EditText)findViewById(R.id.editText22);
        //                editEmail=(EditText)findViewById(R.id.editText23);
        //                editPassword=(EditText)findViewById(R.id.editText29);
        //                editCo_Password=(EditText)findViewById(R.id.editText30);
        //                singupbtn=(Button)findViewById(R.id.singup);
        //                click=(Button)findViewById(R.id.button2);
        //                //AddData();
        //                viewAll();
    }

    //  public void AddData(){
    // singupbtn.setOnClickListener(
    //   new View.OnClickListener() {
    // @Override
    //  public void onClick(View view) {
    //  boolean Inserted= mydb.insertData(editName.getText().toString(),editC0_Number.getText().toString(),editNIC.getText().toString(),editEmail.getText().toString(),editPassword.getText().toString(),editCo_Password.getText().toString());
    // if(Inserted=true)
    //  Toast.makeText(MainActivity.this,"DATA INSERTED",Toast.LENGTH_LONG).show();
    //else
    //   Toast.makeText(MainActivity.this,"DATA NOT INSERTED",Toast.LENGTH_LONG).show();
    // }

    // });
    // }
    //});
    public  void clear(){
        txtName.setText("");
        txtContactNo.setText("");
        txtNic.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        txtCopassword.setText("");


    }




}
//    public void  viewAll(){
//        click.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Cursor res=mydb.getAllData();
//                        if(res.getCount()==0){
//
//                            showMassage("error","Nothing found");
//                            return;
//                        }
//                        StringBuffer buffer=new StringBuffer();
//                        while (res.moveToNext()){
//                            buffer.append("Name:"+res.getString(0)+"\n");
//                            buffer.append("Contact number:"+res.getString(1)+"\n");
//                            buffer.append("Nic:"+res.getString(2)+"\n");
//                            buffer.append("Email:"+res.getString(3)+"\n");
//                            buffer.append("password:"+res.getString(4)+"\n");
//                            buffer.append("co password:"+res.getString(5)+"\n\n");
//
//                        }
//
//                        showMassage("Data",buffer.toString());
//                    }
//                }
//        );
//    }
// public void showMassage(String title,String msg) {
//  AlertDialog.Builder builder = new AlertDialog.Builder(this);
//builder.setCancelable(true);
// builder.setTitle(title);
// builder.setMessage(msg);
// builder.show();

// }
//}
