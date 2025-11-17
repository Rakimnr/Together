package com.example.together

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Uses your default AppTheme
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 1) Greeting (Good Morning/Afternoon/Evening)
        val tvWelcome: TextView? = findViewById(R.id.tv_welcome_message)
        tvWelcome?.text = when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 5..11  -> getString(R.string.good_morning)
            in 12..16 -> getString(R.string.good_afternoon)
            else      -> getString(R.string.good_evening)
        }

        // 2) Display name if provided (fallback = keep XML text)
        val tvUserName: TextView? = findViewById(R.id.tv_user_name)
        intent.getStringExtra("display_name")?.let { name ->
            if (name.isNotBlank()) tvUserName?.text = name
        }

        // 3) Notification bell -> simple toast for now
        findViewById<ImageView?>(R.id.iv_notification)?.setOnClickListener {
            Toast.makeText(this, "No new notifications", Toast.LENGTH_SHORT).show()
        }

        // 4) Profile button(s) -> open ProfileActivity
        //    - Profile picture
        findViewById<ImageView?>(R.id.iv_profile_pic)?.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        //    - Username text (optional convenience)
        tvUserName?.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // 5) Quick Actions
        // Manage Members
        findViewById<View?>(R.id.cv_manage_members)?.setOnClickListener {
            startActivity(Intent(this, ManageMembersActivity::class.java))
        }

        // Plan Projects
        findViewById<View?>(R.id.cv_plan_projects)?.setOnClickListener {
            startActivity(Intent(this, PlanProjectsActivity::class.java))
        }

        // Manage Tasks
        findViewById<View?>(R.id.cv_manage_tasks)?.setOnClickListener {
            startActivity(Intent(this, ManageTaskActivity::class.java))
        }

        // Track Budget
        findViewById<View?>(R.id.cv_track_budget)?.setOnClickListener {
            startActivity(Intent(this, TrackBudgetActivity::class.java))
        }

    }
}
