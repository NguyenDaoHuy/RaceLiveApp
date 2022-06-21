package com.cuongpq.basemvp.view.base;


public interface ViewUI {
    int getMainLayout();
    void onBackPressed();
    void viewLoading();
    void disableLoading();
    int getIdLoading();
    void showMessage(String message);
}
