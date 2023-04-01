package com.example.doanv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.doanv2.adapter.DanhMucAdapter;
import com.example.doanv2.adapter.ItemCallback;
import com.example.doanv2.adapter.MonAnAdapter;
import com.example.doanv2.adapter.MonAnMoiAdapter;
import com.example.doanv2.model.DanhMuc;
import com.example.doanv2.model.MonAn;
import com.example.doanv2.model.MonAnDB;
import com.example.doanv2.model.MonAnDonGian;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrangChuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrangChuFragment extends Fragment implements ItemCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrangChuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrangChuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrangChuFragment newInstance(String param1, String param2) {
        TrangChuFragment fragment = new TrangChuFragment();
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
        return inflater.inflate(R.layout.fragment_trang_chu, container, false);
    }

    RecyclerView rvDaXem;
    RecyclerView rvDanhMuc;
    RecyclerView rvMonMoi;
    MonAnAdapter monAnDaXemAdapter;
    MonAnMoiAdapter monAnAdapter;
    DanhMucAdapter danhMucAdapter;
    ArrayList<MonAnDonGian> lstMonAnDaXem;
    ArrayList<MonAn> lstMonAn;
    ArrayList<DanhMuc> lstDanhMuc;
    MonAnDB monAnDB;
    TextView tvDaXem;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Activity activity = getActivity();
        monAnDB = new MonAnDB(activity);

        lstMonAn = monAnDB.getLstMonAn();
        monAnAdapter = new MonAnMoiAdapter(lstMonAn,getActivity(),this);
        rvMonMoi = view.findViewById(R.id.rvMonMoi);
        rvMonMoi.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false));
        rvMonMoi.setAdapter(monAnAdapter);

        lstMonAnDaXem = monAnDB.getLstLichSu();
        if(lstMonAnDaXem.isEmpty())
        {
            tvDaXem = view.findViewById(R.id.tvDaXem);
            tvDaXem.setText("");
        }
        monAnDaXemAdapter = new MonAnAdapter(lstMonAnDaXem,getActivity(), this);
        rvDaXem = view.findViewById(R.id.rvDaXem);
        rvDaXem.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false));
        rvDaXem.setAdapter(monAnDaXemAdapter);

        lstDanhMuc = monAnDB.getLstDanhMuc();
        danhMucAdapter = new DanhMucAdapter(lstDanhMuc, getActivity(), this);
        rvDanhMuc = view.findViewById(R.id.rvDanhMuc);
        rvDanhMuc.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));
        rvDanhMuc.setAdapter(danhMucAdapter);

    }
    @Override
    public void onResume(){
        super.onResume();
        lstMonAnDaXem.clear();
        lstMonAnDaXem.addAll(monAnDB.getLstLichSu());
        if(!lstMonAnDaXem.isEmpty())
        {
            tvDaXem = getView().findViewById(R.id.tvDaXem);
            tvDaXem.setText("Món đã xem");
        }
        monAnDaXemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(String id) {
        String[] fixInput = id.split(":");

        if(fixInput[0].equals("Danh muc"))
        {
            Intent i = new Intent(getActivity(), ChiTietDanhMucActivity.class);
            i.putExtra("idDanhMuc", fixInput[1]);
            i.putExtra("tenDanhMuc", monAnDB.getNameDanhMucById(fixInput[1]));
            startActivity(i);
        }else{
            MonAn monAn = monAnDB.getMonAnByID(fixInput[1]);
            monAnDB.DeleteLichSu(fixInput[1]);
            monAnDB.InsertLichSu(fixInput[1], monAn.getTenMonAn(), monAn.getAnh());
            Intent i = new Intent(getActivity(), ChiTietMonAnActivity.class);
            i.putExtra("idMonAn", fixInput[1]);
            startActivity(i);
        }
    }
}