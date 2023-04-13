package com.example.practika_5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FirstFragment extends Fragment {

    Button button_number;

    Button button_adress;

    EditText editText_number;

    EditText editText_adress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button_number = view.findViewById(R.id.button_number);
        button_adress = view.findViewById(R.id.button_adress);

        editText_number = view.findViewById(R.id.phoneNumber);
        editText_adress = view.findViewById(R.id.adress);

        button_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String peredachaNomera = editText_number.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("nomer", peredachaNomera);

                Navigation.findNavController(view).navigate(R.id.action_firstFragment_to_secondFragment, bundle);
            }
        });

        button_adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String peredachaAdressa = editText_adress.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("adres", peredachaAdressa);

                Navigation.findNavController(view).navigate(R.id.action_firstFragment_to_thirdFragment, bundle);

            }
        });


    }
}