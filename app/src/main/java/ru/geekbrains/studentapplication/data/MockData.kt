package ru.geekbrains.studentapplication.data

import ru.geekbrains.studentapplication.R
import ru.geekbrains.studentapplication.data.models.ExamResponse
import ru.geekbrains.studentapplication.data.models.FacultativeResponse
import ru.geekbrains.studentapplication.data.models.HomeWorkResponse
import ru.geekbrains.studentapplication.data.models.LessonResponse
import ru.geekbrains.studentapplication.domain.models.Lessonable
import ru.geekbrains.studentapplication.domain.models.Schedulers
import ru.geekbrains.studentapplication.domain.models.Subject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random


val lessons = listOf<Subject>(
    Subject("Химия", R.drawable.ic_chemistry),
    Subject("Алгебра", R.drawable.ic_maths),
    Subject("Геометрия", R.drawable.ic_geometry),
    Subject("Физкультура", R.drawable.ic_physical_culture),
    Subject("Музыка", R.drawable.ic_music),
    Subject("Java Core для Android", R.drawable.ic_java_core),
    Subject("Android 1. Знакомство с платформой", R.drawable.ic_android),
    Subject("Android на Kotlin", R.drawable.ic_kotlin),
    Subject("Android. Material design", R.drawable.ic_material_design),
    Subject("Популярные библиотеки: RxJava 2, Dagger 2, Moxy", R.drawable.ic_populare_library),
    Subject("Профессиональная разработка Android-приложений",
        R.drawable.ic_professional_development),
    Subject("Покрытие тестами Android приложений", R.drawable.ic_testing),
    Subject("Командная разработка Android-приложения", R.drawable.ic_team_development),
    Subject("Подготовка к собеседованию Android-разработчика", R.drawable.ic_interview),
    Subject("Литература", R.drawable.ic_literature),
    Subject("Информатика", R.drawable.ic_informatica),
    Subject("Биология", R.drawable.ic_biology)
)

val works = listOf<String>(
    "Учить стр. 3-5",
    "Повторить правила",
    "Практическая работа № 2",
    "Читать стр. 13-25",
    "Учить стр. 7-9",
    "Практическая работа № 3",
    "Практическая работа № 1",
    "Не задано",
)

val descriptions = listOf<String>(
    "Командный вид спорта, в котором целью является забить мяч в ворота соперника ногами или другими частями тела (кроме рук) большее количество раз, чем команда соперника",
    "Те́ннис[1] (англ. tennis) или большо́й теннис[2] — вид спорта[3], в котором соперничают либо два игрока («одиночная игра»), либо две команды, состоящие из двух игроков («парная игра»)",
    "Командная спортивная игра с бейсбольным мячом и битой. В состязаниях участвуют две команды по девять игроков каждая",
    "Датой рождения дзюдо считается день основания Кано первой школы дзюдо Кодокан",
    "Спортивная командная игра с мячом, в которой мяч забрасывают руками в кольцо соперника",
    "Вид искусства. Согласно А. Н. Сохору (МЭ 3, 1976 г.), этот вид «отражает действительность и воздействует на человека посредством осмысленных и особым образом организованных по высоте и во времени звуковых последований, состоящих в основном из тонов»",
    "Раздел математики, изучающий пространственные структуры и отношения, а также их обобщения",
    "Раздел математики, который можно нестрого охарактеризовать как обобщение и расширение арифметики; в этом разделе числа и другие математические объекты обозначаются буквами и другими символами, что позволяет записывать и исследовать их свойства в самом общем виде",
)

val tags = listOf<Int>(
    R.drawable.ic_man,
    R.drawable.ic_women
)

val tagsFacultativ = listOf<Int>(
    R.drawable.ic_ball,
    R.drawable.ic_globus,
    R.drawable.ic_football,
    R.drawable.ic_tennis,
)

val teachers = listOf<String>(
    "Иванова И.И.",
    "Петрова С.А.",
    "Сергеев А.Л.",
    "Музин А.В."
)

val lessonsStartScheduler = listOf<Schedulers>(
    Schedulers("8:00", "8:45"),
    Schedulers("8:50", "9:35"),
    Schedulers("9:45", "10:30"),
    Schedulers("10:35", "11:20"),
    Schedulers("11:30", "12:15"),
    Schedulers("12:20", "13:05"),
    Schedulers("13:15", "14:00"),
)

fun getRandomDate(): String {
    val simpleDateFormatData = SimpleDateFormat(DATE_FORMAT_STRING, Locale.getDefault())
    Date(System.currentTimeMillis()).let {
        return simpleDateFormatData.format(Date(it.year, it.month, it.date + Random.nextInt(2, 7)))
    }
}

fun mockGetExam(): ExamResponse {
    return ExamResponse(getRandomDate() + " 08:00")
}

fun mockGetLessons(): List<Lessonable> {
    val result: ArrayList<Lessonable> = arrayListOf()
    var randomValue = 0
    var randomType: Boolean = false
    var isTop = true

    for (i in 0..6) {
        randomValue = Random.nextInt(0, lessons.size - 1)
        randomType = Random.nextBoolean()
        result.add(
            if (randomType) {
                LessonResponse(
                    id = i,
                    title = lessons[randomValue].title,
                    icon = lessons[randomValue].imageUrl,
                    scheduler = lessonsStartScheduler[i],
                    useRemote = Random.nextBoolean(),
                    isTop = isTop,
                    teacher = teachers[Random.nextInt(0, teachers.size - 1)],
                )
            } else {
                FacultativeResponse(
                    id = i,
                    title = lessons[randomValue].title,
                    icon = lessons[randomValue].imageUrl,
                    scheduler = lessonsStartScheduler[i],
                    teacher = teachers[Random.nextInt(0, teachers.size - 1)],
                    tagIconOne = tagsFacultativ[Random.nextInt(0, tagsFacultativ.size - 1)],
                    tagIconTwo = tagsFacultativ[Random.nextInt(0, tagsFacultativ.size - 1)],
                    tagIconThree = tagsFacultativ[Random.nextInt(0, tagsFacultativ.size - 1)],
                    description = descriptions[Random.nextInt(0, descriptions.size - 1)],
                    isTop = isTop
                )
            }
        )
        isTop = false
    }
    return result
}

fun mockGetHomWork(): List<HomeWorkResponse> {
    val result: ArrayList<HomeWorkResponse> = arrayListOf()
    var randomValue = 0

    for (i in 0..6) {
        randomValue = Random.nextInt(0, lessons.size - 1)
        result.add(
            HomeWorkResponse(
                id = i,
                title = lessons[randomValue].title,
                icon = lessons[randomValue].imageUrl,
                getRandomDate(),
                works[Random.nextInt(0, works.size - 1)],
                tagIconOne = tags.first(),
                tagIconTwo = tags.last()
            )
        )
    }
    return result
}

private const val DATE_FORMAT_STRING = "yyyy.MM.dd"