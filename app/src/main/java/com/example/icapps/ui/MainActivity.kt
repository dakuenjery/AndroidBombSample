package com.example.AndroidBombSample.ui

//import com.example.AndroidBombSample.databinding.ActivityMainBinding
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.AndroidBombSample.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_navigation.*
import org.koin.android.architecture.ext.viewModel

class MainViewModel : ViewModel() {

}


class MainActivity : AppCompatActivity(), BottomSheet.Listener {

    private val viewModel = viewModel<MainViewModel>()
//    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(bottom_app_bar as Toolbar)

//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        binding.setLifecycleOwner(this)
//        binding.bottomAppBar.replaceMenu(R.menu.bottom_nav_drawer_menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> BottomSheet().show(supportFragmentManager, null)
        }

        return true
    }

    // bottom-sheet menu
    override fun onItemClicked(item: MenuItem) {
        val frag = when (item.itemId) {
            R.id.navigation_games -> GamesFragmentStarter.newInstance(10)
            R.id.navigation_companies -> CompaniesFragmentStarter.newInstance(10)
            R.id.navigation_settings -> PrefFragment()
            else -> throw Exception()
        }

        replaceFragment(frag)

        Toast.makeText(this, "Clicked: ${item.itemId}", Toast.LENGTH_SHORT).show()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit()
    }
}

class BottomSheet : BottomSheetDialogFragment() {
    private var listener: Listener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_navigation, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = (parentFragment as? Listener) ?: context as? Listener
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        navigation_view.setNavigationItemSelectedListener {
            listener?.onItemClicked(it)
            dismiss()
            true
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    interface Listener {
        fun onItemClicked(item: MenuItem)
    }
}