package dev.abdujabbor.homenazorat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import dev.abdujabbor.homenazorat.adapters.RvAdapter
import dev.abdujabbor.homenazorat.adapters.RvClick
import dev.abdujabbor.homenazorat.databinding.ActivityMainBinding
import dev.abdujabbor.homenazorat.databinding.AdddialoglayourBinding
import dev.abdujabbor.homenazorat.db.MyDbHelper
import dev.abdujabbor.homenazorat.models.MyData
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() ,RvClick{
    lateinit var dbHelper: MyDbHelper
    lateinit var list:ArrayList<MyData>
    lateinit var rvAdapter: RvAdapter
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        dbHelper = MyDbHelper(binding.root.context)
        rvAdapter = RvAdapter(dbHelper.readNote() as ArrayList<MyData>,this)
        binding.recycleView.adapter = rvAdapter
        binding.floatingacitonbutton.setOnClickListener {
            addContactdd()
        }
        binding.recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // if the recycler view is scrolled
                // above hide the FAB
                if (dy > 10 && binding.floatingacitonbutton.isShown) {
                    binding.floatingacitonbutton.hide()
                }

                // if the recycler view is
                // scrolled above show the FAB
                if (dy < -10 && !binding.floatingacitonbutton.isShown) {
                    binding.floatingacitonbutton.show()
                }

                // of the recycler view is at the first
                // item always show the FAB
                if (!recyclerView.canScrollVertically(-1)) {
                    binding.floatingacitonbutton.show()
                }
            }
        })
    }

    private fun addContactdd() {
        val alertDialogLayoutBinding = AdddialoglayourBinding.inflate(layoutInflater)
        val dialog: AlertDialog = AlertDialog.Builder(binding.root.context, R.style.MyMenuDialogTheme)
            .setView(alertDialogLayoutBinding.root)
            .setPositiveButton("OK", null)
            .setNegativeButton("Cancel", null)
            .setCancelable(true)
            .create()
        dialog.setOnShowListener {
            val button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)

            button.setOnClickListener {
                val name = alertDialogLayoutBinding.edtName.text.toString().trim()
                if (name.isEmpty()) {
                    alertDialogLayoutBinding.edtName.error = "invalid name"
                }

              else {
                    if (name.isNotEmpty()) {
                        dialog.cancel()
                        val user = MyData(
                            name,
                            SimpleDateFormat("dd.MM.yyyy  HH:mm:ss").format(Date())
                        )
                        dbHelper.addNote(
                            user, binding.root.context
                        )
                    }
                    rvAdapter.notifyDataSetChanged()
                }


            }
        }
        dialog.show()
    }

    override fun click(moview: MyData, position: Int) {

    }
}