package com.playground.dkkovalev.networksolver;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

public class QuadraticEquationTab extends Fragment implements NetworkCallback {

    private ClientHandler clientHandler;
    private String address;
    private int port;

    private Button buttonSendQuadraticData;
    private TextView resultTextView;
    private EditText editTextForA, editTextForB, editTextForC;

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

        resultTextView = (TextView) view.findViewById(R.id.tv_quadratic_response);

        buttonSendQuadraticData = (Button) view.findViewById(R.id.button_send_quadratic_data);

        editTextForA = (EditText)view.findViewById(R.id.et_quadratic_a);
        editTextForB = (EditText)view.findViewById(R.id.et_quadratic_b);
        editTextForC = (EditText)view.findViewById(R.id.et_quadratic_c);

        buttonSendQuadraticData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientHandler = new ClientHandler(address, port, "Quadratic", Double.valueOf(editTextForA.getText().toString()), Double.valueOf(editTextForB.getText().toString()), Double.valueOf(editTextForC.getText().toString()));
                clientHandler.setNetworkCallback(QuadraticEquationTab.this);
                clientHandler.execute();
            }
        });

        return view;
    }

    @Override
    public void getServerResponse(String response) {
        resultTextView.setText(response);
    }
}
