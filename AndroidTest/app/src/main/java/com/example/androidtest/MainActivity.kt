package com.example.androidtest

import android.app.Notification
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    private lateinit var registrationLayout: LinearLayout
    private lateinit var loginLayout: LinearLayout
    private lateinit var homeLayout: LinearLayout
    private lateinit var welcomeLayout: LinearLayout
    private lateinit var saveBtn: Button
    private lateinit var loginBtn: Button
    private lateinit var signUpBtn: Button
    private lateinit var backBtn1: Button
    private lateinit var backBtn2: Button
    private lateinit var shutdownBtn: ImageButton
    private lateinit var logIN: Button
    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var contact: EditText
    private lateinit var loginEmail: EditText
    private lateinit var loginPassword: EditText
    private lateinit var welcomeName: TextView
    private lateinit var welcomeEmail: TextView
    private lateinit var welcomeContact: TextView

    lateinit var handler: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registrationLayout = findViewById(R.id.registration_layout)
        loginLayout = findViewById(R.id.login_layout)
        homeLayout = findViewById(R.id.home)
        welcomeLayout = findViewById(R.id.welcome_layout)
        saveBtn = findViewById(R.id.save)
        loginBtn = findViewById(R.id.login_btn)
        signUpBtn = findViewById(R.id.signUp_btn)
        backBtn1 = findViewById(R.id.back1)
        backBtn2 = findViewById(R.id.back2)
        shutdownBtn = findViewById(R.id.shutdownBtn)
        logIN = findViewById(R.id.login)

        name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        contact = findViewById(R.id.contact)


        loginEmail = findViewById(R.id.login_email)
        loginPassword = findViewById(R.id.login_password)

        welcomeName = findViewById(R.id.welcomeName)
        welcomeEmail = findViewById(R.id.welcomeEmail)
        welcomeContact = findViewById(R.id.welcomeContact)


        handler = DatabaseHelper(this)

        val PASSWORD_REGEX = """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#${'$'}%!\-_?&])(?=\S+${'$'}).{6,}""".toRegex()



        showHome()

        signUpBtn.setOnClickListener {
            showRegistration()
        }

        loginBtn.setOnClickListener {
            showLogin()
        }

        saveBtn.setOnClickListener {

            var count = 0
            var myNewInt: Int = contact.text.toString().toInt()
            while (myNewInt != 0) {
                myNewInt /= 10
                ++count
            }
            if (name.text.trim().isNotEmpty() && email.text.trim()
                    .isNotEmpty() && email.text.contains("@") &&
                password.text.trim().isNotEmpty() && contact.text.trim().isNotEmpty())
            {
                if (count == 10) {
                    if (PASSWORD_REGEX.matches(password.text.toString())) {
                        handler.insertData(
                            name.text.toString(),
                            email.text.toString(),
                            password.text.toString(),
                            contact.text.toString().toInt())
                        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                        showHome()

                    }
                    else
                    {
                        Toast.makeText(this,"Password does not have the correct criteria", Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    Toast.makeText(this, "Contact number is not a valid number", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(this, "You missed fill a blank or invalid email", Toast.LENGTH_SHORT).show()
            }
        }
        logIN.setOnClickListener {
            if (loginEmail.text.trim().isNotEmpty() && loginPassword.text.trim().isNotEmpty()) {

                if (handler.presentData(loginEmail.text.toString(), loginPassword.text.toString()))
                {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    showWelcome()

                    //welcomeName.text = ""
                    //welcomeEmail.text = ""
                    //welcomeContact.text = ""
                    //val cursor = handler.getAllInfo()
                    //cursor!!.moveToFirst()
                    //welcomeName.append((cursor.getString(cursor.getColumnIndex(Database.COLUMN_NAME))))
                    //welcomeEmail.append((cursor.getString(cursor.getColumnIndex(Database.COLUMN_EMAIL))))
                    //welcomeContact.append((cursor.getString(cursor.getColumnIndex(Database.COLUMN_CONTACT))))
                    //while (cursor.moveToNext()) {
                    //    welcomeName.append((cursor.getString(cursor.getColumnIndex(Database.COLUMN_NAME))))
                    //    welcomeName.append("\n")
                    //    welcomeEmail.append((cursor.getString(cursor.getColumnIndex(Database.COLUMN_EMAIL))))
                    //    welcomeEmail.append("\n")
                    //    welcomeContact.append((cursor.getString(cursor.getColumnIndex(Database.COLUMN_CONTACT))))
                    //    welcomeContact.append("\n")
                    //    break }
                    //cursor.close()


                    shutdownBtn.setOnClickListener {
                        showHome()
                    }
                } else
                    Toast.makeText(this, "Username or password are incorrect", Toast.LENGTH_SHORT)
                        .show()
            }
            else
            {
                Toast.makeText(this,"Something is in blank", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun showRegistration(): Boolean {
        registrationLayout.visibility = View.VISIBLE
        loginLayout.visibility = View.GONE
        homeLayout.visibility = View.GONE
        welcomeLayout.visibility = View.GONE
        backBtn1.setOnClickListener {
            showHome()
        }
        return true
    }
    private fun showLogin(): Boolean {
        registrationLayout.visibility = View.GONE
        loginLayout.visibility = View.VISIBLE
        homeLayout.visibility = View.GONE
        welcomeLayout.visibility = View.GONE
        backBtn2.setOnClickListener {
            showHome()
        }
        return true
    }
    private fun showHome(): Boolean {
        registrationLayout.visibility = View.GONE
        loginLayout.visibility = View.GONE
        homeLayout.visibility = View.VISIBLE
        welcomeLayout.visibility = View.GONE
        return true
    }
    private fun showWelcome(): Boolean {
        registrationLayout.visibility = View.GONE
        loginLayout.visibility = View.GONE
        homeLayout.visibility = View.GONE
        welcomeLayout.visibility = View.VISIBLE
        return true
    }



}

