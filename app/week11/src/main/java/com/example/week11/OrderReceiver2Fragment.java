package com.example.week11;

import android.content.BroadcastReceiver;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class OrderReceiver2Fragment extends Fragment {
    private TextView textView;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = getResultExtras(true);
            String msg = extras.getString("modified");
            textView.setText("[순서] 수신2 : " + msg);

            extras.putString("modified", msg + "\n → [수신2 처리됨]");
            setResultExtras(extras);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.receiver_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        textView = view.findViewById(R.id.textView);
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(OrderBRConst.ORDERED);
        filter.setPriority(50);
        ContextCompat.registerReceiver(requireContext(), receiver, filter, ContextCompat.RECEIVER_NOT_EXPORTED);
    }

    @Override
    public void onStop() {
        super.onStop();
        requireActivity().unregisterReceiver(receiver);
    }
}