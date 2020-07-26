package vn.ntu.edu.nguyenthanhhuynh.dangkymonhoc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class SecondFragment extends Fragment {

    Button button_second;
    TextView txtThanhCong;
    NavController navController;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        button_second = view.findViewById(R.id.button_second);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        addView(view);
    }

    private void addView(View view){
        txtThanhCong = view.findViewById(R.id.txtThanhCong);

        Bundle bundle = getArguments();
        String s = bundle.getString(FirstFragment.KEY);
        txtThanhCong.setText(s);
    }
}