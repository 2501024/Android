package com.example.week11;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class SendFragment extends Fragment implements View.OnClickListener {
    private EditText editText;
    private TextView textView;
    private boolean receiver1 = false, receiver2 = false;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customsend_fragment, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.editText);
        textView = view.findViewById(R.id.textView);

        Button button1 = view.findViewById(R.id.button1);
        button1.setOnClickListener(this);
        Button button2 = view.findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3 = view.findViewById(R.id.button3);
        button3.setOnClickListener(this);
        Button button4 = view.findViewById(R.id.button4);
        button4.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button1)
            sendCustom(CustomConst.CUSTOM1);
        else if (id == R.id.button2)
            sendCustom(CustomConst.CUSTOM2);
        else if (id == R.id.button3)
            sendCustom(CustomConst.CUSTOM1, CustomConst.CUSTOM2);
        else
            localSend();
    }

    private void sendCustom(String... customs) {
        String message = editText.getText().toString();
        if (message.isEmpty()) {
            Toast.makeText(getContext(), "입력해주세요", LENGTH_SHORT).show();
        } else {
            editText.setText("");
            receiver1 = false;
            receiver2 = false;
            for (String custom : customs) {
                Intent intent = new Intent(custom);
                intent.putExtra("message", message);
                intent.setPackage(requireContext().getPackageName());
                requireContext().sendBroadcast(intent);
            }
        }
    }

    private void localSend() {

    }


    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(CustomConst.CUSTOM1);
        filter.addAction(CustomConst.CUSTOM2);
        ContextCompat.registerReceiver(requireContext(), receiver, filter, ContextCompat.RECEIVER_NOT_EXPORTED);
    }

    @Override
    public void onStop() {
        super.onStop();
        requireContext().unregisterReceiver(receiver);
    }
}