package com.learning.helloworld

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class StudentCardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_details, container, false)
        view.findViewById<TextView>(R.id.student_name).text = arguments?.getString(KEY_NAME)
        view.findViewById<TextView>(R.id.student_surname).text = arguments?.getString(KEY_SURNAME)
        return view
    }

    companion object {
        const val KEY_NAME = "name"
        const val KEY_SURNAME = "surname"

        fun newInstance(student: Student): StudentCardFragment {
            return StudentCardFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_NAME, student.firstName)
                    putString(KEY_SURNAME, student.lastName)
                }
            }
        }
    }
}
