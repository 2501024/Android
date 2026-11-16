package com.example.week11;

public class OrderFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ordered_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Fragment sender = new OrderSendFragment();
        Fragment receiver1 = new OrderReceiver1Fragment();
        Fragment receiver2 = new OrderReceiver2Fragment();

        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, sender, "SENDER");
        transaction.add(R.id.container, receiver1, "RECEIVER1");
        transaction.hide(receiver1);
        transaction.add(R.id.container, receiver2, "RECEIVER2");
        transaction.hide(receiver2);
        transaction.commit();

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.hide(sender);
                transaction.hide(receiver1);
                transaction.hide(receiver2);
                switch (tab.getPosition()) {
                    case 0:
                        transaction.show(sender);
                        break;
                    case 1:
                        transaction.show(receiver1);
                        break;
                    case 2:
                        transaction.show(receiver2);
                        break;
                }
                transaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
