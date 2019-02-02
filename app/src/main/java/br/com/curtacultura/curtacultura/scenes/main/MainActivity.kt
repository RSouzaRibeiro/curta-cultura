package br.com.curtacultura.curtacultura.scenes.main


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import br.com.curtacultura.curtacultura.R
import br.com.curtacultura.curtacultura.helpers.AuthHelper
import br.com.curtacultura.curtacultura.model.CultureCenter
import br.com.curtacultura.curtacultura.model.User
import br.com.curtacultura.curtacultura.scenes.login.LoginActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), MainInterface.View, NavigationView.OnNavigationItemSelectedListener {





    private var presenter = MainPresenter(this)
    private var user: User? = null

    companion object {
        private var TAG = "TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)



        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        verifyUser()
        //initView()
        presenter.getCentrosCulturais()

        initAdmob()
    }

    private fun initAdmob() {
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        var item = menu?.findItem(R.id.menuSearch)
        var searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if(!p0.isNullOrBlank()){
                    presenter.searchCultureCenter(p0)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if(!p0.isNullOrBlank()){
                    presenter.searchCultureCenter(p0)
                }
                return false
            }

        })
        searchView.setOnCloseListener(object: SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                presenter.getCentrosCulturais()
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {

            R.id.nav_send -> {
                logout()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun logout(){
        AuthHelper().setAuthToken(null)
        this.finish()
        goToLogin()
    }

    private fun verifyUser() {
        if ( AuthHelper().getAuthToken() == null) {
            goToLogin()
        }
    }
/*
    private fun initView() {
        val user = mAuth.currentUser
        if (user != null) {
            nav_view.getHeaderView(0).nomeTXT.text = user.displayName
            nav_view.getHeaderView(0).emailTXT.text = user.email

        }
    }*/

    private fun goToLogin() {
        var loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
        finish()
    }



    override fun emitErrorSnake(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun getCentrosCulturaisSuccess(result: ArrayList<CultureCenter>) {
        initRecycleView(result)
    }

    private fun initRecycleView(result: ArrayList<CultureCenter>){
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MainAdapter(this, result)
    }
}
