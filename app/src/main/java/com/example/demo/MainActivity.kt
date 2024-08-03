package com.example.demo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.demo.view.FragmentOne
import com.example.demo.view.FragmentTwo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState==null){
            val bundle= bundleOf("someInt" to 5)
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<FragmentOne>(R.id.fragment_container_view, args = bundle)
            }
        }
        val addButton: Button = findViewById(R.id.add_button);
        addButton.setOnClickListener{
            addToBackStack();
        }

        val removeButton: Button =findViewById(R.id.remove_button)
        removeButton.setOnClickListener{
            popBackStacklayers();
        }
        /*
         * Fragment Result API communication between Fragment B & Host Activity(MainActivity)
         */
        supportFragmentManager.setFragmentResultListener("requestKey",this){requestKey,  bundle ->
            val result = bundle?.getString("bundleKey")?:"Communication with host failed"
            Log.i("LifeCycleHost-MainActivity Result From Fragment B - ",result)
        }


    }
    fun addToBackStack(){
        supportFragmentManager.commit {
            replace<FragmentTwo>(R.id.fragment_container_view)
            setReorderingAllowed(true)
            addToBackStack("secondFragment")
        }
    }


    fun popBackStacklayers(){
        supportFragmentManager.popBackStack()
    }


}