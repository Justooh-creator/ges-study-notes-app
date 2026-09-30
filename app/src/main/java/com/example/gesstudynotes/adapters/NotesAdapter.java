package com.example.gesstudynotes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageButton;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gesstudynotes.R;
import com.example.gesstudynotes.models.Note;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<Note> notesList;
    private OnNoteClickListener listener;

    public interface OnNoteClickListener {
        void onNoteClick(Note note);
    }

    public NotesAdapter(List<Note> notesList, OnNoteClickListener listener) {
        this.notesList = notesList;
        this.listener = listener;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note note = notesList.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView titleText, subjectText, topicText, authorText;
        private ImageButton downloadBtn;

        public NoteViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.noteTitle);
            subjectText = itemView.findViewById(R.id.noteSubject);
            topicText = itemView.findViewById(R.id.noteTopic);
            authorText = itemView.findViewById(R.id.noteAuthor);
            downloadBtn = itemView.findViewById(R.id.downloadBtn);
        }

        public void bind(Note note) {
            titleText.setText(note.getTitle());
            subjectText.setText(note.getSubject());
            topicText.setText(note.getTopic());
            authorText.setText("By: " + note.getAuthor());

            if (note.isDownloaded()) {
                downloadBtn.setImageResource(R.drawable.ic_downloaded);
                downloadBtn.setEnabled(false);
            } else {
                downloadBtn.setImageResource(R.drawable.ic_download);
                downloadBtn.setEnabled(true);
            }

            itemView.setOnClickListener(v -> listener.onNoteClick(note));
            downloadBtn.setOnClickListener(v -> listener.onNoteClick(note));
        }
    }
}
