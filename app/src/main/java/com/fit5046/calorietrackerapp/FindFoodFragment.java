package com.fit5046.calorietrackerapp;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fit5046.RESTfulWSEntities.Food;
import com.fit5046.commonTools.ConnectToRESTWS;
import com.fit5046.commonTools.PictureWork;
import com.fit5046.commonTools.SearchFoodAPI;
import com.fit5046.commonTools.SearchGoogleAPI;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FindFoodFragment extends Fragment implements View.OnClickListener {

    private View vFindFood;
    private ImageView iv_searched_food;
    private Spinner spinner_food_category;
    private Spinner spinner_food_name;
    private Button search_food_btn;
    private Button add_food_to_daily_btn;
    private EditText et_search_food_name;
    private EditText et_food_daily_amount;
    private TextView tv_searched_food_desc;
    private TextView tv_searched_food_cal;
    private TextView tv_searched_food_fat;
    private List<String> foodCategoriesList = new ArrayList<String>();
    private List<String> foodNameList;
    String selectedCategory = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        vFindFood = inflater.inflate(R.layout.fragment_find_food, container, false);

        iv_searched_food = (ImageView) vFindFood.findViewById(R.id.iv_searched_food);
        spinner_food_category = (Spinner) vFindFood.findViewById(R.id.spinner_food_category);
        spinner_food_name = (Spinner) vFindFood.findViewById(R.id.spinner_food_name);
        search_food_btn = (Button) vFindFood.findViewById(R.id.search_food_btn);
        add_food_to_daily_btn = (Button) vFindFood.findViewById(R.id.add_food_to_daily_btn);
        et_search_food_name = (EditText) vFindFood.findViewById(R.id.et_search_food_name);
        et_food_daily_amount = (EditText) vFindFood.findViewById(R.id.et_food_daily_amount);
        tv_searched_food_desc = (TextView) vFindFood.findViewById(R.id.tv_searched_food_desc);
        tv_searched_food_cal = (TextView) vFindFood.findViewById(R.id.tv_searched_food_cal);
        tv_searched_food_fat = (TextView) vFindFood.findViewById(R.id.tv_searched_food_fat);

        foodCategoriesList.add("Drink");
        foodCategoriesList.add("Meal");
        foodCategoriesList.add("Meat");
        foodCategoriesList.add("Dessert");
        foodCategoriesList.add("Bread");
        foodCategoriesList.add("Cake");
        foodCategoriesList.add("Fruit");
        foodCategoriesList.add("Vegetable");
        foodCategoriesList.add("Other");

        ArrayAdapter<String> spinnerCategoryAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, foodCategoriesList);
        spinnerCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_food_category.setAdapter(spinnerCategoryAdapter);
        spinner_food_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = parent.getItemAtPosition(position).toString();
                FindCategoryFoodAsyncTask findFoodAsyncTask = new FindCategoryFoodAsyncTask();
                findFoodAsyncTask.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        spinner_food_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedFoodName = parent.getItemAtPosition(position).toString();
                FindFoodByNameAsyncTask findFoodByNameAsyncTask = new FindFoodByNameAsyncTask();
                findFoodByNameAsyncTask.execute(selectedFoodName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        search_food_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_search_food_name.getText().toString().length() > 0) {
                    SearchFoodFromAPIAsyncTask searchFoodFromAPIAsyncTask = new SearchFoodFromAPIAsyncTask();
                    searchFoodFromAPIAsyncTask.execute(et_search_food_name.getText().toString());
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Please input the food you want to search!", Toast.LENGTH_LONG);
                }
            }
        });

        return vFindFood;
    }

    @Override
    public void onClick(View v) {
    }

    private class SearchFoodFromAPIAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return SearchFoodAPI.searchFood(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Food food = SearchFoodAPI.getFoodInfoJson(result);
            tv_searched_food_cal.setText(String.valueOf(food.getCalorieAmount()));
            tv_searched_food_fat.setText(String.valueOf(food.getFatAmount()));
            FindFoodPictureAsyncTask findFoodPictureAsyncTask = new FindFoodPictureAsyncTask();
            findFoodPictureAsyncTask.execute(food.getFoodName());
            FindDescAsyncTask findDescAsyncTask = new FindDescAsyncTask();
            findDescAsyncTask.execute(food.getFoodName());
        }
    }

    private class FindCategoryFoodAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String result = ConnectToRESTWS.findFoodByCategory(selectedCategory);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.length() > 10) {
                try {
                    JSONArray foodsArray = new JSONArray(result);
                    foodNameList = new ArrayList<String>();
                    for (int i = 0; i < foodsArray.length(); i++) {
                        JSONObject object = foodsArray.getJSONObject(i);
                        foodNameList.add(object.getString("foodname"));
                    }
//                    spinner_food_name = (Spinner) vFindFood.findViewById(R.id.spinner_food_name);
                    ArrayAdapter<String> spinnerFoodNameAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, foodNameList);
                    spinnerFoodNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_food_name.setAdapter(spinnerFoodNameAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                foodNameList = new ArrayList<String>();
                spinner_food_name = (Spinner) vFindFood.findViewById(R.id.spinner_food_name);
                ArrayAdapter<String> spinnerFoodNameAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, foodNameList);
                spinnerFoodNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_food_name.setAdapter(spinnerFoodNameAdapter);
                Toast.makeText(getActivity().getApplicationContext(), "Nothing found in this category", Toast.LENGTH_LONG);
            }
        }
    }

    private class FindFoodByNameAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return ConnectToRESTWS.findFoodByFoodName(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.length() > 10) {
                try {
                    JSONArray foodsArray = new JSONArray(result);
                    Food food = new Food();
                    for (int i = 0; i < foodsArray.length(); i++) {
                        JSONObject object = foodsArray.getJSONObject(i);
                        food.setFoodid(object.getInt("foodid"));
                        food.setFoodName(object.getString("foodname"));
                        food.setCalorieAmount(object.getInt("calorieamount"));
                        food.setFatAmount(object.getInt("fat"));
                        food.setCategory(object.getString("category"));
                        food.setServingunit(object.getString("servingunit"));
                        food.setServingamount(object.getDouble("servingamount"));
                    }
                    tv_searched_food_cal.setText(String.valueOf(food.getCalorieAmount()));
                    tv_searched_food_fat.setText(String.valueOf(food.getFatAmount()));
                    FindFoodPictureAsyncTask findFoodPictureAsyncTask = new FindFoodPictureAsyncTask();
                    findFoodPictureAsyncTask.execute(food.getFoodName());
                    FindDescAsyncTask findDescAsyncTask = new FindDescAsyncTask();
                    findDescAsyncTask.execute(food.getFoodName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class FindFoodPictureAsyncTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                String result = SearchGoogleAPI.getPicResult(params[0]);
                bitmap = PictureWork.getBitmap(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null)
                iv_searched_food.setImageBitmap(result);
            else
                iv_searched_food.setImageResource(R.drawable.noimagefound);
        }
    }

    private class FindDescAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return SearchGoogleAPI.getGoogleResult(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            tv_searched_food_desc.setText(result);
        }
    }
}


