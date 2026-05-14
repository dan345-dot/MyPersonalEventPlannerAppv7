import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mypersonaeventplannerappv7.Event;

@Database(entities = {Event.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EventDao eventDao();
}
