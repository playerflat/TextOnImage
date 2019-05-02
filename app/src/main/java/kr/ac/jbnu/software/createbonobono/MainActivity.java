package kr.ac.jbnu.software.createbonobono;

import android.graphics.*;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(view->{

        });
    }
    void saveImage(){
        EditText et1 = findViewById(R.id.et1);
        if(et1.getText().toString().replace(" ","").equals("")){
            Toast.makeText(getApplicationContext(),"단어를 입력하세요",Toas)
        }
        Bitmap bmp = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                R.drawable.bonobono);
        File myDir = new File(Environment.getExternalStorageDirectory() +
                File.separator + "DCIM/Bonobono");
        myDir.mkdirs();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        String str_Date = df.format(new Date());
        String fname = str_Date+".jpg";
        File file = new File(myDir,fname);
        if(file.exists()) file.delete();
        try{
            FileOutputStream out = new FileOutputStream(file);

            Canvas canvas = new Canvas(bmp);
            Paint paint = new Paint;
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(12);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
            canvas.drawBitmap(bmp,0,0,paint);
            canvas.drawText();
        }
    }
}
