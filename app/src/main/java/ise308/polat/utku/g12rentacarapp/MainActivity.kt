package ise308.polat.utku.g12rentacarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import ise308.polat.utku.g12rentacarapp.ui.ContactFragment
import java.lang.Exception

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private var jsonSerializer: JSONSerializer? = null
    private lateinit var carList: ArrayList<Cars>
    private var recyclerViewCars: RecyclerView? = null
    private var carsAdapter: CarsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)

        val navigationView = findViewById<NavigationView>(R.id.rc_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.rc_drawer_open,
            R.string.rc_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        jsonSerializer = JSONSerializer("RentACar", applicationContext)

        try {
            carList = jsonSerializer!!.load()
        } catch (e: Exception) {
            carList = ArrayList()
        }

        initializeCars()
        val fabNewCar = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fabNewCar.setOnClickListener {
            val newCarDialog = NewCarDialog()
            newCarDialog.show(supportFragmentManager, "123")
        }

        recyclerViewCars = findViewById<RecyclerView>(R.id.recyclerViewCars) as RecyclerView
        carsAdapter = CarsAdapter(carList, this)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerViewCars!!.layoutManager = layoutManager
        recyclerViewCars!!.itemAnimator = DefaultItemAnimator()
        recyclerViewCars!!.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerViewCars!!.adapter = carsAdapter

    }

    private fun initializeCars() {
        carList = ArrayList<Cars>()
        carList!!.add(Cars("Opel Astra", "Hatchback", 2014, 150.0, true, "34 DD 2991"))
        carList!!.add(Cars("Ford Focus", "Sedan", 2010, 120.0, true, "06 AD 1267"))
        carList!!.add(Cars("Mercedes GLC", "SUV", 2020, 500.0, true, "38 UD 6958"))
        carList!!.add(Cars("Volkswagen Polo", "Hatchback", 2016, 90.0, true, "32 ZEY 1997"))
        carList!!.add(Cars("Audi A5", "Coupe", 2015, 250.0, false, "06 MET 235"))
    }

    fun createNewCar(newCar: Cars) {
        carList?.add(newCar)

    }

    private fun saveCars() {
        try {
            jsonSerializer!!.save(this.carList!!)
        } catch (e: Exception) {
            //Log.e(TAG, "error loading notes :((")
        }
    }

    override fun onPause() {
        super.onPause()
        saveCars()
    }

    fun showNote(adapterPosition: Int) {
        val showCar = DialogShowCars()
        showCar.setCars(carList.get(adapterPosition))
        showCar.show(supportFragmentManager, "124")


    }

    //burada menü kısmında geri tuşuna basınca uygulamadan çıkmamasını sağlıyor.
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            /*R.id.rc_cars -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainActivity()).commit()*/
            R.id.rc_contact -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ContactFragment()).commit()

        }
        drawerLayout.closeDrawer((GravityCompat.START))
        return true
    }
}