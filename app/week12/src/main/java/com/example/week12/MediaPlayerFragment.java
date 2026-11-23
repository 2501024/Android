package com.example.week12;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

public class MediaPlayerFragment extends Fragment {
    private SwitchCompat uiSwitch;
    private TextView textView;
    private View customLayout;
    private View controlView;
    private SeekBar seekBar;
    private Button button;
    private TextView start, duration, end;
    private MediaController controller;
    private MediaPlayerManager manager;
    private boolean prepared = false;
    private final String[] NAME = {"코난", "Lemon Tree", "풍문으로 들었소", "헤어졌다 만났다"};
    private int type = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mediaplayer, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        uiSwitch = view.findViewById(R.id.toggle);
        textView = view.findViewById(R.id.textView);
        customLayout = view.findViewById(R.id.customLayout);
        controlView = view.findViewById(R.id.controller);
        seekBar = view.findViewById(R.id.seekBar);
        start = view.findViewById(R.id.start);
        duration = view.findViewById(R.id.duration);
        end = view.findViewById(R.id.end);
        button = view.findViewById(R.id.button);

        setupController();
        setupSwitch();
        setupSeekBar();
        addMenu();
        loadSource(type);

    }

    private void loadSource(int type) {
        //  manager.load();
    }

    private void addMenu() {
        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@org.jspecify.annotations.NonNull Menu menu, @org.jspecify.annotations.NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.menu, menu);
            }

            @Override
            public boolean onMenuItemSelected(@org.jspecify.annotations.NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.item1) {
                    type = 1;
                } else if (id == R.id.item2) {
                    type = 2;
                } else if (id == R.id.item3) {
                    type = 3;
                } else if(id == R.id.item4) {
                    type = 4;
                }
                menuItem.setChecked(true);
                loadSource(type);
                return true;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }

    private void setupSeekBar() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && prepared) {
                    manager.seekTo(progress);
                    duration.setText(TimeUtil.convert(progress));
                    textView.setText(NAME[type - 1]);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setupSwitch() {
        uiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    customLayout.setVisibility(GONE);
                    controlView.setVisibility(VISIBLE);
                    controller.show(0);
                } else {
                    customLayout.setVisibility(VISIBLE);
                    controlView.setVisibility(GONE);
                    controller.hide();
                }
            }
        });
    }

    private void setupController() {
        MediaController.MediaPlayerControl control = new MediaController.MediaPlayerControl() {
            @Override
            public boolean canPause() {
                return false;
            }

            @Override
            public boolean canSeekBackward() {
                return false;
            }

            @Override
            public boolean canSeekForward() {
                return false;
            }

            @Override
            public int getAudioSessionId() {
                return 0;
            }

            @Override
            public int getBufferPercentage() {
                return 0;
            }

            @Override
            public int getCurrentPosition() {
                return 0;
            }

            @Override
            public int getDuration() {
                return 0;
            }

            @Override
            public boolean isPlaying() {
                return false;
            }

            @Override
            public void pause() {

            }

            @Override
            public void seekTo(int pos) {

            }

            @Override
            public void start() {

            }
        };
        controller = new MediaController(requireContext());
        controller.setAnchorView(controlView);
        controller.setMediaPlayer(control);
        controlView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.show(0);
            }
        });
    }
}