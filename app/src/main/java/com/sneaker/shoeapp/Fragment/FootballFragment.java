package com.sneaker.shoeapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.sneaker.shoeapp.Adapter.ProductAdapter;
import com.sneaker.shoeapp.Interface.ClickItemProduct;
import com.sneaker.shoeapp.MainActivity;
import com.sneaker.shoeapp.ProductDetailsActivity;
import com.sneaker.shoeapp.R;
import com.sneaker.shoeapp.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FootballFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FootballFragment extends Fragment {
    RecyclerView rcv_ft;
    ProductAdapter productAdapter;
    View view;
    FirebaseFirestore db;
    List<Product> productList;
    public FootballFragment() {
        // Required empty public constructor
    }
    public static FootballFragment newInstance() {
        return new FootballFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_football, container, false);
        rcv_ft = view.findViewById(R.id.rcv_ft);
        db = FirebaseFirestore.getInstance();
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(productList, new ClickItemProduct() {
            @Override
            public void onClickItemProduct(Product product) {
                IntentDetails(product);
            }
        },getContext());
        CollectionReference collection = db.collection("Product");
        Query query = collection.document().getParent().whereEqualTo("category","football").limit(4);
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error !=null){
                    return;
                }
                for(DocumentChange dc: value.getDocumentChanges()){

                    String namePro = dc.getDocument().getString("proName");
                    //    Double price = Double.valueOf(dc.getDocument().getString("price"));
                    String color = dc.getDocument().getString("color");
                    String image = dc.getDocument().getString("image");
                    String cate = dc.getDocument().getString("category");
                    String id = dc.getDocument().getId();
                    Double price = Double.valueOf(dc.getDocument().getString("price"));
                    productList.add(new Product(namePro, price, cate, image, color, 2,id));
                }
                productAdapter.notifyDataSetChanged();
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        rcv_ft.setLayoutManager(gridLayoutManager);
        rcv_ft.setOverScrollMode(View.OVER_SCROLL_NEVER);
        rcv_ft.setAdapter(productAdapter);
        return view;
    }
    private void IntentDetails(Product product) {
        Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj_product",product);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}