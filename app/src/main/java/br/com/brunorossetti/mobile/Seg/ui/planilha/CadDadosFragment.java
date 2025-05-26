package br.com.brunorossetti.mobile.Seg.ui.planilha;

import android.content.Context;
import android.location.GnssAntennaInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.transform.ErrorListener;

import br.com.brunorossetti.mobile.Seg.R;
import br.com.brunorossetti.mobile.Seg.model.Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CadDadosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadDadosFragment extends Fragment implements View.OnClickListener, Response.ErrorListener, Response.Listener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;

    private EditText editTextTextPassword;
    private EditText editTextText;
    private EditText editTextTextEmailAddress;
    private EditText editTextPhone;
    private Button button2;
    //volley
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectReq;


    public CadDadosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CadDadosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CadDadosFragment newInstance(String param1, String param2) {
        CadDadosFragment fragment = new CadDadosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_cad_dados, container, false);

        //binding
        this.editTextPhone = view.findViewById(R.id.editTextPhone);
        this.editTextTextEmailAddress = view.findViewById(R.id.editTextTextEmailAddress);
        this.button2 = view.findViewById(R.id.button2);
        this.editTextText = view.findViewById(R.id.editTextText);
        this.editTextTextPassword = view.findViewById(R.id.editTextTextPassword);
        this.button2.setOnClickListener(this);
        //instanciando a fila de requests - caso o objeto seja o view
        this.requestQueue = Volley.newRequestQueue(view.getContext());
        //inicializando a fila de requests do SO
        this.requestQueue.start();
        return view;
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button2){

            //instanciar meu obj de negocio
            try {
                Usuario usuario = new Usuario();
                usuario.setNome(String.valueOf(this.editTextText.getText()).toString());
                usuario.setEmail(this.editTextTextEmailAddress.getText().toString());
                usuario.setTelefone(this.editTextPhone.getText().toString());
                usuario.setSenha(this.editTextTextPassword.getText().toString());
                //REQUEST VOLLEY AQUI !!!!!!!
                jsonObjectReq = new JsonObjectRequest(
                        Request.Method.POST,
                        "http://10.0.2.2/Seg/conautenticacao.php",
                        usuario.toJsonObject(), this, this);
                requestQueue.add(jsonObjectReq);

                Toast.makeText(view.getContext(), "Dados Salvos!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Snackbar mensagem = Snackbar.make(view,
                "Ops! Houve um problema ao realizar o cadastro: " +
                        error.toString(),Snackbar.LENGTH_LONG);
        mensagem.show();
    }

    @Override
    public void onResponse(Object response) {
        try {
//instanciando objeto para manejar o JSON que recebemos
            JSONObject json = new JSONObject(response.toString());
//context e text são para a mensagem na tela o Toast
            Context context = view.getContext();
//pegando mensagem que veio do json
            CharSequence mensagem = json.getString("message");
//duração da mensagem na tela
            int duration = Toast.LENGTH_SHORT;
//verificando se salvou sem erro para limpar campos da tela
            if (json.getBoolean("success")){
                /* limpar campos da tela */
                this.editTextText.setText("");
                this.editTextTextEmailAddress.setText("");
                this.editTextTextPassword.setText("");
                this.editTextPhone.setText("");

            }
//mostrando a mensagem que veio do JSON
            Toast toast = Toast.makeText (context, mensagem, duration);
            toast.show();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}