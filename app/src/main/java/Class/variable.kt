package Class

import android.graphics.Insets.add

class variable {
}//

 fun main() {
    // Numeric variables
    val age: Int = 20
    val long_number: Long = 3513446164
    val temperature: Float = 27.33f
    val weight: Double = 64.125

    // String variables
    val gender: Char = 'M'
    val name: String = "Humberto Martin"

    // Bool variables
    val isGreater: Boolean = false

    // Collection variables
    val names = arrayOf("Josue", "Ana", "Victoria", "Sebas")

    println(age)
    println("Welcome $name, to your first Kotlin Project")
    println(add())
    println(product(5, 8))

    printArray(names)

    val numbers = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    isEven(numbers)
    println(getDay(8, ))


    val person1 = Person("Andrea", 25)
    person1.displayInformation()


 }

fun add(): Int {
   val x = 18
   val y = 8
   return (x + y)
}

fun product(x: Int, y: Int): Int {
   return (x * y)
}

fun printArray(names: Array<String>) {
   for (name in names) {
      println("Hello $name")
   }
}

fun isEven(numbers: Array<Int>) {
   for (number in numbers) {
      if (number % 2 == 0) {
         println("The number $number is even")
      } else {
         println("The number $number is odd")
      }
   }
}
fun getDay(day: Int): String {
   var name = ""

   when (day) {
      1 -> name = "Monday"
      2 -> name = "Tuesday"
      3 -> name = "Wednesday"
      4 -> name = "Thursday"
      5 -> name = "Friday"
      6 -> name = "Saturday"
      7 -> name = "Sunday"
      else -> name = "Incorrect value"
   }
   return name
}

class Person(val name: String, val age: Int) {
   fun displayInformation() {
      println("Name: $name, Age: $age")
   }
}


