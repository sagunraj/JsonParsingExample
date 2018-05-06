package np.com.sagunraj.jsonparsingexample;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    RequestQueue requestQueue;
    String url = "https://api.androidhive.info/contacts/";
    List<DataModule> mydata = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj1 = new JSONObject(response);
                    JSONArray array1 = obj1.getJSONArray("contacts");
                    for (int i = 0; i < array1.length(); i++) {
                        JSONObject obj2 = array1.getJSONObject(i);
                        DataModule m = new DataModule();
                        m.setName(obj2.getString("name"));
                        m.setEmail(obj2.getString("email"));
                        JSONObject obj3 = obj2.getJSONObject("phone");
                        m.setPhone(obj3.getString("mobile"));
                        mydata.add(m);
                    }
                    lv.setAdapter(new MyAdapter(MainActivity.this, mydata));
                }catch(Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Couldn't fetch data.", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest);
    }
}
