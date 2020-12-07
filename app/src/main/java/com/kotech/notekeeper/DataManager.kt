package com.kotech.notekeeper

object DataManager {
    var courses= HashMap<String, CourseInfo>()
    var notes= ArrayList<NoteInfo>()

    init {
        initializeCourses()
    }

    private fun initializeCourses(){
        var course= CourseInfo("android_intents", "Android Programming with Intents")
        courses[course.courseId] = course

        course= CourseInfo(courseId = "android_async", title = "Android Async Programming and Services")
        courses[course.courseId] = course

        course= CourseInfo(title = "Java Fundamental: The Java langage", courseId = "java_lang")
        courses[course.courseId] = course

        course= CourseInfo("java_core","Java Fundamental: The Core Plateform")
        courses[course.courseId] = course

    }
}