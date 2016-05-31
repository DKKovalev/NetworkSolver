package com.playground.dkkovalev.networksolver;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class LinearEquationTab extends Fragment {

    private ClientHandler clientHandler;
    private String address;
    private int port;

    private Button buttonSendLinearData;

    public LinearEquationTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_linear_equation_tab, container, false);



        buttonSendLinearData = (Button) view.findViewById(R.id.button_send_linear_data);
        buttonSendLinearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientHandler = new ClientHandler("192.168.1.64", 8080, "Linear", 5, 2);
                clientHandler.execute();
            }
        });

        return view;
    }

}
