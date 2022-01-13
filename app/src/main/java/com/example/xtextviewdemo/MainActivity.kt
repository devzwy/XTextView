package com.example.xtextviewdemo

import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zwy.xtextview.XTextBean
import com.zwy.xtextview.XTextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<XTextView>(R.id.tvvvvv).let { textView ->

            val user = User("张三", "222022")
            val gift = Gift("浪漫婚礼", "888666123")
            val game = Game("圈圈乐", "123456")

            textView.buildText("恭喜用户:${user.userName} 在玩游戏 ${game.gameName} 时意外获得 ${gift.giftName}*1000,你也可以！")
                .addCustomText(XTextBean(user.userName, "#FFFF9800", false, null) {
                    Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show()
                })
                .addCustomText(XTextBean(game.gameName, "#FF018786", true, null) {
                    Toast.makeText(this, game.toString(), Toast.LENGTH_SHORT).show()
                })
                .addCustomText(
                    XTextBean(
                        "${gift.giftName}*1000",
                        "#FF0303",
                        false,
                        Typeface.create(Typeface.DEFAULT_BOLD, 1)
                    ) {
                        Toast.makeText(this, gift.toString(), Toast.LENGTH_SHORT).show()
                    })
                .addCustomText(XTextBean("你也可以！", "#894578"))
                .show()
        }


    }
}

data class User(val userName: String, val userId: String)
data class Gift(val giftName: String, val giftId: String)
data class Game(val gameName: String, val gameId: String)