package com.example.elishevak.training;

/**
 * Created by elishevak on 5/9/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;


public class FactAdapter extends FragmentStatePagerAdapter {

    List<Fact> factsList = new ArrayList<Fact>() {{
        add(new Fact(
                "Binomial nomenclature",
                "Asio",
                R.drawable.binomial_nomenclature
        ));
        add(new Fact(
                "Species",
                "There are 8 species of Asio",
                R.drawable.species
        ));
        add(new Fact(
                "Beneficial to agriculture",
                "Used as a pest prevention",
                R.drawable.pest_prevention
        ));
        add(new Fact(
                "Reminded in the Torah",
                "In Vayikra Humash, Chapter 11, As an impure bird, because of the way he hunts his food",
                R.drawable.not_kosher
        ));
        add(new Fact(
                "והנה עובדה בעברית :)",
                "ינשוף - מלשון נשף, שפירושו לילה, שהרי הוא צד בלילה",
                R.drawable.yanshuf
        ));
    }};

    public FactAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        return FactFragment.newInstance(index, factsList.get(index));
    }

    @Override
    public int getCount() {
        return factsList.size();
    }


}
