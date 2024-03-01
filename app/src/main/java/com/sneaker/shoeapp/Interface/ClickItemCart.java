package com.sneaker.shoeapp.Interface;

import com.sneaker.shoeapp.model.Cart;

public interface ClickItemCart {
    void increasePro(int position_item, Cart cart);
    void decreasePro(int position_item, Cart cart);
    void removePro(int position_item, Cart cart);
}
