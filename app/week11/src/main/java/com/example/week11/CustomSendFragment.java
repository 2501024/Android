package com.example.week11;

public class CustomSendFragment extends Fragment implements View.OnClickListener {
    private EditText editText;
    private TextView textView;
    private boolean received1 = false;
    private boolean received2 = false;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String msg = intent.getStringExtra("message");
            if (action != null) {
                switch (action) {
                    case CustomConst.CUSTOM1:
                        received1 = true;
                        textView.setText(received2 ? "수신 1과 2 모두에게 송신 : " + msg : "수신 1에게 송신 : " + msg);
                        break;
                    case CustomConst.CUSTOM2:
                        received2 = true;
                        textView.setText(received1 ? "수신 1과 2 모두에게 송신 : " + msg : "수신 2에게 송신 : " + msg);
                        break;
                    case CustomConst.LOCAL:
                        textView.setText("자신의 App에 송신 : " + msg);
                        break;
                }
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.customsend_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.editText);
        textView = view.findViewById(R.id.textView);

        view.findViewById(R.id.button1).setOnClickListener(this);
        view.findViewById(R.id.button2).setOnClickListener(this);
        view.findViewById(R.id.button3).setOnClickListener(this);
        view.findViewById(R.id.button4).setOnClickListener(this);
    }

    // 일반 브로드캐스트: 하나 이상의 action을 같은 메시지로 전송
    private void sendCustom(String... actions) {
        String message = editText.getText().toString();
        if (message.isEmpty()) {
            Toast.makeText(getContext(), "입력해주세요", Toast.LENGTH_SHORT).show();
        } else {
            editText.setText("");
            received1 = false;
            received2 = false;
            for (String action : actions) {
                Intent intent = new Intent(action);
                intent.putExtra("message", message);
                intent.setPackage(requireContext().getPackageName()); // 우리 앱 안으로 한정
                requireContext().sendBroadcast(intent);
            }
        }
    }

    // 로컬 브로드캐스트
    private void sendLocal() {
        String msg = editText.getText().toString();
        if (msg.isEmpty()) {
            Toast.makeText(getContext(), "입력해주세요", Toast.LENGTH_SHORT).show();
        } else {
            editText.setText("");
            Intent intent = new Intent(CustomConst.LOCAL);
            intent.putExtra("message", msg);
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(CustomConst.CUSTOM1);
        filter.addAction(CustomConst.CUSTOM2);
        ContextCompat.registerReceiver(requireContext(), receiver, filter, ContextCompat.RECEIVER_NOT_EXPORTED);
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(receiver, new IntentFilter(CustomConst.LOCAL));
    }

    @Override
    public void onStop() {
        super.onStop();
        requireContext().unregisterReceiver(receiver);
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(receiver);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        editText = null;
        textView = null;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button1) {
            sendCustom(CustomConst.CUSTOM1);
        } else if (id == R.id.button2) {
            sendCustom(CustomConst.CUSTOM2);
        } else if (id == R.id.button3) {
            sendCustom(CustomConst.CUSTOM1, CustomConst.CUSTOM2);
        } else if (id == R.id.button4) {
            sendLocal();
        }
    }
}
