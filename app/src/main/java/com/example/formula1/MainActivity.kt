package com.example.formula1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.formula1.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var viewBinding: ActivityMainBinding? = null
    private var countOfCars: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)
        initView()
    }
    private fun initView() {
        viewBinding?.apply {
            inputNum.doOnTextChanged { text, _, _, _ ->
                send.isEnabled = !text.isNullOrBlank()
            }
            send.setOnClickListener() {
                infoRaces.text = ""
                try {
                    countOfCars = inputNum.text.toString().toInt()
                    val cars = Array<Auto?>(countOfCars) { null }
                    var i: Int = countOfCars
                    while (i != 0) {
                        cars[i - 1] = randomCar()
                        i--
                    }

                var firstList: MutableList<Int> = ArrayList(countOfCars)
                repeat(countOfCars) { firstList.add(it) }
                while (firstList.size > 1) {
                    var tempList: MutableList<Int> = ArrayList<Int>()
                    while (firstList.size >= 2) {
                        val shuffledList = firstList.shuffled()
                        val elements = shuffledList.take(2)
                        firstList.remove(elements[0])
                        firstList.remove(elements[1])
                        if (cars[elements[0]]!! > cars[elements[1]]!!) {
                            tempList.add(elements[0])
                            infoRaces.append("--- Гонка между ${elements[0] + 1} и ${elements[1] + 1}, Победитель ${elements[0] + 1}\n")
                        } else {
                            tempList.add(elements[1])
                            infoRaces.append("--- Гонка между ${elements[0] + 1} и ${elements[1] + 1}, Победитель ${elements[1] + 1}\n")
                        }
                    }
                    if (firstList.size == 1) {
                        infoRaces.append("--- ${firstList[0] + 1} - Нет пары, следующий круг\n")
                        tempList.add(firstList[0])
                    }
                    firstList = tempList
                }
                infoRaces.append("Победитель турнира: ${firstList[0] + 1}")
                } catch (e: NumberFormatException) {
                    Toast.makeText(root.context, "Invalid input, try again", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun randomCar(): Auto {
        return AutoBuilder()
            .setBrand(RandomEnumHelper.random(Brand::class.java))
            .setModel(generateRandomString())
            .setYear(Random.nextInt(1996, 2024))
            .setTypeOfEngine(RandomEnumHelper.random(Engine::class.java))
            .setTypeOfDrive(if (Random.nextInt(0, 1) == 1) RandomEnumHelper.random(Drive::class.java) else null)
            .setEnginePower(Random.nextInt(60, 601))
            .setAutoPilot(yesNoRandom())
            .setFuelConsumption(Random.nextInt(4, 31))
            .setLoadCapacity(Random.nextInt(300, 20001))
            .setFuelReserve(Random.nextInt(40, 601))
            .setHalo(yesNoRandom())
            .setMaxSpeed(Random.nextInt(250, 351))
            .setMileage(Random.nextInt(0, 100001))
            .setInsurance(yesNoRandom())
            .build()
    }

    private fun yesNoRandom() : YesNo? {
        return when {
            Random.nextInt(0, 1) == 1 -> RandomEnumHelper.random(YesNo::class.java)
            else -> null
        }
    }

    private fun generateRandomString(): String {
        val sb = StringBuilder()
        repeat(3) {
            val randomChar = Random.nextInt('a'.code, 'z'.code + 1).toChar()
            sb.append(randomChar)
        }
        sb.append(" - ")
        repeat(2) {
            val randomDigit = Random.nextInt('0'.code, '9'.code + 1).toChar()
            sb.append(randomDigit)
        }
        return sb.toString()
    }

}




