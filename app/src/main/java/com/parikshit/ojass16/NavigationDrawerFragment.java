package com.parikshit.ojass16;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Fragment used for managing interactions for and presentation of a navigation drawer.
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * design guidelines</a> for a complete explanation of the behaviors implemented here.
 */
public class NavigationDrawerFragment extends Fragment {

    /**
     * Remember the position of the selected item.
     */
    private static final String GROUP_STATE_SELECTED_POSITION = "selected_group_navigation_drawer_position";
    private static final String CHILD_STATE_SELECTED_POSITION = "selected_child_navigation_drawer_position";
    private static final String EXPANDED_CONFIG_CHANGE = "selected_expanded_navigation_position";

    /**
     * Per the design guidelines, you should show the drawer on launch until the user manually
     * expands it. This shared preference tracks this.
     */
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private NavigationDrawerCallbacks mCallbacks;

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private ExpandableListView mDrawerExpandableListView;
    private View mFragmentContainerView;
    private View view_group = null;
    Fragment fragment = null;

    private int mCurrentSelectedChildPosition = 0;
    private int mCurrentSelectedGroupPosition = 0;
    public int lastExpanded = 0;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;


    //Navigation List Variable
    private List<IconItem> _listdataHeader;
    private HashMap<IconItem, List<IconItem>> _listDataChild;


    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Read in the flag indicating whether or not the user has demonstrated awareness of the
        // drawer. See PREF_USER_LEARNED_DRAWER for details.
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

        if (savedInstanceState != null) {
            mCurrentSelectedChildPosition = savedInstanceState.getInt(CHILD_STATE_SELECTED_POSITION);
            lastExpanded = savedInstanceState.getInt(EXPANDED_CONFIG_CHANGE);
            mCurrentSelectedGroupPosition = savedInstanceState.getInt(GROUP_STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }
        selectItem(mCurrentSelectedGroupPosition, mCurrentSelectedChildPosition);
        // Select either the default item (0) or the last selected item.
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Indicate that this fragment would like to influence the set of actions in the action bar.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDrawerExpandableListView = (ExpandableListView) inflater.inflate(
                R.layout.fragment_navigation_drawer, container, false);

        mDrawerExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                selectItem(groupPosition, childPosition);
                return false;
            }
        });

        mDrawerExpandableListView.setOnGroupClickListener(
                new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        if (groupPosition == 0 || groupPosition == 1 || groupPosition == 21 || groupPosition == 18 || groupPosition == 19 || groupPosition == 20 || groupPosition == 22||groupPosition==23) {
                            selectItem(groupPosition, 0);
                            mCurrentSelectedChildPosition = 0;
                            mDrawerExpandableListView.collapseGroup(lastExpanded);
                            lastExpanded = 0;
                            return true;
                        } else
                            return false;
                    }
                }
        );

        prepareListData();

        mDrawerExpandableListView.setAdapter(new GroupIconAdapter(getActionBar().getThemedContext(), _listdataHeader, _listDataChild));

        mDrawerExpandableListView.setItemChecked(mCurrentSelectedChildPosition, true);
        //mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);

        return mDrawerExpandableListView;
    }

    public void prepareListData() {
        _listdataHeader = new ArrayList<IconItem>();
        _listDataChild = new HashMap<IconItem, List<IconItem>>();

        //Initialising List of Array for Header Data

        _listdataHeader.add(new IconItem(R.drawable.home, getString(R.string.title_section1), ""));
        _listdataHeader.add(new IconItem(R.drawable.sciq, getString(R.string.title_section2), ""));
        _listdataHeader.add(new IconItem(R.drawable.arthashastra, getString(R.string.title_section3), "+"));
        _listdataHeader.add(new IconItem(R.drawable.circuithouse, getString(R.string.title_section4), "+"));
        _listdataHeader.add(new IconItem(R.drawable.kalpit, getString(R.string.title_section5), "+"));
        _listdataHeader.add(new IconItem(R.drawable.nogroundzone, getString(R.string.title_section6), "+"));
        _listdataHeader.add(new IconItem(R.drawable.parapher, getString(R.string.title_section7), "+"));
        _listdataHeader.add(new IconItem(R.drawable.pie, getString(R.string.title_section8), "+"));
        _listdataHeader.add(new IconItem(R.drawable.siliconvalley, getString(R.string.title_section9), "+"));
        _listdataHeader.add(new IconItem(R.drawable.rom, getString(R.string.title_section10), "+"));
        _listdataHeader.add(new IconItem(R.drawable.nscet, getString(R.string.title_section11), "+"));
        _listdataHeader.add(new IconItem(R.drawable.neodrishti, getString(R.string.title_section12), "+"));
        _listdataHeader.add(new IconItem(R.drawable.lecture, getString(R.string.title_section13), "+"));
        _listdataHeader.add(new IconItem(R.drawable.deusxmachina, getString(R.string.title_section14), "+"));
        _listdataHeader.add(new IconItem(R.drawable.avartan, getString(R.string.title_section15), "+"));
        _listdataHeader.add(new IconItem(R.drawable.armageddon, getString(R.string.title_section16), "+"));
        _listdataHeader.add(new IconItem(R.drawable.akriti, getString(R.string.title_section17), "+"));
        _listdataHeader.add(new IconItem(R.drawable.prayas, getString(R.string.title_section22), "+"));
        _listdataHeader.add(new IconItem(R.drawable.nscet,"Expocision", ""));
        _listdataHeader.add(new IconItem(R.drawable.events, getString(R.string.title_section18), ""));
        _listdataHeader.add(new IconItem(R.drawable.sponsor, getString(R.string.title_section19), ""));
        _listdataHeader.add(new IconItem(R.drawable.team, getString(R.string.title_section20), ""));
        _listdataHeader.add(new IconItem(R.drawable.developer, getString(R.string.title_section21), ""));
        _listdataHeader.add(new IconItem(R.drawable.youtube, "Youtube", ""));

        List<IconItem> armagaddon = new ArrayList<IconItem>();
        armagaddon.add(new IconItem(R.drawable.armageddon, "Feed", ""));
        armagaddon.add(new IconItem(R.drawable.armageddon, "Fifa", ""));
        armagaddon.add(new IconItem(R.drawable.armageddon, "CS", ""));
        armagaddon.add(new IconItem(R.drawable.armageddon, "NFS", ""));
        armagaddon.add(new IconItem(R.drawable.armageddon, "Angry Bird", ""));
        armagaddon.add(new IconItem(R.drawable.armageddon, "Dota", ""));
        armagaddon.add(new IconItem(R.drawable.armageddon, "Age Of Empires 2", ""));
        armagaddon.add(new IconItem(R.drawable.armageddon, "Pioneers", ""));

        List<IconItem> prayas = new ArrayList<IconItem>();
        prayas.add(new IconItem(R.drawable.prayas, "Feed", ""));
        prayas.add(new IconItem(R.drawable.prayas, "Jagriti", ""));
        prayas.add(new IconItem(R.drawable.prayas, "Samvedna", ""));
        prayas.add(new IconItem(R.drawable.prayas, "Pratibimb", ""));
        prayas.add(new IconItem(R.drawable.prayas, "Pioneers", ""));

        List<IconItem> paraph = new ArrayList<IconItem>();
        paraph.add(new IconItem(R.drawable.parapher, "Feed", ""));
        paraph.add(new IconItem(R.drawable.parapher, "The Great Ojass Race", ""));
        paraph.add(new IconItem(R.drawable.parapher, "Si-fi", ""));
        paraph.add(new IconItem(R.drawable.parapher, "Mad ad", ""));
        paraph.add(new IconItem(R.drawable.parapher, "Lens view", ""));
        paraph.add(new IconItem(R.drawable.parapher, "Director’s cut", ""));
        paraph.add(new IconItem(R.drawable.parapher, "Pioneers", ""));

        List<IconItem> klp = new ArrayList<IconItem>();
        klp.add(new IconItem(R.drawable.kalpit, "Feed", ""));
        klp.add(new IconItem(R.drawable.kalpit, "App-Droid", ""));
        klp.add(new IconItem(R.drawable.kalpit, "Codathon", ""));
        klp.add(new IconItem(R.drawable.kalpit, "Codemania", ""));
        klp.add(new IconItem(R.drawable.kalpit, "Code Your Bot", ""));
        klp.add(new IconItem(R.drawable.kalpit, "Decode", ""));
        klp.add(new IconItem(R.drawable.kalpit, "Tux", ""));
        klp.add(new IconItem(R.drawable.kalpit, "Web Vogue", ""));
        klp.add(new IconItem(R.drawable.kalpit, "Pioneers", ""));

        List<IconItem> rom = new ArrayList<IconItem>();
        rom.add(new IconItem(R.drawable.rom, "Feed", ""));
        rom.add(new IconItem(R.drawable.rom, "Auto Quiz", ""));
        rom.add(new IconItem(R.drawable.rom, "Accelderome", ""));
        rom.add(new IconItem(R.drawable.rom, "Moto Virtuale", ""));
        rom.add(new IconItem(R.drawable.rom, "Junkyards Wars", ""));
        rom.add(new IconItem(R.drawable.rom, "Samveg", ""));
        rom.add(new IconItem(R.drawable.rom, "Prakshepan", ""));
        rom.add(new IconItem(R.drawable.rom, "Enigma", ""));
        rom.add(new IconItem(R.drawable.rom, "Pioneers", ""));

        List<IconItem> circuit = new ArrayList<IconItem>();
        circuit.add(new IconItem(R.drawable.circuithouse, "Feed", ""));
        circuit.add(new IconItem(R.drawable.circuithouse, "HVC", ""));
        circuit.add(new IconItem(R.drawable.circuithouse, "Elixir of electricity", ""));
        circuit.add(new IconItem(R.drawable.circuithouse, "Pro-Lo-Co", ""));
        circuit.add(new IconItem(R.drawable.circuithouse, "Mat –sim", ""));
        circuit.add(new IconItem(R.drawable.circuithouse, "Nexus", ""));
        circuit.add(new IconItem(R.drawable.circuithouse, "Electro-Q", ""));
        circuit.add(new IconItem(R.drawable.circuithouse, "Embetrix", ""));
        circuit.add(new IconItem(R.drawable.circuithouse, "Pioneers", ""));

        List<IconItem> avar = new ArrayList<IconItem>();
        avar.add(new IconItem(R.drawable.avartan, "Feed", ""));
        avar.add(new IconItem(R.drawable.avartan, "Indus  Solva", ""));
        avar.add(new IconItem(R.drawable.avartan, "Agnikund", ""));
        avar.add(new IconItem(R.drawable.avartan, "Metal Trivia", ""));
        avar.add(new IconItem(R.drawable.avartan, "Chitrankit", ""));
        avar.add(new IconItem(R.drawable.avartan, "Funtoosh", ""));
        avar.add(new IconItem(R.drawable.avartan, "Pioneers", ""));

        List<IconItem> neodristi = new ArrayList<IconItem>();
        neodristi.add(new IconItem(R.drawable.neodrishti, "Feed", ""));
        neodristi.add(new IconItem(R.drawable.neodrishti, "Codiyapa", ""));
        neodristi.add(new IconItem(R.drawable.neodrishti, "Game_of_Troves", ""));
        neodristi.add(new IconItem(R.drawable.neodrishti, "Scratch Easy", ""));
        neodristi.add(new IconItem(R.drawable.neodrishti, "Simply SQL", ""));
        neodristi.add(new IconItem(R.drawable.neodrishti, "WEB 2.O", ""));
        neodristi.add(new IconItem(R.drawable.neodrishti, "Pioneers", ""));

        List<IconItem> piethrone = new ArrayList<IconItem>();
        piethrone.add(new IconItem(R.drawable.pie, "Feed", ""));
        piethrone.add(new IconItem(R.drawable.pie, "Industrial tycoon", ""));
        piethrone.add(new IconItem(R.drawable.pie, "Utpreksh", ""));
        piethrone.add(new IconItem(R.drawable.pie, "Artifact", ""));
        piethrone.add(new IconItem(R.drawable.pie, "Drongyan", ""));
        piethrone.add(new IconItem(R.drawable.pie, "Udyog", ""));
        piethrone.add(new IconItem(R.drawable.pie, "Pioneers", ""));

        List<IconItem> silicon = new ArrayList<IconItem>();
        silicon.add(new IconItem(R.drawable.siliconvalley, "Feed", ""));
        silicon.add(new IconItem(R.drawable.siliconvalley, "Tukvilla", ""));
        silicon.add(new IconItem(R.drawable.siliconvalley, "Jigyasa", ""));
        silicon.add(new IconItem(R.drawable.siliconvalley, "The pixel game", ""));
        silicon.add(new IconItem(R.drawable.siliconvalley, "Codesense", ""));
        silicon.add(new IconItem(R.drawable.siliconvalley, "Analog hunter", ""));
        silicon.add(new IconItem(R.drawable.siliconvalley, "Digizone", ""));
        silicon.add(new IconItem(R.drawable.siliconvalley, "Netkraft", ""));
        silicon.add(new IconItem(R.drawable.siliconvalley, "Embetrix", ""));
        silicon.add(new IconItem(R.drawable.siliconvalley, "Pioneers", ""));

        List<IconItem> ngz = new ArrayList<IconItem>();
        ngz.add(new IconItem(R.drawable.nogroundzone, "Feed", ""));
        ngz.add(new IconItem(R.drawable.nogroundzone, "RC Plane", ""));
        ngz.add(new IconItem(R.drawable.nogroundzone, "MICAV", ""));
        ngz.add(new IconItem(R.drawable.nogroundzone, "Pioneers", ""));

        List<IconItem> nscet = new ArrayList<IconItem>();
        nscet.add(new IconItem(R.drawable.nscet, "Feed", ""));
        nscet.add(new IconItem(R.drawable.nscet, "NSCET", ""));
        nscet.add(new IconItem(R.drawable.nscet, "Pioneers", ""));

        List<IconItem> deus = new ArrayList<IconItem>();
        deus.add(new IconItem(R.drawable.deusxmachina, "Feed", ""));
        deus.add(new IconItem(R.drawable.deusxmachina, "360 Mania", ""));
        deus.add(new IconItem(R.drawable.deusxmachina, "Vic-Toy-Rie", ""));
        deus.add(new IconItem(R.drawable.deusxmachina, "Battleship", ""));
        deus.add(new IconItem(R.drawable.deusxmachina, "Kurukshetra", ""));
        deus.add(new IconItem(R.drawable.deusxmachina, "Robo Soccer", ""));
        deus.add(new IconItem(R.drawable.deusxmachina, "Shape Shifter", ""));
        deus.add(new IconItem(R.drawable.deusxmachina, "Pioneers", ""));

        List<IconItem> arth = new ArrayList<IconItem>();
        arth.add(new IconItem(R.drawable.arthashastra, "Feed", ""));
        arth.add(new IconItem(R.drawable.arthashastra, "ABC", ""));
        arth.add(new IconItem(R.drawable.arthashastra, "Neetishashtra", ""));
        arth.add(new IconItem(R.drawable.arthashastra, "Let\'s startup", ""));
        arth.add(new IconItem(R.drawable.arthashastra, "Corporate Roadies", ""));
        arth.add(new IconItem(R.drawable.arthashastra, "Bizzathlon 3.0", ""));
        arth.add(new IconItem(R.drawable.arthashastra, "Wolf of Dalal Street", ""));
        arth.add(new IconItem(R.drawable.arthashastra, "Pioneers", ""));

        List<IconItem> guru = new ArrayList<IconItem>();
        guru.add(new IconItem(R.drawable.gurugyan, "Feed", ""));
        guru.add(new IconItem(R.drawable.gurugyan, "Guest Lectures", ""));

        List<IconItem> expo = new ArrayList<IconItem>();
        expo.add(new IconItem(R.drawable.nscet, "Feed", ""));

        List<IconItem> akriti = new ArrayList<IconItem>();
        akriti.add(new IconItem(R.drawable.akriti, "Feed", ""));
        akriti.add(new IconItem(R.drawable.akriti, "Acumen", ""));
        akriti.add(new IconItem(R.drawable.akriti, "Sanrachna", ""));
        akriti.add(new IconItem(R.drawable.akriti, "Archmadeease", ""));
        akriti.add(new IconItem(R.drawable.akriti, "Exemplar", ""));
        akriti.add(new IconItem(R.drawable.akriti, "Pipeomania", ""));
        akriti.add(new IconItem(R.drawable.akriti, "Metropolis", ""));
        akriti.add(new IconItem(R.drawable.akriti, "Pioneers", ""));

        List<IconItem> streaming = new ArrayList<IconItem>();
        streaming.add(new IconItem(R.drawable.akriti, "youtube", ""));
        List<IconItem> devs = new ArrayList<IconItem>();
        devs.add(new IconItem(R.drawable.akriti, "list", ""));

        //Initialising Child Item
        _listDataChild.put(_listdataHeader.get(0), new ArrayList<IconItem>());
        _listDataChild.put(_listdataHeader.get(1), new ArrayList<IconItem>());
        _listDataChild.put(_listdataHeader.get(2), arth);
        _listDataChild.put(_listdataHeader.get(3), circuit);
        _listDataChild.put(_listdataHeader.get(4), klp);
        _listDataChild.put(_listdataHeader.get(5), ngz);
        _listDataChild.put(_listdataHeader.get(6), paraph);
        _listDataChild.put(_listdataHeader.get(7), piethrone);
        _listDataChild.put(_listdataHeader.get(8), silicon);
        _listDataChild.put(_listdataHeader.get(9), rom);
        _listDataChild.put(_listdataHeader.get(10), nscet);
        _listDataChild.put(_listdataHeader.get(11), neodristi);
        _listDataChild.put(_listdataHeader.get(12), guru);
        _listDataChild.put(_listdataHeader.get(13), deus);
        _listDataChild.put(_listdataHeader.get(14), avar);
        _listDataChild.put(_listdataHeader.get(15), armagaddon);
        _listDataChild.put(_listdataHeader.get(16), akriti);
        _listDataChild.put(_listdataHeader.get(17), prayas);
        _listDataChild.put(_listdataHeader.get(18), expo);
        _listDataChild.put(_listdataHeader.get(19), new ArrayList<IconItem>());
        _listDataChild.put(_listdataHeader.get(20), new ArrayList<IconItem>());
        _listDataChild.put(_listdataHeader.get(21), new ArrayList<IconItem>());
        _listDataChild.put(_listdataHeader.get(22), new ArrayList<IconItem>());
        _listDataChild.put(_listdataHeader.get(23), streaming);

    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener


        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ojass16_logo_chota);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the navigation drawer and the action bar app icon.
        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),                    /* host Activity */
                mDrawerLayout,                    /* DrawerLayout object */
                R.drawable.ojass16_logo_chota,             /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description
                 for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }

                if (!mUserLearnedDrawer) {
                    // The user manually opened the drawer; store this flag to prevent auto-showing
                    // the navigation drawer automatically in the future.
                    mUserLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(getActivity());
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
                }

                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

        // If the user hasn't 'learned' about the drawer, open it to introduce them to the drawer,
        // per the navigation drawer design guidelines.
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }

        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }


    private void selectItem(int groupPosition, int childPosition) {
        mCurrentSelectedGroupPosition = groupPosition;
        mCurrentSelectedChildPosition = childPosition;
        if (mDrawerExpandableListView != null) {
            mDrawerExpandableListView.setItemChecked(childPosition, true);
        }
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(groupPosition, childPosition);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(GROUP_STATE_SELECTED_POSITION, mCurrentSelectedGroupPosition);
        outState.putInt(CHILD_STATE_SELECTED_POSITION, mCurrentSelectedChildPosition);
        outState.putInt(EXPANDED_CONFIG_CHANGE, lastExpanded);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // If the drawer is open, show the global app actions in the action bar. See also
        // showGlobalContextActionBar, which controls the top-left area of the action bar.
        if (mDrawerLayout != null && isDrawerOpen()) {
            inflater.inflate(R.menu.global, menu);
            showGlobalContextActionBar();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Per the navigation drawer design guidelines, updates the action bar to show the global app
     * 'context', rather than just what's in the current screen.
     */
    private void showGlobalContextActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle(R.string.app_name);
        actionBar.setHomeAsUpIndicator(R.drawable.ojass16_logo_chota);
    }


    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawers();
    }

    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public static interface NavigationDrawerCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onNavigationDrawerItemSelected(int groupPosition, int childPosition);
    }

    public class IconItem {
        int icon_id;
        String text;
        String sym;

        public IconItem(int icon_id, String text, String sym) {
            this.icon_id = icon_id;
            this.text = text;
            this.sym = sym;
        }
    }

    public class GroupIconAdapter extends BaseExpandableListAdapter {

        private Context _context;
        private List<IconItem> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<IconItem, List<IconItem>> _listDataChild;


        public GroupIconAdapter(Context context, List<IconItem> _listDataHeader, HashMap<IconItem, List<IconItem>> _listDataChild) {
            this._context = context;
            this._listDataHeader = _listDataHeader;
            this._listDataChild = _listDataChild;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(
                    this._listDataHeader.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return this._listDataHeader.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }


        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return this._listDataChild.get(
                    this._listDataHeader.get(groupPosition)).get(childPosition);
        }


        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {

                LayoutInflater vi;
                vi = LayoutInflater.from(getActivity());
                v = vi.inflate(R.layout.fragment_activity2, null);

            }

            IconItem item = (IconItem) getGroup(groupPosition);

            Picasso.with(getActivity()).load(item.icon_id).fit().centerCrop().
                    into(((ImageView) v.findViewById(R.id.icon)));

            ((TextView) v.findViewById(R.id.section_label)).setText(item.text);
            //((ImageView)v.findViewById(R.id.icon)).setImageResource(item.icon_id);

            TextView exp = ((TextView) v.findViewById(R.id.expand));


            exp.setText(item.sym);

            if (isExpanded && exp.getText().toString() == "+") {
                exp.setText("-");
            } else if (!(isExpanded) && exp.getText() == "-") {
                exp.setText("+");
            }

            return v;
        }


        @Override
        public void onGroupExpanded(int groupPosition) {
            if (groupPosition != lastExpanded)
                mDrawerExpandableListView.collapseGroup(lastExpanded);
            super.onGroupExpanded(groupPosition);
            lastExpanded = groupPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {

                LayoutInflater vi;
                vi = LayoutInflater.from(getActivity());
                v = vi.inflate(R.layout.sub_menu_item_layout, null);

            }

            IconItem item = (IconItem) getChild(groupPosition, childPosition);

            Picasso.with(getActivity()).load(item.icon_id).fit().centerCrop().
                    into(((ImageView) v.findViewById(R.id.sub_icon)));

            ((TextView) v.findViewById(R.id.sub_section_label)).setText(item.text);
            //((TextView) v.findViewById(R.id.sub_counter)).setText(99 + "");

            return v;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
