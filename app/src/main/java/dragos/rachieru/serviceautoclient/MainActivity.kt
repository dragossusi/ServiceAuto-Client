package dragos.rachieru.serviceautoclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_save.setOnClickListener(this)
        presenter = MainPresenter(this)
    }

    override fun onClick(p0: View?) {
        val name = edit_name.text.toString()
        val phone = edit_phone.text.toString()
        val issue = edit_issue.text.toString()
        val car = edit_car.text.toString()
        val model = edit_model.text.toString()
        if (name.isEmpty() || phone.isEmpty() || issue.isEmpty() || car.isEmpty() || model.isEmpty())
            Toast.makeText(this, R.string.complete_all_fields, Toast.LENGTH_SHORT).show()
        else
            presenter.createRequest(name, phone, issue, car, model)
    }

    fun onError(e: Throwable) {
        e.printStackTrace()
        Toast.makeText(this, e.message ?: "An error occured", Toast.LENGTH_SHORT).show()
    }

    fun onSent() {
        Toast.makeText(this, R.string.request_sent, Toast.LENGTH_SHORT).show()
    }
}
