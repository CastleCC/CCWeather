package com.ccweather.chencheng.ccweather.util;

import android.text.TextUtils;

import com.ccweather.chencheng.ccweather.db.CCWeatherDB;
import com.ccweather.chencheng.ccweather.model.City;
import com.ccweather.chencheng.ccweather.model.County;
import com.ccweather.chencheng.ccweather.model.Province;

/**
 * Created by ChenCheng on 2016/3/13.
 */
public class Utility {
    /** * 解析和处理服务器返回的省级数据 */
    public synchronized static boolean handleProvincesResponse(CCWeatherDB ccWeatherDB, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0) {
                for (String p : allProvinces) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    // 将解析出来的数据存储到Province表
                    ccWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /** * 解析和处理服务器返回的市级数据 */
    public static boolean handleCitiesResponse(CCWeatherDB ccWeatherDB, String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String c : allCities) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId); // 将解析出来的数据存储到City表
                    ccWeatherDB.saveCity(city);
                } return true;
            }
        }
        return false;
    }

    /** * 解析和处理服务器返回的县级数据 */
    public static boolean handleCountiesResponse(CCWeatherDB ccWeatherDB, String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String c : allCounties) { String[] array = c.split("\\|");
                    County county = new County(); county.setCountyCode(array[0]);
                    county.setCountyName(array[1]); county.setCityId(cityId);
                     // 将解析出来的数据存储到County表
                    ccWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}