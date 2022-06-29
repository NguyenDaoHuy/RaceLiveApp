package com.cuongpq.basemvp.view.ui.fragment.listRace.addmember;

public interface IAddMemberPresenter {
    void initPresenter();
    void createMember(String memberAccount,String memberPassword,String memberName,int permission);

}
