package com.example.week09;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ProgressDialogFragment extends DialogFragment {
    private TextView textView;
    private ProgressBar progressBar;
    private Button button1, button2;
    private Handler handler = new Handler(Looper.getMainLooper());
    private boolean running = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.progrss_dialog, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //    super.onViewCreated(view, savedInstanceState);

        setCancelable(false);
        textView = view.findViewById(R.id.textView);
        progressBar = view.findViewById(R.id.progressBar);
        button1 = view.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "취소 클릭",
                        Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        button2 = view.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "취소 클릭",
                        Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        startThread();
    }

    private void startThread() {
        new Thread(() -> {
            for (int i = 0; i <= 100 && running; i++) {
                int progress = i;
                handler.post(() -> {
                    progressBar.setProgress(progress);
                    textView.setText("진행률: " + progress + "%");
                    if (progress >= 100 && isAdded()) {
                        Toast.makeText(getActivity(), "완료 되었습니다",
                                Toast.LENGTH_SHORT).show();

                        dismiss();

                    }
                });
                try {
                    Thread.sleep(100); // 100까지 약 5초 소요
                } catch (InterruptedException e) {
                    break;
                }
            }
        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        running = false;
    }
}
