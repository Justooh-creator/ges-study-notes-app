package com.example.gesstudynotes.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.gesstudynotes.fragments.NotesFragment;
import com.example.gesstudynotes.fragments.PastPapersFragment;
import com.example.gesstudynotes.fragments.ProgressFragment;
import com.example.gesstudynotes.fragments.SettingsFragment;

public class HomePagerAdapter extends FragmentStateAdapter {

    public HomePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new NotesFragment();
            case 1:
                return new PastPapersFragment();
            case 2:
                return new ProgressFragment();
            case 3:
                return new SettingsFragment();
            default:
                return new NotesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4; // Notes, Past Papers, Progress, Settings
    }
}
