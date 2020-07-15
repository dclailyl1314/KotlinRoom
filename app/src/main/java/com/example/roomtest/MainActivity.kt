package com.example.roomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.myapplication.room.Word
import com.example.myapplication.room.WordDao
import com.example.myapplication.room.WordDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val wordsDao: WordDao by lazy { WordDatabase.getDatabase(application, scope).wordDao() }
    lateinit var allWords: List<Word>
    val scope: CoroutineScope by lazy { CoroutineScope(Dispatchers.IO) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scope.launch {
            getData()
        }
        tv_add.setOnClickListener {
            scope.launch {
                for (i in 0..5) {
                    var word = Word("word$i", "mearn$i")
                    wordsDao.insert(word)
                }
                getData()

            }
        }
        tv_update.setOnClickListener {
            scope.launch {
                if (allWords.size >= 2) {
                    var word = Word("00", "22")
                    word.id = allWords.get(allWords.size - 2).id
                    wordsDao.update(word)
                }
                getData()
            }
        }

        tv_delete.setOnClickListener {
            scope.launch {
                if (allWords.size >= 2) {
                    var word = Word("00", "22")
                    word.id = allWords.get(allWords.size - 2).id
                    wordsDao.delete(word)
                }
                getData()
            }
        }

        tv_delete_all.setOnClickListener(this)

    }

    suspend fun getData() {
        Log.e("3333333", "=========================================================== ")
        allWords = wordsDao.getAllWords()
        for (word in allWords) {
            Log.e("3333333", "onCreate: " + word.toString())
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tv_delete_all -> scope.launch {
                wordsDao.deleteAll()
                getData()
            }
        }
    }
}