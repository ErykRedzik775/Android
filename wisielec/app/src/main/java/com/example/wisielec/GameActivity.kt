package com.example.wisielec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text
import java.lang.StringBuilder

class GameActivity : AppCompatActivity() {
    private lateinit var wordTextView: TextView
    private lateinit var startNewGameButton: FloatingActionButton
    private lateinit var stringArray: Array<String>
    private lateinit var wordToGuess: String
    private lateinit var wordToGuessTmp: String
    var lvl: String = ""

    private  var currentTries: Int = 0
    private var maxTries: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        wordTextView = findViewById(R.id.textView2)
        stringArray = resources.getStringArray(R.array.words)
        startNewGameButton = findViewById(R.id.startNewGameButton)
        startNewGameButton.setOnClickListener {
            startNewGame()
        }
        CheckLetter()

    }
    private fun getRandomWord():String{
            val number = (0..stringArray.size).random()

            return stringArray[number]

    }
    private fun startNewGame(){
            //wordToGuess = getRandomWord()
            wordToGuess = "ass"
            wordTextView.text = wordToGuess
            currentTries = 0;
            maxTries = wordTextView.text.length
            wordTextView.setText("*".repeat((wordTextView.text).length))
            updateImg(0)
    }
    private fun CheckLetter(){
        var introducedLetter = findViewById<TextView>(R.id.editTextTextPersonName).text
        var isInTheWord: Boolean = false
        val checkLetterButton: Button = findViewById(R.id.checkLetterButton)
        checkLetterButton.setOnClickListener{
                for(i in 0..(wordToGuess.length-1)){
                    if(wordToGuess[i] == introducedLetter.toString()[0]){
                        updateWord(introducedLetter.toString()[0],i)
                        isInTheWord = true
                    }

                }
            if(isInTheWord == false) {
                currentTries++
                updateImg(currentTries)
                Log.i("current tries= ", currentTries.toString())
            }
            if(currentTries == maxTries)
                GameLost()

        }
    }

    private fun HangmanDrawable(value: Int): Int{
        return when(value){
            0 -> R.drawable.hangman0
            1 -> R.drawable.hangman1
            2 -> R.drawable.hangman2
            3 -> R.drawable.hangman3
            4 -> R.drawable.hangman4
            5 -> R.drawable.hangman5
            6 -> R.drawable.hangman6
            7 -> R.drawable.hangman7
            8 -> R.drawable.hangman8
            9 -> R.drawable.hangman9
            10 -> R.drawable.hangman10

            else -> R.drawable.hangman10
        }
    }
    private fun updateImg(value: Int){
        val hangmanImg: ImageView = findViewById(R.id.imageView)
        hangmanImg.setImageResource(HangmanDrawable(value))
    }

    private fun GameLost(){
        wordTextView.text = wordToGuess
        updateImg(10)
        val snack = Snackbar.make(findViewById(R.id.mainContainer),"You lost", Snackbar.LENGTH_LONG).show()
    }

    private fun GameWon(){
        updateImg(0)
        val snack = Snackbar.make(findViewById(R.id.mainContainer),"You won", Snackbar.LENGTH_LONG).show()
    }

    private fun checkWord(word: String){
        var tmp: Int = 0
        val checkWordButton: Button = findViewById(R.id.checkWordButton)
        checkWordButton.setOnClickListener{

                if(word == wordToGuess ) {
                    Log.i("xddd", tmp.toString())
                }
        }

    }

    private fun gameInProgres(){
        var introducedLetter = findViewById<TextView>(R.id.editTextTextPersonName).text
        checkWord(introducedLetter.toString())

    }

    private fun updateWord(letter: Char, index: Int){
        val tmp = StringBuilder(wordTextView.text)

        if(wordTextView.text == wordToGuess)
            GameWon()
        else
        tmp.setCharAt(index,letter)
        wordTextView.text = tmp.toString()

    }
}