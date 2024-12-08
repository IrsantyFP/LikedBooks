package ifp.mobile.projek;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import java.util.List;

public class DetailBuku extends AppCompatActivity {

    private BukuDatabase db;
    private BukuDao bukuDao;
    private Handler handler;
    private List<ListBuku> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buku);

        Button likeButton = findViewById(R.id.likeButton);
        Button borrowButton = findViewById(R.id.borrowButton);

        TextView tvJudul = findViewById(R.id.tvJudul);
        TextView tvPenulis = findViewById(R.id.tvPenulis);
        TextView tvTahun = findViewById(R.id.tvTahun);

//        this.handler = new Handler(Looper.getMainLooper()){
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                super.handleMessage(msg);
//            }
//        };
//
        this.db = Room.databaseBuilder(getApplicationContext(), BukuDatabase.class, "buku.db").build();
        this.bukuDao = this.db.bukuDao();
        // Listener untuk likeButton
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(() -> {
                    String judul = tvJudul.getText().toString();
                    String penulis = tvPenulis.getText().toString();
                    String tahun = tvTahun.getText().toString();

                    ListBuku buku = new ListBuku();
                    buku.id = (int) (Math.random() * 1000000);
                    buku.judul = judul;
                    buku.penulis = penulis;
                    buku.tahun = tahun;

                    bukuDao.insertAll(buku);
//                    Intent resultIntent = new Intent();
//                    resultIntent.putExtra("id", buku.id);
//                    resultIntent.putExtra("judul", buku.judul);
//                    resultIntent.putExtra("penulis", buku.penulis);
//                    resultIntent.putExtra("tahun", buku.tahun);
//                    setResult(RESULT_OK, resultIntent);

                    runOnUiThread(() -> {
                        Toast.makeText(DetailBuku.this, "Buku berhasil disimpan!", Toast.LENGTH_SHORT).show();
                    });
                    finish();

                });
                t.start();
            }
        });


        // Listener untuk borrowButton
        borrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat AlertDialog konfirmasi Yes/No
                new AlertDialog.Builder(DetailBuku.this)
                        .setTitle("Confirmation")
                        .setMessage("Apakah anda yakin ingin meminjam buku ini?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Aksi ketika tombol "Yes" ditekan
                                dialog.dismiss();
                                // Menampilkan toast ketika "Yes" ditekan
                                Toast.makeText(DetailBuku.this, "Buku berhasil ditambahkan!!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Menutup dialog ketika "No" ditekan
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert) // Optional: Menambahkan icon alert
                        .show(); // Menampilkan AlertDialog
            }
        });
    }
}
