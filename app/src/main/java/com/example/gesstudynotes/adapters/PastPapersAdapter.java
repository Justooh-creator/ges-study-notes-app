package com.example.gesstudynotes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageButton;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gesstudynotes.R;
import com.example.gesstudynotes.models.PastPaper;
import java.util.List;

public class PastPapersAdapter extends RecyclerView.Adapter<PastPapersAdapter.PaperViewHolder> {

    private List<PastPaper> papersList;
    private OnPaperClickListener listener;

    public interface OnPaperClickListener {
        void onPaperClick(PastPaper paper);
    }

    public PastPapersAdapter(List<PastPaper> papersList, OnPaperClickListener listener) {
        this.papersList = papersList;
        this.listener = listener;
    }

    @Override
    public PaperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_past_paper, parent, false);
        return new PaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PaperViewHolder holder, int position) {
        PastPaper paper = papersList.get(position);
        holder.bind(paper);
    }

    @Override
    public int getItemCount() {
        return papersList.size();
    }

    public class PaperViewHolder extends RecyclerView.ViewHolder {
        private TextView subjectText, yearText, examTypeText;
        private ImageButton downloadBtn;

        public PaperViewHolder(View itemView) {
            super(itemView);
            subjectText = itemView.findViewById(R.id.paperSubject);
            yearText = itemView.findViewById(R.id.paperYear);
            examTypeText = itemView.findViewById(R.id.paperExamType);
            downloadBtn = itemView.findViewById(R.id.downloadBtn);
        }

        public void bind(PastPaper paper) {
            subjectText.setText(paper.getSubject());
            yearText.setText("Year: " + paper.getYear());
            examTypeText.setText(paper.getExamType());

            if (paper.isDownloaded()) {
                downloadBtn.setImageResource(R.drawable.ic_downloaded);
                downloadBtn.setEnabled(false);
            } else {
                downloadBtn.setImageResource(R.drawable.ic_download);
                downloadBtn.setEnabled(true);
            }

            itemView.setOnClickListener(v -> listener.onPaperClick(paper));
            downloadBtn.setOnClickListener(v -> listener.onPaperClick(paper));
        }
    }
}
