package `in`.bk.lifecyclescope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import `in`.bk.lifecyclescope.ui.main.MainFragment
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()

//            lifecycleScope.launch {
//                delay(5000)
//                progressBar.visibility = View.VISIBLE
//                delay(10000)
//                progressBar.visibility = View.GONE
//            }

            lifecycleScope.launch(IO) {
                Log.i("TAG", "Thread is :${Thread.currentThread().name} ")
            }
        }
    }
}