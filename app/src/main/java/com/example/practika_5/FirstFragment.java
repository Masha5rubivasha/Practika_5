package com.example.practika_5;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
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

    Button button_notification;

    Button button_banner;

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

        button_notification = view.findViewById(R.id.button_notification);

        button_banner = view.findViewById(R.id.button_banner);

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

        // *Канал (уведомления)*

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "my channel";
            String description = "it's the channel for notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = requireContext().
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Intent notificationIntent = new Intent(getActivity(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(),
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        button_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // конструктор уведомеления
                NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(),
                        "CHANNEL")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentText("Девушка по вызову в пути!")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                // ПОЛУЧАЕМ МЕНЕДЖЕРА КАПСОМ
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());

                //проверяем есть ли разрешение на отправку уведомления
                if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                // покажись_уведомление
                notificationManager.notify(1, builder.build());
            }
        });


        button_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MyService.class);
                getActivity().startService(intent);
            }
        });


    }
}