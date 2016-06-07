package com.playground.dkkovalev.networksolver;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LinearEquationTab extends Fragment implements NetworkCallback {

    private ClientHandler clientHandler;
    private String address;
    private int port;

    private TextView resultTextView;

    private EditText editTextForA, editTextForB;

    private Button buttonSendLinearData;

    public LinearEquationTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_linear_equation_tab, container, false);

        resultTextView = (TextView)view.findViewById(R.id.tv_linear_response);

        editTextForA = (EditText)view.findViewById(R.id.et_linear_a);
        editTextForB = (EditText)view.findViewById(R.id.et_linear_b);

        buttonSendLinearData = (Button) view.findViewById(R.id.button_send_linear_data);
        buttonSendLinearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientHandler = new ClientHandler("192.168.1.64", 8080, "Linear", Double.valueOf(editTextForA.getText().toString()), Double.valueOf(editTextForB.getText().toString()));
                clientHandler.setNetworkCallback(LinearEquationTab.this);
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
