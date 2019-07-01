package com.marman.basededatosapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_codigo, et_descripcion, et_precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_codigo=(EditText)findViewById(R.id.txt_codigo);
        et_descripcion=(EditText)findViewById(R.id.txt_descripcion);
        et_precio=(EditText)findViewById(R.id.txt_precio);

    }

    //Metodo para dar de alta los productos
    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase basededatos = admin.getWritableDatabase();//abre la bd en modo lectura y escritura

        String codigo= et_codigo.getText().toString();
        String descripcion= et_descripcion.getText().toString();
        String precio= et_precio.getText().toString();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            basededatos.insert("articulos", null, registro);
            basededatos.close();

            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            Toast.makeText(this, "Registro exitoso...", Toast.LENGTH_SHORT).show();


        }else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    //Metdo para Buscar un producto
    public void Buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase basededatos = admin.getWritableDatabase();
        String codigo = et_codigo.getText().toString();
        if(!codigo.isEmpty()){
            Cursor fila = basededatos.rawQuery
                    ("select descripcion, precio from articulos where codigo= "+ codigo,null);
            if(fila.moveToFirst()){
                et_descripcion.setText(fila.getString(0));
                et_precio.setText(fila.getString(1));
            }else {
                Toast.makeText(this, "No existe el artículo", Toast.LENGTH_SHORT).show();
            }
            basededatos.close();

        }else {
            Toast.makeText(this, "Debes introducir el código del artículo", Toast.LENGTH_SHORT).show();
        }
    }

    //Metdo para Eliminar un producto

    public void Eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase basededetos = admin.getWritableDatabase();
        String codigo= et_codigo.getText().toString();
        if(!codigo.isEmpty()){
            int cantidad = basededetos.delete("articulos", "codigo="+codigo, null);
            basededetos.close();
            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            if(cantidad >0){
                Toast.makeText(this, "Eliminado exitosamente..", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "El artículo no exixte", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "No existe el artículo", Toast.LENGTH_SHORT).show();
        }
    }

    //Metdo para Modificat un producto
    public void Modificar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase basededatos = admin.getWritableDatabase();

        String codigo= et_codigo.getText().toString();
        String descripcion= et_descripcion.getText().toString();
        String precio= et_precio.getText().toString();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            int cantidad = basededatos.update("articulos", registro, "codigo="+codigo, null);
            basededatos.close();

            if (cantidad>0){
                Toast.makeText(this, "El artículo se modificó correctamente...", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "El artículo no existe...", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "Debes lenar todos los campos...", Toast.LENGTH_SHORT).show();
        }

    }

}
