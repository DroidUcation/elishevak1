package com.perfecto.optimizer.utils;

import com.perfecto.optimizer.R;

/**
 * Created by elishevak on 7/15/2016.
 */
public enum FilterTypes {

    ANDROID("Android", R.id.android_textView),
    IOS("iOS", R.id.iOS_textView),
    WINDOWS("Windows", R.id.windows_textView),
    TABLET("Tablet", R.id.tablet_textView),
    SMARTPHONE("Smartphone", R.id.smartphone_textView),


    US("United states", R.id.us_checkBox),
    NETHERLANDS("Netherlands", R.id.netherlands_checkBox),
    CANADA("Canada", R.id.canada_checkBox),
    EU5("Eu5", R.id.eu5_checkBox),
    UK("United Kingdom", R.id.uk_checkBox),
    GERMANY("Germany", R.id.germany_checkBox),
    SPAIN("Spain", R.id.spain_checkBox),
    ITALY("Italy", R.id.italy_checkBox),
    FRENCH("French", R.id.french_checkBox);


    private String _filterName;
    private int _filterId;

    FilterTypes(String settingName, int filterId) {
        _filterName = settingName;
        _filterId = filterId;
    }

    public String getFilterName() {
        return _filterName;
    }

    public int getFilterId() {
        return _filterId;
    }

    public static FilterTypes getTypeById(int key) {
        FilterTypes temp = null;
        for (FilterTypes type : FilterTypes.values()) {
            if (type.getFilterId() == key) {
                temp = type;
                break;
            }
        }
        return temp;
    }

    public static FilterTypes getTypeByName(String name) {
        FilterTypes temp = null;
        for (FilterTypes type : FilterTypes.values()) {
            if (type.getFilterName().equals(name)) {
                temp = type;
                break;
            }
        }
        return temp;
    }
}

