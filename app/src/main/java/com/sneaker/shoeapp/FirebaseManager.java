//package com.sneaker.shoeapp;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class FirebaseManager {
//    private static final String FAVOURITE_PATH = "favourite";
//
//    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
//    private static DatabaseReference favouriteRef = database.getReference(FAVOURITE_PATH);
//
//    public static void addToFavourite(String productId) {
//        favouriteRef.child(productId).setValue(true);
//    }
//
//    public static void removeFromFavourite(String productId) {
//        favouriteRef.child(productId).removeValue();
//    }
//
//    public static DatabaseReference getFavouriteRef() {
//        return favouriteRef;
//    }
//
//    // Phương thức để lấy danh sách sản phẩm yêu thích từ Firebase
//    public static void getFavouriteProducts(final OnFavouriteProductsLoadedListener listener) {
//        favouriteRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                List<String> favouriteProductIds = new ArrayList<>();
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    String productId = snapshot.getKey();
//                    favouriteProductIds.add(productId);
//                }
//
//                if (listener != null) {
//                    listener.onFavouriteProductsLoaded(favouriteProductIds);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Xử lý lỗi nếu có
//            }
//        });
//    }
//    public interface OnFavouriteProductsLoadedListener {
//        void onFavouriteProductsLoaded(List<String> favouriteProductIds);
//    }
//}
