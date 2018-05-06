package np.com.sagunraj.jsonparsingexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

class MyAdapter extends BaseAdapter {
    public Context context;
    public List<DataModule> data;
    public MyAdapter(MainActivity mainActivity, List<DataModule> mydata) {
        context = mainActivity;
        data = mydata;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.activity_sample,null);
        TextView nameTv = view.findViewById(R.id.name);
        TextView emailTv = view.findViewById(R.id.email);
        TextView phoneTv = view.findViewById(R.id.phone);
        nameTv.setText(""+data.get(i).getName());
        emailTv.setText(""+data.get(i).getEmail());
        phoneTv.setText(""+data.get(i).getPhone());
        return view;
    }
}
