import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentsdemo.BlueFragment
import com.example.fragmentsdemo.R
import com.examples.coding.fragmentsdemo.OnFragmentInteractionListener
import com.examples.coding.fragmentsdemo.RedFragment


class MainActivity : AppCompatActivity(), OnFragmentInteractionListener,
    BlueFragment.OnFragmentInteractionListener,
    GreenFragment.OnFragmentInteractionListener{
    val redFragment : RedFragment by lazy { supportFragmentManager.findFragmentById(R.id.fgRedFragment) as RedFragment }
    val blueFragment by lazy{ BlueFragment() }
    val greenFragment by lazy{GreenFragment.newInstance(blueValue, redValue)}
    lateinit var redValue : String
    lateinit var blueValue : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        redFragment.setFragListener(this)

        //To Add fragment Dynamically
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frmFragmentDisplayOne, blueFragment)
            .addToBackStack("BLUE_FRAG")
            .commit()
    }

    override fun dataFromRedFragment(value: String) {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show()
        redValue = value
        displayAllValues()
    }

    override fun onFragmentInteraction(string : String) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show()
        blueValue = string
        displayAllValues()
    }

    fun displayAllValues() {
        if(this::redValue.isInitialized && this::blueValue.isInitialized) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.frmFragmentDisplayTwo, greenFragment)
                .addToBackStack("GREEN_FRAG")
                .commit()
        }
    }

    override fun onFragmentInteraction() {
    }


}