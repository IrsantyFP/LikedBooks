package ifp.mobile.projek;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {ListBuku.class}, version = 1)

public abstract class BukuDatabase extends RoomDatabase {

    private static volatile BukuDatabase INSTANCE;

    public static BukuDatabase getDatabase(final Context ctx) {
        if (INSTANCE == null) {
            synchronized (BukuDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(ctx.getApplicationContext(),
                                    BukuDatabase.class, "buku_database")
                            .addCallback(sRoomDatabaseCallback) // Optional, untuk callback
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    // Kode yang dijalankan saat database pertama kali dibuat
                }
            };

    public abstract BukuDao bukuDao();
}