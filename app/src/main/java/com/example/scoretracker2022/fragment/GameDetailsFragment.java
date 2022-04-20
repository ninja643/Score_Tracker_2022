package com.example.scoretracker2022.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scoretracker2022.R;
import com.example.scoretracker2022.databinding.GameDetailsFragmentBinding;
import com.example.scoretracker2022.model.GamesViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class GameDetailsFragment extends Fragment {

    public static GameDetailsFragment newInstance() {
        return new GameDetailsFragment();
    }

    private GamesViewModel gamesViewModel;
    private GameDetailsFragmentBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        gamesViewModel = new ViewModelProvider(requireActivity()).get(GamesViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = GameDetailsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gamesViewModel.getSelectedGame().observe(requireActivity(), game -> binding.setGame(game));
    }
}