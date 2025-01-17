package ifp.mobile.projek;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        View bookItem = v.findViewById(R.id.bookItem1);

        bookItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewbuku) {
                Intent intent = new Intent(requireActivity(), DetailBuku.class);
                startActivity(intent);
            }
        });
        return v;
    }
}