/**
 * @author: Dung Trinh
 * @version: 1.0.0
 * None support for search keyword (Will support in the future version
 * //TODO Version 1.0.0 Support quote
 * //TODO Version 1.0.5 Support menu selection
 * //TODO Version 1.1.0 Refine Layout
 * //TODO Version 1.1.5 Increase number of quote (goal 20 for each category)
 * //TODO Version 2.0.0 Make the share on facebook button
 * //TODO Version 3.0.0 Make the database and migration
 */
package com.example.owner.fortune;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.*;

public class MainActivity extends AppCompatActivity {
    /**all of the quote is in here*/
    private ArrayList<Quote>  quoteList;
    /**The selected quote base on the topic*/
    private ArrayList<Quote> selectedList;

    /**Topic to search from**/
    protected String topic;

    protected String searchKeyword;

    /**Layout and menu*/
    protected Button fortuneButton;
    protected TextView quoteView;
    protected Menu mainMenu;
    private static Map<String, ArrayList<Quote>> myMap;

    private static final String TAG = Activity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
        initGenerateListener();

        this.quoteList = new ArrayList<>();
        this.selectedList = new ArrayList<>();
        this.topic = "general";
        this.searchKeyword = "";
        myMap = new HashMap<String, ArrayList<Quote>>();

        /**import value into the hashmap*/
        importValue();

        /**Import value into the general list/select list*/
        for (String key:myMap.keySet())
        {
            quoteList.addAll(myMap.get(key));
            selectedList.addAll(myMap.get(key));
        }
    }

    /**Import the value in string resource into the map
     * Tedious work just to import the value in here
     *
     * */
    public void importValue()
    {
        //add everything in here
        Resources res = this.getResources();
        importValueInMap(res.getStringArray(R.array.art), "art");
        importValueInMap(res.getStringArray(R.array.general), "general");
    }

    /**
     *
     * @param list list of quote that is used
     * @param name the key for map
     */
    private void importValueInMap(String[] list, String name) {
        Quote quote;
        myMap.put(name, new ArrayList<Quote>());

        for(String s : list) {
            Log.d(TAG, "Adding new joke" + s);
            quote = new Quote(s);
            myMap.get(name).add(quote);
        }
    }

    /**Init the first component*/
    protected void initLayout() {
        setContentView(R.layout.activity_main);
        fortuneButton = (Button) findViewById(R.id.generate_button);
        quoteView = (TextView) findViewById(R.id.quote_view);
    }

    /**Button listener for the generate button*/
    protected void initGenerateListener()
    {
        fortuneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quote newQuote;
                if (v.getId() == R.id.generate_button) {
                    Log.d(TAG, "onClick() called by generateButton");
                    quoteView.setText((getQuote().toString()));
                    if(topic.equals("general"))
                    {
                        newQuote = getQuote();
                    }
                    else
                    {
                        newQuote = topicQuote(topic);
                    }
                    quoteView.setText(newQuote.toString());
                }
            }
        });
    }

    /**Return a random quote from the list*/
    protected Quote getQuote()
    {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(quoteList.size());
        return quoteList.get(index);
    }

    /**Return the quote based on the topic*/
    protected Quote topicQuote(String topic)
    {
        selectedList = myMap.get(topic);
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(selectedList.size());
        return selectedList.get(index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        this.mainMenu = menu;
        return true;
    }

    /**
     * This will change the global variable topic to set the seach
     * @param item is select
     * @return boolean of chosen value
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.art:
                topic = "art";
                break;
            case R.id.general:
                topic = "general";
                break;
            case R.id.definitions:
                topic = "definitions";
                break;
            case R.id.education:
                topic = "education";
                break;
            case R.id.ethnic:
                topic = "ethnic";
                break;
            case R.id.food:
                topic = "food";
                break;
            case R.id.geeky:
                topic = "geeky";
                break;
            case R.id.humorist:
                topic = "humorist";
                break;
            case R.id.law:
                topic = "law";
                break;
            case R.id.literature:
                topic = "literature";
                break;
            case R.id.love:
                topic = "love";
                break;
            case R.id.kids:
                topic = "kids";
                break;
            case R.id.medicine:
                topic = "medicine";
                break;
            case R.id.pet:
                topic = "pet";
                break;
            case R.id.platitude:
                topic = "platitude";
                break;
            case R.id.politics:
                topic = "politics";
                break;
            case R.id.riddle:
                topic = "riddle";
                break;
            case R.id.sports:
                topic = "sports";
                break;
            case R.id.wisdom:
                topic = "wisdom";
                break;
            case R.id.work:
                topic = "work";
                break;
            default:
                super.onOptionsItemSelected(item);
                break;
        }
        return true;
    }

}
