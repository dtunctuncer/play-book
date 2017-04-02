package com.dtunctuncer.playbook.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.dtunctuncer.playbook.App;
import com.dtunctuncer.playbook.R;
import com.dtunctuncer.playbook.detail.PlayDetailActivity;
import com.dtunctuncer.playbook.models.Play;
import com.dtunctuncer.playbook.play.type.OnListFragmentInteractionListener;
import com.dtunctuncer.playbook.play.type.PlayAdapter;
import com.dtunctuncer.playbook.utils.widgets.AdvancedRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements OnListFragmentInteractionListener, ISearchView, TextWatcher, TextView.OnEditorActionListener {

    public static final String ARG_SEARCH_LIST = "search-list";

    @Inject
    SearchPresenter presenter;

    @BindView(R.id.searchView)
    EditText searchView;
    @BindView(R.id.searchRecyclerView)
    AdvancedRecyclerView searchRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.noResultView)
    TextView noResultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ArrayList<Play> plays = getIntent().getParcelableArrayListExtra(ARG_SEARCH_LIST);
        DaggerSearchComponent.builder().applicationComponent(App.getApplicationComponent()).searchModule(new SearchModule(this, plays)).build().inject(this);
        initViews();
    }

    private void initViews() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        searchView.addTextChangedListener(this);
        searchView.setOnEditorActionListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        searchRecyclerView.setLayoutManager(manager);
        searchRecyclerView.setEmptyView(noResultView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(Play play) {
        Intent intent = new Intent(this, PlayDetailActivity.class);
        intent.putExtra(PlayDetailActivity.ARG_PLAY, play);
        startActivity(intent);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        presenter.performSearch(editable.toString());
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            searchView.clearFocus();
            InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            in.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
            presenter.performSearch(textView.getText().toString());
            return true;
        }
        return false;
    }

    @Override
    public void showResult(List<Play> searchResult) {
        PlayAdapter adapter = new PlayAdapter(searchResult, this);
        searchRecyclerView.setAdapter(adapter);
    }
}
