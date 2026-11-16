package com.example.week11;

public class OrderSendFragment extends Fragment {
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ordersend_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.textView);
        EditText editText = view.findViewById(R.id.editText);
        Button button = view.findViewById(R.id.button);

        button.setOnClickListener(v -> {
            String message = editText.getText().toString();
            if (message.isEmpty()) {
                Toast.makeText(getContext(), "입력해주세요", Toast.LENGTH_SHORT).show();
            } else {
                editText.setText("");
                Intent intent = new Intent(OrderBRConst.ORDERED);
                intent.putExtra("message", message);
                intent.setPackage(requireContext().getPackageName()); // 우리 앱 안으로 한정

                // 마지막에 호출되는 최종 수신자 (abort되어도 호출됨)
                BroadcastReceiver resultReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        if (textView != null) {
                            Bundle extras = getResultExtras(true);
                            String msg = extras.getString("modified"); // 자료 106p에는 "message"로 적혀있으나, Receiver1/2에서 저장한 키값인 "modified"로 받아야 화면(93p)처럼 출력됩니다.
                            textView.setText("송신부 수신 결과 : " + msg);
                        }
                    }
                };

                requireContext().sendOrderedBroadcast(
                        intent,
                        null, // receiverPermission
                        resultReceiver,
                        null, // scheduler
                        Activity.RESULT_OK, // initialCode
                        null, // initialData
                        null // initialExtras
                );
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        textView = null;
    }
}
