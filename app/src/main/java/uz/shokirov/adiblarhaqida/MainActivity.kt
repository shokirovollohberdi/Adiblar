package uz.shokirov.adiblarhaqida

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }

  /*  override fun onNavigateUp(): Boolean {
        return Navigation.findNavController(this,R.id.my_navigation_host).navigateUp()
    }*/
}