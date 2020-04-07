package com.learning.helloworld

import android.os.AsyncTask
import java.io.File
import java.lang.ref.WeakReference

class StudentsLoadTask(
    private val listener: WeakReference<UIListener>
) : AsyncTask<File, Void, List<Student>>() {
    interface UIListener {
        fun onStudentsLoaded(students: List<Student>)
    }

    // WORKER THREAD
    override fun doInBackground(vararg params: File?): List<Student> {
        if (params.isNullOrEmpty()) {
            return emptyList()
        }
        params[0]?.let { return StudentLoader.loadStudents(it) }
        return emptyList()
    }

    // UI THREAD
    override fun onPostExecute(result: List<Student>?) {
        listener.get()?.onStudentsLoaded(result!!)
        super.onPostExecute(result)
    }
}