package za.co.masekofortune.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    private ArrayAdapter<NoteInfo> mAdapterNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivity(new Intent(this, NoteActivity.class)));

        initializeDisplayContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapterNotes.notifyDataSetChanged();
    }

    private void initializeDisplayContent() {
        final ListView listNotes = findViewById(R.id.list_notes);

        List<NoteInfo> notes = DataManager.getInstance().getNotes();

        mAdapterNotes = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                notes);

        listNotes.setAdapter(mAdapterNotes);

        listNotes.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, NoteActivity.class);
//            NoteInfo note = (NoteInfo) listNotes.getItemAtPosition(position);
            intent.putExtra(NoteActivity.NOTE_POSITION, position);
            startActivity(intent);
        });
    }


}