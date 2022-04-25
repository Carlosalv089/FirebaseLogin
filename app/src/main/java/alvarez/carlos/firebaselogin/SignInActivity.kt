package alvarez.carlos.firebaselogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import alvarez.carlos.firebaselogin.databinding.ActivitySignInBinding
import android.content.Intent
import android.util.Log
import android.widget.Toast


class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.signInAppCompatButton.setOnClickListener{
            val mEmail = binding.emailEditText.text.toString()
            val mPassword = binding.passwordEditText.text.toString()

            when{
                mEmail.isEmpty() || mPassword.isEmpty()->{
                    Toast.makeText(baseContext, "Email o contraseña incorrecta.", Toast.LENGTH_SHORT).show()
                } else ->{
                    SignIn(mEmail, mPassword)
                }
            }
        }

    }

    private fun SignIn(email:String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success")
                    reaload()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }

    private fun reaload(){
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }
}