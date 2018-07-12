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
import android.widget.Toast
import br.com.curtacultura.curtacultura.R
import br.com.curtacultura.curtacultura.core.App
import br.com.curtacultura.curtacultura.model.Area
import br.com.curtacultura.curtacultura.model.Previsao
import br.com.curtacultura.curtacultura.model.User
import br.com.curtacultura.curtacultura.persistence.dao.UserDAO
import br.com.curtacultura.curtacultura.scenes.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.QuerySnapshot
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*


class MainActivity : AppCompatActivity(), MainInterface.View, NavigationView.OnNavigationItemSelectedListener {



    private var mAuthListener: FirebaseAuth.AuthStateListener? = null
    val mAuth = FirebaseAuth.getInstance()
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
        initView()
        presenter.getCentrosCulturais()
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
        mAuth.signOut()
        this.finish()
        goToLogin()
    }

    private fun verifyUser() {
        if (mAuth.currentUser == null) {
            goToLogin()
        }
    }

    private fun initView() {
        val user = mAuth.currentUser
        if (user != null) {
            nav_view.getHeaderView(0).nomeTXT.text = user.displayName
            nav_view.getHeaderView(0).emailTXT.text = user.email

        }
    }

    private fun goToLogin() {
        var loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
        finish()
    }



    override fun emitErrorSnake(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun getCentrosCulturaisSuccess(result: QuerySnapshot) {
        initRecycleView(result)
    }

    private fun initRecycleView(result: QuerySnapshot){
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MainAdapter(this, result)
    }
}
