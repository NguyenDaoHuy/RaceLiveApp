package com.cuongpq.basemvp.view.ui.fragment.race.carinfo;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentCarInformationBinding;
import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;


public class CarInformationFragment extends BaseFragmentMvp<FragmentCarInformationBinding,CarInfoPresenter> implements ICarInfoView {
    public static final String TAG = CarInformationFragment.class.getName();

    @Override
    protected void initView() {
        super.initView();
        presenter = new CarInfoPresenter(this);
        presenter.initPresenter();
        Car car = (Car) getArguments().getSerializable("car");
        binding.tvRaceName.setText(car.getId() + " . "+ car.getNameCar());
        binding.tvStart.setText(car.getStart());
        binding.tvSS1.setText(car.getSs1());
        binding.tvSS2.setText(car.getSs2());
        binding.tvSS3.setText(car.getSs3());
        binding.tvSS4.setText(car.getSs4());
        binding.tvSS5.setText(car.getSs5());
        binding.tvSS6.setText(car.getSs6());
        binding.tvStop.setText(car.getStop());
    }

    @Override
    public int getMainLayout() {
        return R.layout.fragment_car_information;
    }

    @Override
    public void onClickListener() {

    }
}