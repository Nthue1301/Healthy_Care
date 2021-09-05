package huenguyen.nth.healthycare;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import huenguyen.nth.healthycare.fragment.exercise.ExerciseFragment;
import huenguyen.nth.healthycare.fragment.history.HistoryFragment;
import huenguyen.nth.healthycare.fragment.home.HomeFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

        private static final int FRAGMENT_HOME = 1;
        private static final int FRAGMENT_HISTORY = 2;
        private static final int FRAGMENT_EXERCISE = 3;

        private int currentFragment = FRAGMENT_HOME;
        private NavigationView mNavigationView;
        private BottomNavigationView  mBottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);

        mBottomNavigationView = findViewById(R.id.bottom_nav);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        openHomefragment();
                        mNavigationView.setCheckedItem(R.id.nav_home);
                        break;
                    case R.id.action_history:
                        openHistorufragment();
                        mNavigationView.setCheckedItem(R.id.nav_history);
                        break;
                    case R.id.action_exercise:
                        openExercisefragment();
                        mNavigationView.setCheckedItem(R.id.nav_exercise);
                        break;
                }
                return true;
            }
        });
        replaceFragment(new HomeFragment());
        mNavigationView.setCheckedItem(R.id.nav_home);
        mBottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
        setTitleToolbar();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            openHomefragment();
            mBottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
        } else if (id == R.id.nav_history) {
            openHistorufragment();
            mBottomNavigationView.getMenu().findItem(R.id.action_history).setChecked(true);
        } else if (id == R.id.nav_exercise) {
            openExercisefragment();
            mBottomNavigationView.getMenu().findItem(R.id.action_exercise).setChecked(true);
        }

        setTitleToolbar();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openHomefragment(){
        if(currentFragment != FRAGMENT_HOME){
            replaceFragment(new HomeFragment());
            currentFragment = FRAGMENT_HOME;
        }
    }

    private void openHistorufragment(){
        if(currentFragment != FRAGMENT_HISTORY){
            replaceFragment(new HistoryFragment());
            currentFragment = FRAGMENT_HISTORY;
        }
    }

    private void openExercisefragment(){
        if(currentFragment != FRAGMENT_EXERCISE){
            replaceFragment(new ExerciseFragment());
            currentFragment = FRAGMENT_EXERCISE;
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
    private void setTitleToolbar(){
        String title = "";
        switch (currentFragment){
            case FRAGMENT_HOME:
                title = getString(R.string.menu_home);
                break;
            case FRAGMENT_HISTORY:
                title = getString(R.string.menu_history);
                break;
            case FRAGMENT_EXERCISE:
                title = getString(R.string.menu_exersice);
                break;
        }
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }
    }
}