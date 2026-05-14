import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mypersonaeventplannerappv7.Event;

import java.util.List;

@Dao
public interface EventDao {

    @Insert
    void insert (Event event);

    @Query("SELECT * FROM event ORDER BY id DESC")
    List<Event> getAllEvents();


    @Query("DELETE FROM event")
    void deleteAll();
}
