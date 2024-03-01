package com.sneaker.shoeapp.Interface;

import com.sneaker.shoeapp.model.Cart;

public interface ClickItemCart {
    void increasePro();
    void decreasePro();
    void removePro(int position_item, Cart cart);
}
