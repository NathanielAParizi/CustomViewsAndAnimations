import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasedemo.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance()
    var currentUser : FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        currentUser = mAuth.currentUser
        updateUI()
    }

    fun onClick(view: View) {
        when(view.id) {
            R.id.loginBtn -> logInUser()
            R.id.LogOutBtn -> logOutUser()
            R.id.signupUserBtn -> signUpUser()
        }
    }

    private fun updateUI() {
        if(currentUser != null) {
            txtCurrentUser.text = currentUser?.email?: "NO USER LOGGED IN"
        }

    }

    private fun logInUser(){
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) { // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success")
                    currentUser = mAuth.currentUser
                    updateUI()
                } else { // If sign in fails, display a message to the user.
                    Log.w(
                        "TAG",
                        "signInWithEmail:failure",
                        task.exception
                    )
                    Toast.makeText(
                        this@MainActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI()
                }
            }
    }
    private fun logOutUser(){
        mAuth.signOut()
        currentUser = mAuth.currentUser
        updateUI()
    }
    private fun signUpUser(){
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) { // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    currentUser = mAuth.currentUser
                    updateUI()
                } else { // If sign in fails, display a message to the user.
                    Log.w(
                        "TAG",
                        "createUserWithEmail:failure",
                        task.exception
                    )
                    Toast.makeText(
                        this@MainActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI()
                }

            }
    }
}