package kr.ac.jbnu.software.createbonobono;

import android.Manifest;
import android.content.Intent;
import android.graphics.*;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(view -> saveImage());
    }

    private void saveImage() {
        EditText et1 = findViewById(R.id.et1);
        if (et1.getText().toString().replace(" ", "").equals("")) {
            Toast.makeText(getApplicationContext(), "글자를 입력하세요", Toast.LENGTH_SHORT).show();
        } else {
            Bitmap src = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                    R.drawable.bonobono);
            Bitmap dest = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
            String strTime = df.format(new Date());

            Canvas cs = new Canvas(dest);
            Paint tPaint = new Paint();
            switch (et1.length()) {
                case 6:
                    tPaint.setTextSize(380);
                    break;
                case 5:
                    tPaint.setTextSize(410);
                    break;
                case 4:
                    tPaint.setTextSize(440);
                    break;
                default:
                    tPaint.setTextSize(480);
                    break;
            }
            tPaint.setColor(Color.BLACK);
            tPaint.setStyle(Paint.Style.FILL);
            cs.drawBitmap(src, 0f, 0f, null);
            cs.drawText(et1.getText().toString(), 500f, 1700f, tPaint);

            final String strSDPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            final File myDir = new File(strSDPath + "/DCIM/Bonobono");

            if (!myDir.exists()) {
                myDir.mkdirs();
            }
            try {
                dest.compress(Bitmap.CompressFormat.JPEG, 90,
                        new FileOutputStream(new File(myDir.getPath() + "/" + strTime + ".jpg")));
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.parse("file://" + myDir.getPath() + "/" + strTime + ".jpg")));
                Toast.makeText(getApplicationContext(), "저장 완료", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
