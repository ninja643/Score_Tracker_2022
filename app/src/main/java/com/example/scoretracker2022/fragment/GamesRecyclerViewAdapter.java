package com.example.scoretracker2022.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.scoretracker2022.databinding.FragmentGameBinding;
import com.example.scoretracker2022.model.Game;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Game}.
 * TODO: Replace the implementation with code for your data type.
 */
public class GamesRecyclerViewAdapter extends RecyclerView.Adapter<GamesRecyclerViewAdapter.ViewHolder> {

    private int selectedPosition = RecyclerView.NO_POSITION;
    private final List<Game> games;
    private final GameSelectedListener gameSelectedListener;

    public interface GameSelectedListener {
        void onGameSelected(Game game, int selectedPosition);
    }

    public GamesRecyclerViewAdapter(List<Game> games, GameSelectedListener gameSelectedListener, int initialSelectedPosition) {
        this.games = games;
        this.gameSelectedListener = gameSelectedListener;
        this.selectedPosition = initialSelectedPosition;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentGameBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.setSelection(position);
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Game game;
        private FragmentGameBinding binding;

        public ViewHolder(FragmentGameBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.itemView.setOnClickListener(this);
        }

        public void setSelection(int position) {
            this.game = games.get(position);
            this.binding.setGame(this.game);
            this.itemView.setSelected(position == selectedPosition);
        }

        @Override
        public void onClick(View view) {
            notifyItemChanged(selectedPosition);
            selectedPosition = getBindingAdapterPosition();
            notifyItemChanged(selectedPosition);
            gameSelectedListener.onGameSelected(game, selectedPosition);
        }
    }
}