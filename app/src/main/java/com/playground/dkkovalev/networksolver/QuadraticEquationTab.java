package com.playground.dkkovalev.networksolver;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class QuadraticEquationTab extends Fragment {

    private ClientHandler clientHandler;
    private String address;
    private int port;

    private Button buttonSendQuadraticData;

    public QuadraticEquationTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_quadratic_equation, container, false);

        address = "192.168.1.64";
        port = 8080;

        buttonSendQuadraticData = (Button)view.findViewById(R.id.button_send_quadratic_data);

        buttonSendQuadraticData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientHandler = new ClientHandler(address, port, "Quadratic", 5, 6, 1);
                clientHandler.execute();
            }
        });

        return view;
    }

}
