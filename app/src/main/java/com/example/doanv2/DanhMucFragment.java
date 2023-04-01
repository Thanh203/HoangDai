package com.example.doanv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doanv2.adapter.DanhMucAdapter;
import com.example.doanv2.adapter.ItemCallback;
import com.example.doanv2.adapter.MonAnAdapter;
import com.example.doanv2.adapter.TatCaDanhMucAdapter;
import com.example.doanv2.model.DanhMuc;
import com.example.doanv2.model.MonAn;
import com.example.doanv2.model.MonAnDB;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DanhMucFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DanhMucFragment extends Fragment implements ItemCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DanhMucFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DanhMucFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DanhMucFragment newInstance(String param1, String param2) {
        DanhMucFragment fragment = new DanhMucFragment();
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
        return inflater.inflate(R.layout.fragment_danh_muc, container, false);
    }

    RecyclerView rvTatCaDanhMuc;
    TatCaDanhMucAdapter tatCaDanhMucAdapter;
    ArrayList<DanhMuc> lstDanhMuc;
    MonAnDB monAnDB;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Activity activity = getActivity();
        monAnDB = new MonAnDB(activity);


        lstDanhMuc = monAnDB.getLstDanhMuc();
        tatCaDanhMucAdapter = new TatCaDanhMucAdapter(lstDanhMuc, getActivity(), this);
        rvTatCaDanhMuc = view.findViewById(R.id.rvTatCaDanhMuc);
        rvTatCaDanhMuc.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvTatCaDanhMuc.setAdapter(tatCaDanhMucAdapter);

    }

    @Override
    public void onItemClick(String id) {
        String[] fixInput = id.split(":");

        Intent i = new Intent(getActivity(), ChiTietDanhMucActivity.class);
        i.putExtra("idDanhMuc", fixInput[1]);
        i.putExtra("tenDanhMuc", monAnDB.getNameDanhMucById(fixInput[1]));
        startActivity(i);
    }
}