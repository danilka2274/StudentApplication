package ru.geekbrains.studentapplication.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import ru.geekbrains.studentapplication.R
import ru.geekbrains.studentapplication.ui.base.BaseActivity

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(viewBinding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_classes, R.id.navigation_favourite
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        this.supportActionBar?.hide()

        initClickListener()
    }
}