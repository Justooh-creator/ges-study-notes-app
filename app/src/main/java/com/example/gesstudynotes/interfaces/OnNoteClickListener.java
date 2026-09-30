package com.example.gesstudynotes.interfaces;

import com.example.gesstudynotes.models.Note;

public interface OnNoteClickListener {
    void onNoteClick(Note note);
    void onNoteDownload(Note note);
    void onNoteShare(Note note);
}
