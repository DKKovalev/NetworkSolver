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
public class RandomTab extends Fragment implements NetworkCallback {

    private ClientHandler clientHandler;
    private String address;
    private int port;

    private Button buttonSendQuadraticData;
    private TextView resultTextView;
    private EditText editTextForA, editTextForB;

    public RandomTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_random_tab, container, false);

        address = "192.168.1.64";
        port = 8080;

        resultTextView = (TextView) view.findViewById(R.id.tv_random_response);

        buttonSendQuadraticData = (Button) view.findViewById(R.id.button_send_random_data);

        editTextForA = (EditText) view.findViewById(R.id.et_random_a);
        editTextForB = (EditText) view.findViewById(R.id.et_random_b);

        buttonSendQuadraticData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientHandler = new ClientHandler(address, port, "Random", Integer.valueOf(editTextForA.getText().toString()), Integer.valueOf(editTextForB.getText().toString()));
                clientHandler.setNetworkCallback(RandomTab.this);
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
